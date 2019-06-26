package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.JsonToObjectClass;
import pojos.Localidad;

public class LocalidadDao {
	private MongoClient mongoClient;
	private DB database;
	
	public LocalidadDao() throws UnknownHostException {

		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}
	
	public void agregarLocalidad(String nombre) {
		Localidad localidad = new Localidad(nombre);
		DBCollection localidades = database.getCollection("localidades");
		localidades.insert(localidad.objectToJson());

	}
	
	public void agregarLocalidad(Localidad localidad) {
		DBCollection localidades = database.getCollection("localidades");
		localidades.insert(localidad.objectToJson());

	}
	

	public Localidad traerLocalidad(String nombre) {
		DBCollection localidades = database.getCollection("localidades");
		DBObject query = new BasicDBObject("nombre", nombre);
		DBCursor cursor = localidades.find(query);
		BasicDBObject localidadJSON = (BasicDBObject) cursor.one();

		return JsonToObjectClass.jsonToLocalidad(localidadJSON);

	}
	
	public void actualizarLocalidad(String nombre, Localidad localidadActualizada) {
		DBCollection localidades = database.getCollection("localidades");
		DBObject query = new BasicDBObject("nombre", nombre);
		localidades.update(query, localidadActualizada.objectToJson());

	}

}
