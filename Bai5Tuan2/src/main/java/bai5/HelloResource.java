package bai5;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {
	@GET
	public String sayHello() {
		return "Hello World";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{name}")
	public String doGreeting(@PathParam("name") String name, @QueryParam("language") String language) {
		return "Hello " + name + " with language " + language;
	}

}
