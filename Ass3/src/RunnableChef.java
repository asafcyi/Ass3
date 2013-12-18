import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedTransferQueue;


public class RunnableChef implements Runnable {
	
	public LinkedTransferQueue<Order> ordersQueue;
	public LinkedTransferQueue<Order> chefsToDeliveryPersons;	
	public Bell chefsBell;
	
	public RunnableChef(LinkedTransferQueue<Order> chefsToDeliveryPersons, Bell chefsBell) {
		ordersQueue = new LinkedTransferQueue<>();
		this.chefsToDeliveryPersons = chefsToDeliveryPersons;
		this.chefsBell= chefsBell;
	}

	@Override
	public void run() {
		Order curentOrder = null;
		while (! Thread.interrupted()) {
			try {
				curentOrder = ordersQueue.take();
				makeOrder(curentOrder);
				pushToDeliveryPersons(curentOrder);
				chefsBell.Ring();
			} catch (InterruptedException e) {
				// Got shutdown signal
				Thread.interrupted();
			}
		}
		
		// finish handling all orders in the queue
		ArrayList<Order> ordersToFinish = new ArrayList<>(); 
		
		ordersQueue.drainTo(ordersToFinish);
		Iterator<Order> iterator = ordersToFinish.iterator();

		while ( iterator.hasNext() ){
			makeOrder( curentOrder = iterator.next() );
			pushToDeliveryPersons(curentOrder);
		}
	}
	
	public boolean acceptOrder(Order order) {
		// @TODO compare the current pressure and see if it can be added to the queue..		
		return false;
	}
	
	// *************************** Private Functions *********************//
	
	private void pushToDeliveryPersons(Order curentOrder) {
		chefsToDeliveryPersons.put(curentOrder);
	}

	private void makeOrder(Order order) {
		// @TODO complete make order
	}
}
