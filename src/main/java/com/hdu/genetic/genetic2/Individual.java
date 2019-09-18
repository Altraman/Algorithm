package com.hdu.genetic.genetic2;

import java.util.Random;

public class Individual {
    public static final int GENS_BITS = 17;
    public String[] gens;
    public double result;
    public double fitness;
    public Individual next;
    //选中的概率
    public double rate;

    public Individual() {
        this.gens = new String[GENS_BITS];
        this.result = 0d;
        this.fitness = 0d;
        this.next = null;
        this.rate = 0d;
    }

    public final void createByRandomGenes() {
        final Random random = new Random();
        for (int i = 0; i < gens.length; i++) {
            if (random.nextDouble() < 0.5d) {
                this.gens[i] = "0";
            } else {
                this.gens[i] = "1";
            }
        }
    }

    public final double decode(String[] gens) {
        final StringBuilder builder = new StringBuilder();
        for (String gen : gens) {
            builder.append(gen);
        }
        final double ten = MathUtils.binToTen(builder.toString());
        return ten * 9 / (Math.pow(2, 17) - 1);
    }

    public final void calFitness(double x) {
        this.result = x + Math.sin(5 * x) * 10 + Math.cos(4 * x) * 7;
        this.fitness = 1.0d / this.result;
    }
}
