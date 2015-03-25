package supton.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import supton.entity.pseudo.OrganizationTree;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONValue;


public class JsonTool {
	
	
	/**
	 * desc : 通过反射机制，将一个对象转换成json格式，
	 *        json中不包含父类属性 
	 */
	public static String objectToJsonString(Object obj){
		StringBuffer jsonString = new StringBuffer();
		jsonString.append("{");
		Class<?> demo = obj.getClass();
		String className = demo.getName();
		
		Field[] fields = demo.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			field.setAccessible(true);
			try {
				Object fieldValue = field.get(obj) == null?"":field.get(obj);
				String type = fieldValue.getClass().getName();
				
				if(!"".equals(fieldValue) && Date.class.getName().equals(fieldValue.getClass().getName())){
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					fieldValue = format.format(fieldValue); 
				}
				if(ArrayList.class.getName().equals(fieldValue.getClass().getName()))
				{
					jsonString.append(",\"" + fieldName + "\":"+listTojsonString((List<OrganizationTree>)fieldValue));
				}
				else
				{
					jsonString.append(",\"" + fieldName + "\":" + "\"" + fieldValue + "\"");
				}
				
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		jsonString.append("}");
		return jsonString.toString().replaceFirst(",", "");
	}

	
	public static String listTojsonString(List list){
		StringBuffer jsonString = new StringBuffer("[");
		boolean comma = false;
		for (Object object : list) {
			if(comma){
				jsonString.append(",");
			}
			jsonString.append(objectToJsonString(object));
			comma = true;
		}
		jsonString.append("]");
		return jsonString.toString();
	}
	
	public static List<String> jsonToListString(String json){
		List<String> list = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
		try {
			list = mapper.readValue(json, new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	public static boolean checkJsonContainsKey(String jsonString,String key){
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonString);  
		return jsonObject.containsKey(key);
	}
	
	
	public static String getObjectFromJson(String jsonString,String key){
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonString);   
		Object obj = null;
		if(jsonObject.containsKey(key)){
			obj = jsonObject.get(key);
		}
		String result = null;
		if(null != obj){
			result = obj.toString();
		}
		return result;
	}
	
	public static String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }
	public static String objectToJsonStr(Object obj,boolean format) throws MapperException{  
        JSONValue jsonValue = JSONMapper.toJSON(obj);    
	    String jsonStr = jsonValue.render(format);  
        return jsonStr;  
    }

}








