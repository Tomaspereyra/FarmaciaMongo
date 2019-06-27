package test;

import java.net.UnknownHostException;
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

	public static void main(String[] args) throws UnknownHostException {
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
		 ItemVenta item4 =null;
		 
		 Empleado atencionS3 = new Empleado("Tomaselli","Carla", 44777111,domicilio2,"22-555555-2");
		 Empleado cajaS3 = new Empleado("Lopez","Pablo", 23444211,domicilio2,"22-444444-1");
		 
		 //VENTAS
		 
	     Venta venta = new Venta(new Date(),"0003-1",0,formaDePago,clienteDao.traerCliente(22222222),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Perfume"),2);
		 venta.agregarItem(item);	   
		 ventaDao.agregarVenta(venta); 
		 
		 Venta venta2 = new Venta(new Date(),"0003-2",0,formaDePago2,clienteDao.traerCliente(33333333),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Perfume2"),4);
		 item2=new ItemVenta(productoDao.traerProducto("Ibuprofeno"),1);
		 venta2.agregarItem(item);	
		 venta2.agregarItem(item2);	 
		 ventaDao.agregarVenta(venta2); 
		 
		 Venta venta3 = new Venta(new Date(),"0003-3",0,formaDePago,clienteDao.traerCliente(33333333),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Ibuprofeno"),2);
		 venta3.agregarItem(item);	   
		 ventaDao.agregarVenta(venta3); 
		 
		 Venta venta4 = new Venta(new Date(),"0003-4",0,formaDePago2,clienteDao.traerCliente(22222222),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Ibuprofeno600"),1);
		 venta4.agregarItem(item);	   
		 ventaDao.agregarVenta(venta4); 
		 
		 Venta venta5 = new Venta(new Date(),"0003-5",0,formaDePago,clienteDao.traerCliente(33333333),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Ibuprofeno600"),2);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume"),3);
		 item3= new ItemVenta(productoDao.traerProducto("Aspirina"),1);
		 venta5.agregarItem(item);	   
		 venta5.agregarItem(item2);	
		 venta5.agregarItem(item3);	
		 ventaDao.agregarVenta(venta5); 
		 
		 Venta venta6 = new Venta(new Date(),"0003-6",0,formaDePago2,clienteDao.traerCliente(44444444),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Aspirina"),4);
		 venta6.agregarItem(item);	   
		 ventaDao.agregarVenta(venta6); 
		 
		 Venta venta7 = new Venta(new Date(),"0003-7",0,formaDePago,clienteDao.traerCliente(44444444),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),2);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),3);
		 item3= new ItemVenta(productoDao.traerProducto("Aspirina"),3);
		 venta7.agregarItem(item);
		 venta7.agregarItem(item2);	   
		 venta7.agregarItem(item3);	   
		 ventaDao.agregarVenta(venta7); 
		 
		 Venta venta8 = new Venta(new Date(),"0003-8",0,formaDePago2,clienteDao.traerCliente(22456789),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),1);
		 venta8.agregarItem(item);
		 venta8.agregarItem(item2);
		 ventaDao.agregarVenta(venta8); 
		 
		 Venta venta9 = new Venta(new Date(),"0003-9",0,formaDePago,clienteDao.traerCliente(22456789),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("SolucionFisiologica"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Amoxicilina"),1);
		 venta9.agregarItem(item);
		 venta9.agregarItem(item2);	  
		 ventaDao.agregarVenta(venta9); 
		 
		 Venta venta10 = new Venta(new Date(),"0003-10",0,formaDePago2,clienteDao.traerCliente(22456789),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("SolucionFisiologica"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),2);
		 item3= new ItemVenta(productoDao.traerProducto("Amoxicilina"),3);
		 venta10.agregarItem(item);	 
		 venta10.agregarItem(item2);	   
		 venta10.agregarItem(item3);	   
		 ventaDao.agregarVenta(venta10); 
		 
		 Venta venta11 = new Venta(new Date(),"0003-11",0,formaDePago,clienteDao.traerCliente(33445566),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Aspirina"),2);
		 item3= new ItemVenta(productoDao.traerProducto("Amoxicilina"),3);
		 venta11.agregarItem(item);	  
		 venta11.agregarItem(item2);	   
		 venta11.agregarItem(item3);	   
		 ventaDao.agregarVenta(venta11); 
		 
		 Venta venta12 = new Venta(new Date(),"0003-12",0,formaDePago2,clienteDao.traerCliente(33445566),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Aspirina"),1);
		 item3= new ItemVenta(productoDao.traerProducto("Amoxicilina"),1);
		 venta12.agregarItem(item);	  
		 venta12.agregarItem(item2);	   
		 venta12.agregarItem(item3);	 
		 ventaDao.agregarVenta(venta12); 
		 
		 Venta venta13 = new Venta(new Date(),"0003-13",0,formaDePago,clienteDao.traerCliente(12344455),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amoxicilina"),1);
		 venta13.agregarItem(item);	   
		 ventaDao.agregarVenta(venta13); 
		 
		 Venta venta14 = new Venta(new Date(),"0003-14",0,formaDePago,clienteDao.traerCliente(12344455),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 venta14.agregarItem(item);	   
		 ventaDao.agregarVenta(venta14); 
		 
		 Venta venta15 = new Venta(new Date(),"0003-15",0,formaDePago2,clienteDao.traerCliente(12345678),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("SolucionFisiologica"),4);
		 venta15.agregarItem(item);	   
		 ventaDao.agregarVenta(venta15); 
		 
		 Venta venta16 = new Venta(new Date(),"0003-16",0,formaDePago2,clienteDao.traerCliente(12345678),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Perfume"),6);
		 venta16.agregarItem(item);	   
		 ventaDao.agregarVenta(venta16); 
		 
		 Venta venta17 = new Venta(new Date(),"0003-17",0,formaDePago,clienteDao.traerCliente(40894068),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Perfume2"),3);
		 venta17.agregarItem(item);	   
		 ventaDao.agregarVenta(venta17); 
		 
		 Venta venta18 = new Venta(new Date(),"0003-18",0,formaDePago2,clienteDao.traerCliente(40894068),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Perfume2"),1);
		 venta18.agregarItem(item);	   
		 ventaDao.agregarVenta(venta18); 
		 
		 Venta venta19 = new Venta(new Date(),"0003-19",0,formaDePago,clienteDao.traerCliente(40894068),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Aspirina"),3);
		 venta19.agregarItem(item);	   
		 venta19.agregarItem(item2);	
		 ventaDao.agregarVenta(venta19); 
		 
		 Venta venta20 = new Venta(new Date(),"0003-20",0,formaDePago,clienteDao.traerCliente(40894068),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Ibuprofeno"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume2"),3);
		 item3 = new ItemVenta(productoDao.traerProducto("Desodorante"),1);
		 item4= new ItemVenta(productoDao.traerProducto("Aspirina"),3);
		 venta20.agregarItem(item);	   
		 venta20.agregarItem(item2);	   
		 venta20.agregarItem(item3);	   
		 venta20.agregarItem(item4);	   
		 ventaDao.agregarVenta(venta20); 
		 
		 Venta venta21 = new Venta(new Date(),"0003-21",0,formaDePago,clienteDao.traerCliente(40894068),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Ibuprofeno"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume2"),1);
		 item3 = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 item4= new ItemVenta(productoDao.traerProducto("Aspirina"),4);
		 venta21.agregarItem(item);	   
		 venta21.agregarItem(item2);	   
		 venta21.agregarItem(item3);	   
		 venta21.agregarItem(item4);	    
		 ventaDao.agregarVenta(venta21); 
		 
		 Venta venta22 = new Venta(new Date(),"0003-22",0,formaDePago2,clienteDao.traerCliente(40867986),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Paracetamol"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume2"),1);
		 item3 = new ItemVenta(productoDao.traerProducto("Amoxicilina"),2);
		 item4= new ItemVenta(productoDao.traerProducto("SolucionFisiologica"),4);
		 venta22.agregarItem(item);	   
		 venta22.agregarItem(item2);	   
		 venta22.agregarItem(item3);	   
		 venta22.agregarItem(item4);	   
		 ventaDao.agregarVenta(venta22); 
		 
		 Venta venta23 = new Venta(new Date(),"0003-23",0,formaDePago2,clienteDao.traerCliente(40867986),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Paracetamol"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume2"),1);
		 item3 = new ItemVenta(productoDao.traerProducto("Amoxicilina"),1);
		 item4= new ItemVenta(productoDao.traerProducto("SolucionFisiologica"),1);
		 venta23.agregarItem(item);	   
		 venta23.agregarItem(item2);	   
		 venta23.agregarItem(item3);	   
		 venta23.agregarItem(item4);   
		 ventaDao.agregarVenta(venta23); 
		 
		 Venta venta24 = new Venta(new Date(),"0003-24",0,formaDePago2,clienteDao.traerCliente(40867986),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Paracetamol"),3);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume2"),1);
		 item3 = new ItemVenta(productoDao.traerProducto("Amoxicilina"),2);
		 venta24.agregarItem(item);	   
		 venta24.agregarItem(item2);	   
		 venta24.agregarItem(item3);	   
		 ventaDao.agregarVenta(venta24); 
		 
		 Venta venta25 = new Venta(new Date(),"0003-25",0,formaDePago,clienteDao.traerCliente(38678945),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Desodorante"),2);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume2"),1);
		 venta25.agregarItem(item);	 
		 venta25.agregarItem(item2);	
		 ventaDao.agregarVenta(venta25); 
		 
		 Venta venta26 = new Venta(new Date(),"0003-26",0,formaDePago,clienteDao.traerCliente(38678945),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Desodorante"),2);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume2"),4);
		 venta26.agregarItem(item);	 
		 venta26.agregarItem(item2);	
		 ventaDao.agregarVenta(venta26); 
		 
		 Venta venta27 = new Venta(new Date(),"0003-27",0,formaDePago,clienteDao.traerCliente(38678945),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Amoxicilina"),2);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume2"),4);
		 venta27.agregarItem(item);	 
		 venta27.agregarItem(item2);	
		 ventaDao.agregarVenta(venta27); 
		 
		 Venta venta28 = new Venta(new Date(),"0003-28",0,formaDePago,clienteDao.traerCliente(22222222),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Desodorante"),2);
		 item2= new ItemVenta(productoDao.traerProducto("Amoxicilina"),4);
		 venta28.agregarItem(item);	 
		 venta28.agregarItem(item2);	 
		 ventaDao.agregarVenta(venta28); 
		 
		 Venta venta29 = new Venta(new Date(),"0003-29",0,formaDePago,clienteDao.traerCliente(38678945),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Paracetamol"),1);
		 venta29.agregarItem(item);	   
		 ventaDao.agregarVenta(venta29); 
		 
		 Venta venta30 = new Venta(new Date(),"0003-30",0,formaDePago,clienteDao.traerCliente(22222222),atencionS3,cajaS3);	   
		 item = new ItemVenta(productoDao.traerProducto("Ibuprofeno600"),2);
		 venta30.agregarItem(item);	   
		 ventaDao.agregarVenta(venta30); 
	}

}
