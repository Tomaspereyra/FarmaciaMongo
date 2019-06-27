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

public class TestVentasS2 {

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
		 
		 Empleado atencionS2 = new Empleado("Tomaselli","Federico", 34567890,domicilio2,"20-555555-8");
		 Empleado cajaS2 = new Empleado("Perez","Camila", 22222333,domicilio2,"20-444444-9");

		 //VENTAS
		 
	     Venta venta = new Venta(new Date(),"0002-1",0,formaDePago,clienteDao.traerCliente(22222222),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Perfume"),2);
		 item2 = new ItemVenta(productoDao.traerProducto("Perfume2"),2);
		 venta.agregarItem(item);	   
		 venta.agregarItem(item2);
		 ventaDao.agregarVenta(venta); 
		 
		 Venta venta2 = new Venta(new Date(),"0002-2",0,formaDePago2,clienteDao.traerCliente(33333333),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Perfume2"),2);
		 item2=new ItemVenta(productoDao.traerProducto("Ibuprofeno"),4);
		 venta2.agregarItem(item);	
		 venta2.agregarItem(item2);	 
		 ventaDao.agregarVenta(venta2); 
		 
		 Venta venta3 = new Venta(new Date(),"0002-3",0,formaDePago,clienteDao.traerCliente(33333333),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Ibuprofeno600"),3);
		 item3= new ItemVenta(productoDao.traerProducto("Aspirina"),2);
		 venta3.agregarItem(item);	  
		 venta3.agregarItem(item2);	 
		 venta3.agregarItem(item3);	 
		 ventaDao.agregarVenta(venta3); 
		 
		 Venta venta4 = new Venta(new Date(),"0002-4",0,formaDePago2,clienteDao.traerCliente(22222222),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Ibuprofeno600"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),3);
		 item3= new ItemVenta(productoDao.traerProducto("Aspirina"),3);
		 venta4.agregarItem(item);	
		 venta4.agregarItem(item2);	
		 venta4.agregarItem(item3);	
		 ventaDao.agregarVenta(venta4); 
		 
		 Venta venta5 = new Venta(new Date(),"0002-5",0,formaDePago,clienteDao.traerCliente(33333333),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Ibuprofeno600"),6);
		 item2= new ItemVenta(productoDao.traerProducto("Perfume"),8);
		 item3= new ItemVenta(productoDao.traerProducto("Aspirina"),3);
		 venta5.agregarItem(item);	   
		 venta5.agregarItem(item2);	
		 venta5.agregarItem(item3);	
		 ventaDao.agregarVenta(venta5); 
		 
		 Venta venta6 = new Venta(new Date(),"0002-6",0,formaDePago2,clienteDao.traerCliente(44444444),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Aspirina"),4);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),3);
		 item3= new ItemVenta(productoDao.traerProducto("Amlodipina"),3);
		 venta6.agregarItem(item);	
		 venta6.agregarItem(item2);	
		 venta6.agregarItem(item3);	
		 ventaDao.agregarVenta(venta6); 
		 
		 Venta venta7 = new Venta(new Date(),"0002-7",0,formaDePago,clienteDao.traerCliente(44444444),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),2);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),3);
		 item3= new ItemVenta(productoDao.traerProducto("Aspirina"),3);
		 venta7.agregarItem(item);
		 venta7.agregarItem(item2);	   
		 venta7.agregarItem(item3);	   
		 ventaDao.agregarVenta(venta7); 
		 
		 Venta venta8 = new Venta(new Date(),"0002-8",0,formaDePago2,clienteDao.traerCliente(22456789),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),3);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),9);
		 venta8.agregarItem(item);
		 venta8.agregarItem(item2);
		 ventaDao.agregarVenta(venta8); 
		 
		 Venta venta9 = new Venta(new Date(),"0002-9",0,formaDePago,clienteDao.traerCliente(22456789),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("SolucionFisiologica"),3);
		 item2= new ItemVenta(productoDao.traerProducto("Amoxicilina"),5);
		 venta9.agregarItem(item);
		 venta9.agregarItem(item2);	  
		 ventaDao.agregarVenta(venta9); 
		 
		 Venta venta10 = new Venta(new Date(),"0002-10",0,formaDePago2,clienteDao.traerCliente(22456789),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("SolucionFisiologica"),10);
		 item2= new ItemVenta(productoDao.traerProducto("Paracetamol"),2);
		 item3= new ItemVenta(productoDao.traerProducto("Amoxicilina"),3);
		 venta10.agregarItem(item);	 
		 venta10.agregarItem(item2);	   
		 venta10.agregarItem(item3);	   
		 ventaDao.agregarVenta(venta10); 
		 
		 Venta venta11 = new Venta(new Date(),"0002-11",0,formaDePago,clienteDao.traerCliente(33445566),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Aspirina"),2);
		 item3= new ItemVenta(productoDao.traerProducto("Amoxicilina"),3);
		 venta11.agregarItem(item);	  
		 venta11.agregarItem(item2);	   
		 venta11.agregarItem(item3);	   
		 ventaDao.agregarVenta(venta11); 
		 
		 Venta venta12 = new Venta(new Date(),"0002-12",0,formaDePago2,clienteDao.traerCliente(33445566),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 item2= new ItemVenta(productoDao.traerProducto("Desodorante"),1);
		 item3= new ItemVenta(productoDao.traerProducto("Amoxicilina"),1);
		 venta12.agregarItem(item);	  
		 venta12.agregarItem(item2);	   
		 venta12.agregarItem(item3);	 
		 ventaDao.agregarVenta(venta12); 
		 
		 Venta venta13 = new Venta(new Date(),"0002-13",0,formaDePago,clienteDao.traerCliente(12344455),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Desodorante"),11);
		 item = new ItemVenta(productoDao.traerProducto("Amoxicilina"),2);
		 venta13.agregarItem(item);	
		 venta13.agregarItem(item2);	 
		 ventaDao.agregarVenta(venta13); 
		 
		 Venta venta14 = new Venta(new Date(),"0002-14",0,formaDePago,clienteDao.traerCliente(12344455),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("Amlodipina"),1);
		 item = new ItemVenta(productoDao.traerProducto("Desodorante"),3);
		 venta14.agregarItem(item);	 
		 venta14.agregarItem(item2);	 
		 ventaDao.agregarVenta(venta14); 
		 
		 Venta venta15 = new Venta(new Date(),"0002-15",0,formaDePago2,clienteDao.traerCliente(12345678),atencionS2,cajaS2);	   
		 item = new ItemVenta(productoDao.traerProducto("SolucionFisiologica"),4);
		 venta15.agregarItem(item);	   
		 ventaDao.agregarVenta(venta15); 


	}

}
