package ManageBean.Eventos;

import java.util.Date;


public class Evento_MB {
    private int idEvento;
    private String nombreEvento;
    private int noParticipantesh;
    private int noParticipantesm;
    private String institucionOrganizadora;
    private String tipoEvento;
    private String periodo;
    private Date fecha;    
    private int anio;
    private int idActividad;
    private String nombreActividad;
    private int totalParticipantes;
    private String resultado;

    public Evento_MB(int idEvento, String nombreEvento, int noParticipantesh, int noParticipantesm, String institucionOrganizadora, String tipoEvento, String periodo, Date fecha, int anio, int idActividad,String nombreActividad, int totalParticipantes) {
        this.idEvento = idEvento;
        this.nombreEvento = nombreEvento;
        this.noParticipantesh = noParticipantesh;
        this.noParticipantesm = noParticipantesm;
        this.institucionOrganizadora = institucionOrganizadora;
        this.tipoEvento = tipoEvento;
        this.periodo = periodo;
        this.fecha = fecha;
        this.anio = anio;
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.totalParticipantes = totalParticipantes;
    }

    public Evento_MB(int idEvento, String nombreEvento, int noParticipantesh, int noParticipantesm, String institucionOrganizadora, String tipoEvento, String periodo, Date fecha, int anio, int idActividad) {
        this.idEvento = idEvento;
        this.nombreEvento = nombreEvento;
        this.noParticipantesh = noParticipantesh;
        this.noParticipantesm = noParticipantesm;
        this.institucionOrganizadora = institucionOrganizadora;
        this.tipoEvento = tipoEvento;
        this.periodo = periodo;
        this.fecha = fecha;
        this.anio = anio;
        this.idActividad = idActividad;
    }

    public Evento_MB(String nombreEvento, int noParticipantesh, int noParticipantesm, String institucionOrganizadora, String tipoEvento, String periodo, Date fecha, int anio, int idActividad) {
        this.nombreEvento = nombreEvento;
        this.noParticipantesh = noParticipantesh;
        this.noParticipantesm = noParticipantesm;
        this.institucionOrganizadora = institucionOrganizadora;
        this.tipoEvento = tipoEvento;
        this.periodo = periodo;
        this.fecha = fecha;
        this.anio = anio;
        this.idActividad = idActividad;
        
        
    }

    public Evento_MB(String nombreEvento, String institucionOrganizadora, String tipoEvento, String periodo, Date fecha, int idActividad) {
        this.nombreEvento = nombreEvento;
        this.institucionOrganizadora = institucionOrganizadora;
        this.tipoEvento = tipoEvento;
        this.periodo = periodo;
        this.fecha = fecha;
        this.idActividad = idActividad;
    }
    
    
    

    public Evento_MB() {
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public int getNoParticipantesh() {
        return noParticipantesh;
    }

    public void setNoParticipantesh(int noParticipantesh) {
        this.noParticipantesh = noParticipantesh;
    }

    public int getNoParticipantesm() {
        return noParticipantesm;
    }

    public void setNoParticipantesm(int noParticipantesm) {
        this.noParticipantesm = noParticipantesm;
    }

    public String getInstitucionOrganizadora() {
        return institucionOrganizadora;
    }

    public void setInstitucionOrganizadora(String institucionOrganizadora) {
        this.institucionOrganizadora = institucionOrganizadora;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public int getTotalParticipantes() {
        return totalParticipantes;
    }

    public void setTotalParticipantes() {
        this.totalParticipantes = getNoParticipantesh()+getNoParticipantesm();
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
    
    
    
    
}
