//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, vhudson-jaxb-ri-2.1-661
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2009.01.16 at 04:26:00 PM CET
//

package eu.planets_project.ifr.core.services.characterisation.extractor.xcdl.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

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
 *     &lt;extension base=&quot;&lt;http://www.planets-project.eu/xcl/schemas/xcl&gt;nameType&quot;&gt;
 *       &lt;attribute name=&quot;id&quot; use=&quot;required&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; /&gt;
 *       &lt;attribute name=&quot;alias&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "values" })
@XmlRootElement(name = "name")
public class Name {

    @XmlValue
    protected List<String> values;
    @XmlAttribute(required = true)
    protected String id;
    @XmlAttribute
    protected String alias;

    /**
     * union of xcl defined namings for xcdl properties Gets the value of the
     * values property.
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the values property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getValues().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     */
    public List<String> getValues() {
        if (values == null) {
            values = new ArrayList<String>();
        }
        return this.values;
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
     * Gets the value of the alias property.
     * @return possible object is {@link String }
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * @param value allowed object is {@link String }
     */
    public void setAlias(String value) {
        this.alias = value;
    }

}
