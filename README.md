Summary:

The program is about implementing an event-driven simulation of a queueing system. In an event-driven simulation, the system state is updated only when an event (e.g., an arrival or a departure) occurs, rather than being updated at periodic time intervals. When an event occurs, several steps must be taken to update the system state. The first step is to update the system time to the time at which the event occurred. The next step is to update any other state parameters, such as the number of customers in the queue. Finally, new events are generated based on the current event. Once the system state is updated, the simulation moves on to the next event in chronological order.

Queueing System Description:

Two machines generate components that are sent to a processing center for packaging. The first machine generates components according to a Poisson process with rate gamma components/minute. The second machine generates components according to a Poisson process with rate lambda components/minute. If there are two or more components in the processing center, the first machine stops generating components. If there are K or more components in the processing center (K>=2), the second machine continues to generate components, but these components are discarded (they do not enter the queueing system). The processing center employs m workers who package the components. The time it takes for each worker to package a component is exponentially distributed with an average packing time of 1/µ minutes.

Event-Driven Simulation:

In the simulation of a Markovian queueing system, we need to consider two basic types of events: arrivals and departures. When an arrival event occurs, we need to perform the following tasks:

• Update the system time to reflect the time of the current arrival.

• Increment the system size if the system is not at its full capacity and the arrival is not blocked.

• If there is an idle server, then generate a departure event for the new arrival. The departure time will be the current system time plus an exponentially distributed length of time with parameter µ.

• Generate the next arrival event, if applicable. The time of the next arrival will be the current system time plus an exponentially distributed length of time.

When a departure event occurs, we must perform the following steps: • Update the system time.

• Decrement the system size.

• If there are customers waiting in the queue, and if a server is available, then one customer will enter service when the departure occurs. Generate a departure event for the customer entering service.

• If applicable, generate an arrival event.

Once the tasks associated with an event have been completed, the simulation should go on to the next event. In order to manage events, we maintain an event list. An event list consists of a linked list(or any collection) whose elements are data structures which indicate the type of event and the time at which the event occurs. By sorting the event list in chronological order, the next event can be selected from the head of the event list. When a new event is generated, it is placed in the event list in the correct chronological sequence. Note that the event list is simply a data structure for keeping track of the timing of events in the simulation. The event list does not represent the state of the queueing system.

Collecting Performance Measures:

When implementing the simulation, we also need to maintain additional information in order to calculate performance measures for the system. In particular, we should determine the average number of jobs in the system, the average time a job spends in the system, and the blocking probability versus rho, where rho is defined as lambda/mµ. The parameter rho should range between 0.1 and 1.0. The values of rho can be determined from the values of µ, lambda, and m. For each data point, we run the simulation for at least 100000 departures.
