package org.example;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Bottle {
    private final List<LiquidColor> colors;
    private final int capacity;

    public Bottle(int capacity) {
        this.colors = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    /**
     * Переливает жидкость из текущего сосуда в destBottle по следующим правилам:<br/>
     * 1. переливать можно только верхние капли и только одного цвета<br/>
     * 2. если в пробирке A сверху находится более одной капли одного цвета, их можно перелить либо все, либо столько, сколько есть места в пробирке B
     *
     * @param destBottle сосуд в который переливаем
     * @return целое число перелитых капель
     */
    public int transfuse(Bottle destBottle) {
        LiquidColor color = getTop();
        int dropCount = Math.min(canTransfuseCount(), destBottle.freeCapacity());
        doTransfuse(destBottle, color, dropCount);
        return dropCount;
    }

    /**
     * Переливает жидкость из текущего сосуда в destBottle
     *
     * @param destBottle сосуд в который переливаем
     * @param color      цвет переливаемых капель
     * @param dropCount  количество переливаемых капель
     */
    private void doTransfuse(Bottle destBottle, LiquidColor color, int dropCount) {
        for (int i = 0; i < dropCount; i++) {
            colors.remove(colors.size() - 1);
            destBottle.fill(color);
        }
    }

    /**
     * Наполняет сосуд одной каплей жидкости
     *
     * @param color цвет жидкости
     */
    public void fill(LiquidColor color) {
        colors.add(color);
    }

    /**
     * @return true - если сосуд пустой
     */
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(colors);
    }

    /**
     * @return true - если сосуд заполнен
     */
    public boolean isFilled() {
        return colors.size() == capacity;
    }

    /**
     * @return количество капель, которые еще можно налить в сосуд
     */
    private int freeCapacity() {
        return capacity - colors.size();
    }

    /**
     * Проверяет, нужно ли перелить жидкость из текущего сосуда в destBottle
     *
     * @return true - если в пробирке A есть жидкость и если выполняется 1 или 2 условие:<br/>
     * 1. пробирка B пуста и после переливания пробирка А не будет пуста
     * 2. пробирка B не пуста и верхняя жидкость в пробирках одного цвета и после переливания в верху пробирки А будет жидкость другого цвета
     */
    public boolean isNeedTransfuseTo(Bottle destBottle) {
        return !isEmpty() &&
                (destBottle.isEmpty() && this.canTransfuseCount() != colors.size()) ||
                (!destBottle.isEmpty() && this.getTop().equals(destBottle.getTop()) && destBottle.freeCapacity() >= this.canTransfuseCount());
    }

    /**
     * @return количество капель которые можно перелить
     */
    private int canTransfuseCount() {
        LiquidColor color = getTop();
        int transfuseCount = 1;
        for (int i = colors.size() - 2; i >= 0; i--) {
            if (colors.get(i).equals(color)) {
                transfuseCount++;
            } else {
                break;
            }
        }
        return transfuseCount;
    }

    /**
     * @return цвет верхней капли или null если пробирка пустая
     */
    private LiquidColor getTop() {
        return isEmpty() ? null : colors.get(colors.size() - 1);
    }

    /**
     * @return true - если пробирка заполнена каплями одного цвета
     */
    public boolean isSorted() {
        if (isEmpty()) {
            return true;
        }
        if (!isFilled()) {
            return false;
        }
        LiquidColor topColor = getTop();
        for (LiquidColor color : colors) {
            if (!color.equals(topColor)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Переливает жидкость обратно из destBottle в текущий сосуд
     *
     * @param destBottle сосуд в который изначально переливали жидкость
     * @param dropCount  число капель перелитых в destBottle
     */
    public void cancelTransfuse(Bottle destBottle, int dropCount) {
        destBottle.doTransfuse(this, destBottle.getTop(), dropCount);
    }

    @Override
    public String toString() {
        return "Bottle{" +
                "colors=" + colors +
                '}';
    }
}
