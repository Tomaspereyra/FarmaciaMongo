package test;

import java.net.UnknownHostException;

import dao.ClienteDao;
import dao.ProductoDao;
import dao.SucursalDao;
import pojos.Domicilio;
import pojos.Empleado;
import pojos.Laboratorio;
import pojos.Localidad;
import pojos.ObraSocial;
import pojos.Provincia;
import pojos.Sucursal;


public class TestSetDeDatos {

	public static void main(String[] args) throws UnknownHostException {
		/* A fin de poder realizar las consultas, de deberá generar un set de datos conteniendo como mìnimo 3 sucursales, 
		10 clientes, 3 vendedores por sucursal, uno de ellos con categoría  encargado; 10 productos (7 medicamentos y 3 perfumerìa),
		y un promedio de 30 ventas por sucursal. Es deseable que haya variación en las cantidades de venta por sucursal (+/- 20%). 
		Las ventas deben tener un promedio mínimo de 1,5 productos.*/
		
		ClienteDao clienteDao= new ClienteDao();
		ProductoDao productoDao = new ProductoDao();		
		SucursalDao sucursalDao = new SucursalDao();
	
		
		//INSTANCIAS
		
		 Provincia provincia = new Provincia("Buenos Aires");		
		 Localidad localidad = new Localidad("Temperley");
		 Localidad localidad2 = new Localidad("Lomas de Zamora");
		 Domicilio domicilio = new Domicilio("Ezequiel Paz",224,localidad,provincia);
		 Domicilio domicilio2 = new Domicilio("Laprida",566,localidad2,provincia);
		 ObraSocial obraSocial= new ObraSocial("IOMA");
		 ObraSocial obraSocial2= new ObraSocial("SwissMedical");
		 Laboratorio laboratorio = new Laboratorio("Bayer");
		 Laboratorio laboratorio2 = new Laboratorio("Laprida");
		
		
		
		 //CLIENTES
		 clienteDao.agregarCliente("Otegui", "Luciano",22222222,domicilio2,obraSocial2);
		 clienteDao.agregarCliente("Paz", "Agustin",33333333,domicilio,obraSocial);
		 clienteDao.agregarCliente("Rios", "Martin",44444444,domicilio,obraSocial2);		 
		 clienteDao.agregarCliente("Paz", "Maxi",22456789,domicilio2,obraSocial2);
		 clienteDao.agregarCliente("Marchiano", "Agustin",33445566,domicilio2,obraSocial2);
		 clienteDao.agregarCliente("Pereyra", "Tomas",12345678,domicilio,obraSocial);		 
		 clienteDao.agregarCliente("Leotta", "Ezequiel",12344455,domicilio,obraSocial);
		 clienteDao.agregarCliente("Henriquez", "Lautaro",40894068,domicilio2,obraSocial2);
		 clienteDao.agregarCliente("Pereyra", "Mauro",40867986,domicilio,obraSocial);
		 clienteDao.agregarCliente("Orsi", "Damian",38678945,domicilio2,obraSocial2);

		//SUCURSALES//
		
		 //Sucursal 1//
		 Empleado encargado = new Empleado("Perez","Carlos", 12345678,domicilio,"20-999999-7");
		 Empleado vendedor1 = new Empleado("Ortega","Alejo", 12345678,domicilio,"20-999999-8");
		 Empleado vendedor2 = new Empleado("Huerta","Maximiliano", 11111111,domicilio,"20-999999-9");
	     Sucursal sucursal = new Sucursal(domicilio,encargado,"Sucursal Temperley");
	     sucursal.agregarEmpleados(vendedor1);
	     sucursal.agregarEmpleados(vendedor2);	   
	     sucursalDao.agregarSucursal(sucursal);
	   
	     //Sucursal 2//
	     Empleado encargadoS2 = new Empleado("Perez","Juan", 23456789,domicilio2,"20-888888-7");
		 Empleado vendedor1S2 = new Empleado("Tomaselli","Federico", 34567890,domicilio2,"20-555555-8");
		 Empleado vendedor2S2 = new Empleado("Perez","Camila", 22222333,domicilio2,"20-444444-9");
	     Sucursal sucursal2 = new Sucursal(domicilio2,encargadoS2,"Sucursal Laprida");
	     sucursal.agregarEmpleados(vendedor1S2);
	     sucursal.agregarEmpleados(vendedor2S2);	   
	     sucursalDao.agregarSucursal(sucursal2);
	     
	  
	     //Sucursal 3//
	     Empleado encargadoS3 = new Empleado("Perez","Alberto", 44555222,domicilio2,"22-888888-3");
		 Empleado vendedor1S3 = new Empleado("Tomaselli","Carla", 44777111,domicilio2,"22-555555-2");
		 Empleado vendedor2S3 = new Empleado("Lopez","Pablo", 23444211,domicilio2,"22-444444-1");
	     Sucursal sucursal3 = new Sucursal(domicilio2,encargadoS3,"Sucursal Lomas Central");
	     sucursal.agregarEmpleados(vendedor1S3);
	     sucursal.agregarEmpleados(vendedor2S3);	   
	     sucursalDao.agregarSucursal(sucursal3);
	 
	
	     //PRODUCTOS
	     productoDao.agregarProducto("Ibuprofeno","Farmacia","Tableta Ibu 400", laboratorio2, 1234, 60);
		 productoDao.agregarProducto("Ibuprofeno600","Farmacia","Tableta Ibu 600", laboratorio, 1235, 80);
		 productoDao.agregarProducto("Aspirina","Farmacia","Aspirina", laboratorio2, 1236, 90);
		 productoDao.agregarProducto("Amlodipina","Farmacia"," Amlodipina", laboratorio, 1237, 550);
		 productoDao.agregarProducto("Paracetamol","Farmacia","Paracetamol TYLENOL", laboratorio2, 1238, 170);
		 productoDao.agregarProducto("SolucionFisiologica","Farmacia","Solucion Estirilizada Fisiologica", laboratorio, 1239, 50);
		 productoDao.agregarProducto("Amoxicilina","Farmacia","Amoxicilina duo 500", laboratorio2, 1240, 350);
		 productoDao.agregarProducto("Perfume","Perfumeria","Kevin", laboratorio, 2233, 600);
		 productoDao.agregarProducto("Desodorante","Perfumeria","Axe", laboratorio2, 2234, 200);
		 productoDao.agregarProducto("Perfume2","Perfumeria","Kevingston", laboratorio, 2235, 800);
	
		 
		 
		 
		 
		 
		 //VENTAS
		 //SUCURSAL1
		 test.TestVentasS1.main(args);
		
		 //SUCURSAL2
		 test.TestVentasS2.main(args);
		
		 //SUCURSAL3
		 test.TestVentasS3.main(args);

	}

}
