package ManageBean.RegistroHistorico;


public class RegistroHistorico_MB {
    private int idRegistroHistorico;
    private String estado;    
    private String noControl;
    private String periodo; 
    private String nomActividad;
    private int anio;
    private String tipo;

    public RegistroHistorico_MB(int idRegistroHistorico, String estado, String noControl, String periodo, String nomActividad, int anio, String tipo) {
        this.idRegistroHistorico = idRegistroHistorico;
        this.estado = estado;
        this.noControl = noControl;
        this.periodo = periodo;
        this.nomActividad = nomActividad;
        this.anio = anio;
        this.tipo=tipo;
    }

    public RegistroHistorico_MB(String estado, String noControl, String periodo, String nomActividad, int anio,String tipo) {
        this.estado = estado;
        this.noControl = noControl;
        this.periodo = periodo;
        this.nomActividad = nomActividad;
        this.anio = anio;
        this.tipo=tipo;
    }

    public RegistroHistorico_MB() {
    }

    public int getIdRegistroHistorico() {
        return idRegistroHistorico;
    }

    public void setIdRegistroHistorico(int idRegistroHistorico) {
        this.idRegistroHistorico = idRegistroHistorico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getNomActividad() {
        return nomActividad;
    }

    public void setNomActividad(String nomActividad) {
        this.nomActividad = nomActividad;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
}
