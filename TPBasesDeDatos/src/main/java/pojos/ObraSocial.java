package pojos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ObraSocial {
	private int idObraSocial;
	private String nombre;
	public ObraSocial(String nombre) {
		this.nombre = nombre;
	}
	public int getIdObraSocial() {
		return idObraSocial;
	}
	public void setIdObraSocial(int idObraSocial) {
		this.idObraSocial = idObraSocial;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public DBObject objectToJson() {
		DBObject obraSocial = new BasicDBObject("nombre",this.getNombre());
		return obraSocial;
	}
	
	
}
