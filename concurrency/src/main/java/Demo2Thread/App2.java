package Demo2Thread;

class Runner2  implements Runnable
{

	public void run()
	{
		for (int i = 0;i<10;i++)
			System.out.println("Hello");
	}

}

public class App2 {

	public static void main(String args[])
	{
		Runner2 runner = new Runner2();
		
		Thread t1 = new Thread(runner);
		t1.start();
	}
	
}
