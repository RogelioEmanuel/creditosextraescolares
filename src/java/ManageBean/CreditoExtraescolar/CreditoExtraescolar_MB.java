/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean.CreditoExtraescolar;

/**
 *
 * @author Emanuel
 */
public class CreditoExtraescolar_MB {
    
    
    private int idCredito;
    private String periodo;
    private int estado;
    private String noControl;

    public CreditoExtraescolar_MB(int idCredito, String periodo, int estado, String noControl) {
        this.idCredito = idCredito;
        this.periodo = periodo;
        this.estado = estado;
        this.noControl = noControl;
    }

    public CreditoExtraescolar_MB(String periodo, int estado, String noControl) {
        this.periodo = periodo;
        this.estado = estado;
        this.noControl = noControl;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }
    
    
    
           
    
}
