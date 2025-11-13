package org.example;

import java.util.List;

public class SortWorker {
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
