package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.JsonToObjectClass;
import pojos.PuntoDeVenta;
import pojos.Sucursal;

public class PuntoDeVentaDao {
	private MongoClient mongoClient;
	private DB database;
	
	public PuntoDeVentaDao() throws UnknownHostException {

		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}
	
	public void agregarPuntoDeVenta(Sucursal sucursal, int nroPuntoDeVenta) {
		PuntoDeVenta puntoDeVenta = new PuntoDeVenta(sucursal,nroPuntoDeVenta);
		DBCollection puntosDeVenta = database.getCollection("puntosDeVenta");
		puntosDeVenta.insert(puntoDeVenta.objectToJson());
	}
	
	public void agregarPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		DBCollection puntosDeVenta = database.getCollection("puntosDeVenta");
		puntosDeVenta.insert(puntoDeVenta.objectToJson());
	}
	

	public PuntoDeVenta traerPuntoDeVenta(int nroPuntoDeVenta) {
		DBCollection puntosDeVenta = database.getCollection("puntosDeVenta");
		DBObject query = new BasicDBObject("nroPuntoDeVenta", nroPuntoDeVenta);
		DBCursor cursor = puntosDeVenta.find(query);
		BasicDBObject puntoDeVentaJSON = (BasicDBObject) cursor.one();

		return JsonToObjectClass.jsonToPuntoDeVenta(puntoDeVentaJSON);
	}
	
	public void actualizarPuntoDeVenta(int nroPuntoDeVenta, PuntoDeVenta puntoDeVentaActualizado) {
		DBCollection puntosDeVenta = database.getCollection("puntosDeVenta");
		DBObject query = new BasicDBObject("nroPuntoDeVenta", nroPuntoDeVenta);
		puntosDeVenta.update(query, puntoDeVentaActualizado.objectToJson());
	}
}
