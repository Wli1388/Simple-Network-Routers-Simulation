/**
 * 
 * @author Name:Wen Feng Li ID:115091650 Recitation 02
 * 
 *         This class represents a PacketNode that is used for a linked list in
 *         class Packet
 *
 */
public class PacketNode {
	private Packet packet;
	private PacketNode next;

	/**
	 * This is a constructor that creates a new PacketNode object
	 * 
	 * @param packet
	 *        The packet to be stored in the node
	 */
	public PacketNode(Packet packet) {
		this.packet = packet;
		this.next = null;
	}

	/**
	 * Gets the packet in node
	 * 
	 * @return The packet
	 */
	public Packet getPacket() {
		return packet;
	}

	/**
	 * Changes the packet in node
	 * 
	 * @param packet
	 *        The packet to be changed
	 */
	public void setPacket(Packet packet) {
		this.packet = packet;
	}

	/**
	 * Gets the next node
	 * 
	 * @return The next PacketNode
	 */
	public PacketNode getNext() {
		return next;
	}

	/**
	 * Changes the next node
	 * 
	 * @param next
	 *        The next PacketNode to be changed
	 */
	public void setNext(PacketNode next) {
		this.next = next;
	}

}
