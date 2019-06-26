package pojos;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ItemVenta {
	private Producto producto;
	private int cantidad;
	
	// Subtotal calculado
	public ItemVenta(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public ItemVenta() {
		
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	public DBObject objectToJson() {
		DBObject itemVenta = new BasicDBObject ("producto",this.getProducto().objectToJson()).append("cantidad", this.getCantidad());
		return itemVenta;
	}
	
}
