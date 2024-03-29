package pojos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Localidad {
	private int idLocalidad;
	private String nombre;
	
	public Localidad(String nombre) {
		this.nombre = nombre;
	}
	
	public Localidad() {
		
	}
	
	public int getIdLocalidad() {
		return idLocalidad;
	}
	
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public DBObject objectToJson() {
		DBObject localidad = new BasicDBObject("nombre",this.getNombre());
	    
		return localidad;
	}

	@Override
	public String toString() {
		return "Localidad [idLocalidad=" + idLocalidad + ", nombre=" + nombre + "]";
	}
	
	
	
}
