package pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Venta {
	private int idVenta;
	private Date fecha;
	private String nroTicket;
	private double total;
	private FormaDePago formaDePago;
	private Cliente cliente;
	private Empleado empleadoAtencion;
	private Empleado empleadoCaja;
	private List<ItemVenta> itemsVenta;
	
	// Total calculado
	public Venta(Date fecha, String nroTicket, double total, FormaDePago formaDePago,
			Cliente cliente, Empleado empleadoAtencion, Empleado empleadoCaja) {
		
		this.fecha = fecha;
		this.nroTicket = nroTicket;
		this.total = total;
		this.formaDePago = formaDePago;
		this.cliente = cliente;
		this.empleadoAtencion = empleadoAtencion;
		this.empleadoCaja = empleadoCaja;
		this.itemsVenta = new ArrayList<ItemVenta>();
	}
	
	public int getIdVenta() {
		return idVenta;
	}
	
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getNroTicket() {
		return nroTicket;
	}
	
	public void setNroTicket(String nroTicket) {
		this.nroTicket = nroTicket;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
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
	
	public void agregarItem(ItemVenta item) {
		this.itemsVenta.add(item);
	}
	
	public DBObject objectToJson() {
		DBObject venta = new BasicDBObject("fecha",this.getFecha()).append("nroTicket",this.getNroTicket()).append("total",this.getTotal()).append(
				"formaDePago",this.getFormaDePago().objectToJson()).append("cliente",this.getCliente().objectToJson()).append("empleadoAtencion",this.getEmpleadoAtencion().objectToJson()).append(
						"empleadoCaja",this.getEmpleadoCaja().objectToJson()).append("itemsVenta",this.listToJson());
		return venta;
		
	}
	
	public List<DBObject> listToJson() {
		List<DBObject> itemsVentaJSON = new ArrayList<DBObject>();
			for(int i=0;i<this.itemsVenta.size();i++) {
				itemsVentaJSON.add(this.itemsVenta.get(i).objectToJson());
			}
		return itemsVentaJSON;
	}
	
	public List<ItemVenta> jsonToListItemVenta(BasicDBList itemsVentaJSON) {
		List<ItemVenta> itemVentaList = new ArrayList<ItemVenta>();
		
			for(int i=0;i<itemsVentaJSON.size();i++) {
				itemVentaList.add( JsonToObjectClass.jsonToItemVenta((BasicDBObject) itemsVentaJSON.get(i)));
			}
		return itemVentaList;
	}
	
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", fecha=" + fecha + ", nroTicket=" + nroTicket + ", total=" + total
				+ ", formaDePago=" + formaDePago + ", cliente=" + cliente + ", empleadoAtencion=" + empleadoAtencion
				+ ", empleadoCaja=" + empleadoCaja + ", itemsVenta=" + itemsVenta + "]";
	}
	
	
}
