package com.hdu.genetic;

/**
 * 种群类
 * 包含：
 * 1.add 添加物种
 * 2.traverse 遍历
 */

public class SpeciesPopulation {

    SpeciesIndividual head;//头结点
    int speciesNum;//种群数量

    SpeciesPopulation() {
        head = new SpeciesIndividual();
        speciesNum = TSPData.SPECIES_NUM;
    }

    //添加物种
    void add(SpeciesIndividual species) {
        SpeciesIndividual point = head;//游标
        //寻找表尾结点
        while (point.next != null) {
            point = point.next;
        }
        point.next = species;
    }

    //遍历
    void traverse() {
        SpeciesIndividual point = head.next;//游标
        //寻找表尾结点
        while (point != null) {
            for (int i = 0; i < TSPData.CITY_NUM; i++)
                System.out.print(point.genes[i] + " ");
            System.out.println(point.distance);
            point = point.next;
        }
        System.out.println("_______________________");
    }

}
