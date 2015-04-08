public class Analysis {

    public void analysis(double lambda, double gamma, double mu, int k, int m) {
        double EN = 0.0;
        double ET = 0.0;
        double PBlock = 0.0;
        double lambdaEff = 0.0;
        double util = 0.0;
        double[] p = new double[k + 1];
        double[] arrRate = new double[k];
        double[] serRate = new double[k + 1];
        double[] factor = new double[k + 1];
        for (int i = 1; i <= k; i++) {
            factor[i] = 1.0;
        }
        arrRate[0] = lambda + gamma;
        arrRate[1] = arrRate[0];
        for (int i = 2; i < k; i++) {
            arrRate[i] = lambda;
        }
        for (int i = 1; i <= m; i++) {
            serRate[i] = i * mu;
        }
        for (int i = m + 1; i <= k; i++) {
            serRate[i] = m * mu;
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < i; j++) {
                factor[i] = factor[i] * arrRate[j] / serRate[j + 1];
            }
            factor[0] = factor[0] + factor[i];
        }
        p[0] = 1 / (1 + factor[0]);
        for (int i = 1; i <= k; i++) {
            p[i] = factor[i] * p[0];
        }
        for (int i = 1; i <= k; i++) {
            EN = EN + i * p[i];
        }
        for (int i = 0; i < k; i++) {
            lambdaEff = lambdaEff + arrRate[i] * p[i];
        }
        ET = EN / lambdaEff;
        PBlock = (lambda * p[4]) / (lambdaEff + (lambda * p[4]));
        util = 1 - p[0] - (0.5 * p[1]);
        output(EN, ET, PBlock, util);
    }

    private void output(double EN, double ET, double PBlock, double util) {
        System.out.println("THEORETICAL ANALYSIS:");
        System.out.println("Expected number of customers: " + EN);
        System.out.println("Expected time spent by customer: " + ET);
        System.out.println("Blocking Probabiity: " + PBlock);
        System.out.println("System utilization: " + util);
        System.out.println("************************************************************");
    }
}
