package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SortTest {
    @Test
    public void testSolveDifficult() {
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

        List<Step> steps = new ArrayList<>();
        boolean isSolved = SortWorker.sort(bottles, steps);
        Assert.assertTrue(isSolved);
    }

    @Test
    public void testSolveUnsolved() {
        int m = 3;
        int v = 4;
        int n = 4;
        LiquidColor[][] liquidColors = new LiquidColor[][]{
                {LiquidColor.GREEN, LiquidColor.YELLOW, LiquidColor.RED, LiquidColor.GREEN},
                {LiquidColor.RED, LiquidColor.YELLOW, LiquidColor.RED, LiquidColor.GREEN},
                {LiquidColor.YELLOW, LiquidColor.YELLOW, LiquidColor.RED, LiquidColor.GREEN}
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

        List<Step> steps = new ArrayList<>();
        boolean isSolved = SortWorker.sort(bottles, steps);
        Assert.assertFalse(isSolved);
    }

    @Test
    public void testSolveAlreadySolved() {
        int m = 3;
        int v = 4;
        int n = 4;
        LiquidColor[][] liquidColors = new LiquidColor[][]{
                {LiquidColor.RED, LiquidColor.RED, LiquidColor.RED, LiquidColor.RED},
                {LiquidColor.YELLOW, LiquidColor.YELLOW, LiquidColor.YELLOW, LiquidColor.YELLOW},
                {LiquidColor.GREEN, LiquidColor.GREEN, LiquidColor.GREEN, LiquidColor.GREEN}
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

        List<Step> steps = new ArrayList<>();
        boolean isSolved = SortWorker.sort(bottles, steps);
        Assert.assertTrue(isSolved);
    }
}
