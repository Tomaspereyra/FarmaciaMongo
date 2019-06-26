package pojos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class PuntoDeVenta {
	private int idPuntoDeVenta;
	private Sucursal sucursal;
	private int nroPuntoDeVenta;
	
	public PuntoDeVenta(Sucursal sucursal, int nroPuntoDeVenta) {
		
		this.sucursal = sucursal;
		this.nroPuntoDeVenta = nroPuntoDeVenta;
	}
	
	public int getIdPuntoDeVenta() {
		return idPuntoDeVenta;
	}
	
	public void setIdPuntoDeVenta(int idPuntoDeVenta) {
		this.idPuntoDeVenta = idPuntoDeVenta;
	}
	
	public Sucursal getSucursal() {
		return sucursal;
	}
	
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public int getNroPuntoDeVenta() {
		return nroPuntoDeVenta;
	}
	
	public void setNroPuntoDeVenta(int nroPuntoDeVenta) {
		this.nroPuntoDeVenta = nroPuntoDeVenta;
	}
	
	public DBObject objectToJson() {
		DBObject puntoDeVenta = new BasicDBObject ("sucursal", this.getSucursal().objectToJson()).append("nroPuntoDeVenta", this.getNroPuntoDeVenta());
		return puntoDeVenta;
	}
	
}
