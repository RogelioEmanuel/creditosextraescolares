package ManageBean.Usuarios;


public class Usuarios_MB {
    
    private int id_Usuario;
    private String Usuario;
    private String nombre_ussuario;
    private String contrasenia; 
    private String rol;
    private boolean hay;

    public boolean isHay() {
        return hay;
    }

    public void setHay(boolean hay) {
        this.hay = hay;
    }

    
    
    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getNombre_ussuario() {
        return nombre_ussuario;
    }

    public void setNombre_ussuario(String nombre_ussuario) {
        this.nombre_ussuario = nombre_ussuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuarios_MB(int id_Usuario, String Usuario, String nombre_ussuario, String contrasenia, String rol) {
        this.id_Usuario = id_Usuario;
        this.Usuario = Usuario;
        this.nombre_ussuario = nombre_ussuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public Usuarios_MB(String Usuario, String nombre_ussuario, String contrasenia, String rol) {
        this.Usuario = Usuario;
        this.nombre_ussuario = nombre_ussuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public Usuarios_MB() {
    }
    
    
    
    
}
