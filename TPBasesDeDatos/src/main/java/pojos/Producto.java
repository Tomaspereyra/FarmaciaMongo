package pojos;
import java.math.BigDecimal;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
public class Producto {
	private int idProducto;
	private String descripcion;
	private Laboratorio laboratorio;
	private int codigo;
	private BigDecimal precio;
	public Producto(int idProducto, String descripcion, Laboratorio laboratorio, int codigo, BigDecimal precio) {
		
		this.idProducto = idProducto;
		this.descripcion = descripcion;
		this.laboratorio = laboratorio;
		this.codigo = codigo;
		this.precio = precio;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Laboratorio getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	public DBObject objectToJson() {
	DBObject producto = new BasicDBObject ("descripcion", this.getDescripcion()).append("laboratorio",this.getLaboratorio().objectToJson()).append("codigo", this.getCodigo()).append("precio",this.getPrecio());	
	return producto;
	}
	
	
}
