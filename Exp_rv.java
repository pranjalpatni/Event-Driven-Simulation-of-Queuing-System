import java.util.Random;

/**
 *
 * @author Pranjal
 */
public class Exp_rv {

    Random r = new Random();
    public static double s = 72864.0;
    private final double k = 16807.0;
    private final double m = 2147483647;

    public double exp_rv(Double lambda) {
        double temp_rv, rv;
        s = (k * s) % m;
        temp_rv = s / m;
        rv = ((-1) / lambda) * (Math.log(temp_rv));
        return rv;
    }
}
