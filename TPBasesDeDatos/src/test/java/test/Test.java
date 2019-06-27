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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

	public static void main(String[] args) throws UnknownHostException, ParseException {
		// TODO Auto-generated method stub
		Provincia provincia = new Provincia("Buenos Aires");
		Localidad localidad = new Localidad("Temperley");
		Domicilio domicilio = new Domicilio("Ezequiel Paz",224,localidad,provincia);
		ObraSocial obraSocial= new ObraSocial("obra social");
		ClienteDao clienteDao= new ClienteDao();
		ProductoDao productoDao = new ProductoDao();
		Laboratorio laboratorio = new Laboratorio("bayer");
		VentaDao ventaDao = new VentaDao(); 
		Empleado caja = new Empleado("caja","A", 12345678,domicilio,"20-999999-8");
		 Empleado atencion = new Empleado("atencion","A", 11111111,domicilio,"20-999999-8");
   
    
	 
	 Empleado encargado = new Empleado("apellido","nombre", 12345678,domicilio,"20-999999-7");
     Sucursal sucursal = new Sucursal(domicilio,encargado,"Sucursal A");
     sucursal.setIdSucursal("0001");
     sucursal.agregarEmpleados(caja);
     sucursal.agregarEmpleados(atencion);
     SucursalDao sucursalDao = new SucursalDao();
     //sucursalDao.agregarSucursal(sucursal);
    
	
	  // Carga Clientes
	 /*clienteDao.agregarCliente("Otegui", "Luciano",22222222,domicilio,obraSocial);
	 clienteDao.agregarCliente("Paz", "Agustin",33333333,domicilio,obraSocial);
	 clienteDao.agregarCliente("Rios", "Martin",44444444,domicilio,obraSocial);*/
	
	/* carga Productos	
	productoDao.agregarProducto("Ibuprofeno","Farmacia","descripcion de un Ibuprofeno", laboratorio, 1234, 50);
	productoDao.agregarProducto("perfume","Perfumeria","descripcion de un Perfume", laboratorio, 1234, 500);*/
	
	//Carga ventas
	FormaDePago formaDePago = new FormaDePago("Efectivo");
    Venta venta = new Venta(new Date(),"0001-12345678",formaDePago,clienteDao.traerCliente(22222222),atencion,caja);	   
	ItemVenta item = new ItemVenta(productoDao.traerProducto("perfume"),1);
	 
	venta.agregarItem(item);
	venta.calcularTotal(); //Seteo el total antes de agregarla
    
	//ventaDao.agregarVenta(venta);
		
		Date fechaInicial = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = "2018-10-1";
		fechaInicial = ft.parse(fecha);
		Date fechaFinal = new Date();
		
		String fecha1 = "2018-12-12";
		fechaFinal = ft.parse(fecha1);
		
		System.out.println(fechaInicial);
		System.out.println(fechaFinal);
		venta.setFecha(fechaInicial);
		//ventaDao.agregarVenta(venta);
	
		//Detalle de ventas por fechas (Toda la cadena)
		System.out.println(ventaDao.traerVentas(fechaInicial, fechaFinal));
		System.out.println(ventaDao.traerTotal(ventaDao.traerVentas(fechaInicial, fechaFinal)));
		//Detalle de ventas por fechas y por sucursal
		System.out.println(ventaDao.traerVentas("0001",fechaInicial, fechaFinal));
		
		System.out.println(ventaDao.traerTotal(ventaDao.traerVentas(fechaInicial, fechaFinal)));
		System.out.println("------");
		//2-
		// Detalle de ventas para la cadena completa,entre fechas y por obra Social
		System.out.println(ventaDao.traerVentas(fechaInicial, fechaFinal, true));
		// Detalle de ventas por sucursal,entre fechas y por obra social
		System.out.println(ventaDao.traerVentas("0001",fechaInicial, fechaFinal, true));
		//3-
		// Detalle de ventas para la cadena completa por medios de pago diferentes
		System.out.println(ventaDao.traerVentas(fechaInicial, fechaFinal, "Efectivo"));
		// Detalle de ventas para la sucursal por medios de pago diferentes
		System.out.println(ventaDao.traerVentas("0001",fechaInicial, fechaFinal, "Efectivo"));

		//4-
		//Detalle de ventas para la cadena completa por fecha y por tipo de producto
		System.out.println(ventaDao.traerVentasTipo(fechaInicial, fechaFinal, "farmacia"));
		//Detalle de ventas para la cadena completa por fecha y por tipo de producto
        System.out.println(ventaDao.traerVentasTipo("0001", fechaInicial, fechaFinal, "farmacia"));
		
        //5
        //Detalle de ventas por productos para toda la cadena
        ventaDao.rankingProductos();
	   

	 

	
	 
     
     
     
     
	}

}
