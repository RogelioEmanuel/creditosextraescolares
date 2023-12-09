
package ManageBean.Eventos;

/**
 *
 * @author Emanuel
 */
public class Evento_MB {
    private int idEvento;
    private int noParticipantes;
    private String institucionOrganizadora;
    private String periodo;
   // private Date fecha;
    private String lugar;
    private int idGrupo;

    
    
    
    
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getNoParticipantes() {
        return noParticipantes;
    }

    public void setNoParticipantes(int noParticipantes) {
        this.noParticipantes = noParticipantes;
    }

    public String getInstitucionOrganizadora() {
        return institucionOrganizadora;
    }

    public void setInstitucionOrganizadora(String institucionOrganizadora) {
        this.institucionOrganizadora = institucionOrganizadora;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    
    
    
    
}
