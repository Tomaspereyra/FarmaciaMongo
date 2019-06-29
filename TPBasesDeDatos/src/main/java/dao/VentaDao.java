package dao;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.AggregationOptions;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.util.JSON;

import pojos.Cliente;
import pojos.Empleado;
import pojos.FormaDePago;
import pojos.JsonToObjectClass;
import pojos.Venta;

public class VentaDao {
	private MongoClient mongoClient;
	private DB database;

	public VentaDao() throws UnknownHostException {
		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}

	public void agregarVenta(Date fecha, String nroTicket, FormaDePago formaDePago, Cliente cliente,
			Empleado empleadoAtencion, Empleado empleadoCaja) {
		Venta venta = new Venta(fecha, nroTicket, formaDePago, cliente, empleadoAtencion, empleadoCaja);
		DBCollection ventas = database.getCollection("ventas");
		ventas.insert(venta.objectToJson());
	}

	public void agregarVenta(Venta venta) {
		DBCollection ventas = database.getCollection("ventas");
		ventas.insert(venta.objectToJson());
	}

	public void actualizarVenta(String nroTicket, Venta ventaActualizada) {
		DBCollection ventas = database.getCollection("ventas");
		DBObject query = new BasicDBObject("nroTicket", nroTicket);
		ventas.update(query, ventaActualizada.objectToJson());
	}

	public Venta traerVenta(String nroTicket) {
		DBCollection ventas = database.getCollection("ventas");
		DBObject query = new BasicDBObject("nroTicket", nroTicket);
		DBCursor cursor = ventas.find(query);
		BasicDBObject ventaJSON = (BasicDBObject) cursor.one();

		return JsonToObjectClass.jsonToVenta(ventaJSON);
	}

	// total de ventas para una lista

	public double traerTotal(List<Venta> ventas) {
		double total = 0;

		for (int i = 0; i < ventas.size(); i++) {
			total += ventas.get(i).getTotal();
		}

		return total;
	}

	// 1. Detalle de ventas para la cadena completa entre fechas

	public List<Venta> traerVentas(Date fechaInicial, Date fechaFinal) {
		DBCollection ventasCollection = database.getCollection("ventas");
		DBObject query = new BasicDBObject();

		query.put("fecha", new BasicDBObject("$gte", fechaInicial));
		query.put("fecha", new BasicDBObject("$lte", fechaFinal));
		DBCursor cursor = ventasCollection.find(query);

		List<DBObject> ventasJSON = cursor.toArray();
		List<Venta> ventas = JsonToObjectClass.jsonToVentas(ventasJSON);

		return ventas;

	}

	// 1. Total de ventas por sucursal entre fechas

