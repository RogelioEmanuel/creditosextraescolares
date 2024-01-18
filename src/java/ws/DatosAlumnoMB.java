
package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosAlumnoMB complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosAlumnoMB"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="apellidoMaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="apellidoPaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="carrera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="codigoPostal" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="colonia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="creditosAcumulados" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mensajeError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="municipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroControl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroExterior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="promedioGeneral" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="regular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="semestre" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telefonoCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telefonoFijo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoSangre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosAlumnoMB", propOrder = {
    "apellidoMaterno",
    "apellidoPaterno",
    "calle",
    "carrera",
    "codigoError",
    "codigoPostal",
    "colonia",
    "correo",
    "creditosAcumulados",
    "estado",
    "fechaNacimiento",
    "mensajeError",
    "municipio",
    "nombre",
    "numeroControl",
    "numeroExterior",
    "promedioGeneral",
    "regular",
    "semestre",
    "sexo",
    "telefonoCelular",
    "telefonoFijo",
    "tipoSangre"
})
public class DatosAlumnoMB {

    protected String apellidoMaterno;
    protected String apellidoPaterno;
    protected String calle;
    protected String carrera;
    protected int codigoError;
    protected int codigoPostal;
    protected String colonia;
    protected String correo;
    protected int creditosAcumulados;
    protected String estado;
    protected String fechaNacimiento;
    protected String mensajeError;
    protected String municipio;
    protected String nombre;
    protected String numeroControl;
    protected String numeroExterior;
    protected double promedioGeneral;
    protected String regular;
    protected int semestre;
    protected String sexo;
    protected String telefonoCelular;
    protected String telefonoFijo;
    protected String tipoSangre;

    /**
     * Obtiene el valor de la propiedad apellidoMaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Define el valor de la propiedad apellidoMaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoMaterno(String value) {
        this.apellidoMaterno = value;
    }

    /**
     * Obtiene el valor de la propiedad apellidoPaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Define el valor de la propiedad apellidoPaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoPaterno(String value) {
        this.apellidoPaterno = value;
    }

    /**
     * Obtiene el valor de la propiedad calle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Define el valor de la propiedad calle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Obtiene el valor de la propiedad carrera.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * Define el valor de la propiedad carrera.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrera(String value) {
        this.carrera = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoError.
     * 
     */
    public int getCodigoError() {
        return codigoError;
    }

    /**
     * Define el valor de la propiedad codigoError.
     * 
     */
    public void setCodigoError(int value) {
        this.codigoError = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoPostal.
     * 
     */
    public int getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Define el valor de la propiedad codigoPostal.
     * 
     */
    public void setCodigoPostal(int value) {
        this.codigoPostal = value;
    }

    /**
     * Obtiene el valor de la propiedad colonia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Define el valor de la propiedad colonia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColonia(String value) {
        this.colonia = value;
    }

    /**
     * Obtiene el valor de la propiedad correo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Define el valor de la propiedad correo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreo(String value) {
        this.correo = value;
    }

    /**
     * Obtiene el valor de la propiedad creditosAcumulados.
     * 
     */
    public int getCreditosAcumulados() {
        return creditosAcumulados;
    }

    /**
     * Define el valor de la propiedad creditosAcumulados.
     * 
     */
    public void setCreditosAcumulados(int value) {
        this.creditosAcumulados = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaNacimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Define el valor de la propiedad fechaNacimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimiento(String value) {
        this.fechaNacimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajeError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * Define el valor de la propiedad mensajeError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeError(String value) {
        this.mensajeError = value;
    }

    /**
     * Obtiene el valor de la propiedad municipio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Define el valor de la propiedad municipio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipio(String value) {
        this.municipio = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroControl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroControl() {
        return numeroControl;
    }

    /**
     * Define el valor de la propiedad numeroControl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroControl(String value) {
        this.numeroControl = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroExterior.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroExterior() {
        return numeroExterior;
    }

    /**
     * Define el valor de la propiedad numeroExterior.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroExterior(String value) {
        this.numeroExterior = value;
    }

    /**
     * Obtiene el valor de la propiedad promedioGeneral.
     * 
     */
    public double getPromedioGeneral() {
        return promedioGeneral;
    }

    /**
     * Define el valor de la propiedad promedioGeneral.
     * 
     */
    public void setPromedioGeneral(double value) {
        this.promedioGeneral = value;
    }

    /**
     * Obtiene el valor de la propiedad regular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegular() {
        return regular;
    }

    /**
     * Define el valor de la propiedad regular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegular(String value) {
        this.regular = value;
    }

    /**
     * Obtiene el valor de la propiedad semestre.
     * 
     */
    public int getSemestre() {
        return semestre;
    }

    /**
     * Define el valor de la propiedad semestre.
     * 
     */
    public void setSemestre(int value) {
        this.semestre = value;
    }

    /**
     * Obtiene el valor de la propiedad sexo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Define el valor de la propiedad sexo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Obtiene el valor de la propiedad telefonoCelular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    /**
     * Define el valor de la propiedad telefonoCelular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoCelular(String value) {
        this.telefonoCelular = value;
    }

    /**
     * Obtiene el valor de la propiedad telefonoFijo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * Define el valor de la propiedad telefonoFijo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoFijo(String value) {
        this.telefonoFijo = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoSangre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSangre() {
        return tipoSangre;
    }

    /**
     * Define el valor de la propiedad tipoSangre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSangre(String value) {
        this.tipoSangre = value;
    }

}
