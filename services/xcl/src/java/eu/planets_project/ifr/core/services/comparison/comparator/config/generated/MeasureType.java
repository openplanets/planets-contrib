//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-661 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.08.10 at 03:21:55 PM CEST 
//


package eu.planets_project.ifr.core.services.comparison.comparator.config.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for measureType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="measureType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="bit"/>
 *     &lt;enumeration value="twip"/>
 *     &lt;enumeration value="pixel"/>
 *     &lt;enumeration value="inch"/>
 *     &lt;enumeration value="meter"/>
 *     &lt;enumeration value="palette"/>
 *     &lt;enumeration value="point"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "measureType")
@XmlEnum
public enum MeasureType {

    @XmlEnumValue("bit")
    BIT("bit"),
    @XmlEnumValue("twip")
    TWIP("twip"),
    @XmlEnumValue("pixel")
    PIXEL("pixel"),
    @XmlEnumValue("inch")
    INCH("inch"),
    @XmlEnumValue("meter")
    METER("meter"),
    @XmlEnumValue("palette")
    PALETTE("palette"),
    @XmlEnumValue("point")
    POINT("point");
    private final String value;

    MeasureType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MeasureType fromValue(String v) {
        for (MeasureType c: MeasureType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}