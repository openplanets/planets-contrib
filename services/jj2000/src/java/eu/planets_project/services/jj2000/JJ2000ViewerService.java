/**
 * 
 */
package eu.planets_project.services.jj2000;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import eu.planets_project.ifr.core.storage.utils.DigitalObjectDiskCache;
import eu.planets_project.ifr.core.techreg.formats.FormatRegistryFactory;
import eu.planets_project.services.PlanetsServices;
import eu.planets_project.services.datatypes.Content;
import eu.planets_project.services.datatypes.DigitalObject;
import eu.planets_project.services.datatypes.Parameter;
import eu.planets_project.services.datatypes.ServiceDescription;
import eu.planets_project.services.datatypes.ServiceReport;
import eu.planets_project.services.datatypes.Tool;
import eu.planets_project.services.datatypes.ServiceReport.Status;
import eu.planets_project.services.datatypes.ServiceReport.Type;
import eu.planets_project.services.view.CreateView;
import eu.planets_project.services.view.CreateViewResult;
import eu.planets_project.services.view.ViewAction;
import eu.planets_project.services.view.ViewActionResult;
import eu.planets_project.services.view.ViewStatus;

/**
 * A viewer service based on the JJ2000 code.
 * 
 * Caches the digital object locally and manages viewer sessions for them.
 * 
 * @author <a href="mailto:Andrew.Jackson@bl.uk">Andy Jackson</a>
 *
 */

@WebService(name = JJ2000ViewerService.NAME, 
        serviceName = CreateView.NAME, 
        targetNamespace = PlanetsServices.NS,
        endpointInterface = "eu.planets_project.services.view.CreateView" )
        
public class JJ2000ViewerService implements CreateView {

    /** The name of the service */
    public static final String NAME="JJ2000 Viewer Service";

    /** The default context path */
    private static final String CONTEXT_PATH = "/pserv-pa-jj2000/";
    private static final URI defaultBaseUrl = URI.create("http://localhost:8080"+CONTEXT_PATH);

    /** A logger */
    private static final Logger log = Logger.getLogger(JJ2000ViewerService.class.getName());
    
    /** A reference to the web service context. */
    @Resource 
    WebServiceContext wsc;

    /* (non-Javadoc)
     * @see eu.planets_project.services.view.CreateView#describe()
     */
    public ServiceDescription describe() {
        ServiceDescription.Builder mds = new ServiceDescription.Builder(NAME, CreateView.class.getCanonicalName());
        mds.description("A JPEG 2000 viewer service. Uses the JJ2000 reference implementation. See http://jj2000.epfl.ch/ for copyright information.");
        mds.author("Andrew Jackson <Andrew.Jackson@bl.uk>");
        mds.classname(this.getClass().getCanonicalName());
        
        // Add a link to the JJ2000 homepage as the 'tool' id.
        mds.tool( Tool.create(null, "JJ2000", null, null, "http://jj2000.epfl.ch/") );
        
        // Add links to this service:
        mds.furtherInfo( URI.create( getBaseURIFromWSContext(wsc).toString() ) );
        mds.logo( URI.create( getBaseURIFromWSContext(wsc) + "logos/jj2000_logo_150w.png") );
        
        // Add supported formats:
        mds.inputFormats( FormatRegistryFactory.getFormatRegistry().createExtensionUri("JP2") );
        
        return mds.build();
    }

    private static CreateViewResult returnWithErrorMessage(String message) {
        ServiceReport rep = new ServiceReport(Type.ERROR, Status.TOOL_ERROR, message);
        log.severe(message);
        return new CreateViewResult(null, null, rep);
    }
    
