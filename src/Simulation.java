
/**
 * 
 * @author Name:Wen Feng Li ID:115091650 Recitation 02
 * 
 *         This class contains the main method that tests the simulation. 
 *
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {
	public static final int MAX_PACKETS = 3;

	/**
	 * The main method will prompt the user for inputs to the simulator. It
	 * will then run the simulator, and outputs the result. Prompt the user
	 * whether he or she wants to run another simulation.
	 * 
	 * @param args
	 * @throws Exception
	 *         Throws when an exception occurs in the simulation
	 */
	public static void main(String[] args) throws Exception {
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Starting simulator...");
			System.out.println("\nEnter the number of Intermediate routers: ");
			int intRouters = scan.nextInt();
			scan.nextLine();
			System.out
					.println("\nEnter the arrival probability of a packet: ");
			double prob = scan.nextDouble();
			scan.nextLine();
			System.out
					.println("\nEnter the maximum buffer size of a router: ");
			int buffSize = scan.nextInt();
			scan.nextLine();
			System.out.println("\nEnter the minimum size of a packet: ");
			int minSize = scan.nextInt();
			scan.nextLine();
			System.out.println("\nEnter the maximum size of a packet: ");
			int maxSize = scan.nextInt();
			scan.nextLine();
			System.out.println("\nEnter the bandwidth size: ");
			int bandwidth = scan.nextInt();
			scan.nextLine();
			System.out.println("\nEnter the simulation duration: ");
			int duration = scan.nextInt();
			scan.nextLine();
			simulate(intRouters, prob, buffSize, minSize, maxSize, bandwidth,
					duration);
			System.out
					.println("Do you want to try another simulation? (y/n): ");
			if (scan.nextLine().equalsIgnoreCase("n")) {
				System.out.println("Program terminating successfully...");
				System.exit(0);
			}
			Packet.setPacketCount(0);
		}

	}

	/**
	 * Runs the simulator
	 * 
	 * @param intRouters
	 *        the number of Intermediate routers in the network.
	 * @param probability
	 *        the probability of a new packet arriving at the Dispatcher
	 * @param maxBuffSize
	 *        the maximum number of Packets a Router can accommodate for
	 * @param minSize
	 *        the minimum size of a Packet
	 * @param maxSize
	 *        the maximum size of a Packet
	 * @param bandSize
	 *        the maximum number of Packets the Destination router can accept
	 *        at a given simulation unit
	 * @param dur
	 *        the number of simulation units
	 * @return the average time each packet spends within the network.
	 * @throws Exception
	 *         Throws when a queue is empty and tries to dequeue
	 */
	public static double simulate(int intRouters, double probability,
			int maxBuffSize, int minSize, int maxSize, int bandSize, int dur)
			throws Exception {
		Router dispatcher = new Router();
		ArrayList<Router> routers = new ArrayList<Router>();
		int totalServiceTime = 0;
		int totalPacketsArrived = 0;
		int packetsDropped = 0;
		double arrivalProb = probability;
		int numIntRouters = intRouters;
		int maxBufferSize = maxBuffSize;
		int minPacketSize = minSize;
		int maxPacketSize = maxSize;
		int bandwidth = bandSize;
		int duration = dur;
		int dispatcherArrival = 0;
		int currentBandwidth = 0;
		double averageServiceTime;
		// Adds the intermediate routers to a collection
		for (int numRouter = 0; numRouter < numIntRouters; numRouter++) {
			routers.add(new Router());
		}
		Router.setMaxBufferSize(maxBufferSize);

		for (int time = 1; time <= duration; time++) {
			String reachedDestination = "";
			System.out.println("\nTime: " + time);
			for (int routerIndex = 0; routerIndex < routers
					.size(); routerIndex++) {
				if (!routers.get(routerIndex).isEmpty()) {
					routers.get(routerIndex).peek().setTimeToDest(
							routers.get(routerIndex).peek().getTimeToDest()
									- 1);
					if (routers.get(routerIndex).peek().getTimeToDest() <= 0)
						if (currentBandwidth < bandwidth) {
							currentBandwidth++;
							Packet sentPacket = routers.get(routerIndex)
									.dequeue();
							reachedDestination += "Packet "
									+ sentPacket.getId()
									+ " has successfully reached "
									+ "its destination: +"
									+ (time - sentPacket.getTimeArrive()
											+ "\n");
							totalPacketsArrived++;
							totalServiceTime += time
									- sentPacket.getTimeArrive();
						}
				}
			}
			for (int packet = 1; packet <= MAX_PACKETS; packet++) {
				if (Math.random() < arrivalProb) {
					dispatcher.enqueue(new Packet(
							randInt(minPacketSize, maxPacketSize), time));
					System.out.println("Packet "
							+ dispatcher.getRear().getPacket().getId()
							+ " arrives at dispatcher with size " + dispatcher
									.getRear().getPacket().getPacketSize());
					dispatcherArrival++;
				}
			}
			if (dispatcherArrival == 0)
				System.out.println("No packets arrived");
			for (int arrived = 0; arrived < dispatcherArrival; arrived++) {
				Packet dequeuedPacket = dispatcher.dequeue();
				try {
					int send = Router.sendPacketTo(routers);
					routers.get(send).enqueue(dequeuedPacket);
					System.out.println("Packet " + dequeuedPacket.getId()
							+ " sent to Router " + (send + 1));
				} catch (Exception e) {
					System.out.println("Network is congested. Packet "
							+ dequeuedPacket.getId() + " is " + "dropped.");
					packetsDropped++;
				}
			}
			System.out.print(reachedDestination);
			for (int routerIndex = 0; routerIndex < routers
					.size(); routerIndex++) {
				System.out.println("R" + (routerIndex + 1) + ": "
						+ routers.get(routerIndex));
			}
			dispatcherArrival = 0;
			currentBandwidth = 0;
		}
		if (totalPacketsArrived > 0)
			averageServiceTime = (double) totalServiceTime
					/ (double) totalPacketsArrived;
		else
			averageServiceTime = 0;
		System.out.println("Simulation ending...");
		System.out.println("Total service time: " + totalServiceTime);
		System.out.println("Total packets served: " + totalPacketsArrived);
		System.out.println(
				"Average service time per packet: " + averageServiceTime);
		System.out.println("Total packets dropped: " + packetsDropped);
		return averageServiceTime;
	}

	/**
	 * Generate a random number between minVal and maxVal, inclusively.
	 * 
	 * @param minVal
	 *        The minimum value
	 * @param maxVal
	 *        The maximum value
	 * @return Randomly generated number
	 */
	private static int randInt(int minVal, int maxVal) {
		return (int) (Math.random() * (maxVal - minVal + 1) + minVal);
	}

}
