package pojos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Domicilio {
	private String calle;
	private Localidad localidad;
	private Provincia provincia;
	private int numero;
	
	public Domicilio(String calle,int numero, Localidad localidad, Provincia provincia) {
		super();
		this.calle = calle;
		this.localidad = localidad;
		this.provincia = provincia;
		this.numero = numero;
	}
	
	public Domicilio(String calle,int numero, Localidad localidad) {
		super();
		this.calle = calle;
		this.localidad = localidad;
		this.numero = numero;
	}
	
	public Domicilio(String calle,int numero) {
		this.calle = calle;
		this.numero = numero;
	}
	
	public Domicilio() {
		
	}
	
	public String getCalle() {
		return calle;
	}
	
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public Localidad getLocalidad() {
		return localidad;
	}
	
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	public Provincia getProvincia() {
		return provincia;
	}
	
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public DBObject objectToJson() {
		DBObject domicilio= new BasicDBObject("calle",this.getCalle()).append("numero",this.getNumero()).append("localidad",this.getLocalidad().objectToJson()).append("provincia", this.getProvincia().objectToJson());
	    return domicilio;
	}
	
	@Override
	public String toString() {
		return "Domicilio [calle=" + calle + ", localidad=" + localidad + ", provincia=" + provincia + ", numero="
				+ numero + "]";
	}
	
	
}
