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
	 MongoClient mongoClient;
     DB database;
	public ClienteDao() throws UnknownHostException {
		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}
	
	public void agregarCliente(String apellido, String nombre,int dni, Domicilio domicilio, ObraSocial obraSocial) {
		Cliente cliente = new Cliente(apellido,nombre,dni,domicilio,obraSocial);
		
		DBCollection collection = database.getCollection("Clientes");
	     
	    collection.insert(cliente.objectToJson());
		
	}
	
	public DBObject traerCliente(int dni) {

		DBCollection collection = database.getCollection("Clientes");
	     
		DBObject query = new BasicDBObject("dni", dni);
		DBCursor cursor = collection.find(query);
		DBObject jo = cursor.one();
	    return jo;
		
	}
     
     
     
     
}
