package ManageBean.Periodo;

import java.util.Date;


public class Periodo {
    
    private int id;
    private int insscripcion;
    private int cierre;
    private String periodo;
    private int anio;
    private Date fecha_inicio;
    private Date fecha_fin;

    public Periodo(int id, int insscripcion, int cierre, String periodo, int anio, Date fecha_inicio, Date fecha_fin) {
        this.id = id;
        this.insscripcion = insscripcion;
        this.cierre = cierre;
        this.periodo = periodo;
        this.anio = anio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public Periodo() {
    }

    public Periodo(int insscripcion, int cierre, String periodo, int anio, Date fecha_inicio, Date fecha_fin) {
        this.insscripcion = insscripcion;
        this.cierre = cierre;
        this.periodo = periodo;
        this.anio = anio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInsscripcion() {
        return insscripcion;
    }

    public void setInsscripcion(int insscripcion) {
        this.insscripcion = insscripcion;
    }

    public int getCierre() {
        return cierre;
    }

    public void setCierre(int cierre) {
        this.cierre = cierre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    
    
    
}
