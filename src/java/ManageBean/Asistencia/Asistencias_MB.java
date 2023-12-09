package ManageBean.Asistencia;


public class Asistencias_MB {
    
    private int idAsistencia;
    private int noCOntrol;
    private int idGrupos;

    public Asistencias_MB(int idAsistencia, int noCOntrol, int idGrupos) {
        this.idAsistencia = idAsistencia;
        this.noCOntrol = noCOntrol;
        this.idGrupos = idGrupos;
    }

    public Asistencias_MB(int noCOntrol, int idGrupos) {
        this.noCOntrol = noCOntrol;
        this.idGrupos = idGrupos;
    }
    
    

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getNoCOntrol() {
        return noCOntrol;
    }

    public void setNoCOntrol(int noCOntrol) {
        this.noCOntrol = noCOntrol;
    }

    public int getIdGrupos() {
        return idGrupos;
    }

    public void setIdGrupos(int idGrupos) {
        this.idGrupos = idGrupos;
    }
    
    
    
}
