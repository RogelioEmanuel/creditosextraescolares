package ManageBean.Maestros;

import java.util.Date;


public class Maestros_MB {
    
    
    private int idMaestros;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String correo;
    private String telefono;
    private Date fecha_nacimiento;
    private String banco;
    private String curp;
    private String rfc;
    private String sexo;
    private String clave;
    private String direccion;
    

    public Maestros_MB(int idMaestros, String nombre, String apPaterno, String apMaterno, String correo, String telefono, Date fecha_nacimiento, String banco, String curp, String rfc, String sexo, String clave, String direccion) {
        this.idMaestros = idMaestros;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.banco = banco;
        this.curp = curp;
        this.rfc = rfc;
        this.sexo = sexo;
        this.clave = clave;
        this.direccion=direccion;
        
    }

    public Maestros_MB(String nombre, String apPaterno, String apMaterno, String correo, String telefono, Date fecha_nacimiento, String banco, String curp, String rfc, String sexo, String clave, String direccion) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.banco = banco;
        this.curp = curp;
        this.rfc = rfc;
        this.sexo = sexo;
        this.clave = clave;
        this.direccion=direccion;
        
    }
            
    
    
    
    public Maestros_MB() {
    }

    public int getIdMaestros() {
        return idMaestros;
    }

    public void setIdMaestros(int idMaestros) {
        this.idMaestros = idMaestros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    
   

   
    
    
   
    
    
    
}
