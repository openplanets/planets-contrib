package eu.planets_project.ifr.core.services.characterisation.extractor.xcdl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.planets_project.ifr.core.services.characterisation.extractor.impl.XcdlCharacterise;
import eu.planets_project.ifr.core.techreg.formats.FormatRegistryFactory;
import eu.planets_project.services.characterise.CharacteriseResult;
import eu.planets_project.services.datatypes.Content;
import eu.planets_project.services.datatypes.DigitalObject;
import eu.planets_project.services.datatypes.Property;

/**
 * Tests for the XcdlAccess and implementations.
 * @author Fabian Steeg (fabian.steeg@uni-koeln.de)
 */
public class XcdlAccessTests {
    private static final String XCDL = "PP/xcl/src/java/eu/planets_project"
            + "/ifr/core/services/characterisation/extractor/xcdl/xcdl.xml";
    private static final String PNG = "PP/xcl/src/resources/basi0g08.png";

    @Test
    public void testPropertiesImage() throws FileNotFoundException {
        /*
         * Test the simple implementation which only picks out the essential values from the XCDL:
         */
        check(new XcdlProperties(new FileReader(XCDL)), "Image");
    }

    @Test
    public void testParserImage() throws FileNotFoundException {
        /*
         * Test the full parser which parses the complete XCDL including norm data and property set:
         */
        check(new XcdlParser(new FileReader(XCDL)), "Image");
    }

    @Test
    public void testBoth() throws FileNotFoundException {
        /*
         * Both implementations should not return identical results (see comments above):
         */
        CharacteriseResult p1 = new XcdlProperties(new FileReader(XCDL)).getCharacteriseResult();
        CharacteriseResult p2 = new XcdlParser(new FileReader(XCDL)).getCharacteriseResult();
        Assert.assertNotSame(p1, p2);
    }

    @Test
    public void testListPropertiesMatchExtractedProperties() {
        XcdlCharacterise characterise = new XcdlCharacterise();
        /* Check what we can expect for PNG: */
        URI uri = FormatRegistryFactory.getFormatRegistry().getUrisForExtension("png").iterator()
                .next();
        List<Property> extractable = characterise.listProperties(uri);
        /* Now we actually extract a PNG: */
        CharacteriseResult result = characterise.characterise(new DigitalObject.Builder(Content
                .byValue(new File(PNG))).format(uri).build(), null);
        List<Property> extracted = result.getProperties();
        /* And check if the IDs correspond: */
        assertAllExtractedPropsAreListedAsExtractable(extractable, extracted);
    }

    private void assertAllExtractedPropsAreListedAsExtractable(List<Property> extractable,
            List<Property> extracted) {
        extracted = XcdlProperties.realProperties(extracted);
        for (Property property : extracted) {
            boolean found = false;
            for (Property fileFormatProperty : extractable) {
                if (property.getUri().equals(fileFormatProperty.getUri())) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(
                    "List of supposedly extractable properties should contain extracted property: "
                            + property, found);
        }
    }

    /**
     * @param xcdlAccess The access to check
     */
    private void check(final XcdlAccess xcdlAccess, String normDataType) {
        List<Property> properties = xcdlAccess.getCharacteriseResult().getProperties();
        Assert.assertTrue("No properties extracted by " + xcdlAccess.getClass().getSimpleName(),
                properties.size() > 0);
        testNormDataPropertyName(xcdlAccess, normDataType);
        for (Property prop : properties) {
            Assert.assertTrue("Properties should not have empty values", prop.getValue().trim()
                    .length() > 0);
            Assert.assertTrue("Properties should not have empty names", prop.getName().trim()
                    .length() > 0);
        }
    }

    private void testNormDataPropertyName(XcdlAccess access, String normDataType) {
        List<Property> properties = access.getCharacteriseResult().getProperties();
        for (Property property : properties) {
            String uri = property.getUri().toString();
            if (uri.contains("normData")) {
                Assert.assertEquals(
                        "Norm data property URI should match the ID used in the ontology;",
                        "http://planetarium.hki.uni-koeln.de/public/XCL/ontology/XCLOntology.owl#normData"
                                + normDataType, uri);
            }
        }
    }
}
