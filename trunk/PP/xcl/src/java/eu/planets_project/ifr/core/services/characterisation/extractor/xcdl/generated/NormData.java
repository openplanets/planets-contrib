//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, vhudson-jaxb-ri-2.1-661
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2009.01.16 at 04:26:00 PM CET
//

package eu.planets_project.ifr.core.services.characterisation.extractor.xcdl.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base=&quot;&lt;http://www.planets-project.eu/xcl/schemas/xcl&gt;unionType01Type&quot;&gt;
 *       &lt;attribute name=&quot;id&quot; use=&quot;required&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}ID&quot; /&gt;
 *       &lt;attribute name=&quot;type&quot; use=&quot;required&quot; type=&quot;{http://www.planets-project.eu/xcl/schemas/xcl}informType&quot; /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value" })
@XmlRootElement(name = "normData")
public class NormData {

    @XmlValue
    protected String value;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(required = true)
    protected InformType type;

    /**
     * Gets the value of the value property.
     * @return possible object is {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * @param value allowed object is {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the id property.
     * @return possible object is {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * @param value allowed object is {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * @return possible object is {@link InformType }
     */
    public InformType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * @param value allowed object is {@link InformType }
     */
    public void setType(InformType value) {
        this.type = value;
    }

}