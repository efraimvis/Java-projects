package Json;

/**
 * 
 * @author Efraim Vishnevetsky & Shon Dayan
 * 
 * Implementation of JsonValue representing JsonNumber
 *
 */
public class JsonNumber implements JsonValue
{
	private Number k;
	
	/**
	 * Constructor
	 * 
	 * @param k - number
	 */
	public JsonNumber(Number k)
	{
		this.k = k;
	}

	@Override
	public JsonValue get(int i) throws JsonQueryException
	{
		throw new JsonQueryException("Method get(int i) not applicable for type JsonNumber");
	}

	@Override
	public JsonValue get(String s) throws JsonQueryException
	{
		throw new JsonQueryException("Method get(String s) not applicable for type JsonNumber");
	}
	
	/**
	 * @return Returns String representation of JsonNumber
	 */
	public String toString()
	{
		StringBuilder numstr = new StringBuilder();
		numstr.setLength(0);
		numstr.append(this.k);
		return numstr.toString();
	}
}
