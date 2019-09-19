package com.hdu.genetic.genetic2;

public class Population {
    public Individual head;
    public int speciesNum;

    public Population() {
        this.head = new Individual();
        this.speciesNum = 200;
    }

    public void add(Individual species) {
        Individual point = head;
        while (point.next != null) {
            point = point.next;
        }
        point.next = species;
    }
}
