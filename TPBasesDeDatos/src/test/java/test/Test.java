package test;
import pojos.Cliente;
import pojos.Domicilio;
import pojos.Empleado;
import pojos.FormaDePago;
import pojos.ItemVenta;
import pojos.Laboratorio;
import pojos.Localidad;
import pojos.ObraSocial;
import pojos.Persona;
import pojos.Provincia;
import pojos.Sucursal;
import pojos.Venta;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.GregorianCalendar;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dao.ClienteDao;
import dao.EmpleadoDao;
import dao.ProductoDao;
import dao.SucursalDao;
import dao.VentaDao;
public class Test {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		Provincia provincia = new Provincia("Buenos Aires");
		Localidad localidad = new Localidad("Temperley");
		Domicilio domicilio = new Domicilio("Ezequiel Paz",224,localidad,provincia);
		ObraSocial obraSocial= new ObraSocial("obra social");
		ClienteDao clienteDao= new ClienteDao();
		ProductoDao productoDao = new ProductoDao();
		Laboratorio laboratorio = new Laboratorio("bayer");
   
    
	 /* Sucursal
	 Empleado encargado = new Empleado("apellido","nombre", 12345678,domicilio,"20-999999-7");
	 Empleado caja = new Empleado("caja","A", 12345678,domicilio,"20-999999-8");
	 Empleado atencion = new Empleado("atencion","A", 11111111,domicilio,"20-999999-8");
     Sucursal sucursal = new Sucursal(domicilio,encargado,"Sucursal A");
     sucursal.agregarEmpleados(caja);
     sucursal.agregarEmpleados(atencion);
     SucursalDao sucursalDao = new SucursalDao();
     sucursalDao.agregarSucursal(sucursal);
     */
	
	  // Clientes
	 /*clienteDao.agregarCliente("Otegui", "Luciano",22222222,domicilio,obraSocial);
	 clienteDao.agregarCliente("Paz", "Agustin",33333333,domicilio,obraSocial);
	 clienteDao.agregarCliente("Rios", "Martin",44444444,domicilio,obraSocial);*/
	
	/* productos	
	productoDao.agregarProducto("Ibuprofeno","Farmacia","descripcion de un Ibuprofeno", laboratorio, 1234, 50);
	productoDao.agregarProducto("perfume","Perfumeria","descripcion de un Perfume", laboratorio, 1234, 500);*/
		
		Empleado atencion = new Empleado("atencion","A", 11111111,domicilio,"20-999999-8");
		Empleado caja = new Empleado("caja","A", 12345678,domicilio,"20-999999-8");
		FormaDePago formaDePago = new FormaDePago("Efectivo");
	
	   Venta venta = new Venta(new GregorianCalendar(),"0001-12345678",500,formaDePago,clienteDao.traerCliente(22222222),atencion,caja);
	   
	   ItemVenta item = new ItemVenta(productoDao.traerProducto("perfume"),1);
	   VentaDao ventaDao = new VentaDao();
	   
	   venta.agregarItem(item);
	   
	   //ventaDao.agregarVenta(venta);

	   

	 

	
	 
     
     
     
     
	}

}
