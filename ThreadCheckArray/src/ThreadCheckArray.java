import java.util.ArrayList;
/**
 * Runnable class that checks for a solution in the array stored in SharedData.
 * <p>
 * This class is designed to be run in multiple threads. Each thread recursively
 * checks subsets of the array to see if a subset sums to the target value (b).
 * Once a solution is found, the shared flag is set and the winArray is updated.
 * </p>
 */
public class ThreadCheckArray implements Runnable 
{
	private boolean flag;
	private boolean [] winArray;
	SharedData sd;
	ArrayList<Integer> array;
	int b;
	/**
     * Constructor for ThreadCheckArray.
     * Copies the array and target value from SharedData while synchronizing access.
     *
     * @param sd the shared data object
     */
	public ThreadCheckArray(SharedData sd) 
	{
		this.sd = sd;	
		synchronized (sd) 
		{
			array = sd.getArray();
			b = sd.getB();
		}		
		winArray = new boolean[array.size()];
	}
	/**
     * Recursive method to check subsets of the array for a sum equal to b.
     * Updates the local winArray and the shared flag when a solution is found.
     *
     * @param n current size of the subarray to consider
     * @param b remaining target sum to match
     */
	void rec(int n, int b)
	{
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		if (n == 1)
		{
			if(b == 0 || b == array.get(n-1))
			{
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}			
			}
			if (b == array.get(n-1))
				winArray[n-1] = true;
			return;
		}
		
		rec(n-1, b - array.get(n-1));
		if (flag)
			winArray[n-1] = true;
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		rec(n-1, b);
	}
	/**
     * Main method executed by the thread.
     * Determines which part of the array to check based on thread name
     * and updates SharedData when a solution is found.
     */
	public void run() {
		if (array.size() != 1)
			if (Thread.currentThread().getName().equals("thread1"))
				rec(array.size()-1, b - array.get(array.size()- 1));
			else 
				rec(array.size()-1, b);
		if (array.size() == 1)
			if (b == array.get(0) && !flag)
			{
				winArray[0] = true;
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}
			}
		if (flag)
		{
			if (Thread.currentThread().getName().equals("thread1"))
				winArray[array.size() - 1] = true;
			synchronized (sd) 
			{
				sd.setWinArray(winArray);
			}	
		}
	}
}
