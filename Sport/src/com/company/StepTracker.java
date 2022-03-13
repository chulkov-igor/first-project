package com.company;
import java.util.HashMap;
import java.util.Scanner;

public class StepTracker {
    boolean tracker = true;
    private int target = 10000;

    private final static int stepToCal = 50;
    private final static double stepToSm = 75;

    HashMap<Integer, MonthData> monthToData = new HashMap<Integer, MonthData>();

    public StepTracker() {
        for (int i = 0; i < 12; i++) {
            monthToData.put(i, new MonthData());
        }
    }

    class MonthData {
        int[] daysStep = new int[30];

        public MonthData() {
            while (tracker) {
                getFirstMenu();
            }
        }

        public void getFirstMenu() {
            System.out.println("1 - Ввести количество шагов за определённый день");
            System.out.println("2 - Напечатать статистику за определённый месяц");
            System.out.println("3 - Изменить цель по количеству шагов в день");
            System.out.println("0 - Выйти из приложения.");
            Scanner scanner = new Scanner(System.in);
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("Выбирите день");
                    int day = scanner.nextInt();
                    System.out.println("Введите количество шагов");
                    int step = scanner.nextInt();
                    daysStep[day-1] = step;
                    break;
                case 2:
                    String result = "";
                    int allSteps = 0;
                    int maxSteps = 0;
                    int series = 0;
                    int maxSeries = 0;
                    for (int i = 0; i < 30; i++) {
                        result = (i + 1 + " день: " + daysStep[i] + ", ");
                        allSteps = allSteps + daysStep[i];
                        if (maxSteps < daysStep[i]) {
                            maxSteps = daysStep[i];
                        }
                        if (daysStep[i] >= target) {
                            series = series + 1;
                        } else {
                            if (series > maxSeries) {
                                maxSeries = series;
                            }
                            series = 0;
                        }
                        System.out.println(result.substring(0, result.length() - 2));
                    }

                    System.out.println(allSteps);
                    System.out.println(maxSteps);
                    System.out.println(allSteps / 30 + " : среднее количество шагов в день");
                    System.out.println(allSteps * stepToSm / 100000 + " киллометров");
                    System.out.println(allSteps * stepToCal / 1000 + " киллокалорий");
                    System.out.println(maxSeries);
                    break;
                case 3:
                    int digit = scanner.nextInt();
                    if (digit < 0) {
                        System.out.println("Неправильное значение");
                    } else {
                        target = digit;
                    }
                    break;
                case 0:
                    System.out.println("Выход из программы");
                    tracker = false;
                    break;
            }
        }
    }
}