package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.FormaDePago;
import pojos.JsonToObjectClass;


public class FormaDePagoDao {
	 private MongoClient mongoClient;
     private DB database;
     
 	public FormaDePagoDao() throws UnknownHostException {
		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}
 	
	public void agregarFormaDePago(String nombre) {
		FormaDePago formaDePago = new FormaDePago(nombre);		
		DBCollection collection = database.getCollection("formasDePago");
	    collection.insert(formaDePago.objectToJson());		
	}
	
	public void agregarFormaDePago(FormaDePago formaDePago) {
		DBCollection collection = database.getCollection("formasDePago");
	    collection.insert(formaDePago.objectToJson());		
	}
	
	public FormaDePago traerFormaDePago(String nombre) {
		
		DBCollection collection = database.getCollection("formasDePago");
		
		DBObject query = new BasicDBObject("nombre", nombre);
		DBCursor cursor = collection.find(query);
		BasicDBObject formaDePagoJSON = (BasicDBObject)cursor.one();
		
		return JsonToObjectClass.jsonToFormaDePago(formaDePagoJSON);
	}
	
	public void actualizarFormaDePago(String nombre, FormaDePago formaDePagoActualizada) {
		DBCollection formasDePago = database.getCollection("formasDePago"); 
		DBObject query = new BasicDBObject("nombre", nombre);
		formasDePago.update(query, formaDePagoActualizada.objectToJson());

	}
}
