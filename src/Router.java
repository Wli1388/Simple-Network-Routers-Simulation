import java.util.ArrayList;

/**
 * 
 * @author Name:Wen Feng Li ID:115091650 Recitation 02
 * 
 *         This class represents a router in the network, which is ultimately a
 *         queue
 *
 */
public class Router {
	private static int maxBufferSize;
	private int size;
	private PacketNode front;
	private PacketNode rear;

	/**
	 * This is a constructor that constructs a new Router object
	 */
	public Router() {
		this.size = 0;
		this.front = null;
		this.rear = null;
	}

	/**
	 * Gets the maximum buffer size of routers
	 * 
	 * @return The max buffer size
	 */
	public static int getMaxBufferSize() {
		return maxBufferSize;
	}

	/**
	 * Changes the maximum buffer size of routers
	 * 
	 * @param maxBufferSize
	 *        The buffer size to be changed
	 */
	public static void setMaxBufferSize(int maxBufferSize) {
		Router.maxBufferSize = maxBufferSize;
	}

	/**
	 * Gets the front of the queue in the router
	 * 
	 * @return Front of queue
	 */
	public PacketNode getFront() {
		return front;
	}

	/**
	 * Changes the front of the queue in the router
	 * 
	 * @param front
	 *        The front to be changed
	 */
	public void setFront(PacketNode front) {
		this.front = front;
	}

	/**
	 * Gets the rear of the queue in the router
	 * 
	 * @return The rear of queue
	 */
	public PacketNode getRear() {
		return rear;
	}

	/**
	 * Changes the rear of the queue in the router
	 * 
	 * @param rear
	 *        The rear to be changed
	 */
	public void setRear(PacketNode rear) {
		this.rear = rear;
	}

	/**
	 * Adds a new Packet to the end of the router buffer.
	 * 
	 * @param p
	 *        The packet to be added
	 */
	public void enqueue(Packet p) {
		PacketNode newNode = new PacketNode(p);
		if (front == null) {
			front = newNode;
			rear = front;
		} else {
			rear.setNext(newNode);
			rear = newNode;
		}
		size++;
	}

	/**
	 * Removes the first Packet in the router buffer.
	 * 
	 * @return The removed Packet
	 * @throws Exception
	 *         Throws when there is no packets in the router
	 */
	public Packet dequeue() throws Exception {
		if (front == null)
			throw new Exception("The router is empty");
		Packet formerFront = front.getPacket();
		front = front.getNext();
		if (front == null)
			rear = null;
		size--;
		return formerFront;
	}

	/**
	 * @return The first Packet in the router buffer
	 */
	public Packet peek() {
		return front.getPacket();
	}

	/**
	 * 
	 * @return the number of Packets that are in the router buffer.
	 */
	public int size() {
		return size;
	}

	/**
	 * 
	 * @return whether the router buffer is empty or not.
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * @return a String representation of the router buffer
	 */
	public String toString() {
		String rep = "{";
		PacketNode current = front;
		while (current != null) {
			rep += current.getPacket().toString();
			if (current.getNext() != null)
				rep += ", ";
			current = current.getNext();
		}
		rep += "}";
		return rep;
	}

	/**
	 * Finds the router with the most free buffer space and return the index of
	 * the router.
	 * 
	 * @param routers
	 *        The list of routers to go through
	 * @return The index of router with most free buffer space
	 * @throws Exception
	 *         Throws when all router buffers are full
	 */
	public static int sendPacketTo(ArrayList<Router> routers)
			throws Exception {
		int bufferSpace = maxBufferSize;
		int routerIndex = -1;
		for (int index = 0; index < routers.size(); index++) {
			if (routers.get(index).size() < bufferSpace) {
				bufferSpace = routers.get(index).size();
				routerIndex = index;
			}
		}
		if (routerIndex == -1)
			throw new Exception("Full!");
		return routerIndex;
	}
}
