package Json;

import java.util.List;

/**
 * 
 * @author Efraim Vishnevetsky & Shon Dayan
 * 
 * Implementation of JsonValue using ArrayList
 */
public class JsonArray implements JsonValue
{
	private List<JsonValue> a;
	
	/**
	 * Constructor
	 * 
	 * @param a - list of JsonValues
	 */
	public JsonArray(List<JsonValue> a)
	{
		this.a = a;
	}

	@Override
	public JsonValue get(int i) throws JsonQueryException
	{
		if(i < 0 || i >= this.a.size()) throw new JsonQueryException("Index out of bounds");
		return a.get(i);
	}

	@Override
	public JsonValue get(String s) throws JsonQueryException
	{
		throw new JsonQueryException("Method get(String s) not applicable for type JsonArray");
	}
	
	/**
	 * @return Returns String representation of JsonArray
	 */
	public String toString()
	{
		StringBuilder arrtext = new StringBuilder();
		arrtext.setLength(0);
		arrtext.append("[");
		for(int i=0;i<this.a.size();i++)
		{
			arrtext.append(this.a.get(i).toString());
			if(i < this.a.size()-1) arrtext.append(",");
		}
		arrtext.append("]");
		return arrtext.toString();
	}
	
}
