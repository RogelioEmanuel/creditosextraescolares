/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterDAO;

/**
 *
 * @author Emanuel
 */
public class Empleado {
    private int crear;
    private int leer;
    private int editar;
    private int eliminar;
    private int idEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombrePuesto;
    private String usuario;
    private String correo;
    private String fechaNac;

    public Empleado(int idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    public Empleado(int idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String nombrePuesto) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombrePuesto = nombrePuesto;
    }
    public Empleado(int crear, int leer, int editar, int eliminar, int idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String nombrePuesto) {
        this.crear = crear;
        this.leer = leer;
        this.editar = editar;
        this.eliminar = eliminar;
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombrePuesto = nombrePuesto;
    }

    public Empleado() {
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
    

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
        
    public int getCrear() {
        return crear;
    }
    
    public void setCrear(int crear) {
        this.crear = crear;
    }
    
    public int getLeer() {
        return leer;
    }
    
    public void setLeer(int leer) {
        this.leer = leer;
    }
    
    public int getEditar() {
        return editar;
    }
    
    public void setEditar(int editar) {
        this.editar = editar;
    }
    
    public int getEliminar() {
        return eliminar;
    }
    
    public void setEliminar(int eliminar) {
        this.eliminar = eliminar;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }
    
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    public String getNombrePuesto() {
        return nombrePuesto;
    }
    
    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }
    public String desplegar() {
    String datosEmpleado = "ID Empleado: " + idEmpleado + "\n";
    datosEmpleado += "Nombre: " + nombre + "\n";
    datosEmpleado += "Apellido Paterno: " + apellidoPaterno + "\n";
    datosEmpleado += "Apellido Materno: " + apellidoMaterno + "\n";
    datosEmpleado += "Nombre Puesto: " + nombrePuesto + "\n";
    datosEmpleado += "Permiso de Crear: " + crear + "\n";
    datosEmpleado += "Permiso de Leer: " + leer + "\n";
    datosEmpleado += "Permiso de Editar: " + editar + "\n";
    datosEmpleado += "Permiso de Eliminar: " + eliminar + "\n";

    return datosEmpleado;
}
    
}
