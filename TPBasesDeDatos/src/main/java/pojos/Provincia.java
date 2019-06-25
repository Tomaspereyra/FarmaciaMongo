package pojos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Provincia {
	private int idProvincia;
	private String nombre;
	public Provincia(String nombre) {
		
		this.nombre = nombre;
	}
    public Provincia() {
		
	}
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public DBObject objectToJson() {
		DBObject provincia = new BasicDBObject("nombre",this.getNombre());
		return provincia;
	}
	public Provincia jsonToObject(BasicDBObject provincia) {
		Provincia p = new Provincia(provincia.getString("nombre"));
		return p;
	}
	
	
}
