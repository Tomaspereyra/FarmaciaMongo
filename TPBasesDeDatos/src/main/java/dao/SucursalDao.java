package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.Domicilio;
import pojos.Empleado;
import pojos.Sucursal;

public class SucursalDao {
	private MongoClient mongoClient;
	private DB database;

	public SucursalDao() throws UnknownHostException {
		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}

	public void agregarSucursal(Domicilio domicilio, Empleado encargado, String nombre) throws UnknownHostException {

		Sucursal nuevaSucursal = new Sucursal(domicilio, encargado, nombre);
		DBCollection sucursales = database.getCollection("sucursales");
		sucursales.insert(nuevaSucursal.objectToJson());

	}

	public void agregarSucursal(Sucursal sucursal) throws UnknownHostException {

		
		DBCollection sucursales = database.getCollection("sucursales");
		sucursales.insert(sucursal.objectToJson());

	}

	public void actualizarSucursal(String nombre, Sucursal sucursalActualizada) {
		DBCollection sucursales = database.getCollection("sucursales"); // trae la colleccion
		DBObject query = new BasicDBObject("nombre", nombre); // creo la query
		sucursales.update(query, sucursalActualizada.objectToJson());

	}

}
