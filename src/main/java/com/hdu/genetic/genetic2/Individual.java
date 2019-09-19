package com.hdu.genetic.genetic2;

import java.util.Random;

public class Individual {
    public static final int GENS_BITS = 17;
    public String[] genes;
    public double result;
    public Individual next;
    //被选中的概率
    public double rate;

    public Individual() {
        this.genes = new String[GENS_BITS];
        this.result = 0d;
        this.next = null;
        this.rate = 0d;
    }

    public final void createByRandomGenes() {
        final Random random = new Random();
        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < 0.5d) {
                this.genes[i] = "0";
            } else {
                this.genes[i] = "1";
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
    }

    public final Individual cloneArray() {
        final Individual individual = new Individual();
        System.arraycopy(this.genes, 0, individual.genes, 0, this.genes.length);
        individual.result = this.result;
        return individual;
    }

    public final void printRate() {
        System.out.print("最大基因：");
        for (String gene : genes) {
            System.out.print(gene);
        }
        System.out.println();
        System.out.println("对应x:" + decode(this.genes));
        System.out.println("最大长度：" + this.result);
    }
}