	public List<Venta> traerVentas(String idSucursal, Date fechaInicial, Date fechaFinal) {
		List<Venta> ventas = traerVentas(fechaInicial, fechaFinal);
		List<Venta> ventasFiltro = this.filtrarVentasPorSucursal(idSucursal, ventas);
		

		return ventasFiltro;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////

// Detalle de ventas para la cadena completa por obra social o privado

	public List<Venta> traerVentas(Date fechaInicial, Date fechaFinal, boolean esObraSocial, String obraSocial) {
		DBCollection ventasCollection = database.getCollection("ventas");
		DBObject query = new BasicDBObject();

		if (esObraSocial) {
			query.put("fecha", new BasicDBObject("$gte", fechaInicial));
			query.put("fecha", new BasicDBObject("$lte", fechaFinal));
			query.put("cliente.obraSocial.nombre", new BasicDBObject("$eq", obraSocial));
		}

		if (!esObraSocial) {
			query.put("fecha", new BasicDBObject("$gte", fechaInicial));
			query.put("fecha", new BasicDBObject("$lte", fechaFinal));
			query.put("cliente.obraSocial.nombre", new BasicDBObject("$eq", obraSocial));
		}

		DBCursor cursor = ventasCollection.find(query);

		List<DBObject> ventasJSON = cursor.toArray();

		return JsonToObjectClass.jsonToVentas(ventasJSON);

	}
	// Detalle de ventas para la sucursal, por obra social o privado

	public List<Venta> traerVentas(String idSucursal, Date fechaInicial, Date fechaFinal, boolean esObraSocial, String obraSocial) {
		List<Venta> ventas = this.traerVentas(fechaInicial, fechaFinal, esObraSocial, obraSocial);
		
		List<Venta> ventasFiltro = this.filtrarVentasPorSucursal(idSucursal, ventas);
		
		return ventasFiltro;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////

// Detalle cobranza para la cadena completa por medio de pago y entre fechas

	public List<Venta> traerVentas(Date fechaInicial, Date fechaFinal, String medio) {
		DBCollection ventasCollection = database.getCollection("ventas");
		DBObject query = new BasicDBObject();

		query.put("fecha", new BasicDBObject("$gte", fechaInicial));
		query.put("fecha", new BasicDBObject("$lte", fechaFinal));
		query.put("formaDePago", new BasicDBObject("nombre", medio));

		DBCursor cursor = ventasCollection.find(query);

		List<DBObject> ventasJSON = cursor.toArray();

		return JsonToObjectClass.jsonToVentas(ventasJSON);

	}
// Detalle cobranza por sucursal,por medio de pago y entre fechas
 public List<Venta> traerVentas(String idSucursal, Date fechaInicial, Date fechaFinal, String medio ){
	 List<Venta> ventas = this.traerVentas(fechaInicial, fechaFinal, medio);
	 List<Venta> ventasFiltro = this.filtrarVentasPorSucursal(idSucursal, ventas);

	 return ventasFiltro;
	 
 }

/////////////////////////////////////////////////////////////////////////////////////////////////

// Detalle de ventas de productos de la cadena entre fechas, diferenciados entre farmacia y perfumería.

	public List<Venta> traerVentasTipo(Date fechaInicial, Date fechaFinal, String tipo) {
		DBCollection ventasCollection = database.getCollection("ventas");
		DBObject query = new BasicDBObject();

		query.put("fecha", new BasicDBObject("$gte", fechaInicial));
		query.put("fecha", new BasicDBObject("$lte", fechaFinal));
		query.put("itemsVenta.producto.tipo", new BasicDBObject("$eq", tipo));

		DBCursor cursor = ventasCollection.find(query);

		List<DBObject> ventasJSON = cursor.toArray();

		return JsonToObjectClass.jsonToVentas(ventasJSON);

	}
	
// Detalle de ventas de productos de sucursal entre fechas, diferenciados entre farmacia y perfumería.
    public List<Venta> traerVentasTipo(String idSucursal, Date fechaInicial, Date fechaFinal,String tipo){
    	List<Venta> ventas = this.traerVentasTipo(fechaInicial, fechaFinal, tipo);
        List<Venta> ventasFiltro = this.filtrarVentasPorSucursal(idSucursal, ventas);
       
        return ventasFiltro;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	private List<Venta> filtrarVentasPorSucursal(String idSucursal, List<Venta> ventas) {
		String id;
		List<Venta> ventasFiltro = new ArrayList<Venta>();
		for (int i = 0; i < ventas.size(); i++) {
			id = ventas.get(i).getNroTicket().substring(0, 4);
			if (id.equalsIgnoreCase(idSucursal)) {
				ventasFiltro.add(ventas.get(i));

			}

		}
		return ventasFiltro;
	}
	
	public void rankingProductosPorMonto(Date fechaInicial, Date fechaFinal) {
		 Block<Document> printBlock = new Block<Document>() {
		        @Override
		        public void apply(final Document document) {
		            System.out.println(document.toJson());
		        }
		    };	    
		MongoDatabase db = mongoClient.getDatabase("farmacia");
		MongoCollection<Document> collection = db.getCollection("ventas");
		Document multiply = new Document("$multiply", Arrays.asList("$itemsVenta.producto.precio", "$itemsVenta.cantidad"));	
		collection.aggregate(
			      Arrays.asList(
			             Aggregates.match(Filters.gte("fecha", fechaInicial)),
			             Aggregates.match(Filters.lte("fecha", fechaFinal)),
			             Aggregates.unwind("$itemsVenta"),
			             Aggregates.group("$itemsVenta.producto",Accumulators.sum("totalCantidad", "$itemsVenta.cantidad"),Accumulators.sum("totalPrecio",multiply )),
			             Aggregates.sort(Sorts.descending("totalPrecio"))
			      )
			).forEach(printBlock);		
	}
	
	public void rankingProductosPorSucursal(String idSucursal,Date fechaInicial, Date fechaFinal) {
		List <Venta> ventas = this.traerVentas(idSucursal, fechaInicial, fechaFinal);
		
		
		
		
		
		
	}
	
	public void rankingProductosPorCantidadVendida(Date fechaInicial, Date fechaFinal) {
		 Block<Document> printBlock = new Block<Document>() {
		        @Override
		        public void apply(final Document document) {
		            System.out.println(document.toJson());
		        }
		    };	    
		MongoDatabase db = mongoClient.getDatabase("farmacia");
		MongoCollection<Document> collection = db.getCollection("ventas");
		Document multiply = new Document("$multiply", Arrays.asList("$itemsVenta.producto.precio", "$itemsVenta.cantidad"));		
		collection.aggregate(
			      Arrays.asList(
			             Aggregates.match(Filters.gte("fecha", fechaInicial)),
			             Aggregates.match(Filters.lte("fecha", fechaFinal)),
			             Aggregates.unwind("$itemsVenta"),
			             Aggregates.group("$itemsVenta.producto",Accumulators.sum("totalCantidad", "$itemsVenta.cantidad"),Accumulators.sum("totalPrecio",multiply )),
			             Aggregates.sort(Sorts.descending("totalCantidad"))
			      )
			).forEach(printBlock);		
	}
	

	public void rankingClientesPorMonto(Date fechaInicial, Date fechaFinal) {
		 Block<Document> printBlock = new Block<Document>() {
		        @Override
		        public void apply(final Document document) {
		            System.out.println(document.toJson());
		        }
		    };		    
		MongoDatabase db = mongoClient.getDatabase("farmacia");
		MongoCollection<Document> collection = db.getCollection("ventas");	
		collection.aggregate(
			      Arrays.asList(			         
			             Aggregates.match(Filters.gte("fecha", fechaInicial)),
			             Aggregates.match(Filters.lte("fecha", fechaFinal)),
			             Aggregates.unwind("$cliente"),
			             Aggregates.group("$cliente",Accumulators.sum("montoTotalDeTodasLasCompras","$total" ),Accumulators.sum("cantidadDeCompras",1)),	
			             Aggregates.sort(Sorts.descending("montoTotalDeTodasLasCompras"))
			      )
			).forEach(printBlock);		
	}
}