    public static URI getBaseURIFromWSContext( WebServiceContext wsc ) {
        if( wsc == null ) return null;
        // Lookup server config from message context:
        // @see https://jax-ws.dev.java.net/articles/MessageContext.html
        MessageContext mc = wsc.getMessageContext();
        if( mc == null ) return null;
        ServletRequest request = (ServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        ServletContext sc = (ServletContext) mc.get(MessageContext.SERVLET_CONTEXT);
        if( request == null || sc == null ) return null;

        // Construct a base URL;
        URI baseUrl = null;
        try {
            baseUrl = new URL( request.getScheme(), request.getServerName(), request.getServerPort(), sc.getContextPath()+"/" ).toURI();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        return baseUrl;
    }

    /* (non-Javadoc)
     * @see eu.planets_project.services.view.CreateView#createView(java.util.List, java.util.List)
     */
    public CreateViewResult createView(List<DigitalObject> digitalObjects,
            List<Parameter> parameters) {
        // Instanciate the View:
        return createViewerSession( digitalObjects, getBaseURIFromWSContext(wsc) );
    }

    /**
     * 
     * @param digitalObjects
     * @return
     */
    public static CreateViewResult createViewerSession(List<DigitalObject> digitalObjects, URI baseUrl ) {
        // Store copies of the viewable digital objects:
        for( DigitalObject dob : digitalObjects ) {
            // Can only cope if the object is 'simple':
            if( dob.getContent() == null ) {
                return returnWithErrorMessage("The Content of the DigitalObject should not be NULL.");
            }
        }
        
        String sessionID = DigitalObjectDiskCache.cacheDigitalObjects(digitalObjects);
        URL sessionURL;
        try {
            sessionURL = baseUrl.resolve("view.jsp?sid="+sessionID).toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return returnWithErrorMessage("Failed to construct session URL.");
        }
        
        // Create a service report:
        ServiceReport rep = new ServiceReport(Type.INFO, Status.SUCCESS, "OK");

        // Return the view id:
        return new CreateViewResult(sessionURL, sessionID, rep);
    }
    
    /**
     * @param jp2url
     * @return
     */
    public static CreateViewResult createViewerSession( URL jp2url ) {
        DigitalObject dob = new DigitalObject.Builder( Content.byReference(jp2url) ).build();
        List<DigitalObject> dobs = new ArrayList<DigitalObject>();
        dobs.add(dob);
        return createViewerSession(dobs, defaultBaseUrl);
    }
    
    public static CreateViewResult createViewerSessionViaService( URI testUrl ) throws MalformedURLException {
        Service service = Service.create( defaultBaseUrl.resolve("JJ2000ViewerService?wsdl").toURL(), JJ2000ViewerService.QNAME );
        CreateView jj2k = service.getPort(CreateView.class);
        
        // Test the description:
        ServiceDescription sd = jj2k.describe();
        log.info("Got: "+sd.toXmlFormatted());

        // Construct a list of DOBs covering the given URL:
        DigitalObject.Builder dob = new DigitalObject.Builder( Content.byReference( testUrl.toURL() ));
        List<DigitalObject> digitalObjects = new ArrayList<DigitalObject>();
        digitalObjects.add(dob.build());

        // Invoke the service and create the view:
        CreateViewResult cvr = jj2k.createView(digitalObjects, null );
        return cvr;
    }

    /* (non-Javadoc)
     * @see eu.planets_project.services.view.CreateView#getViewStatus(java.lang.String)
     */
    public ViewStatus getViewStatus(String sessionIdentifier) {
        // Lookup this cache:
        List<DigitalObject> cache = DigitalObjectDiskCache.recoverDigitalObjects( sessionIdentifier );
        
        // Default to 'inactive'
        ViewStatus vs = new ViewStatus( ViewStatus.Status.INACTIVE, null );
        
        // If it's active, return info:
        if( cache != null && cache.size() > 0 ) {
            vs = new ViewStatus( ViewStatus.Status.ACTIVE, null );
        }
        // TODO Override with UNKNOWN for now.
        vs = new ViewStatus( ViewStatus.Status.UNKNOWN, null );
        return vs;
    }

    /* (non-Javadoc)
     * @see eu.planets_project.services.view.CreateView#doAction(java.lang.String, java.lang.String)
     */
    public ViewActionResult doAction(String sessionIdentifier, String action) {
        if( ViewAction.SHUTDOWN.equals(action)) {
            // TODO find the session and kill the cache
        }
        return null;
    }

    
}
