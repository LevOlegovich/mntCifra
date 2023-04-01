import java.util.Arrays;

/**
 * Круговой массив - массив из элементов, в котором по достижению конца массива следующим
 * элементом будет снова первый. Mассив задается числом n, то есть представляет собой числа от 1
 * до n.
 * Пример кругового массива для n=3: 1231231
 * Напишите программу, которая выводит путь, по которому, двигаясь интервалом длины m по
 * заданному массиву, концом будет являться первый элемент.
 * Началом одного интервала является конец предыдущего. Путь - массив из начальных элементов
 * полученных интервалов.
 * Пример 1:
 * n = 4, m = 3
 * Решение:
 * Круговой массив: 1234. При длине обхода 3 получаем интервалы: 123, 341.
 * Полученный путь: 13.
 *
 *
 *
 * Замечания: программа реализована:
 * 1. с аргументами через cmd для автотеста;
 * 2. с дефолтными данными для самопроверки.
 **/

public class Task1 {

    public static void main(String[] args) {

        int n; // количество элементов для массива
        int m; // интервал движения по круговому массиву

        // Обработка входных данных
        if (args.length == 0) {
            n = 5;
            m = 4;
            resultForAdmin(n, m); // метод с визуализацией вывода с дефолтными данными. Реализован для удобства самопроверки
        } else {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
            resultForAutoTest(n, m);
        }


    }

    /**
     * Метод с визуализацией без аргументов командной строки.
     * Для самопроверки.
     **/
    private static void resultForAdmin(int n, int m) {
        int[] arrayCirc = new int[n]; // объявление массива из n элементов


        // заполнение массива числами от 1 до n включительно
        for (int i = 0; i < n; i++) {
            arrayCirc[i] = i + 1;
        }
        System.out.println("Дан круговой массив: " + Arrays.toString(arrayCirc));

        int[] arrayFromArrayCirc = new int[m]; // подмассив состоящий из количества элементов равных интервалу
        String way = null;
        int count = 0; // счетчик
        int numberElementOfArray = 0;  // переменная для извлечения i-го элемента из кругового массива

        do {

            // В этом цикле for достаем подмассив из кругового массива.
            // Выборка элементов для него происходит с учетом интервала m.
            for (int j = 0; j < arrayFromArrayCirc.length; j++) {
                arrayFromArrayCirc[j] = arrayCirc[numberElementOfArray];
                numberElementOfArray = numberElementOfArray + 1;

                // Если массив с интервалом заполнился,
                // убавляем значение numberElementOfArray,
                // чтобы у следующего массива первый элемент был равен последнему элементу из предыдущего
                if (j == arrayFromArrayCirc.length - 1) {
                    numberElementOfArray = numberElementOfArray - 1;
                }

                // Проверка конца кругового массива.
                // Обнуление номера для возврата к первому элементу кругового массива
                if (numberElementOfArray == arrayCirc.length) {
                    numberElementOfArray = 0;
                }
            }
            ++count;
            System.out.println(count + "-й подмассив с учетом интервала " +
                    m +
                    ": " +
                    Arrays.toString(arrayFromArrayCirc));

            // Обновление значений пути
            if (way == null) {
                way = String.valueOf(arrayFromArrayCirc[0]);
            } else {
                way = way + arrayFromArrayCirc[0];
            }

            // Выход из цикла, если последний элемент ПОДМАССИВА совпал с первым элементом КРУГОВОГО массива
        } while (arrayFromArrayCirc[m - 1] != arrayCirc[0]);

        System.out.println("Путь при условии задания ( ...концом должен являться первый элемент...): " + way); // вывод сформированного пути
    }


    /**
     * Метод с введенными аргументами в cmd.
     * Для автотеста.
     */
    private static void resultForAutoTest(int n, int m) {
        int[] arrayCirc = new int[n]; // объявление массива из n элементов


        // заполнение массива числами от 1 до n включительно
        for (int i = 0; i < n; i++) {
            arrayCirc[i] = i + 1;
        }
        int[] arrayFromArrayCirc = new int[m];
        String way = null;
        int numberElementOfArray = 0;  // переменная для извлечения i-го элемента из кругового массива

        do {

            // В этом цикле for достаем подмассив из кругового массива.
            // Выборка элементов для него происходит с учетом интервала m.
            for (int j = 0; j < arrayFromArrayCirc.length; j++) {
                arrayFromArrayCirc[j] = arrayCirc[numberElementOfArray];
                numberElementOfArray = numberElementOfArray + 1;

                // Если массив с интервалом заполнился,
                // убавляем значение numberElementOfArray,
                // чтобы у следующего массива первый элемент был равен последнему элементу из предыдущего
                if (j == arrayFromArrayCirc.length - 1) {
                    numberElementOfArray = numberElementOfArray - 1;
                }

                // Проверка конца кругового массива.
                // Обнуление номера для возврата к первому элементу кругового массива
                if (numberElementOfArray == arrayCirc.length) {
                    numberElementOfArray = 0;
                }
            }
            // Обновление значений пути
            if (way == null) {
                way = String.valueOf(arrayFromArrayCirc[0]);
            } else {
                way = way + arrayFromArrayCirc[0];
            }


            // Выход из цикла, если последний элемент ПОДМАССИВА совпал с первым элементом КРУГОВОГО массива
        } while (arrayFromArrayCirc[m - 1] != arrayCirc[0]);

        System.out.println(way); // вывод сформированного пути
    }


    /**
     * 2-й способ извлечения. Неиспользуемый метод.
     * Вариант с меньшим количеством вычислений.
     * Для заметки: выбрал первый способ т.к он более понятен в обьяснении))).
     * При необходимости можно заменить метод "resultForAdmin(n, m)" на данный.
     */

    private static void resultPerfomanseMashine(int n, int m) {
        int[] arrayCirc = new int[n]; // объявление массива из n элементов


        // заполнение массива числами от 1 до n включительно
        for (int i = 0; i < n; i++) {
            arrayCirc[i] = i + 1;
        }
        int count = 1; // счетчик интервала m

        String route = String.valueOf(arrayCirc[0]); // начальный путь

        int i = 0; // переменная для извлечения извлечения числа из массива
        int number;  // извлеченное число

        boolean isFind = false; // флажок для проверки соответсвия условию задания
        do {
            count = count + 1;

            // вычисления для возобновления с учетом достижения конечного элемента массива
            if (i < n - 1) {
                i = i + 1;
            } else {
                i = 0;
            }

            number = arrayCirc[i]; // первое значение из массива разделенного интервалом движения

            // Операция при несоответвии условию задания ( ...концом должен являться первый элемент...)
            // Обновляется путь
            if (count == m && number != arrayCirc[0]) {
                count = 1;
                route = route + number;

            }

            // Операция при выполнении условия задания ( ...концом должен являться первый элемент...)
            // Флажок переводим в значение true для завершения цикла.
            if (number == arrayCirc[0] && count == m) {
                System.out.println(route);
                isFind = true;
            }

        } while (!isFind); // проверка значения флажка
    }
}
