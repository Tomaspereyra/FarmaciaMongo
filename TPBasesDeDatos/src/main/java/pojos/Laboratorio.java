package pojos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Laboratorio {
	private int idLaboratorio;
	private String nombre;
	
	public Laboratorio(String nombre) {	
		this.nombre = nombre;
	}
	
	public Laboratorio() {
	
	}
	
	public int getIdLaboratorio() {
		return idLaboratorio;
	}
	
	public void setIdLaboratorio(int idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public DBObject objectToJson() {
		DBObject laboratorio = new BasicDBObject("nombre",this.getNombre());
		return laboratorio;
	}

	@Override
	public String toString() {
		return "Laboratorio [idLaboratorio=" + idLaboratorio + ", nombre=" + nombre + "]";
	}
	
	
}
