/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author Emanuel
 */
public class GenericResponse<T> {
    
    private int status;
    private String mensaje;
    private T responseObject;

    public int getStatus() {
        return status;
    }

    public void setStatus(int estado) {
        this.status = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(T objetoRespuesta) {
        this.responseObject = objetoRespuesta;
    }
}
