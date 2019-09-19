package com.hdu.sa;

/**
 * 模拟退火算法 概率公式exp⁡(−Δf/(kT))
 */
public class Annealing {
    private static final int INIT_T = 1000;//初识温度
    private static final int MIN_T = 1;//温度下限
    private static final int ITER_TIMES = 1000;//每个T值的迭代次数
    private static final double DELTA = 0.95d;//温度衰减系数
    private static final double K = 1;//k值
    private double nowT;
    private double initX;

    public Annealing() {
        this.nowT = INIT_T;
        this.initX = 10 * (Math.random() * 2 - 1);
    }

    public double getNowT() {
        return nowT;
    }

    public double getInitX() {
        return initX;
    }

    private void run() {
        while (this.nowT > MIN_T) {
            for (int i = 0; i < ITER_TIMES; i++) {
                final double funVal = f(this.initX);
                final double xNew = this.initX + (Math.random() * 2 - 1);
                if (xNew > -10 && xNew < 10) {
                    final double funNew = f(xNew);
                    final double res = funNew - funVal;
                    if (res < 0) {
                        this.initX = xNew;
                    } else {
                        final double probability = Math.exp(-res / (K * this.nowT));
                        if (Math.random() < probability) {
                            this.initX = xNew;
                        }
                    }
                }
            }
            this.nowT *= DELTA;
        }
    }

    private double f(double x) {
        return (x - 2) * (x + 3) * (x + 8) * (x - 9);
    }

    public static void main(String[] args) {
        final Annealing sa = new Annealing();
        sa.run();
        System.out.println("最优解:" + sa.getInitX());
        System.out.println("最优解值:" + sa.f(sa.getInitX()));
    }
}
