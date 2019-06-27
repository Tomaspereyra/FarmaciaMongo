package pojos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Empleado extends Persona {
	private String cuil;
	
	public Empleado(String apellido, String nombre,int dni, Domicilio domicilio, String cuil) {
		super(apellido, nombre,dni, domicilio);
		this.cuil = cuil;
		
	}

	public String getCuil() {
		return cuil;
	}
	
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}	
	
	public DBObject objectToJson() {
		DBObject empleado = new BasicDBObject("nombre",this.getNombre()).append("apellido",this.getApellido()).append("dni",this.getDni()).append("domicilio",this.getDomicilio().objectToJson()).append("cuil", this.getCuil());
	    return empleado;
	}

	@Override
	public String toString() {
		return super.toString()+ "Empleado [cuil=" + cuil + "]";
	}

}
