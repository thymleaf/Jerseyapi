package com.gxtec.api.resource;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxtec.api.bean.BaseResult;
import com.gxtec.api.bean.Message;
import com.gxtec.api.dao.MsgDAO;
import com.gxtec.api.util.HttpCode;
import com.topjavatutorial.dao.JacksonFilter;

@Path("/msg")
public class MessageResourece {

	
	@Path("/list")
	@GET
//	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessage(@DefaultValue("1") @QueryParam("type") int type)
	{
		BaseResult<List<Message>> entity = new BaseResult<>();
		MsgDAO dao = new MsgDAO();
		List<Message> messages = dao.getMessages(type);
		
		if(messages == null || messages.size() == 0)
		{
			entity.setCode(HttpCode.NO_CONTENT);
			entity.setMsg("NO CONTENT");
			return Response.ok(entity, MediaType.APPLICATION_JSON).build();
		}
		
		entity.setCode(HttpCode.OK);
		entity.setMsg("OK");
		entity.setData(messages);
		
		ObjectMapper mapper = new ObjectMapper();
		JacksonFilter jacksonFilter = new JacksonFilter();
	 
//	    jacksonFilter.include(Employee.class, "id","name","age");
	    jacksonFilter.filter(Message.class, "id");
	    mapper.setFilterProvider(jacksonFilter);  // 
	    mapper.addMixIn(Message.class, jacksonFilter.getClass()); // 
	    String result = "";
	    try {
			result = mapper.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return Response.ok(result, MediaType.APPLICATION_JSON).build();
	}
	
	
	@Path("/test")
	@GET
	public Response test()
	{
		BaseResult<?> entity = new BaseResult<>();
		entity.setCode(200);
		entity.setMsg("ok");
		return Response.ok(entity, MediaType.APPLICATION_JSON).build();
	}
}
