package pojos;

import java.time.ZoneId;

import com.mongodb.BasicDBObject;

public class JsonToObjectClass {

	public static Laboratorio jsonToLaboratorio(BasicDBObject l ) {
		Laboratorio laboratorio = new Laboratorio(l.getString("nombre"));
		return laboratorio;		
	}
	
	public static Producto jsonToProducto(BasicDBObject producto) {
		Laboratorio l= jsonToLaboratorio((BasicDBObject) producto.get("laboratorio"));
		Producto p = new Producto(producto.getString("nombre"),producto.getString("tipo"),producto.getString("descripcion"),l,producto.getInt("codigo"),producto.getDouble("precio"));
		return p;
	}
	
	public static Cliente jsonToCliente(BasicDBObject cliente) {
		Cliente c=null;
		if(cliente.get("obraSocial")==null) {
			Domicilio d = jsonToDomicilio((BasicDBObject) cliente.get("domicilio"));
		    c = new Cliente(cliente.getString("apellido"),cliente.getString("nombre"),cliente.getInt("dni"),d);	
		}else {
			c= jsonToClienteConObraSocial(cliente);
		}	
		return c;		
	}
	
	public static ObraSocial jsonToObraSocial(BasicDBObject obraSocial) {
		ObraSocial o= new ObraSocial(obraSocial.getString("nombre"));
	    return o;
	}
	
	
	public static Cliente jsonToClienteConObraSocial(BasicDBObject cliente) {
		Domicilio d = jsonToDomicilio((BasicDBObject) cliente.get("domicilio"));
		ObraSocial o= jsonToObraSocial((BasicDBObject) cliente.get("obraSocial"));
		Cliente c = new Cliente(cliente.getString("apellido"),cliente.getString("nombre"),cliente.getInt("dni"),d,o);	
		return c;
		
	}
	
	public static Empleado jsonToEmpleado(BasicDBObject empleado) {
		Domicilio d = jsonToDomicilio((BasicDBObject) empleado.get("domicilio"));
		Empleado e = new Empleado(empleado.getString("apellido"),empleado.getString("nombre"),empleado.getInt("dni"),d,empleado.getString("cuil"));	
		return e;
		
	}
	
	public static Persona jsonToPersona(BasicDBObject persona) {
		Domicilio d = jsonToDomicilio((BasicDBObject) persona.get("domicilio"));
		Persona p = new Persona(persona.getString("apellido"),persona.getString("nombre"),persona.getInt("dni"),d);	
		return p;
		
	}
	
	
	public static Domicilio jsonToDomicilio(BasicDBObject domicilio) {
		Localidad l = jsonToLocalidad((BasicDBObject) domicilio.get("localidad")); 
		Provincia p = jsonToProvincia((BasicDBObject) domicilio.get("provincia"));
		Domicilio d = new Domicilio(domicilio.getString("calle"),domicilio.getInt("numero"),l,p);	
		return d;
		
	}
	
	public static Localidad jsonToLocalidad(BasicDBObject localidad) {
		Localidad l = new Localidad(localidad.getString("nombre"));	
		return l;	
	}
	
	public static Provincia jsonToProvincia(BasicDBObject provincia) {
		Provincia p = new Provincia(provincia.getString("nombre"));
		return p;
	}
	
	public static ItemVenta jsonToItemVenta(BasicDBObject itemVentas) {
		Producto p = jsonToProducto((BasicDBObject) itemVentas.get("producto"));
		ItemVenta i = new ItemVenta(p,itemVentas.getInt("cantidad"));	
		return i;
		
	}
	
	public static FormaDePago jsonToFormaDePago(BasicDBObject formaDePago) {
		FormaDePago f= new FormaDePago(formaDePago.getString("nombre"));
		return f;
	}
	
	public static PuntoDeVenta jsonToPuntoDeVenta(BasicDBObject puntoDeVenta) {
		Sucursal s=jsonToSucursal((BasicDBObject) puntoDeVenta.get("sucursal"));
		PuntoDeVenta p= new PuntoDeVenta(s,puntoDeVenta.getInt("nroPuntoDeVenta"));
		return p;
	}
	
	public static Sucursal jsonToSucursal(BasicDBObject sucursal) {
		Domicilio d= jsonToDomicilio((BasicDBObject) sucursal.get("domicilio"));
		Empleado e= jsonToEmpleado((BasicDBObject) sucursal.get("empleado"));
		Sucursal s= new Sucursal(d,e,sucursal.getString("nombre"));
		//ACA HABRIA QUE VER COMO SETEARLE LA LISTA DESDE EL JSON Y DEVOLVERLA JUNTO CON LA SUCURSAL//
		return s;
	}
	
	public static Venta jsonToVenta(BasicDBObject venta) {
		FormaDePago f= jsonToFormaDePago((BasicDBObject) venta.get("formaDePago"));
		Cliente c=	jsonToClienteConObraSocial((BasicDBObject) venta.get("cliente"));
		Empleado e1= jsonToEmpleado((BasicDBObject) venta.get("empleadoAtencion"));
		Empleado e2= jsonToEmpleado((BasicDBObject) venta.get("empleadoCaja"));
		Venta v= new Venta(venta.getDate("fecha").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), venta.getString("nroTicket"), venta.getDouble("total"), f, c, e1, e2);
		//ACA HABRIA QUE VER COMO SETEARLE LA LISTA DESDE EL JSON Y DEVOLVERLA JUNTO CON LA VENTA//
		return v;
	}
	
}
