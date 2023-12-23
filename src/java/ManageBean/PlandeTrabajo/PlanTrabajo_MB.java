
package ManageBean.PlandeTrabajo;


public class PlanTrabajo_MB {
    
    private int idPlan;
    private int actividadExtraescolar;
    private int maestro;

    public PlanTrabajo_MB(int idPlan, int actividadExtraescolar, int maestro) {
        this.idPlan = idPlan;
        this.actividadExtraescolar = actividadExtraescolar;
        this.maestro = maestro;
    }

    public PlanTrabajo_MB(int actividadExtraescolar, int maestro) {
        this.actividadExtraescolar = actividadExtraescolar;
        this.maestro = maestro;
    }
    
    

    public PlanTrabajo_MB() {
    }

    
    
    
    
    
    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getActividadExtraescolar() {
        return actividadExtraescolar;
    }

    public void setActividadExtraescolar(int actividadExtraescolar) {
        this.actividadExtraescolar = actividadExtraescolar;
    }

    public int getMaestro() {
        return maestro;
    }

    public void setMaestro(int maestro) {
        this.maestro = maestro;
    }
    
    
    
    
}
