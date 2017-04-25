package com.gxtec.api.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.gxtec.api.util.StringUtil;
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
		
		entity.setMsg("OK");
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
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response login(@FormParam("username") String username, @FormParam("password") String password)
	{
		
		BaseResult<User> entity = new BaseResult<>();
		if(StringUtil.isEmpty(username) || StringUtil.isEmpty(password))
		{
			entity.setCode(HttpCode.UNAUTHORIZED);
			entity.setMsg("用户名或密码不正确");
			
			return Response.ok(entity, MediaType.APPLICATION_JSON).build();
		}
		
		
		
		UserDAO dao = new UserDAO();
		User user = dao.queryByName(username, password);
		if(user == null)
		{
			entity.setCode(HttpCode.UNAUTHORIZED);
			entity.setMsg("用户名或密码不正确");
			
			return Response.ok(entity, MediaType.APPLICATION_JSON).build();
		}
		
		entity.setCode(HttpCode.OK);
		entity.setMsg("OK");
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















