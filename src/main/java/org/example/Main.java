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

        List<Step> steps = new ArrayList<>();
        boolean isSolved = sort(bottles, steps);
        printResult(isSolved, bottles, steps);
    }

    /**
     * Рекурсивный метод сортировки жидкостей
     *
     * @param bottles сосуды
     * @param steps   шаги, приводящие к отсортированному состоянию
     * @return true - если последний шаг привел к отсортированному состоянию<br/>
     * false - если после последнего шага не осталось ходов и не все сосуды приведены к отсортированному состоянию
     */
    public static boolean sort(Bottle[] bottles, List<Step> steps) {
        if (isAllSorted(bottles)) {
            return true;
        }
        int n = bottles.length;
        for (int i = 0; i < n; i++) {
            if (bottles[i].isSorted() || bottles[i].isEmpty()) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (i != j && bottles[i].isNeedTransfuseTo(bottles[j])) {
                    int dropCount = bottles[i].transfuse(bottles[j]);
                    steps.add(new Step(i, j));
                    if (sort(bottles, steps)) {
                        return true;
                    } else {
                        steps.remove(steps.size() - 1);
                        bottles[i].cancelTransfuse(bottles[j], dropCount);
                    }
                }
            }
        }
        return false;
    }

    /**
     * Проверяет что задача решена и все сосуды находятся в отсортированном состоянии
     *
     * @param bottles сосуды
     * @return true - если все сосуды отсортированы
     */
    public static boolean isAllSorted(Bottle[] bottles) {
        for (Bottle bottle : bottles) {
            if (!bottle.isSorted()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Выводит результат
     *
     * @param isSolved Найдено ли было решение
     * @param bottles  конечное состояние сосудов
     * @param steps    шаги приведшие к конечному состоянию
     */
    public static void printResult(boolean isSolved, Bottle[] bottles, List<Step> steps) {
        if (isSolved) {
            System.out.printf("Total steps: %d%n", steps.size());
            steps.forEach(System.out::println);
            System.out.println();
            System.out.println("Sorted state:");
            for (Bottle bottle : bottles) {
                System.out.println(bottle);
            }
        } else {
            System.out.println("No solutions");
        }
    }
}