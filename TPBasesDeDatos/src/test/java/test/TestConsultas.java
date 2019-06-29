package test;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import dao.VentaDao;
public class TestConsultas {

	public static void main(String[] args) throws UnknownHostException, ParseException {	
		Date fechaInicial = new Date();
		Date fechaFinal = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String fechaI = "2019-4-1";
		String fechaF = "2019-6-1";
		fechaInicial = ft.parse(fechaI);	
		fechaFinal = ft.parse(fechaF);		
		VentaDao ventaDao= new VentaDao();
	
		//Detalle de ventas por fechas (Toda la cadena)
		System.out.println("\n\n1-Detalle de ventas por fechas (Toda la cadena)\n");
		System.out.println(ventaDao.traerVentas(fechaInicial, fechaFinal));
		System.out.println(ventaDao.traerTotal(ventaDao.traerVentas(fechaInicial, fechaFinal)));
		//Detalle de ventas por fechas y por sucursal
		System.out.println("\n\n1-Detalle de ventas por fechas y por sucursal\n");
		System.out.println(ventaDao.traerVentas("0001",fechaInicial, fechaFinal));
		
		System.out.println(ventaDao.traerTotal(ventaDao.traerVentas("0001",fechaInicial, fechaFinal)));
		//2-
		// Detalle de ventas para la cadena completa,entre fechas y por obra Social
		System.out.println("\n\n2-Detalle de ventas para la cadena completa,entre fechas y por obra Social\n");
		System.out.println(ventaDao.traerVentas(fechaInicial, fechaFinal, true, "SwissMedical"));
		// Detalle de ventas por sucursal,entre fechas y por obra social
		System.out.println(ventaDao.traerVentas("0001",fechaInicial, fechaFinal, false,"PRIVADO"));
		//3-
		// Detalle de ventas para la cadena completa por medios de pago diferentes
		System.out.println("\n\n3-Detalle de ventas para la cadena completa por medios de pago diferentes\n");
		System.out.println(ventaDao.traerVentas(fechaInicial, fechaFinal, "Efectivo"));
		// Detalle de ventas para la sucursal por medios de pago diferentes
		System.out.println(ventaDao.traerVentas("0001",fechaInicial, fechaFinal, "Efectivo"));

		//4-
		//Detalle de ventas para la cadena completa por fecha y por tipo de producto
        System.out.println("\n\n4-Detalle de ventas para la cadena completa por fecha y por tipo de producto\n");
		System.out.println(ventaDao.traerVentasTipo(fechaInicial, fechaFinal, "Perfumeria"));
		//Detalle de ventas para la cadena completa por fecha y por tipo de producto
        System.out.println(ventaDao.traerVentasTipo("0001", fechaInicial, fechaFinal, "Farmacia"));
		
        
        ////////////////////////////////////INSTANCIAS DE FECHAS PARA RANKINGS////////////////////////////////////////
        Date fechaInicialRanking = new Date();
    	Date fechaFinalRanking = new Date();
		SimpleDateFormat ftr = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String fechaInicialR = "2018-11-11 00-00-00";
		String fechaFinalR = "2019-06-27 23-59-59";
		fechaInicialRanking = ftr.parse(fechaInicialR);
		fechaFinalRanking = ftr.parse(fechaFinalR);  
       
		//5
        //Detalle de ventas por productos para toda la cadena entre fechas por monto            
        System.out.println("\n\n5-Detalle de ventas por productos para toda la cadena entre fechas por monto\n");
        ventaDao.rankingProductosPorMonto(fechaInicialRanking, fechaFinalRanking);
        //Detalle de ventas por productos por sucursal entre fechas por monto
        System.out.println("\n\nDetalle de ventas por productos por sucursal entre fechas por monto\n");
	    ventaDao.rankingProductosPorMontoPorSucursal("0003", fechaInicialRanking, fechaFinalRanking);
        
        //6
        //Detalle de ventas por productos para toda la cadena  entre fechas por cantidad
        System.out.println("\n\n6-Detalle de ventas por productos para toda la cadena  entre fechas por cantidad\n");
        ventaDao.rankingProductosPorCantidadVendida(fechaInicialRanking, fechaFinalRanking);
        //Detalle de ventas por productos por sucursal  entre fechas por cantidad
        System.out.println("\n\nDetalle de ventas por productos por sucursal  entre fechas por cantidad\n");
        ventaDao.rankingProductosPorCantidadVendidaPorSucursal("0003", fechaInicialRanking, fechaFinalRanking);
        
        //7
        //Detalle de compras de clientes para toda la cadena  entre fechas por monto
        System.out.println("\n\n7-Detalle de compras de clientes para toda la cadena  entre fechas por monto\n");
        ventaDao.rankingClientesPorMonto(fechaInicialRanking, fechaFinalRanking);
        //Detalle de compras de clientes por sucursal  entre fechas por monto
        System.out.println("\n\nDetalle de compras de clientes por sucursal  entre fechas por monto\n");     
        ventaDao.rankingClientesPorMontoPorSucursal("0003", fechaInicialRanking, fechaFinalRanking);
     
     
        //8
        //Detalle de compras de clientes para toda la cadena  entre fechas por cantidad vendida
        System.out.println("\n\n8-Detalle de compras de clientes para toda la cadena  entre fechas por cantidad vendida\n");
        ventaDao.rankingClientesPorCantidadVendida(fechaInicialRanking, fechaFinalRanking);
        //Detalle de compras de clientes por sucursal  entre fechas por cantidad vendida
        System.out.println("\n\nDetalle de compras de clientes por sucursal  entre fechas por cantidad vendida\n");     
        ventaDao.rankingClientesPorCantidadVendidaPorSucursal("0003", fechaInicialRanking, fechaFinalRanking);
     
     
	}

}
