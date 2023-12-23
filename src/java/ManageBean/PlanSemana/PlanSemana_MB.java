package ManageBean.PlanSemana;


public class PlanSemana_MB {
    
    public int idRegistro;
    public int id_plan;
    public int noSemana;
    public String programa;
    public String plataforma;
    public String llevar_a_cabo;

    public PlanSemana_MB(int idRegistro, int id_plan, int noSemana, String programa, String plataforma, String llevar_a_cabo) {
        this.idRegistro = idRegistro;
        this.id_plan = id_plan;
        this.noSemana = noSemana;
        this.programa = programa;
        this.plataforma = plataforma;
        this.llevar_a_cabo = llevar_a_cabo;
    }

    public PlanSemana_MB(int id_plan, int noSemana, String programa, String plataforma, String llevar_a_cabo) {
        this.id_plan = id_plan;
        this.noSemana = noSemana;
        this.programa = programa;
        this.plataforma = plataforma;
        this.llevar_a_cabo = llevar_a_cabo;
    }

    public PlanSemana_MB(int noSemana, String programa, String plataforma, String llevar_a_cabo) {
        this.noSemana = noSemana;
        this.programa = programa;
        this.plataforma = plataforma;
        this.llevar_a_cabo = llevar_a_cabo;
    }
    
    

    public PlanSemana_MB() {
    }
    
        
    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public int getNoSemana() {
        return noSemana;
    }

    public void setNoSemana(int noSemana) {
        this.noSemana = noSemana;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getLlevar_a_cabo() {
        return llevar_a_cabo;
    }

    public void setLlevar_a_cabo(String llevar_a_cabo) {
        this.llevar_a_cabo = llevar_a_cabo;
    }
    
    
    
    
    
    
}
