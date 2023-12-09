
package ManageBean.Grupos;

public class Grupos_MB {
    
    private int idGrupo;    
    private int noGrupo;
    private int cupo;
    private String periodo;
    private int idActividad;
    private int idMaestros;
    private String nombreMaestro;
    private int totalhorassemanales;    
    

    public Grupos_MB(int idGrupo,int noGrupo, int cupo, String periodo, int idActividad, int idMaestros, String nombreMaestro, int totalhorassemanales) {
        this.idGrupo = idGrupo;
        this.cupo = cupo;
        this.periodo = periodo;
        this.idActividad = idActividad;
        this.idMaestros = idMaestros;
        this.nombreMaestro = nombreMaestro;
        this.totalhorassemanales = totalhorassemanales;
        this.noGrupo=noGrupo;
       
    }

    public Grupos_MB(int idGrupo,int noGrupo, int cupo, String periodo, int idActividad, int idMaestros, int totalhorassemanales) {
        this.idGrupo = idGrupo;
        this.cupo = cupo;
        this.periodo = periodo;
        this.idActividad = idActividad;
        this.idMaestros = idMaestros;
        this.totalhorassemanales = totalhorassemanales;
        this.noGrupo=noGrupo;
        
    }

    public Grupos_MB(int noGrupo,int cupo, String periodo, int idActividad, int idMaestros, int totalhorassemanales) {
        this.cupo = cupo;
        this.periodo = periodo;
        this.idActividad = idActividad;
        this.idMaestros = idMaestros;
        this.totalhorassemanales = totalhorassemanales;
        this.noGrupo=noGrupo;
       
    }

   

    public Grupos_MB() {
    }

    public int getNoGrupo() {
        return noGrupo;
    }

    public void setNoGrupo(int noGrupo) {
        this.noGrupo = noGrupo;
    }
    
    

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdMaestros() {
        return idMaestros;
    }

    public void setIdMaestros(int idMaestros) {
        this.idMaestros = idMaestros;
    }

    public String getNombreMaestro() {
        return nombreMaestro;
    }

    public void setNombreMaestro(String nombreMaestro) {
        this.nombreMaestro = nombreMaestro;
    }

    public int getTotalhorassemanales() {
        return totalhorassemanales;
    }

    public void setTotalhorassemanales(int totalhorassemanales) {
        this.totalhorassemanales = totalhorassemanales;
    }


    

    
    
}
