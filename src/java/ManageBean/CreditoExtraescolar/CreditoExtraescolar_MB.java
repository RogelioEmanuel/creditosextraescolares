
package ManageBean.CreditoExtraescolar;


public class CreditoExtraescolar_MB {
    
    
    private int idCredito;
    private String periodo;
    private String estado;
    private String tipo;
    private String noControl;
    private int anio;
    private String nombreActividad;
    private int noGrupo;
    private String nombreAlumno;

    public CreditoExtraescolar_MB(int idCredito, String periodo, String estado, String tipo, String noControl, int anio, String nombreActividad, int idGrupo, String nombreAlumno) {
        this.idCredito = idCredito;
        this.periodo = periodo;
        this.estado = estado;
        this.tipo = tipo;
        this.noControl = noControl;
        this.anio = anio;
        this.nombreActividad = nombreActividad;
        this.noGrupo = idGrupo;
        this.nombreAlumno = nombreAlumno;
    }

    public CreditoExtraescolar_MB(String periodo, String estado, String tipo, String noControl, int anio, String nombreActividad, int idGrupo, String nombreAlumno) {
        this.periodo = periodo;
        this.estado = estado;
        this.tipo = tipo;
        this.noControl = noControl;
        this.anio = anio;
        this.nombreActividad = nombreActividad;
        this.noGrupo = idGrupo;
        this.nombreAlumno = nombreAlumno;
    }

    public CreditoExtraescolar_MB() {
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public int getIdGrupo() {
        return noGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.noGrupo = idGrupo;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }
    
    
}
