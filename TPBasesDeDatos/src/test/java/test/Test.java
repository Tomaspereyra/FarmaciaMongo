package test;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import dao.VentaDao;
public class Test {

	public static void main(String[] args) throws UnknownHostException, ParseException {	
		Date fechaInicial = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = "2018-10-1";
		fechaInicial = ft.parse(fecha);
		Date fechaFinal = new Date();
		
		String fecha1 = "2018-12-12";
		fechaFinal = ft.parse(fecha1);
		
		System.out.println(fechaInicial);
		System.out.println(fechaFinal);
		VentaDao ventaDao= new VentaDao();
	
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
