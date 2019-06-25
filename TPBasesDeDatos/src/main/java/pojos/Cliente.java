package pojos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Cliente extends Persona {
private ObraSocial obraSocial;

public Cliente(String apellido, String nombre,int dni, Domicilio domicilio, ObraSocial obraSocial) {
	super(apellido, nombre,dni, domicilio);
	this.obraSocial = obraSocial;
}
public Cliente(String apellido, String nombre,int dni, Domicilio domicilio) {
	super(apellido, nombre,dni, domicilio);
	this.obraSocial = null;
}

public ObraSocial getObraSocial() {
	return obraSocial;
}

public void setObraSocial(ObraSocial obraSocial) {
	this.obraSocial = obraSocial;
}
public DBObject objectToJson() {
	DBObject cliente = null;
	if (this.getObraSocial()!=null) {
	   cliente = new BasicDBObject("nombre",this.getNombre()).append("apellido",this.getApellido()).append("dni",this.getDni()).append("domicilio",this.getDomicilio().objectToJson()).append("obraSocial", this.getObraSocial().objectToJson());
	}
	   else {
	    
	  cliente = new BasicDBObject("nombre",this.getNombre()).append("apellido",this.getApellido()).append("dni",this.getDni()).append("domicilio",this.getDomicilio().objectToJson());

	}
	return cliente;
}

public Cliente jsonToObject(BasicDBObject cliente) {
	Domicilio d = new Domicilio();
	
	Cliente c = new Cliente(cliente.getString("apellido"),cliente.getString("nombre"),cliente.getInt("dni"),d.jsonToObject((BasicDBObject) cliente.get("domicilio")));

	
	return c;
	
}



}
