
package ManageBean.HorariosGrupo;


public class HorariosGrupo_MB {
    
    private int idHorarioGrupo;
    private int idGrupo;
    private String dia;
    private String horaInicio;
    private String horaFinal;

    public HorariosGrupo_MB(int idHorarioGrupo, int idGrupo, String dia, String horaInicio, String horaFinal) {
        this.idHorarioGrupo = idHorarioGrupo;
        this.idGrupo = idGrupo;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public HorariosGrupo_MB(int idGrupo, String dia, String horaInicio, String horaFinal) {
        this.idGrupo = idGrupo;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public HorariosGrupo_MB(String dia, String horaInicio, String horaFinal) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
    
    

    public HorariosGrupo_MB() {
    }

    public int getIdHorarioGrupo() {
        return idHorarioGrupo;
    }

    public void setIdHorarioGrupo(int idHorarioGrupo) {
        this.idHorarioGrupo = idHorarioGrupo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }
    
    
    
    
}
