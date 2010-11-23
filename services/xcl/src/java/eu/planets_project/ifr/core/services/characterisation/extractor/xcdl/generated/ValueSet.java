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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
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
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref=&quot;{http://www.planets-project.eu/xcl/schemas/xcl}rawValue&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element ref=&quot;{http://www.planets-project.eu/xcl/schemas/xcl}labValue&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;objectRef&quot; minOccurs=&quot;0&quot;&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;&gt;
 *               &lt;pattern value=&quot;(file://.*|\.):.*&quot;/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref=&quot;{http://www.planets-project.eu/xcl/schemas/xcl}dataRef&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name=&quot;id&quot; use=&quot;required&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}ID&quot; /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "rawValue", "labValue", "objectRef",
        "dataReves" })
@XmlRootElement(name = "valueSet")
public class ValueSet {

    protected RawValue rawValue;
    protected LabValue labValue;
    protected String objectRef;
    @XmlElement(name = "dataRef")
    protected List<DataRef> dataReves;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the rawValue property.
     * @return possible object is {@link RawValue }
     */
    public RawValue getRawValue() {
        return rawValue;
    }

    /**
     * Sets the value of the rawValue property.
     * @param value allowed object is {@link RawValue }
     */
    public void setRawValue(RawValue value) {
        this.rawValue = value;
    }

    /**
     * Gets the value of the labValue property.
     * @return possible object is {@link LabValue }
     */
    public LabValue getLabValue() {
        return labValue;
    }

    /**
     * Sets the value of the labValue property.
     * @param value allowed object is {@link LabValue }
     */
    public void setLabValue(LabValue value) {
        this.labValue = value;
    }

    /**
     * Gets the value of the objectRef property.
     * @return possible object is {@link String }
     */
    public String getObjectRef() {
        return objectRef;
    }

    /**
     * Sets the value of the objectRef property.
     * @param value allowed object is {@link String }
     */
    public void setObjectRef(String value) {
        this.objectRef = value;
    }

    /**
     * Gets the value of the dataReves property.
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the dataReves property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDataReves().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link DataRef }
     */
    public List<DataRef> getDataReves() {
        if (dataReves == null) {
            dataReves = new ArrayList<DataRef>();
        }
        return this.dataReves;
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

}