package com.kodewerk.model.rt.model;

public class ErlangFormula {

    private final int m;
    private final double mFactorial;

    public ErlangFormula(int numberOfServers) {
        this.m = numberOfServers;
        mFactorial = factorial(m);
    }

    /*
    1 + (((m*x)^m)/m!) / ((1-x) * sumOf(m,p))
     */
    public double fOfX(double x) {
        double numerator = Math.pow(m * x,m) / mFactorial;
        double demoninator = (1.0d - x) * sum(x, m);
        return 1.0d + (numerator / demoninator);
    }

    private double sum(double x, int m) {
        double partial = 0.0d;
        double term;
        for (int n = 0; n < m - 1; n++) {
            term = (Math.pow(m * x, n) / factorial(n)) + (Math.pow(m*x,m)/mFactorial);
            partial += term;
        }
        return partial;
    }

    /**
     * Closed form approximation
     * @param n
     * @return n!
     */
    private long factorial(int n) {
        return Math.round(Math.sqrt(2.0d*Math.PI*n) * Math.pow( (n / Math.E) + (1 / (12.0d * Math.E * n)), n));
    }

    public static void main(String[] args) {
        ErlangFormula e = new ErlangFormula(2);
        System.out.println(e.factorial(10000));
    }
}
