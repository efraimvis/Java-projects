
public interface Service
{
	/*Inserts a new task into the task list, and waits for a thread to execute it
	 * 
	 * @param r - Runnable task*/
	public void execute(Runnable r);
	
	/*Clears the task list and sets value of shutdown to true*/
	public void shutdown();
	
	/*Returns true if service is shut down*/
	public boolean isShutdown();
}
