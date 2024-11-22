package train.calculator;

import java.io.PrintStream;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.print("Введите необходимое количество ячеек памяти:  ");
        int maxMemorySize = reader.nextInt();
        CalculationList currentCalculating = new CalculationList(maxMemorySize);
        while (true) {
            if (currentCalculating.getSize() == maxMemorySize) {
                System.out.println("Память заполнена");
                closeCalculator(currentCalculating, reader);
                break;
            }

            System.out.print("Введите первое число или 'Стоп' для выхода:  ");
            String input = reader.next();
            if (input.equalsIgnoreCase("Стоп")) {
                closeCalculator(currentCalculating, reader);
                break;
            } else {
                reader.nextLine();
                try {
                    double first = Double.parseDouble(input);
                    System.out.print("Число: " + first);
                    System.out.print(" | Введите второе число:  ");
                    double second = reader.nextDouble();
                    reader.nextLine();
                    System.out.print("Число: " + second);
                    System.out.print(" | Введите оператор: (+, -, *, /): ");
                    char operator = reader.next().charAt(0);

                    double result = 0.0;
                    boolean ifResult = true;

                    switch (operator) {
                        case '+':
                            result = currentCalculating.addition(first, second);
                            break;

                        case '-':
                            result = currentCalculating.subtraction(first, second);
                            break;

                        case '*':
                            result = currentCalculating.multiplication(first, second);
                            break;

                        case '/':
                            try {
                                result = currentCalculating.division(first, second);
                            } catch (RuntimeException error) {
                                ifResult = false;
                                System.out.println(error.getMessage());
                            }
                            break;


                        default:
                            System.out.println("Введите корректный оператор");
                            ifResult = false;
                            break;
                    }
                    if (ifResult) {
                        System.out.printf("%.1f %c %.1f = %.1f", first, operator, second, result);
                        System.out.println();
                    }

                } catch (NumberFormatException error) {
                    System.out.println("Ошибка: введите корректное число");
                }
            }

        }
    }

    private static void closeCalculator(CalculationList currentCalculating, Scanner reader) {
        reader.close();
        currentCalculating.printingResultList();
        System.out.println("Работа завершена");
    }
}

/*
 *     ДЗ
 *     1. добавьте массив для сохранения результатов размерностью 10
 *     если результатов стало больше мы завершаем работы, информируя пользователя и распечатывая результаты
 *
 *     2. поместите код в цикл для возможности использования без постоянного запуска программы.
 *     Для выхода пусть буду слова "выход"
 *     T.е. пользователь ввел выход - мы просто выходим, сохраняя результат в массиве результатов и выводим массив на консоль.
 *
 *
 */
