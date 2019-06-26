package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.JsonToObjectClass;
import pojos.ObraSocial;

public class ObraSocialDao {
	private MongoClient mongoClient;
	private DB database;
	
	public ObraSocialDao() throws UnknownHostException {

		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}
	
	public void agregarObraSocial(String nombre) {
		ObraSocial obraSocial = new ObraSocial(nombre);
		DBCollection ObraSociales = database.getCollection("obraSociales");
		ObraSociales.insert(obraSocial.objectToJson());
	}
	
	public void agregarObraSocial(ObraSocial obraSocial) {
		DBCollection ObraSociales = database.getCollection("obraSociales");
		ObraSociales.insert(obraSocial.objectToJson());
	}
	

	public ObraSocial traerObraSocial(String nombre) {
		DBCollection ObraSociales = database.getCollection("obraSociales");
		DBObject query = new BasicDBObject("nombre", nombre);
		DBCursor cursor = ObraSociales.find(query);
		BasicDBObject obraSocialJSON = (BasicDBObject) cursor.one();

		return JsonToObjectClass.jsonToObraSocial(obraSocialJSON);
	}
	
	public void actualizarObraSocial(String nombre, ObraSocial obraSocialActualizada) {
		DBCollection ObraSociales = database.getCollection("obraSociales");
		DBObject query = new BasicDBObject("nombre", nombre);
		ObraSociales.update(query, obraSocialActualizada.objectToJson());
	}
}
