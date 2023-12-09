
package ManageBean.ActividadExtraescolar;


public class ActividadExtraescolar_MB {
    
    private int idActividad_Extraescolar; 
    private String nombre;
    private String tipo;
    private int status;
    private String descripcion;

    

    public ActividadExtraescolar_MB(String nombre, String tipo, int status, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.status = status;
        this.descripcion = descripcion;
    }

    public ActividadExtraescolar_MB(int idActividadExtraescolar, String nombre, String tipo, int status, String descripcion) {
        this.idActividad_Extraescolar = idActividadExtraescolar;
        this.nombre = nombre;
        this.tipo = tipo;
        this.status = status;
        this.descripcion = descripcion;
    }

    public ActividadExtraescolar_MB() {
    }

    
    
    public int getIdActividad_Extraescolar() {
        return idActividad_Extraescolar;
    }

    public void setIdActividad_Extraescolar(int idActividadExtraescolar) {
        this.idActividad_Extraescolar = idActividadExtraescolar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
