package Json;

import java.util.Map;

/**
 * 
 * @author Efraim Vishnevetsky & Shon Dayan
 *
 * Implementation of JsonValue representing a Json Object using Map
 */
public class JsonObject implements JsonValue
{
	private Map<String,JsonValue> o;
	
	/**
	 * Constructor 
	 * @param o - Json object
	 */
	public JsonObject(Map<String,JsonValue> o)
	{
		this.o = o;
	}
	
	@Override
	public JsonValue get(int i) throws JsonQueryException
	{
		throw new JsonQueryException("Method get(int i) not applicable for type JsonObject");
	}

	@Override
	public JsonValue get(String s) throws JsonQueryException
	{
		if(!o.containsKey(s)) throw new JsonQueryException("Object does not contain key \""+s+"\"");
		return o.get(s);
	}
	
	/**
	 * @return Returns String representation of JsonObject
	 */
	public String toString()
	{
		StringBuilder objtext = new StringBuilder();
		objtext.setLength(0);
		objtext.append("{");
		int i = 0;
		for(String s : this.o.keySet())
		{
			i++;
			objtext.append(s+":"+this.o.get(s).toString());
			if(i < this.o.size()) objtext.append(",");
		}
		objtext.append("}");
		return objtext.toString();
	}
}
