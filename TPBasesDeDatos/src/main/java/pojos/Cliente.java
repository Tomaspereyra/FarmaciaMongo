package pojos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Cliente extends Persona {
private ObraSocial obraSocial;

public Cliente(String apellido, String nombre,int dni, Domicilio domicilio, ObraSocial obraSocial) {
	super(apellido, nombre,dni, domicilio);
	this.obraSocial = obraSocial;
}

public ObraSocial getObraSocial() {
	return obraSocial;
}

public void setObraSocial(ObraSocial obraSocial) {
	this.obraSocial = obraSocial;
}
public DBObject objectToJson() {
	DBObject cliente = new BasicDBObject("nombre",this.getNombre()).append("apellido",this.getApellido()).append("dni",this.getDni()).append("domicilio",this.getDomicilio().objectToJson()).append("obraSocial", this.getObraSocial().objectToJson());
    return cliente;
}


}
