package ManageBean.RegistroHistorico;


public class RegistroHistorico_MB {
    private int idRegistroHistorico;
    private int estado;
    private int idCredito;
    private int noControl;

    public RegistroHistorico_MB(int idRegistroHistorico, int estado, int idCredito, int noControl) {
        this.idRegistroHistorico = idRegistroHistorico;
        this.estado = estado;
        this.idCredito = idCredito;
        this.noControl = noControl;
    }

    public RegistroHistorico_MB(int estado, int idCredito, int noControl) {
        this.estado = estado;
        this.idCredito = idCredito;
        this.noControl = noControl;
    }

    public int getIdRegistroHistorico() {
        return idRegistroHistorico;
    }

    public void setIdRegistroHistorico(int idRegistroHistorico) {
        this.idRegistroHistorico = idRegistroHistorico;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public int getNoControl() {
        return noControl;
    }

    public void setNoControl(int noControl) {
        this.noControl = noControl;
    }
    
    
    
}
