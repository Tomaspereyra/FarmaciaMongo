package dao;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import pojos.Venta;

public class VentaDao {
private MongoClient mongoClient;
private DB database;

public VentaDao() throws UnknownHostException {
	this.mongoClient = new MongoClient() ;
	this.database = mongoClient.getDB("farmacia");
}

public void agregarVenta(Venta venta) {
	DBCollection ventas = database.getCollection("ventas");
	ventas.insert(venta.objectToJson());
	
}
}
