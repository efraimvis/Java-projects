package Json;

/**
 * 
 * @author Efraim Vishnevetsky & Shon Dayan
 * 
 * Implementation of JsonValue representing JsonString using String
 *
 */
public class JsonString implements JsonValue
{
	private String s;
	
	/**
	 * Constructor
	 * 
	 * @param s - String
	 */
	public JsonString(String s)
	{
		this.s = s;
	}

	@Override
	public JsonValue get(int i) throws JsonQueryException
	{
		throw new JsonQueryException("Method get(int i) not applicable for type JsonString");
	}

	@Override
	public JsonValue get(String s) throws JsonQueryException
	{
		throw new JsonQueryException("Method get(String s) not applicable for type JsonString");
	}
	
	/**
	 * @return Returns String representation of JsonString
	 */
	public String toString()
	{
		return this.s;
	}
	
}
