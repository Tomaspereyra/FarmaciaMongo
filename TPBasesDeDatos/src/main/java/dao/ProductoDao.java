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
import pojos.Producto;

public class ProductoDao {
	private MongoClient mongoClient;
	private DB database;

	public ProductoDao() throws UnknownHostException {
		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}

	public void agregarProducto(String nombre, String tipo, String descripcion, Laboratorio laboratorio, int codigo, double precio) {
		Producto producto = new Producto(nombre,tipo, descripcion, laboratorio, codigo, precio);
		DBCollection productos = database.getCollection("productos");
		productos.insert(producto.objectToJson());

	}
	
	public void agregarProducto(Producto producto) {
		DBCollection productos = database.getCollection("productos");
		productos.insert(producto.objectToJson());
	}
	
	
	public Producto traerProducto(String nombre) {
		DBCollection productos = database.getCollection("productos");
		DBObject query = new BasicDBObject("nombre",nombre);
		DBCursor cursor = productos.find(query);
		BasicDBObject productoJSON = (BasicDBObject) cursor.one();
		
		return JsonToObjectClass.jsonToProducto(productoJSON);
		
		
	}
	
	public void actualizarProductos(String nombre, Producto productoActualizado) {
		DBCollection productos = database.getCollection("productos");
		DBObject query = new BasicDBObject("nombre", nombre);
		productos.update(query, productoActualizado.objectToJson());

	}

}
