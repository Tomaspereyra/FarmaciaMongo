package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import pojos.Domicilio;
import pojos.Empleado;
import pojos.JsonToObjectClass;

public class EmpleadoDao {
	private MongoClient mongoClient;
	private DB database;

	public EmpleadoDao() throws UnknownHostException {

		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDB("farmacia");
	}

	public void agregarEmpleado(String apellido, String nombre, int dni, Domicilio domicilio, String cuil) {
		Empleado empleado = new Empleado(apellido, nombre, dni, domicilio, cuil);
		DBCollection empleados = database.getCollection("empleados");
		empleados.insert(empleado.objectToJson());

	}
	
	public void agregarEmpleado(Empleado empleado) {
		DBCollection empleados = database.getCollection("empleados");
		empleados.insert(empleado.objectToJson());

	}

	public Empleado traerEmpleado(int dni) {
		DBCollection collection = database.getCollection("empleados");

		DBObject query = new BasicDBObject("dni", dni);
		DBCursor cursor = collection.find(query);
		BasicDBObject empleadoJSON = (BasicDBObject) cursor.one();

		return JsonToObjectClass.jsonToEmpleado(empleadoJSON);

	}
	
	public void actualizarEmpleados(int dni, Empleado empleadoActualizado) {
		DBCollection empleados = database.getCollection("empleados");
		DBObject query = new BasicDBObject("dni", dni);
		empleados.update(query, empleadoActualizado.objectToJson());

	}

}
