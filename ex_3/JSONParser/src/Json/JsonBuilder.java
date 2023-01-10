package Json;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Efraim Vishnevetsky & Shon Dayan
 *
 * Implementation of JsonValue representing the JsonValue read from a file
 */
public class JsonBuilder implements JsonValue
{
	private CharScanner cs;
	private JsonValue v;
	
	/**
	 * Constructor 
	 * 
	 * @param file - input file
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws JsonSyntaxException
	 */
	public JsonBuilder(File file) throws FileNotFoundException, JsonSyntaxException
	{
		try
		{
			this.cs = new CharScanner(file);
			this.v = parseValue();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("File not found");
		}
	}
	
	/**
	 * Parses text from input file, and returns the appropriate JsonValue if successful
	 * 
	 * @return Returns JsonValue matching text in file, if text is identified as JsonValue; Returns null otherwise
	 * 
	 * @throws JsonSyntaxException
	 */
	public JsonValue parseValue() throws JsonSyntaxException
	{
		if(this.cs.peek() == '"')
		{
			try
			{
				this.v = parseString();
				return this.v;
			}
			catch (JsonSyntaxException se)
			{
				throw new JsonSyntaxException(se.getMessage());
			}
		}
		else if(this.cs.peek() == '-' || Character.isDigit(this.cs.peek()))
		{
			try
			{
				this.v = parseNumber();
				return this.v;
			}
			catch(JsonSyntaxException se)
			{
				throw new JsonSyntaxException(se.getMessage());
			}
		}
		else if(this.cs.peek() == '[')
		{
			try
			{
				this.v = parseArray();
				return this.v;
			}
			catch(JsonSyntaxException se)
			{
				throw new JsonSyntaxException(se.getMessage());
			}
		}
		else if(this.cs.peek() == '{')
		{
			try
			{
				this.v = parseObject();
				return this.v;
			}
			catch(JsonSyntaxException se)
			{
				throw new JsonSyntaxException(se.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * Returns a JsonArray from text in input file
	 * 
	 * @return Returns a JsonArray from text in input file if text has correct syntax
	 * 
	 * @throws JsonSyntaxException
	 */
	public JsonArray parseArray() throws JsonSyntaxException
	{
		List<JsonValue> lst = new ArrayList<JsonValue>();
		if(this.cs.hasNext()) this.cs.next();
		while(this.cs.hasNext())
		{
			if(this.cs.peek() == ']')
			{
				this.cs.next();
				return new JsonArray(lst);
			}
			if(parseValue() == null) throw new JsonSyntaxException("Syntax error");
			lst.add(this.v);
			if(this.cs.peek() != ',' && this.cs.peek() != ']') throw new JsonSyntaxException("Syntax error");
			if(this.cs.peek() == ',') this.cs.next();
		}
		return new JsonArray(lst);
	}
	
	/**
	 * Returns JsonObject from text in input file
	 * 
	 * @return Returns JsonObject from text in input file, if text has correct syntax
	 * 
	 * @throws JsonSyntaxException
	 */
	public JsonObject parseObject() throws JsonSyntaxException
	{
		Map<String,JsonValue> obj = new HashMap<String,JsonValue>();
		if(this.cs.hasNext()) this.cs.next();
		while(this.cs.hasNext())
		{
			if(this.cs.peek() == '}')
			{
				this.cs.next();
				return new JsonObject(obj);
			}
			if(!(parseValue() instanceof JsonString)) throw new JsonSyntaxException("Syntax error");
			String key = this.v.toString();
			if(this.cs.peek() != ':') throw new JsonSyntaxException("Syntax error");
			this.cs.next();
			if(parseValue() == null) throw new JsonSyntaxException("Syntax error");
			obj.put(key, this.v);
			if(this.cs.peek() != ',' && this.cs.peek() != '}') throw new JsonSyntaxException("Syntax error");
			if(this.cs.peek() == ',') this.cs.next();
		}
		return new JsonObject(obj);
	}
	
	/**
	 * Returns JsonString from text in input file
	 * 
	 * @return Returns JsonString from text in input file, if text has correct syntax
	 * 
	 * @throws JsonSyntaxException
	 */
	public JsonString parseString() throws JsonSyntaxException
	{
		StringBuilder str = new StringBuilder();
		str.setLength(0);
		if(this.cs.hasNext()) this.cs.next();
		while(this.cs.hasNext())
		{
			if(!(this.cs.peek() == '"' || this.cs.peek() == '\\')) str.append(this.cs.next());
			else
			{
				if(this.cs.peek() == '"')
				{
					this.cs.next();
					return new JsonString(str.toString());
				}
				else if(this.cs.peek() == '\\')
				{
					str.append(this.cs.next());
					if(this.cs.peek() != '"' && this.cs.peek() != '\\') throw new JsonSyntaxException("Syntax error");
					str.append(this.cs.next());
				}
			}
			
		}
		return new JsonString(str.toString());
	}
	
	/**
	 * Returns JsonNumber from text in input file
	 * 
	 * @return Returns JsonNumber from text in input file, if text has correct syntax
	 * 
	 * @throws JsonSyntaxException
	 */
	public JsonNumber parseNumber() throws JsonSyntaxException
	{
	    StringBuilder num=new StringBuilder();
	    num.setLength(0);
	    while(this.cs.hasNext())
	    {
		    if(this.cs.peek()=='-') num.append(this.cs.next());
		    if(this.cs.peek()=='0')
		    {
		        num.append(this.cs.next());
		        if(this.cs.peek()!='.' && Character.isDigit(this.cs.peek())) {throw new JsonSyntaxException("Syntax error");}
		        num.append(cs.next());
		    }
		    while (Character.isDigit(this.cs.peek())){
		        num.append(this.cs.next());
		    }
		    if(this.cs.peek()=='.') num.append(this.cs.next());
		    while (Character.isDigit(this.cs.peek()))
		    {
		        num.append(this.cs.next());
		    }
		    if(this.cs.peek()=='e' || this.cs.peek()=='E')
		    {
		      num.append('E');
		      this.cs.next();
		    }
		    if(this.cs.peek()=='+' || this.cs.peek()=='-')
		    {
		      if(this.cs.peek() == '-') num.append(this.cs.next());
		      else this.cs.next();
		    }
		    while (Character.isDigit(this.cs.peek()))
		    {
		        num.append(this.cs.next());
		    }
		    try
		    {
		    	return new JsonNumber(NumberFormat.getInstance().parse(num.toString()));
		    }
		    catch(ParseException e) {}
	    }
	    return null;
	}


	@Override
	public JsonValue get(int i) throws JsonQueryException
	{
		if(!(this.v instanceof JsonArray)) throw new JsonQueryException("Method get(int i) is only applicable for type JsonArray");
		return this.v.get(i);
	}

	@Override
	public JsonValue get(String s) throws JsonQueryException
	{
		if(!(this.v instanceof JsonObject)) throw new JsonQueryException("Method get(String s) is only applicable for type JsonObject");
		return this.v.get(s);
	}
	
	/**
	 * @return Returns String representation of current JsonValue in v
	 */
	public String toString()
	{
		return this.v.toString();
	}
	
}