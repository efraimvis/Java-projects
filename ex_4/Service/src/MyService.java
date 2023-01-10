import java.util.LinkedList;
import java.util.Queue;

public class MyService implements Service
{
	private boolean shutdown;
	private WorkerThread[] threads;
	private LinkedList<Runnable> tasks;
	
	/*Constructor for MyService
	 * 
	 * @param threadCount - number of threads*/
	public MyService(int threadCount) throws Exception
	{
		if(threadCount <= 0) throw new Exception("Thread count must be positive");
		this.shutdown = false;
		this.threads = new WorkerThread[threadCount];
		this.tasks = new LinkedList<Runnable>();
		for(int i=0;i<threadCount;i++)
		{
			threads[i] = new WorkerThread();
			threads[i].start();
		}
	}
	
	/*Inserts a new task into the task list, and waits for a thread to execute it
	 * 
	 * @param r - Runnable task*/
	public void execute(Runnable r)
	{
		if(!this.isShutdown())
		{
			synchronized(tasks)
			{
				tasks.addLast(r);
				tasks.notify();
			}
		}
	}

	/*Clears the task list and sets value of shutdown to true*/
	public void shutdown()
	{
		this.tasks.clear();
		this.shutdown = true;
	}
	
	/*Getter for shutdown
	 * 
	 * @return Returns value of shutdown*/
	public boolean isShutdown()
	{
		return this.shutdown;
	}
	
	/*Helper class representing a Worker Thread*/
	private class WorkerThread extends Thread
	{
		@Override
		/*Run method for WorkerThread
		 * Runs available tasks in task list*/
		public void run()
		{
			Runnable r;
			while(true)
			{
				synchronized(tasks)
				{
					while(tasks.isEmpty())
					{
						try
						{
							tasks.wait();
						}
						catch(InterruptedException e) {e.printStackTrace();}
					}
					r = (Runnable)tasks.removeFirst();
				}
				try
				{
					r.run();
					if(isShutdown() == true) break;
				}
				catch(Exception e) {e.printStackTrace();}
			}
		}
	}
}
