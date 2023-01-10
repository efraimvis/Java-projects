package Json;

/**
 * 
 * @author Efraim Vishnevetsky & Shon Dayan
 * 
 * Interface for JsonValue
 *
 */
public interface JsonValue
{
	/**
	 * Returns the JsonValue in index i - applicable only for JsonArray
	 * 
	 * @param i - index of JsonValue 
	 * 
	 * @return Returns JsonValue in index i
	 * 
	 * @throws JsonQueryException
	 */
	public JsonValue get(int i) throws JsonQueryException;
	
	/**
	 * Returns the JsonValue matching key 's' - applicable only for JsonObject
	 * 
	 * @param s
	 * 
	 * @return Returns the JsonValue matching key 's'
	 * 
	 * @throws JsonQueryException
	 */
	public JsonValue get(String s) throws JsonQueryException;
}