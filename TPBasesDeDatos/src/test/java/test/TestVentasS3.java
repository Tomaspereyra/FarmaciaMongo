package test;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.ClienteDao;
import dao.ProductoDao;
import dao.VentaDao;
import pojos.Domicilio;
import pojos.Empleado;
import pojos.FormaDePago;
import pojos.ItemVenta;
import pojos.Localidad;
import pojos.Provincia;
import pojos.Venta;

public class TestVentasS3 {

	public static void main(String[] args) throws UnknownHostException, ParseException {
		//INSTANCIAS
		 VentaDao ventaDao = new VentaDao();   
		 ClienteDao clienteDao= new ClienteDao();
		 ProductoDao productoDao = new ProductoDao();		
		 FormaDePago formaDePago = new FormaDePago("Efectivo");
		 FormaDePago formaDePago2 = new FormaDePago("Tarjeta");
		 Provincia provincia = new Provincia("Buenos Aires");		
		 Localidad localidad2 = new Localidad("Lomas de Zamora");
		 Domicilio domicilio2 = new Domicilio("Laprida",566,localidad2,provincia);
		 ItemVenta item =null;
		 ItemVenta item2 =null;
		 ItemVenta item3 =null;
		 ItemVenta item4=null;
		 Date fecha = new Date();
		 Date fecha2 = new Date();
		 SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		 fecha=ft.parse( "2019-6-1");
		 fecha2=ft.parse("2019-4-1");
		 
		 Empleado atencionS3 = new Empleado("Tomaselli","Carla", 44777111,domicilio2,"22-555555-2");
		 Empleado cajaS3 = new Empleado("Lopez","Pablo", 23444211,domicilio2,"22-444444-1");
		 
		 //VENTAS
		 
	     Venta venta = new Venta(fecha,"0003-1",formaDePago,clienteDao.traerCliente(22222222),atencionS3,cajaS3);	   
	     item = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Aspirina"),2);
		 item3= new ItemVenta(productoDao.traerProducto("Ibuprofeno600"),3); 
		 item4= new ItemVenta(productoDao.traerProducto("Amoxicilina"),3);
		 venta.agregarItem(item);	   
		 venta.agregarItem(item2);	
		 venta.agregarItem(item3);	
		 venta.agregarItem(item4);	
		 ventaDao.agregarVenta(venta); 
		 
		 Venta venta2 = new Venta(fecha,"0003-2",formaDePago2,clienteDao.traerCliente(33333333),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Perfume2"),4);
		 item2=new ItemVenta(productoDao.traerProducto("Ibuprofeno"),1);
		 venta2.agregarItem(item);	
		 venta2.agregarItem(item2);	 
		 ventaDao.agregarVenta(venta2); 
		 
		 Venta venta3 = new Venta(fecha,"0003-3",formaDePago,clienteDao.traerCliente(33333333),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amoxicilina"),2);
		 item2= new ItemVenta(productoDao.traerProducto("Aspirina"),2);
		 item3= new ItemVenta(productoDao.traerProducto("Ibuprofeno600"),3); 
		 item4= new ItemVenta(productoDao.traerProducto("SolucionFisiologica"),3);
		 venta3.agregarItem(item);	   
		 venta3.agregarItem(item2);
		 venta3.agregarItem(item3);
		 venta3.agregarItem(item4);
		 ventaDao.agregarVenta(venta3); 
		 
		 Venta venta4 = new Venta(fecha,"0003-4",formaDePago2,clienteDao.traerCliente(22222222),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Ibuprofeno600"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Aspirina"),2);
		 item3= new ItemVenta(productoDao.traerProducto("Ibuprofeno"),3); 
		 venta4.agregarItem(item);	
		 venta4.agregarItem(item2);	
		 venta4.agregarItem(item3);	
		 ventaDao.agregarVenta(venta4); 
		 
		 Venta venta5 = new Venta(fecha2,"0003-5",formaDePago,clienteDao.traerCliente(33333333),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Ibuprofeno600"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume"),1);
		 item3= new ItemVenta(productoDao.traerProducto("Aspirina"),1);
		 venta5.agregarItem(item);	   
		 venta5.agregarItem(item2);	
		 venta5.agregarItem(item3);	
		 ventaDao.agregarVenta(venta5); 
		 
		 Venta venta6 = new Venta(fecha2,"0003-6",formaDePago2,clienteDao.traerCliente(44444444),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Aspirina"),4);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),1);
		 item3= new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 venta6.agregarItem(item);	   
		 venta6.agregarItem(item2);	
		 venta6.agregarItem(item3);	
		 ventaDao.agregarVenta(venta6); 
		 
		 Venta venta7 = new Venta(fecha2,"0003-7",formaDePago,clienteDao.traerCliente(44444444),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),2);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),7);
		 item3= new ItemVenta(productoDao.traerProducto("Aspirina"),2);
		 venta7.agregarItem(item);
		 venta7.agregarItem(item2);	   
		 venta7.agregarItem(item3);	   
		 ventaDao.agregarVenta(venta7); 
		 
		 Venta venta8 = new Venta(fecha2,"0003-8",formaDePago2,clienteDao.traerCliente(22456789),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),4);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),4);
		 venta8.agregarItem(item);
		 venta8.agregarItem(item2);
		 ventaDao.agregarVenta(venta8); 
		 

	}

}