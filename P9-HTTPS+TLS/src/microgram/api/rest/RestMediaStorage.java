package microgram.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST API of the media storage service...
 * 
 * @author smd
 *
 */
@Path(RestMediaStorage.PATH)
public interface RestMediaStorage {
	
	public static final String PATH="/media";
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	String upload( byte[] bytes);
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	byte[] download(@PathParam("id") String id);
	
	
	@DELETE
	@Path("/{id}")
	void delete(@PathParam("id") String id);
}

