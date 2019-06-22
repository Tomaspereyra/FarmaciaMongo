package test;
import pojos.Domicilio;
import pojos.Empleado;
import pojos.Localidad;
import pojos.Persona;
import pojos.Provincia;
import pojos.Sucursal;

import java.net.UnknownHostException;


import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
public class Test {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		Provincia provincia = new Provincia("Buenos Aires");
		Localidad localidad = new Localidad("Temperley");
		Domicilio domicilio = new Domicilio("Ezequiel Paz",224,localidad,provincia);
     Persona p = new Persona("Pereyra", "Tomas",40897248,domicilio);
     System.out.println(p.objectToJson());
     
  
     //DBCollection collection = database.getCollection("Personas");
     
     //collection.insert(p.ObjectToJson());*/
     Empleado encargado = new Empleado("apellido","nombre", 12345678,domicilio,"999999");
     Sucursal sucursal = new Sucursal(domicilio,encargado);
     sucursal.agregarEmpleados(encargado);
     sucursal.agregarEmpleados(encargado);
     sucursal.agregarEmpleados(encargado);
     sucursal.agregarEmpleados(encargado);
     System.out.println(sucursal.objectToJson());
     MongoClient mongoClient = new MongoClient();
     DB database = mongoClient.getDB("farmacia");
     DBCollection sucursales = database.getCollection("Sucursales");
     sucursales.insert(sucursal.objectToJson());
     
     
	}

}
