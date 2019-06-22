package pojos;
import java.math.BigDecimal;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
public class ItemVenta {
	private Producto producto;
	private BigDecimal unitario;
	private int cantidad;
	// Subtotal calculado
	public ItemVenta(Producto producto, BigDecimal unitario, int cantidad) {
		this.producto = producto;
		this.unitario = unitario;
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public BigDecimal getUnitario() {
		return unitario;
	}
	public void setUnitario(BigDecimal unitario) {
		this.unitario = unitario;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public DBObject objectToJson() {
		DBObject itemVenta = new BasicDBObject ("producto",this.getProducto().objectToJson()).append("unitario",this.getUnitario()).append("cantidad", this.getCantidad());
		return itemVenta;
	}
	
}
