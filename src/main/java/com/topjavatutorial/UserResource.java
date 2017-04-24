package com.topjavatutorial;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topjavatutorial.dao.JacksonFilter;
import com.topjavatutorial.dao.StringUtil;

@Path("/user")
public class UserResource {
	
	
	@POST
	@Path("/login")
	@Consumes("application/x-www-form-urlencoded")
	public Response login(@FormParam("username") String username, @FormParam("password") String password)
	{
		BaseEntity<User> entity = new BaseEntity<>();
		if(StringUtil.isEmpty(username) || StringUtil.isEmpty(password))
		{
			entity.setCode("201");
			entity.setMsg("用户名或密码不正确");
			
			return Response.ok(entity, MediaType.APPLICATION_JSON).build();
		}
		
		
		entity.setCode("200");
		entity.setMsg("OK");
		User user = new User();
		user.setUsername("Tome");
		user.setPassword("admin");
		user.setNickName("tomcat");
		entity.setData(user);
		
		
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
