// and the 1st task is: 
// Ќаписать программу, котора€ гарантированно выводит на консоль все состо€ни€ потока. 
package dataArtsMultyThreading1;

public class Main {
	public static void main(String [] args){
		try{
			Thread thread = new ThreadStateTest();
			System.out.println("So here is the list of our threads' states:");
			System.out.println("1: " + thread.getState());
			thread.start();
			System.out.println("2: " + thread.getState());
			Thread.sleep(10);
			System.out.println("3: " + thread.getState());
			thread.join();
			System.out.println("4: " + thread.getState());
		} catch (InterruptedException e) {}
		try {
			Thread thread = new ThreadStateTest2();
			thread.start();
			synchronized (thread) {
				Thread.sleep(10);
				System.out.println("5: " + thread.getState());
			}
			Thread.sleep(10);
			System.out.println("6: " + thread.getState());
			thread.interrupt();
		} catch (InterruptedException e) {}
	}
}
class ThreadStateTest extends Thread {
	public void run() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {}
	}
}
class ThreadStateTest2 extends Thread {
	public void run() {
		try {
			synchronized (this) {
				wait();
			}
		} catch (InterruptedException e) {
			System.err.print("7: THREAD ERROR");
		}
	}
}