/**
 * 
 * @author Name:Wen Feng Li ID:115091650 Recitation 02
 * 
 *         This class represents a packet that will be sent through the
 *         network.
 *
 */
public class Packet {
	private static int packetCount = 0;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int timeToDest;

	/**
	 * This is a constructor class that creates a new Packet object
	 * 
	 * @param timeArrive
	 *        the time this Packet is created
	 */
	public Packet(int packetSize, int timeArrive) {
		packetCount++;
		this.id = packetCount;
		this.packetSize = packetSize;
		this.timeArrive = timeArrive;
		this.timeToDest = this.packetSize / 100;
	}

	/**
	 * Gets the total number of packets
	 * 
	 * @return packetCount
	 */
	public static int getPacketCount() {
		return packetCount;
	}

	/**
	 * Changes the total number of packets
	 * 
	 * @param packetCount
	 *        The number of packets to be changed to
	 */
	public static void setPacketCount(int packetCount) {
		Packet.packetCount = packetCount;
	}

	/**
	 * Gets the id of packet
	 * 
	 * @return id of packet
	 */
	public int getId() {
		return id;
	}

	/**
	 * Changes the id of packet
	 * 
	 * @param id
	 *        The id to be changed
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the size of the packet
	 * 
	 * @return Size of packet
	 */
	public int getPacketSize() {
		return packetSize;
	}

	/**
	 * Changes the size of the packet
	 * 
	 * @param packetSize
	 *        The size to be changed
	 */
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}

	/**
	 * Gets the arrival time of packet
	 * 
	 * @return The arrival time of packet
	 */
	public int getTimeArrive() {
		return timeArrive;
	}

	/**
	 * Changes the arrival time of packet
	 * 
	 * @param timeArrive
	 *        The arrival time to be changed
	 */
	public void setTimeArrive(int timeArrive) {
		this.timeArrive = timeArrive;
	}

	/**
	 * Gets the amount of time to destination
	 * 
	 * @return The time to destination
	 */
	public int getTimeToDest() {
		return timeToDest;
	}

	/**
	 * Changes the amount of time to destination
	 * 
	 * @param timeToDest
	 *        The amount of time to change
	 */
	public void setTimeToDest(int timeToDest) {
		this.timeToDest = timeToDest;
	}

	/**
	 * @return prints the Packet object in the format: [id, timeArrive,
	 *         timeToDest]
	 */
	public String toString() {
		return ("[" + id + ", " + timeArrive + ", " + timeToDest + "]");
	}
}
