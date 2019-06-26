package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.ItemVenta;
import pojos.JsonToObjectClass;
import pojos.Producto;

public class ItemVentaDao {
	 private MongoClient mongoClient;
     private DB database;
     
  	public ItemVentaDao() throws UnknownHostException {
 		this.mongoClient = new MongoClient();
 		this.database = mongoClient.getDB("farmacia");
 	}
  	
  	
  	
	public void agregarItemVenta(Producto producto, int cantidad) {
		ItemVenta itemVenta = new ItemVenta(producto,cantidad);		
		DBCollection collection = database.getCollection("itemVentas");	     
	    collection.insert(itemVenta.objectToJson());
		
	}
	
	public void agregarItemVenta(ItemVenta itemVenta) {
		DBCollection collection = database.getCollection("itemVentas");	     
	    collection.insert(itemVenta.objectToJson());
		
	}
	
	public ItemVenta traerItemVenta(Producto producto) {
		
		DBCollection collection = database.getCollection("itemVentas");
		
		DBObject query = new BasicDBObject("producto", producto);
		DBCursor cursor = collection.find(query);
		BasicDBObject itemVentasJSON = (BasicDBObject)cursor.one();

		return JsonToObjectClass.jsonToItemVenta(itemVentasJSON);
	}
	
	public void actualizarItemVenta(Producto producto, ItemVenta itemVentaActualizado) {
		DBCollection itemVenta = database.getCollection("itemVentas"); 
		DBObject query = new BasicDBObject("producto", producto);
		itemVenta.update(query, itemVentaActualizado.objectToJson());

	}
}
