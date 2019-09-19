package com.hdu.genetic.genetic2;

import java.util.Random;

public class Algorithm implements IEvolution {
    private static final int DEVELOP_NUM = 1000;//繁衍代数
    private static final double pcl = 0.6d, pch = 0.95d;//交叉概率
    private static final double pm = 0.4d;//变异概率

    public final Individual run(Population list) {
        createBeginSpecies(list);
        for (int i = 0; i < DEVELOP_NUM; i++) {
            select(list);
            crossover(list);
            mutate(list);
        }
        return getBest(list);
    }

    private void createBeginSpecies(Population list) {
        final int speciesNum = list.speciesNum;
        for (int i = 0; i < speciesNum; i++) {
            final Individual individual = new Individual();
            individual.createByRandomGenes();
            individual.calFitness(individual.decode(individual.genes));
            while (individual.result <= 0.0d) {
                individual.createByRandomGenes();
                individual.calFitness(individual.decode(individual.genes));
            }
            list.add(individual);
        }
    }

    private void calRate(Population list) {
        double totalResult = 0.0d;
        Individual point = list.head.next;
        while (point != null) {
            point.calFitness(point.decode(point.genes));
            totalResult += point.result;
            point = point.next;
        }
        point = list.head.next;
        while (point != null) {
            point.rate = point.result / totalResult;
            point = point.next;
        }
    }

    @Override
    public void select(Population list) {
        calRate(list);

        double talentResult = Double.MIN_VALUE;
        Individual talentSpecies = null;
        Individual point = list.head.next;

        while (point != null) {
            if (talentResult < point.result) {
                talentResult = point.result;
                talentSpecies = point;
            }
            point = point.next;
        }

        final Population newPopulation = new Population();
        assert talentSpecies != null;
        final int talentSpeciesNum = list.speciesNum / 4;
        for (int i = 0; i < talentSpeciesNum; i++) {
            final Individual newSpecies = talentSpecies.cloneArray();
            newPopulation.add(newSpecies);
        }

        final int remainSpeciesNum = list.speciesNum - talentSpeciesNum;
        for (int i = 0; i < remainSpeciesNum; i++) {
            double rate = Math.random();
            Individual oldPoint = list.head.next;
            while (oldPoint != null && oldPoint != talentSpecies) {
                if (rate <= oldPoint.rate) {
                    final Individual newSpecies = oldPoint.cloneArray();
                    newPopulation.add(newSpecies);
                    break;
                } else {
                    rate -= oldPoint.rate;
                }
                oldPoint = oldPoint.next;
            }
            if (oldPoint == null || oldPoint == talentSpecies) {
                point = list.head;
                while (point.next != null) {
                    point = point.next;
                }
                final Individual newSpecies = point.cloneArray();
                newPopulation.add(newSpecies);
            }
        }

        list.head = newPopulation.head;
    }

    @Override
    public void crossover(Population list) {
        final double rate = Math.random();
        if (rate > pcl && rate < pch) {
            final Random rand = new Random();
            Individual point = list.head.next;
            int find = rand.nextInt(list.speciesNum);
            while (point != null && find != 0) {
                point = point.next;
                find--;
            }

            assert point != null;
            int crossoverLocationLeft = rand.nextInt(point.genes.length);
            int crossoverLocationRight = rand.nextInt(point.genes.length);
            if (crossoverLocationLeft > crossoverLocationRight) {
                int temp = crossoverLocationLeft;
                crossoverLocationLeft = crossoverLocationRight;
                crossoverLocationRight = temp;
            }

            for (int i = crossoverLocationLeft; i < crossoverLocationRight; i++) {
                String gene = point.genes[i];
                point.genes[i] = point.next.genes[i];
                point.next.genes[i] = gene;
            }
        }
    }

    @Override
    public void mutate(Population list) {
        Individual point = list.head.next;
        while (point != null) {
            final double rate = Math.random();
            if (rate <= pm) {
                final Random rand = new Random();
                int left = rand.nextInt(point.genes.length);
                int right = rand.nextInt(point.genes.length);
                if (left > right) {
                    int temp = left;
                    left = right;
                    right = temp;
                }

                for (int i = left; i <= right; i++) {
                    point.genes[i] = String.valueOf((Integer.parseInt(point.genes[i]) + 1) % 2);
                }
            }
            point = point.next;
        }
    }

    public Individual getBest(Population list) {
        double min = Double.MIN_VALUE;
        Individual point = list.head.next;
        Individual bestSpecies = null;
        while (point != null) {
            if (min < point.result) {
                min = point.result;
                bestSpecies = point;
            }
            point = point.next;
        }
        return bestSpecies;
    }
}
