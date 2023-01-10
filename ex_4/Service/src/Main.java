
public class Main
{
	/*Tests functionality of MyService*/
	 public static void main( String[] args ) throws InterruptedException
	 {
		 try
		 {
			 Service s = new MyService( 5 );
			 for( int j = 0; j < 30; j++ )
			 {
			 s.execute( new Runnable()
			 {
			 public void run()
			 {
			 long id = Thread.currentThread().getId();
			 System.out.println( id + " " + this );
			 for( int i = 0; i < 100000; i++ )
			 {
			 int j = i * i;
			 }
			 }
			 } );
			 }
			 
			 Thread.sleep( 7 );
			 s.shutdown();
		 }
		 catch(Exception e) {}
	 }
}

