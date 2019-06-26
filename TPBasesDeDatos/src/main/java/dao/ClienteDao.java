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
import pojos.JsonToObjectClass;
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
	
	public void agregarCliente(Cliente cliente) {
		DBCollection collection = database.getCollection("clientes");
	    collection.insert(cliente.objectToJson());
		
	}
	
	public Cliente traerCliente(int dni) {    
		DBCollection collection = database.getCollection("clientes");
	     
		DBObject query = new BasicDBObject("dni", dni);
		DBCursor cursor = collection.find(query);
		BasicDBObject clienteJSON = (BasicDBObject)cursor.one();

	    return JsonToObjectClass.jsonToCliente(clienteJSON);
		
	}
	
	public void actualizarCliente(int dni, Cliente clienteActualizado) {
		DBCollection clientes = database.getCollection("clientes"); 
		DBObject query = new BasicDBObject("dni", dni);
		clientes.update(query, clienteActualizado.objectToJson());

	}
     
     
     
     
}
