package pojos;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
public class Persona {
private int idPersona;
private String apellido;
private String nombre;
private int dni;
private Domicilio domicilio;



public Persona(String apellido, String nombre, int dni, Domicilio domicilio) {
	super();
	this.apellido = apellido;
	this.nombre = nombre;
	this.dni = dni;
	this.domicilio = domicilio;
}

public int getDni() {
	return dni;
}

public void setDni(int dni) {
	this.dni = dni;
}

public Domicilio getDomicilio() {
	return domicilio;
}

public void setDomicilio(Domicilio domicilio) {
	this.domicilio = domicilio;
}

public int getIdPersona() {
	return idPersona;
}
public void setIdPersona(int idPersona) {
	this.idPersona = idPersona;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}

public DBObject objectToJson() {
	
	DBObject persona = new BasicDBObject("_id", this.getIdPersona()).append("apellido",this.getApellido())
			.append("nombre",this.getNombre()).append("dni", this.getDni()).append("Domicilio", this.getDomicilio().objectToJson());
	
	return persona;
}
}
