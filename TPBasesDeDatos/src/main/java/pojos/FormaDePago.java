package pojos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class FormaDePago {
	private int idFormaDePago;
	private String nombre;
	
	public FormaDePago(String nombre) {
		this.nombre = nombre;
	}
	
	public int getIdFormaDePago() {
		return idFormaDePago;
	}
	
	public void setIdFormaDePago(int idFormaDePago) {
		this.idFormaDePago = idFormaDePago;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public DBObject objectToJson() {
		DBObject formaDePago = new BasicDBObject("nombre",this.getNombre());
	    return formaDePago;
	}
	
	
}
