package com.hdu.sa.sa2;

public class ConstantData {
    public static int INIT_T = 50000;
    public static final int MIN_T = 1;
    public static final int ITER_TIMES = 10000;
    public static final double DELTA = 0.95d;
    public static final int K = 1;
    public static final double[][] DISTANCE_MAP;
    public static final int CITIES_NUM;

    static {
        int[][] cities = {{1304, 2312}, {3639, 1315},
                {4177, 2244}, {3712, 1399},
                {3488, 1535}, {3326, 1556},
                {3238, 1229}, {4196, 1004},
                {4312, 790}, {4386, 570},
                {3007, 1970}, {2562, 1756},
                {2788, 1491}, {2381, 1676},
                {1332, 695}, {3715, 1678},
                {3918, 2179}, {4061, 2370},
                {3780, 2212}, {3676, 2578},
                {4029, 2838}, {4263, 2931},
                {3429, 1908}, {3507, 2367},
                {3394, 2643}, {3439, 3201},
                {2935, 3240}, {3140, 3550},
                {2545, 2357}, {2778, 2826},
                {2370, 2975}};
        CITIES_NUM = cities.length;
        DISTANCE_MAP = new double[CITIES_NUM][CITIES_NUM];
        for (int i = 0; i < CITIES_NUM; i++) {
            for (int j = i; j < CITIES_NUM; j++) {
                final double distance = Math.sqrt(Math.pow(cities[i][0] - cities[j][0], 2) + Math.pow(cities[i][1] - cities[j][1], 2));
                DISTANCE_MAP[i][j] = distance;
                DISTANCE_MAP[j][i] = DISTANCE_MAP[i][j];
            }
        }
    }
}
