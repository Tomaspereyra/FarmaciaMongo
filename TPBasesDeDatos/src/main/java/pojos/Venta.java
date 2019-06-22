package pojos;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Venta {
	private int idVenta;
	private GregorianCalendar fecha;
	private String nroTicket;
	private BigDecimal total;
	private FormaDePago formaDePago;
	private Cliente cliente;
	private Empleado empleadoAtencion;
	private Empleado empleadoCaja;
	private List<ItemVenta> itemsVenta;
	// Total calculado
	public Venta(GregorianCalendar fecha, String nroTicket, BigDecimal total, FormaDePago formaDePago,
			Cliente cliente, Empleado empleadoAtencion, Empleado empleadoCaja, List<ItemVenta> itemsVenta) {
		
		this.fecha = fecha;
		this.nroTicket = nroTicket;
		this.total = total;
		this.formaDePago = formaDePago;
		this.cliente = cliente;
		this.empleadoAtencion = empleadoAtencion;
		this.empleadoCaja = empleadoCaja;
		this.itemsVenta = itemsVenta;
	}
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public GregorianCalendar getFecha() {
		return fecha;
	}
	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}
	public String getNroTicket() {
		return nroTicket;
	}
	public void setNroTicket(String nroTicket) {
		this.nroTicket = nroTicket;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public FormaDePago getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(FormaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Empleado getEmpleadoAtencion() {
		return empleadoAtencion;
	}
	public void setEmpleadoAtencion(Empleado empleadoAtencion) {
		this.empleadoAtencion = empleadoAtencion;
	}
	public Empleado getEmpleadoCaja() {
		return empleadoCaja;
	}
	public void setEmpleadoCaja(Empleado empleadoCaja) {
		this.empleadoCaja = empleadoCaja;
	}
	public List<ItemVenta> getItemsVenta() {
		return itemsVenta;
	}
	public void setItemsVenta(List<ItemVenta> itemsVenta) {
		this.itemsVenta = itemsVenta;
	}
	
	public DBObject objectToJson() {
		DBObject venta = new BasicDBObject("fecha",this.getFecha()).append("nroTicket",this.getNroTicket()).append("total",this.getTotal()).append(
				"formaDePago",this.getFormaDePago().objectToJson()).append("cliente",this.getCliente().objectToJson()).append("empleadoAtencion",this.getEmpleadoAtencion().objectToJson()).append(
						"empleadoCaja",this.getEmpleadoCaja().objectToJson()).append("itemsVenta",this.getItemsVenta());
		return venta;
		
	}
	
	//ublic Venta(GregorianCalendar fecha, String nroTicket, BigDecimal total, FormaDePago formaDePago,
		//	Cliente cliente, Empleado empleadoAtencion, Empleado empleadoCaja, List<ItemVenta> itemsVenta)
	
}
