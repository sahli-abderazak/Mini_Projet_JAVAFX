package application;


import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    private long idr;
    private long idv;
    private long idc;
    private Date dateDebut;
    private Date dateFin;
    private float total;
    
    // Constructeur
    public Reservation(long idr, long idv, long idc, Date dateDebut, Date dateFin, float total) {
        this.idr = idr;
        this.idv = idv;
        this.idc = idc;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.total = total;
    }
    public Reservation( Date dateDebut, Date dateFin, float total) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.total = total;
    }
    
    

	//	public Reservation(long idR2, long idV2, long idC2, java.util.Date dB, java.util.Date dF, Float total2) {
//		this.idr = idR2;
//        this.idv = idV2;
//        this.idc = idC2;
//        this.dateDebut = (Date) dB;
//        this.dateFin = (Date) dF;
//        this.total = total2;
//	}
	// Getters et Setters
    public long getIdr() {
        return idr;
    }
    
    public void setIdr(long idr) {
        this.idr = idr;
    }
    
    public long getIdv() {
        return idv;
    }
    
    public void setIdv(long idv) {
        this.idv = idv;
    }
    
    public long getIdc() {
        return idc;
    }
    
    public void setIdc(long idc) {
        this.idc = idc;
    }
    
    public Date getDateDebut() {
        return dateDebut;
    }
    
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public Date getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public float getTotal() {
        return total;
    }
    
    public void setTotal(float total) {
        this.total = total;
    }
}

