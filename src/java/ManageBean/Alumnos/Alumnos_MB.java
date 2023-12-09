
package ManageBean.Alumnos;


public class Alumnos_MB {
    private int noControl;
    private String nombre;
    private int semestre;
    private int edad;
    private String correo;
    private int sexo;
    private String carrera;

    public Alumnos_MB(int noControl, String nombre, int semestre, int edad, String correo, int sexo, String carrera) {
        this.noControl = noControl;
        this.nombre = nombre;
        this.semestre = semestre;
        this.edad = edad;
        this.correo = correo;
        this.sexo = sexo;
        this.carrera = carrera;
    }

    public Alumnos_MB(String nombre, int semestre, int edad, String correo, int sexo, String carrera) {
        this.nombre = nombre;
        this.semestre = semestre;
        this.edad = edad;
        this.correo = correo;
        this.sexo = sexo;
        this.carrera = carrera;
    }

    public Alumnos_MB() {
    }

    public int getNoControl() {
        return noControl;
    }

    public void setNoControl(int noControl) {
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

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

   
    
}
