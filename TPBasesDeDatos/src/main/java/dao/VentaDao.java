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
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
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
			total = +ventas.get(i).getTotal();
		}

		return total;
	}

	// 1. Detalle de ventas para la cadena completa entre fechas

	public List<Venta> traerVentas(Date fechaInicial, Date fechaFinal) {
		DBCollection ventasCollection = database.getCollection("ventas");
		DBObject query = new BasicDBObject();

		query.put("fecha", new BasicDBObject("$gt", fechaInicial));
		query.put("fecha", new BasicDBObject("$lt", fechaFinal));
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

	public List<DBObject> traerVentas(Date fechaInicial, Date fechaFinal, boolean esObraSocial) {
		DBCollection ventasCollection = database.getCollection("ventas");
		DBObject query = new BasicDBObject();

		if (esObraSocial) {
			query.put("fecha", new BasicDBObject("$gt", fechaInicial));
			query.put("fecha", new BasicDBObject("$lt", fechaFinal));
			query.put("cliente.obraSocial", new BasicDBObject("$ne", null));
		}

		if (!esObraSocial) {
			query.put("fecha", new BasicDBObject("$gt", fechaInicial));
			query.put("fecha", new BasicDBObject("$lt", fechaFinal));
			query.put("cliente.obraSocial", new BasicDBObject("$eq", null));
		}

		DBCursor cursor = ventasCollection.find(query);

		List<DBObject> ventasJSON = cursor.toArray();

		return ventasJSON;

	}
	// Detalle de ventas para la sucursal, por obra social o privado

	public List<Venta> traerVentas(String idSucursal, Date fechaInicial, Date fechaFinal, boolean esObraSocial) {
		List<Venta> ventas = JsonToObjectClass.jsonToVentas(this.traerVentas(fechaInicial, fechaFinal, esObraSocial));
		
		List<Venta> ventasFiltro = this.filtrarVentasPorSucursal(idSucursal, ventas);
		
		return ventasFiltro;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////

// Detalle cobranza para la cadena completa por medio de pago y entre fechas

	public List<Venta> traerVentas(Date fechaInicial, Date fechaFinal, String medio) {
		DBCollection ventasCollection = database.getCollection("ventas");
		DBObject query = new BasicDBObject();

		query.put("fecha", new BasicDBObject("$gt", fechaInicial));
		query.put("fecha", new BasicDBObject("$lt", fechaFinal));
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

		query.put("fecha", new BasicDBObject("$gt", fechaInicial));
		query.put("fecha", new BasicDBObject("$lt", fechaFinal));
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
	
	public void rankingProductos() {
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
			              //Aggregates.match(Filters.eq("categories", "Bakery")),
			              //Aggregates.group("$itemsVenta.producto.nombre", Accumulators.sum("count", 1)),
			           
			              Aggregates.unwind("$itemsVenta"),
			              Aggregates.sortByCount("$itemsVenta")
			              
			      )
			).forEach(printBlock);
		
	}
	

}
