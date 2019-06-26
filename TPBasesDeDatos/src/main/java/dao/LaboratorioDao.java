package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import pojos.JsonToObjectClass;
import pojos.Laboratorio;


public class LaboratorioDao {
	 private MongoClient mongoClient;
     private DB database;
     
  	public LaboratorioDao() throws UnknownHostException {
 		this.mongoClient = new MongoClient();
 		this.database = mongoClient.getDB("farmacia");
 	}
  	
  	
  	public void agregarLaboratorio(String nombre) {
		Laboratorio laboratorio = new Laboratorio(nombre);		
		DBCollection collection = database.getCollection("laboratorios");	     
	    collection.insert(laboratorio.objectToJson());
		
	}
	
  	public void agregarLaboratorio(Laboratorio laboratorio) {
		DBCollection collection = database.getCollection("laboratorios");	     
	    collection.insert(laboratorio.objectToJson());
		
	}
	
	public Laboratorio traerLaboratorio(String nombre) {		
		DBCollection collection = database.getCollection("laboratorios");	   		
		DBObject query = new BasicDBObject("nombre", nombre);
		DBCursor cursor = collection.find(query);
		BasicDBObject laboratorioJSON = (BasicDBObject)cursor.one();

		return JsonToObjectClass.jsonToLaboratorio(laboratorioJSON);
	}
	
	public void actualizarLaboratorio(String nombre, Laboratorio laboratorioActualizado) {
		DBCollection laboratorios = database.getCollection("laboratorios"); 
		DBObject query = new BasicDBObject("nombre", nombre);
		laboratorios.update(query, laboratorioActualizado.objectToJson());

	}
  	
}
