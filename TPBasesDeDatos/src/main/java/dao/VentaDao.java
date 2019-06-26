package dao;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.Cliente;
import pojos.Empleado;
import pojos.FormaDePago;
import pojos.JsonToObjectClass;
import pojos.Venta;

public class VentaDao {
	private MongoClient mongoClient;
	private DB database;
	
	public VentaDao() throws UnknownHostException {
		this.mongoClient = new MongoClient() ;
		this.database = mongoClient.getDB("farmacia");
	}
	
	
	public void agregarVenta(Date fecha, String nroTicket, double total, FormaDePago formaDePago, Cliente cliente, Empleado empleadoAtencion, Empleado empleadoCaja) {
		Venta venta=new Venta(fecha, nroTicket, total, formaDePago, cliente, empleadoAtencion, empleadoCaja);
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
}
