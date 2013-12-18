
public class Bell {
	
	
	synchronized void waitForRing() {
		try {
			// Wait for chefs to ring the bell
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized void Ring() {
		// notify an Order is ready
		notifyAll();
	}
	
	

}
