package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.Domicilio;
import pojos.JsonToObjectClass;
import pojos.Persona;

public class PersonaDao {
	private MongoClient mongoClient;
	private DB database;
	
	public PersonaDao() throws UnknownHostException {

		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}
	
	public void agregarPersona(String apellido, String nombre, int dni, Domicilio domicilio) {
		Persona persona = new Persona(apellido, nombre, dni, domicilio);
		DBCollection personas = database.getCollection("personas");
		personas.insert(persona.objectToJson());
	}
	
	public void agregarPersona(Persona persona) {
		DBCollection personas = database.getCollection("personas");
		personas.insert(persona.objectToJson());
	}

	public Persona traerPersona(int dni) {
		DBCollection personas = database.getCollection("personas");
		DBObject query = new BasicDBObject("dni", dni);
		DBCursor cursor = personas.find(query);
		BasicDBObject personaJSON = (BasicDBObject) cursor.one();

		return JsonToObjectClass.jsonToPersona(personaJSON);
	}
	
	public void actualizarPersona(int dni,Persona personaActualizada) {
		DBCollection personas = database.getCollection("personas");
		DBObject query = new BasicDBObject("dni", dni);
		personas.update(query, personaActualizada.objectToJson());
	}
}
