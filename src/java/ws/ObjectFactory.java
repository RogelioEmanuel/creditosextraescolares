
package ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetInfo_QNAME = new QName("http://ws/", "getInfo");
    private final static QName _GetInfoResponse_QNAME = new QName("http://ws/", "getInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetInfo }
     * 
     */
    public GetInfo createGetInfo() {
        return new GetInfo();
    }

    /**
     * Create an instance of {@link GetInfoResponse }
     * 
     */
    public GetInfoResponse createGetInfoResponse() {
        return new GetInfoResponse();
    }

    /**
     * Create an instance of {@link DatosAlumnoMB }
     * 
     */
    public DatosAlumnoMB createDatosAlumnoMB() {
        return new DatosAlumnoMB();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getInfo")
    public JAXBElement<GetInfo> createGetInfo(GetInfo value) {
        return new JAXBElement<GetInfo>(_GetInfo_QNAME, GetInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInfoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getInfoResponse")
    public JAXBElement<GetInfoResponse> createGetInfoResponse(GetInfoResponse value) {
        return new JAXBElement<GetInfoResponse>(_GetInfoResponse_QNAME, GetInfoResponse.class, null, value);
    }

}
