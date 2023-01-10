package Json;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * @author Efraim Vishnevetsky & Shon Dayan
 * 
 * Test class for JSON parser program
 *
 */
public class TestClass {

	public static void main(String[] args)
	{
		JsonBuilder avraham = null;
		 try
		 {
		 avraham = new JsonBuilder( new File( args[0] ));
		 System.out.println( avraham );
		 System.out.println( avraham.get( "issue" ).get( "Ketura" ).get( 2 ) );
		 }
		 catch( JsonSyntaxException e )
		 {
		 e.printStackTrace();
		 }
		 catch( JsonQueryException e )
		 {
		 e.printStackTrace();
		 }
		 catch (FileNotFoundException e) 
		 {
		 e.printStackTrace();
		 }
	}

}
