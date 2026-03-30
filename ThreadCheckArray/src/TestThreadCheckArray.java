import java.util.ArrayList;
import java.util.Scanner;
/**
 * Test class for demonstrating multi-threaded processing using SharedData
 * and ThreadCheckArray.
 * 
 * <p>
 * The program asks the user to input an array of integers and a target number (b).
 * Then it creates two threads that try to process the array simultaneously 
 * (e.g., checking for a subset sum or some condition implemented in ThreadCheckArray).
 * After threads finish, it prints the solution if found.
 * </p>
 */
public class TestThreadCheckArray {
	/**
     * Main method to run the multi-threaded test.
     */
	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			Thread thread1, thread2;
			System.out.println("Enter array size");
			int num = input.nextInt();
			
			ArrayList<Integer> array = new ArrayList<>(); 
			
			System.out.println("Enter numbers for array");
			
			for (int index = 0; index < num; index++) 
				array.add(input.nextInt());
			
			System.out.println("Enter number");
			num = input.nextInt();
			
			SharedData sd = new SharedData(array, num);
			
			thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
			thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
			thread1.start();
			thread2.start();
			
			try {
				thread1.join();
				thread2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (!sd.getFlag()) {
				System.out.println("Sorry");
				return;
			}
			
			System.out.println("Solution for b : " + sd.getB() + ", n = " + sd.getArray().size());
			System.out.print("I:    ");
			for(int index = 0; index < sd.getArray().size(); index++)
				System.out.print(index + "    ");
			
			System.out.println();
			System.out.print("A:    ");
			for (int value : sd.getArray()) {
				System.out.print(value);
				int tempValue = value;
				int counter = 5;
				while (true) {
					tempValue = tempValue / 10;
					counter--;
					if (tempValue == 0)
						break;
				}
				for (int i = 0; i < counter; i++)
					System.out.print(" ");
			}

			System.out.println();
			System.out.print("C:    ");
			for (boolean index : sd.getWinArray()) {
				if (index)
					System.out.print("1    ");
				else
					System.out.print("0    ");	
			}
		}
	}
}