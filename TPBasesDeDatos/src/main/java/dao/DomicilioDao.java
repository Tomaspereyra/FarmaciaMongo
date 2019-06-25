package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.Domicilio;
import pojos.Localidad;
import pojos.Provincia;

public class DomicilioDao {
	private MongoClient mongoClient;
	private DB database;

	public DomicilioDao() throws UnknownHostException {
		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("mongoClient");
	}

	public void agregarDomicilio(String calle, int numero, Localidad localidad, Provincia provincia) {

		Domicilio domicilio = new Domicilio(calle, numero, localidad, provincia);
		DBCollection domicilios = database.getCollection("domicilios");
		domicilios.insert(domicilio.objectToJson());

	}
	public void agregarDomicilio(Domicilio domicilio) {
		DBCollection domicilios = database.getCollection("domicilios");
		domicilios.insert(domicilio.objectToJson());

	}

public Domicilio traerDomicilio(String calle, int numero) {
	
	DBCollection domicilios = database.getCollection("domicilios");
	DBObject query = new BasicDBObject();
	query.put("calle", calle);
	query.put("numero", numero);
	
	DBCursor cursor = domicilios.find(query);
	
	BasicDBObject domicilioJSON = (BasicDBObject)cursor.one();
	Domicilio d = new Domicilio(domicilioJSON.getString("calle"),domicilioJSON.getInt("numero"));
    
	return d;
}

}
