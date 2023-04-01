import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Дан массив целых чисел nums. Напишите программу, выводящую минимальное количество ходов,
 * требуемых для приведения всех элементов к одному числу. За один ход можно уменьшить или
 * увеличить число массива на 1.
 * Пример:
 * nums = [1, 2, 3]
 * Решение: [1, 2, 3] => [2, 2, 3] => [2, 2, 2]
 * Минимальное количество ходов: 2
 * Элементы массива читаются из файла, переданного в качестве аргумента командной строки.
 * Пример:
 * На вход подаётся файл с содержимым:
 *
 * 1
 * 10
 * 2
 * 9
 *
 * Вывод в консоль:
 * 16
 **/
public class Task2 {

    public static void main(String[] args) {

        // Условие if для самопроверки и автотеста.
        if (args.length == 0) {
            resultForAdmin(getDefaultData());
        } else {
            resultForAutoTest(getDataFromFile(args));
        }

    }

    /**
     * Дефолтные данные
     **/
    private static List<Integer> getDefaultData() {
        ArrayList listDefault = new ArrayList<>();
        listDefault.add(1);
        listDefault.add(10);
        listDefault.add(2);
        listDefault.add(9);
        return listDefault;
    }

    /**
     * Метод с визуализацией выполнения программы.
     * Для самопроверки
     * Способ вычисления не соответствует полностью условию задания.
     **/
    private static void resultForAdmin(List<Integer> listDefaultData) {
        System.out.println("\nДан массив:" + listDefaultData);
        ArrayList<Integer> variantsAdmin = new ArrayList<>();
        List<Integer> copyListDefaultData = listDefaultData;
        ArrayList<Integer> listValueByTask = new ArrayList<>();

        // Вычисления ходов без изменения елементов на единицу.
        // Сделал с подсчетом разницы между сравниваемыми елементами массива.
        // В цикле производится подсчет необходимого количества ходов для
        // приведения каждого элемента к другим с учетом положительных и отрицательных.
        // Другой способ через медиану. Не реализован.
        for (int i = 0; i < listDefaultData.size(); i++) {
            int count = 0;

            for (int j = 0; j < listDefaultData.size(); j++) {

                if (listDefaultData.get(j) < 0) {
                    count = count + (Math.abs(listDefaultData.get(j)) + Math.abs(listDefaultData.get(i)));

                }
                if (listDefaultData.get(j) >= 0) {
                    count = count + Math.abs(listDefaultData.get(j) - listDefaultData.get(i));

                }

            }
            variantsAdmin.add(count);


        }
        System.out.println("\nРезультаты вычислений:\n");
        for (int i = 0; i < variantsAdmin.size(); i++) {


            for (int j = 0; j < listDefaultData.size(); j++) {
                if (i == j) {
                    System.out.println(variantsAdmin.get(i) + " - это количество проходов для числа    " +
                            listDefaultData.get(j) +
                            "    до выравнивания значений. Порядковый номер элемента: " + (i));
                }

            }
        }
        System.out.println("\nВаринаты проходов по каждому элементу массива: " + variantsAdmin);
        System.out.println("\nВаринаты соответствующие условию задания (...минимальное количество ходов...): ");
        for (int i = 0; i < variantsAdmin.size(); i++) {
            if (variantsAdmin.get(i) == Collections.min(variantsAdmin)) {
                System.out.println(
                        "    Минимальное количество ходов достигается при приведении к числу: " +
                                listDefaultData.get(i) + " ; номер элемента из массива: " + i);
            }
        }

        System.out.println("\nОтвет: минимальное количество ходов = " +
                Collections.min(variantsAdmin));
    }

    /**
     * Метод с введенными аргументами в cmd.
     * Для автотеста.
     * Способ вычисления не соответствует полностью условию задания.
     **/
    private static void resultForAutoTest(List<Integer> listData) {

        ArrayList<Integer> variantsAutoTest = new ArrayList<>();

        // Вычисления ходов без изменения елементов на единицу.
        // Сделал с подсчетом разницы между сравниваемыми елементами массива.
        // В цикле производится подсчет необходимого количества ходов для
        // приведения каждого элемента к другим с учетом положительных и отрицательных.
        // Другой способ через медиану. Не реализован.
        for (Integer value : listData) {
            int count = 0;


            for (int i = 0; i < listData.size(); i++) {

                if (listData.get(i) < 0) {
                    count = count + (Math.abs(listData.get(i)) + Math.abs(value));

                }
                if (listData.get(i) >= 0) {
                    count = count + Math.abs(listData.get(i) - value);

                }
            }
            variantsAutoTest.add(count);
        }

        System.out.println(Collections.min(variantsAutoTest));
    }


    /**
     * Чтение и обработка данных из файла
     **/
    private static List<Integer> getDataFromFile(String[] args) {
        ArrayList<Integer> numsArrayList = new ArrayList<>();

        boolean isRead = false;
        String path;
        path = args[0];
        File file;

        // Обработка данных:
        // первое исключение  - относительный путь к файлу отправленный в командную строку;
        // второе исключение  - полный путь к файлу ИЗ СИСТЕМЫ после ошибки, на всякий случай.
        try {
            file = new File(path);
            isRead = true;
        } catch (Exception e) {
            file = new File(System.getProperty("user.dir") + "\\" + path);
        }

        if (isRead) {
            Scanner scanner;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (scanner.hasNextLine()) {
                int line = Integer.parseInt(scanner.nextLine());
                numsArrayList.add(line);
            }
        }
        return numsArrayList;
    }

}
