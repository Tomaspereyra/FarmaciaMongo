package pojos;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Sucursal {
	private int idSucursal;
	private Domicilio domicilio;
	private Empleado encargado;
	private List<Empleado> empleados;
	public Sucursal(Domicilio domicilio, Empleado encargado) {
		
		this.domicilio = domicilio;
		this.encargado = encargado;
		this.empleados = new ArrayList<Empleado>();
	}
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public Empleado getEncargado() {
		return encargado;
	}
	public void setEncargado(Empleado encargado) {
		this.encargado = encargado;
	}
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	public void agregarEmpleados(Empleado e) {
		this.empleados.add(e);
	}
	
	public List<DBObject> listToJson() {
	List<DBObject> empleadosJSON = new ArrayList<DBObject>();
	for(int i=0;i<this.empleados.size();i++) {
		empleadosJSON.add(this.empleados.get(i).objectToJson());
	}
	return empleadosJSON;
	
	}
	
	public DBObject objectToJson() {
		DBObject sucursal = new BasicDBObject("domicilio",this.getDomicilio().objectToJson()).append("encargado",this.getEncargado().objectToJson()).append("empleados",this.listToJson());
	    return sucursal;
	}
	
}
