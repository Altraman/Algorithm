package com.hdu.sa.sa2;

import java.util.Random;

/**
 * 模拟退火算法 概率公式exp⁡(−Δf/(kT))
 */
public class TspRun {
    private double nowT;
    private int[] cities;
    private int citiesNum;

    public TspRun() {
        this.nowT = ConstantData.INIT_T;
        this.citiesNum = ConstantData.CITIES_NUM;
        this.cities = new int[citiesNum];
        for (int i = 0; i < citiesNum; i++) {
            this.cities[i] = i;
        }
    }

    private void run() {
        while (this.nowT > ConstantData.MIN_T) {
            for (int i = 0; i < ConstantData.ITER_TIMES; i++) {
                int[] newCities = createNewRoad(this.cities);
                final double d1 = calRoadSum(this.cities);
                final double d2 = calRoadSum(newCities);
                final double res = d2 - d1;
                if (res < 0) {
                    System.arraycopy(newCities, 0, this.cities, 0, citiesNum);
                } else {
                    if (Math.random() < Math.exp(-res / (ConstantData.K * this.nowT))) {
                        System.arraycopy(newCities, 0, this.cities, 0, citiesNum);
                    }
                }
            }
            this.nowT *= ConstantData.DELTA;
        }
        for (int city : this.cities) {
            System.out.print(city + "->");
        }
        System.out.println();
        System.out.println("最短路径:" + calRoadSum(this.cities));
    }

    private int[] createNewRoad(int[] a) {
        final Random rand = new Random();
        final int i = rand.nextInt(a.length);
        final int j = rand.nextInt(a.length);
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        return a;
    }

    private double calRoadSum(int[] a) {
        double sum = 0.0d;
        for (int i = 0; i < a.length - 1; i++) {
            sum += ConstantData.DISTANCE_MAP[a[i]][a[i + 1]];
        }
        return sum;
    }

    public static void main(String[] args) {
        final TspRun fun = new TspRun();
        fun.run();
    }
}
