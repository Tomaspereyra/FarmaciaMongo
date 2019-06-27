package pojos;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
public class Producto {
	private int idProducto;
	private String nombre;
	private String tipo;
	private String descripcion;
	private Laboratorio laboratorio;
	private int codigo;
	private double precio;
	
	public Producto() {
		
	}
	
	public Producto(String nombre,String tipo, String descripcion, Laboratorio laboratorio, int codigo, double precio) {
		this.descripcion = descripcion;
		this.laboratorio = laboratorio;
		this.codigo = codigo;
		this.precio = precio;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public DBObject objectToJson() {
	DBObject producto = new BasicDBObject ("descripcion", this.getDescripcion()).append("nombre", this.getNombre()).append("laboratorio",this.getLaboratorio().objectToJson()).append("codigo", this.getCodigo()).append("tipo",this.getTipo()).append("precio",this.getPrecio());	
	return producto;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", tipo=" + tipo + ", descripcion="
				+ descripcion + ", laboratorio=" + laboratorio + ", codigo=" + codigo + ", precio=" + precio + "]";
	}
	
	
	
	
}
