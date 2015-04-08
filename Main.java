public class Main {

    public static void main(String... args) {
        QueuingSystem[] system = new QueuingSystem[10];
        for (int i = 0; i < 10; i++) {
            system[i] = new QueuingSystem();
        }
        for (int i = 0; i < 10; i++) {
            system[i].queuingSystem(i + 1, 4, 2, 4.0, 5.0); /*Input the parameters as follows: <leave first one>, <system capacity>, <number of servers>, <service rate>, <arrival rate for machine 1> */

        }
    }
}
