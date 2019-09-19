package com.hdu.genetic.genetic2;

public interface IEvolution {
    void select(Population list);

    void crossover(Population list);

    void mutate(Population list);
}
