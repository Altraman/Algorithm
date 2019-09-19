package com.hdu.genetic.genetic2;

public class Main {
    public static void main(String[] args) {
        final Algorithm ga = new Algorithm();
        final Population population = new Population();
        final Individual bestSpecies = ga.run(population);
        bestSpecies.printRate();
    }
}
