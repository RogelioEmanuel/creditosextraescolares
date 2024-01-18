package ManageBean.Asistencia;


public class Asistencias_MB {
    
    private int idAsistencia;
    private int noControl;
    private int idClase;
    private int dia;
    private String mes;
    private String qr;

    public Asistencias_MB(int idAsistencia, int noControl, int idClase, int dia, String mes) {
        this.idAsistencia = idAsistencia;
        this.noControl = noControl;
        this.idClase = idClase;
        this.dia = dia;
        this.mes = mes;
    }

    public Asistencias_MB(int noControl, int idClase, int dia, String mes) {
        this.noControl = noControl;
        this.idClase = idClase;
        this.dia = dia;
        this.mes = mes;
    }

    public Asistencias_MB() {
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getNoControl() {
        return noControl;
    }

    public void setNoControl(int noControl) {
        this.noControl = noControl;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
    
    
    
    
    

    
    
}
