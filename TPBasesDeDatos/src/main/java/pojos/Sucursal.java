package pojos;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Sucursal {
	private String idSucursal;
	private String nombre;
	private Domicilio domicilio;
	private Empleado encargado;
	private List<Empleado> empleados;
	
	public Sucursal(Domicilio domicilio, Empleado encargado,String nombre) {
		
		this.domicilio = domicilio;
		this.encargado = encargado;
		this.empleados = new ArrayList<Empleado>();
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdSucursal() {
		return idSucursal;
	}
	
	public void setIdSucursal(String idSucursal) {
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
	
	public List<Empleado> jsonToListEmpleado() {
		List<Empleado> empleadoList = new ArrayList<Empleado>();
		List<DBObject> empleadosJSON = listToJson();	
			for(int i=0;i<empleadosJSON.size();i++) {
				empleadoList.add( JsonToObjectClass.jsonToEmpleado((BasicDBObject) empleadosJSON.get(i)));
			}
		return empleadoList;
	}
	
	public DBObject objectToJson() {
		DBObject sucursal = new BasicDBObject("domicilio",this.getDomicilio().objectToJson()).append("_id", this.getIdSucursal()).append("nombre", this.getNombre()).append("encargado",this.getEncargado().objectToJson()).append("empleados",this.listToJson());
	    return sucursal;
	}

	@Override
	public String toString() {
		return "Sucursal [idSucursal=" + idSucursal + ", nombre=" + nombre + ", domicilio=" + domicilio + ", encargado="
				+ encargado + ", empleados=" + empleados + "]";
	}
	
	
	
}
