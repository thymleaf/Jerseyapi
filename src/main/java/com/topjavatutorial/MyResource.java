package com.topjavatutorial;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.topjavatutorial.dao.Employee;
import com.topjavatutorial.dao.EmployeeDAO;

@Path("/employees")
public class MyResource {

	@GET
	@Path("/retrieve/{uuid}")
	public Response retrieveSomething(@PathParam("uuid") String uuid) {
		if (uuid == null || uuid.trim().length() == 0) {
			return Response.serverError().entity("UUID cannot be blank").build();
		}
		// Entity entity = ...
		if ("entity".equals("")) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + uuid).build();
		}
		String json = "{\"entity\":\"entity\"}";// convert entity to json
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/test")
	public String sayHtmlHello() {
		return "hello ~~~";
	}

	@GET
	@Produces("application/json")
	public List<Employee> getEmployee() {
		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> employees = dao.getEmployees();
		for (Employee employee : employees) {
			System.out.println("employees: " + employee.getName() + " " + employee.getAge());
		}
		return employees;
	}

	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response addEmployee(Employee emp) {
		emp.setName(emp.getName());
		emp.setAge(emp.getAge());

		EmployeeDAO dao = new EmployeeDAO();
		dao.addEmployee(emp);

		return Response.ok().build();
	}

	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	public Response updateEmployee(@PathParam("id") int id, Employee emp) {
		EmployeeDAO dao = new EmployeeDAO();
		int count = dao.updateEmployee(id, emp);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes("application/json")
	public Response deleteEmployee(@PathParam("id") int id) {
		EmployeeDAO dao = new EmployeeDAO();
		int count = dao.deleteEmployee(id);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
}