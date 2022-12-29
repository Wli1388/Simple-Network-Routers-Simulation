# Simple-Network-Routers-Simulation

This program is used to imitate a simple network of routers.

## Assume we have 3 levels of routers

Level 1: Dispatcher Router\
All packets in the simulation will arrive at this router. The job of this router is to send off the queued packets to one of the available routers in the second level. 

Level 2: Intermediate Hop Routers\
These routers will accept a packet from the dispatcher router. There will be a user-determined amount of these routers. Depending the size of the packet, it takes a variable amount of time to process the packet. Once the arrived packets have been processed, the router can send those packets to their destination.

Level 3: Destination Router\
To simplify the simulation, we assume all packets have the same destination. The job of this router is to accept incoming packets after they have been sent from the second level. However, due to a limited bandwidth (bottleneck in the network), this router can only accept a limited amount of packets at a given moment.

## Background

Packets first arrive at the Dispatcher router. Each packet can arrive at a probability that is determined by the user. For each simulation time unit, a maximum of 3 packets can arrive at the Dispatcher. On the same time unit, the Dispatcher will decide which Intermediate router a specified packet should be forwarded to. 
When a Packet arrives at an Intermediate router, it is placed onto its corresponding queue. Only the first packet in the queue will be processed, while the others remain in the queue. However, if the queue is full at the time a packet comes in, it is considered as a buffer overflow, and the network will drop this packet. Once the router finds that a packet is ready to be sent, it will forward it to its final destination.
The final destination receives all incoming packets from the Intermediate routers. However, due to limited bandwidth, the destination router can only accept a limited amount of packets, limit, which is determined by the user. For example, if 3 packets have been finished processing by the Intermediate routers, but the limit is 2, only 2 packets can arrive at the destination in a simulation unit. The third packet must arrive in the next simulation unit.
