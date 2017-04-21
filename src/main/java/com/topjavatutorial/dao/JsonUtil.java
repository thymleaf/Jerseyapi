package com.topjavatutorial.dao;
//
//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.json.JsonObjectBuilder;
//
//import com.topjavatutorial.BaseEntity;
//
public class JsonUtil {
//
//	public static JsonObjectBuilder createObjectBuilder()
//	{
//		return Json.createObjectBuilder();
//	}
//	
//	public static <T>  JsonObject response(BaseEntity<T> entity, JsonFilter filter)
//	{
//		JsonObjectBuilder builder = createObjectBuilder();
//		builder.add(BaseEntity.CODE, entity.getCode());
//		builder.add(BaseEntity.MSG, entity.getMsg());
//		
//		
//		builder.add(BaseEntity.DATA, filter.onFilter(entity));
//		
//		return builder.build();
//	}
//	
//	
//	
////	static interface JsonFilter
////	{
////		<T> JsonValue onFilter(BaseEntity<T> entity);
////	}
}
