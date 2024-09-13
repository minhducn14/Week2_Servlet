package bai5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/persons")
public class PersonResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{person-id}")
	public Person getPerson() {
		Person person = new Person();
		person.setName("Nguyen Van A");
		person.setAge(20);

		return person;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String handlePersonRequest(Person person) {
		return person.toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getListPerson() {

		List<Person> personList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Person person = new Person();
			person.setName("Name #" + i);
			person.setAge(ThreadLocalRandom.current().nextInt(1, 10));
			personList.add(person);
		}

		return personList;
	}
}
