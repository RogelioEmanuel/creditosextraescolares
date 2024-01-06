
package ManageBean.Asistencia;


public class Clases_MB {
    
    private int idClase;
    private int idGrupo;
    private int dia;
    private String mes;
    private int idAsistencia;
    
    
    
    private int idMaestro;

    public Clases_MB(int idClase, int idGrupo, int dia, String mes, int idAsistencia, int idMaestro) {
        this.idClase = idClase;
        this.idGrupo = idGrupo;
        this.dia = dia;
        this.mes = mes;
        this.idAsistencia = idAsistencia;
        this.idMaestro = idMaestro;
    }

    public Clases_MB(int idClase, int idGrupo, int dia, String mes) {
        this.idClase = idClase;
        this.idGrupo = idGrupo;
        this.dia = dia;
        this.mes = mes;
        
    }

    public Clases_MB(int idGrupo, int dia, String mes) {
        this.idGrupo = idGrupo;
        this.dia = dia;
        this.mes = mes;
        
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    
    
    
    public Clases_MB() {
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
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

    public int getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(int idMaestro) {
        this.idMaestro = idMaestro;
    }
    
    
    
}
