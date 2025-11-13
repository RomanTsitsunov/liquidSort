package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int m = LiquidColor.values().length;
        int v = 4;
        int n = 14;
        LiquidColor[][] liquidColors = new LiquidColor[][]{
                {LiquidColor.GREEN, LiquidColor.GREEN, LiquidColor.ORANGE, LiquidColor.PINK},
                {LiquidColor.RED, LiquidColor.GRAY, LiquidColor.RED, LiquidColor.YELLOW},
                {LiquidColor.BROWN, LiquidColor.CYAN, LiquidColor.BLUE, LiquidColor.ORANGE},
                {LiquidColor.CYAN, LiquidColor.PINK, LiquidColor.MAJENTA, LiquidColor.CYAN},
                {LiquidColor.BLUE, LiquidColor.RED, LiquidColor.BLACK, LiquidColor.WHITE},
                {LiquidColor.PINK, LiquidColor.YELLOW, LiquidColor.GRAY, LiquidColor.GRAY},
                {LiquidColor.BLACK, LiquidColor.RED, LiquidColor.BLUE, LiquidColor.GREEN},
                {LiquidColor.YELLOW, LiquidColor.MAJENTA, LiquidColor.BLACK, LiquidColor.ORANGE},
                {LiquidColor.BROWN, LiquidColor.BROWN, LiquidColor.BLUE, LiquidColor.ORANGE},
                {LiquidColor.BLACK, LiquidColor.WHITE, LiquidColor.PINK, LiquidColor.WHITE},
                {LiquidColor.MAJENTA, LiquidColor.BROWN, LiquidColor.WHITE, LiquidColor.GREEN},
                {LiquidColor.YELLOW, LiquidColor.GRAY, LiquidColor.MAJENTA, LiquidColor.CYAN},
        };

        Bottle[] bottles = new Bottle[n];
        for (int i = 0; i < m; i++) {
            bottles[i] = new Bottle(v);
            for (int j = 0; j < v; j++) {
                bottles[i].fill(liquidColors[i][j]);
            }
        }
        for (int i = m; i < n; i++) {
            bottles[i] = new Bottle(v);
        }

        System.out.println("Initial state:");
        for (Bottle bottle : bottles) {
            System.out.println(bottle);
        }
        System.out.println();

        List<Step> steps = new ArrayList<>();
        boolean isSolved = SortWorker.sort(bottles, steps);
        SortWorker.printResult(isSolved, bottles, steps);
    }
}