package com.gxtec.api.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxtec.api.bean.BaseResult;
import com.gxtec.api.bean.User;
import com.gxtec.api.dao.UserDAO;
import com.gxtec.api.util.HttpCode;
import com.topjavatutorial.dao.JacksonFilter;

@Path("/users")
public class UserResource {

	@GET
	@Produces("application/json")
	public Response getUsers()
	{
		UserDAO dao = new UserDAO();
		List<User> users = dao.getUsers();
		
		BaseResult<List<User>> entity = new BaseResult<>();
		
		if(users == null || users.size() == 0)
		{
			entity.setCode(HttpCode.UNAUTHORIZED);
			entity.setMsg("找不到用户");
		}
		
		entity.setData(users);
		
		
		ObjectMapper mapper = new ObjectMapper();
		JacksonFilter jacksonFilter = new JacksonFilter();
	 
//	    jacksonFilter.include(Employee.class, "id","name","age");
	    jacksonFilter.filter(User.class, "id");
	    mapper.setFilterProvider(jacksonFilter);  // 
	    mapper.addMixIn(User.class, jacksonFilter.getClass()); // 
	    String result = "";
	    try {
			result = mapper.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return Response.ok(result, MediaType.APPLICATION_JSON).build();
	}
}
