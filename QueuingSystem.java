import java.util.TreeMap;

public class QueuingSystem {

    private String ARR, DEP;            /*Event Types  */


    public void queuingSystem(int coeff, int cap, int servers, double servRate, double machine1Rate) {
        int k = cap;  /* System capacity */

        int m = servers;  /* Number of servers */

        int N = 0;  /* Total number of customers in system */

        double clock = 0.0; /* Clock */

        double gamma = machine1Rate;   /* Arrival rate of machine 1 */

        double mu = servRate;      /* Service rate of each server */

        double lambda = (m * mu * coeff) / 10; /* Arrival rate of second system */

        int exit = 0;   /* Condition to exit the computation */

        double EN = 0.0;    /* Expected number to customers in system */

        int NDep = 0;   /* Number of departures */

        int NBlock = 0; /* Number of components blocked */

        int NArr = 0;   /* Number of arivals */

        double u = 0;   /* Utilization */

        TreeMap<Double, String> tmap = new TreeMap<>(); /* To maintain event list */

        Exp_rv rand = new Exp_rv(); /* Exponential eandom variable object */

        Event currentEvent = new Event();
        tmap.put(rand.exp_rv(lambda), "ARR1");  /* Generating arrivals initially */

        tmap.put(rand.exp_rv(gamma), "ARR2");
        while (exit != 1) { /* Run system till number of departures equal to 100000 */

            currentEvent = returnEvent(tmap);
            double prev_clock = clock;
            clock = currentEvent.value;
            switch (currentEvent.type) {    /* To find whether it's Arrival or Departure */

                case "ARR1":    /* Arrival from machine 2 */

                    EN = EN + (N * (clock - prev_clock));
                    u = utilization(N, clock, prev_clock, u, m);
                    if (N < k) {
                        N++;
                        NArr++;
                        tmap.put(clock + rand.exp_rv(lambda), "ARR1");
                    } else {
                        NArr++;
                        tmap.put(clock + rand.exp_rv(lambda), "ARR1");
                        NBlock++;
                    }
                    if (N <= m) {
                        tmap.put(clock + rand.exp_rv(mu), "DEP");
                    }
                    break;
                case "ARR2":    /* Arrival from machine 1 */

                    EN = EN + (N * (clock - prev_clock));
                    u = utilization(N, clock, prev_clock, u, m);
                    if (N < 2) {
                        N++;
                        NArr++;
                        tmap.put(clock + rand.exp_rv(gamma), "ARR2");
                        if (N <= m) {
                            tmap.put(clock + rand.exp_rv(mu), "DEP");
                        }
                    } else {
                        tmap.put(clock + rand.exp_rv(gamma), "ARR2");
                    }
                    break;

                case "DEP": /* Departure from server */

                    EN = EN + (N * (clock - prev_clock));
                    u = utilization(N, clock, prev_clock, u, m);
                    N--;
                    NDep++;
                    if (N >= m) {
                        tmap.put(clock + rand.exp_rv(mu), "DEP");
                    }
            }
            if (NDep == 100000) {   /* Break condition */

                exit = 1;
            }
        }
        simulationResult(N, EN, clock, NArr, NBlock, NDep, lambda, mu, m, u);   /* To output simulation results */

        analysis(lambda, gamma, mu, k, m);  /* To output theoretical analysis */

    }
    /* This method calculates the value of utilization */

    private double utilization(int N, double clock, double prev_clock, double u, int m) {
        switch (N) {
            case 0:
                break;
            default:
                if (N < m) {
                    u = u + ((N / (double) m) * (clock - prev_clock));
                } else {
                    u = u + (clock - prev_clock);
                }
                break;
        }
        return u;
    }
    /* This method is used to print the theoretical results */

    private void analysis(double lambda, double gamma, double mu, int k, int m) {
        Analysis test = new Analysis();
        test.analysis(lambda, gamma, mu, k, m);
    }
    /* This method prints the simulation results */

    private void simulationResult(int N, double EN, double clock, int NArr, int NBlock, int NDep, double lambda, double mu, int m, double u) {
        System.out.println("\t\tFor rho = " + (lambda / (m * mu)));
        System.out.println("SIMULATION RESULT:");
        System.out.println("Current number of customers in the system: " + N);
        System.out.println("Total number of customers arrived: " + NArr);
        System.out.println("Number of customers blocked: " + NBlock);
        System.out.println("Expected number of customers in the system: " + EN / clock);
        System.out.println("Expected time spent by a customer: " + EN / NDep);
        System.out.println("Blocking probability: " + (NBlock / (double) NArr));
        System.out.println("Total utilization of the system: " + u / clock);
        System.out.println();
    }
    /* This method returns the latest event that will occur and removes that event from the event list */

    public Event returnEvent(TreeMap<Double, String> tmap) {
        Event returnEvent = new Event();
        double key;
        String value;
        key = tmap.firstKey();
        value = tmap.get(key);
        returnEvent.type = value;
        returnEvent.value = key;
        tmap.remove(key);
        return returnEvent;
    }
}
