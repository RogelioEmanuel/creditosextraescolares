
package ManageBean.Alumnos;


public class Alumnos_MB {
    private String noControl;
    private String nombre;
    private int semestre;
    private int edad;
    private String correo;
    private String sexo;
    private String carrera;
    private boolean regular;
    private int noReinscripcion;
    private String selectivo;
    private String acreditado;

    public Alumnos_MB(String noControl, String nombre, int semestre, int edad, String correo, String sexo, String carrera,boolean regular, int noReinscripcion) {
        this.noControl = noControl;
        this.nombre = nombre;
        this.semestre = semestre;
        this.edad = edad;
        this.correo = correo;
        this.sexo = sexo;
        this.carrera = carrera;
        this.regular = regular;
        
    }

    public Alumnos_MB(String nombre, int semestre, int edad, String correo, String sexo, String carrera, boolean regular,int noReinscripcion) {
        this.nombre = nombre;
        this.semestre = semestre;
        this.edad = edad;
        this.correo = correo;
        this.sexo = sexo;
        this.carrera = carrera;
        this.regular = regular;
    }

    public Alumnos_MB(String noControl, String nombre, int semestre, int edad, String correo, String sexo, String carrera, boolean regular) {
        this.noControl = noControl;
        this.nombre = nombre;
        this.semestre = semestre;
        this.edad = edad;
        this.correo = correo;
        this.sexo = sexo;
        this.carrera = carrera;
        this.regular = regular;
    }

    public Alumnos_MB(String nombre, int semestre, int edad, String correo, String sexo, String carrera, boolean regular) {
        this.nombre = nombre;
        this.semestre = semestre;
        this.edad = edad;
        this.correo = correo;
        this.sexo = sexo;
        this.carrera = carrera;
        this.regular = regular;
    }
    
    

    public Alumnos_MB() {
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public boolean getRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }

    public int getNoReinscripcion() {
        return noReinscripcion;
    }

    public void setNoReinscripcion(int noReinscripcion) {
        this.noReinscripcion = noReinscripcion;
    }

    public String getSelectivo() {
        return selectivo;
    }

    public void setSelectivo(String selectivo) {
        this.selectivo = selectivo;
    }

    public String isAprovado() {
        return acreditado;
    }

    public void setAprovado(String aprovado) {
        this.acreditado = aprovado;
    }
    
    

   
    
}
