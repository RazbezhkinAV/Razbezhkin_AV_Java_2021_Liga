package lesson2.Calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static int[][] matrixMonth = new int[5][7];

    public static void main(String... args) {
        //System.out.println(DateTimeFormatter.ISO_INSTANT.format(Calendar.getInstance().toInstant()));

        // Отобразите календарь текущего месяца в консоли
        // например:
        // пн вт ср чт пт сб вс
        // 30 31 1  2  3  4  5
        // и т.д.
        //
        showCurrentMonth();
    }

    public static void showCurrentMonth() {
        Calendar calendar = new GregorianCalendar();
        updateMatrix(calendar);
    }


    private static void updateMatrix(Calendar calendar) {
        int firstDayOfWeek = firstDateOfMonth(calendar); // численное обозначение названия первого дня месяца
        int dayPerMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // кол-во дней в месяце
        int previousMont = lastDayPreviousMonth(calendar); // кол-во дней в прошлом месяце

        int count = 1; //счётчик

        int[][] matrix = new int[6][7];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 && j < firstDayOfWeek - 1) {
                    matrix[i][j] = previousMont - ((firstDayOfWeek-2) - j);
                }
                if (i == 0 && j >= firstDayOfWeek - 1) {
                    matrix[i][j] = count++;
                }
                if (i != 0 && count <= dayPerMonth) {
                    matrix[i][j] = count++;
                }
                if (count > dayPerMonth) count = 1;
            }
        }

        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println();
        System.out.println(Stream.of(DayOfWeek.values()).
                map(DayOfWeek::name).
                collect(Collectors.joining("\t")));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // преобразование дня недели из полученных в Calendar , где день недли начинается с Понедельника.
    private static int convertDayOfWeek(int day) {
        if (day == 1) return 7;
        else return day - 1;
    }

    // преобразование месяца из полученных в Calendar.
    private static int convertMonth(int month) {
        return month + 1;
    }

    // порядковый номер наименования первого дня недели.
    private static int firstDateOfMonth(Calendar calendar) {
        calendar.set(Calendar.DATE, 1);
        return convertDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
    }

    private static int lastDayPreviousMonth(Calendar calendar) {
        calendar.add(Calendar.MONTH, -1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
