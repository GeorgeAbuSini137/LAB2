import java.util.ArrayList;
/**
 * Class representing shared data for a multi-threaded or single-threaded computation.
 * It stores an array of integers, a boolean array for results, a flag, and a target value.
 */
public class SharedData 
{
	private ArrayList<Integer> array;
	private boolean [] winArray;
	private boolean flag;
	private final int b;
	/**
     * Constructor to initialize the shared data object.
     *
     * @param array the list of integers to be shared
     * @param b the target value for the computation
     */
	public SharedData(ArrayList<Integer> array, int b) {
		
		this.array = array;
		this.b = b;
	}
	/**
     * Returns the array representing success/failure for each element.
     *
     * @return the boolean array of results
     */
	public boolean[] getWinArray() 
	{
		return winArray;
	}
	/**
     * Sets the array representing success/failure for each element.
     *
     * @param winArray the boolean array to set
     */
	public void setWinArray(boolean [] winArray) 
	{
		this.winArray = winArray;
	}
	 /**
     * Returns the list of integers stored in this object.
     *
     * @return the ArrayList of integers
     */
	public ArrayList<Integer> getArray() 
	{
		return array;
	}
	/**
     * Returns the constant target value used in computation.
     *
     * @return the target integer value
     */
	public int getB() 
	{
		return b;
	}
	/**
     * Returns the current value of the flag.
     *
     * @return true if the flag is set, false otherwise
     */
	public boolean getFlag() 
	{
		return flag;
	}
	/**
     * Sets the flag to indicate a certain condition.
     *
     * @param flag the boolean value to set
     */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
