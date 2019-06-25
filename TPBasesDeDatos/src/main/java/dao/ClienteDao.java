package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.Cliente;
import pojos.Domicilio;
import pojos.ObraSocial;

public class ClienteDao {
	 private MongoClient mongoClient;
     private DB database;
	public ClienteDao() throws UnknownHostException {
		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}
	
	public void agregarCliente(String apellido, String nombre,int dni, Domicilio domicilio, ObraSocial obraSocial) {
		Cliente cliente = new Cliente(apellido,nombre,dni,domicilio,obraSocial);
		
		DBCollection collection = database.getCollection("clientes");
	     
	    collection.insert(cliente.objectToJson());
		
	}
	
	public Cliente traerCliente(int dni) {
        
		DBCollection collection = database.getCollection("clientes");
	     
		DBObject query = new BasicDBObject("dni", dni);
		DBCursor cursor = collection.find(query);
		BasicDBObject cliente = (BasicDBObject)cursor.one();
		Domicilio d = new Domicilio();
		Cliente c = new Cliente(cliente.getString("apellido"),cliente.getString("nombre"),cliente.getInt("dni"),d.jsonToObject((BasicDBObject) cliente.get("domicilio")));

	    return c;
		
	}
     
     
     
     
}
