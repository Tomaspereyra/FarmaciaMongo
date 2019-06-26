package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.JsonToObjectClass;
import pojos.Provincia;

public class ProvinciaDao {
	private MongoClient mongoClient;
	private DB database;
	
	public ProvinciaDao() throws UnknownHostException {

		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}
	
	public void agregarProvincia(String nombre) {
		Provincia provincia = new Provincia(nombre);
		DBCollection provincias = database.getCollection("provincias");
		provincias.insert(provincia.objectToJson());

	}
	
	public void agregarProvincia(Provincia provincia) {
		DBCollection provincias = database.getCollection("provincias");
		provincias.insert(provincia.objectToJson());

	}
	

	public Provincia traerProvincia(String nombre) {
		DBCollection provincias = database.getCollection("provincias");
		DBObject query = new BasicDBObject("nombre", nombre);
		DBCursor cursor = provincias.find(query);
		BasicDBObject provinciaJSON = (BasicDBObject) cursor.one();

		return JsonToObjectClass.jsonToProvincia(provinciaJSON);

	}
	
	public void actualizarProvincia(String nombre, Provincia provinciaActualizada) {
		DBCollection provincias = database.getCollection("provincias");
		DBObject query = new BasicDBObject("nombre", nombre);
		provincias.update(query, provinciaActualizada.objectToJson());

	}
}
