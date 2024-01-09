package masterDAO;


public class Usuario {
    
    private int crear;
    private int leer;
    private int editar;
    private int eliminar;
    private String idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombrePuesto;
    private String usuario;
    private String correo;
    private String fechaNac;

    public Usuario(String idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idUsuario = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    public Usuario(String idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String nombrePuesto) {
        this.idUsuario = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombrePuesto = nombrePuesto;
    }
    public Usuario(int crear, int leer, int editar, int eliminar, String idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String nombrePuesto) {
        this.crear = crear;
        this.leer = leer;
        this.editar = editar;
        this.eliminar = eliminar;
        this.idUsuario = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombrePuesto = nombrePuesto;
    }

    public Usuario() {
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
    String datosEmpleado = "ID Empleado: " + idUsuario + "\n";
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
