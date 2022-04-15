import org.jetbrains.annotations.NotNull;

import java.util.InputMismatchException;
import java.util.Scanner;


public class main {
    static Scanner scanner = new Scanner(System.in);
    static int number;
    static char operation;
    String a;
    String b;
    static String result;

    public static void main(String[] args) {
        System.out.println("Enter an expression [\"a\" + \"b\", \"a\" * x, \"a\" / x, \"a\" - \"b\"] where a and b are strings, and x is a number from 1 to 10 + Enter");
//      Считываем строку userInput которую ввёл пользователь
        String userInput = scanner.nextLine();
        operation = metodOperation(userInput);
        splitLengthString(userInput);
    }

    private static void splitLengthString(@NotNull String userInput) {
        String[] blocks = userInput.split("[+-/*\"]");
        if (blocks.length == 5) {
            String st01 = blocks[1];
            String st04 = blocks[4];
            result = calculated(st01, operation, st04);
            if (result.length() > 40) {
                String rez = result.substring(0, 40);
                System.out.println(rez + "...");
            } else {
                System.out.println(result);
            }
        } else {              //            blocks.length == 4;
            String st01 = blocks[1];
            String st03 = blocks[3];
            number = Integer.parseInt(st03);
            result = calculated(st01, operation, number);
            if (result.length() > 40) {
                String rez = result.substring(0, 40);
                System.out.println(rez + "...");
            } else {
                System.out.println(result);
            }
        }
    }

    //         Метод поиска знака операции
    private static char metodOperation(String userInput) {
        char[] uchar = new char[26];
//      Заполняем символьный массив символами строки которую ввел пользователь и по ходу ловим знак операции
        for (int i = 0; i < userInput.length(); i++) {
            uchar[i] = userInput.charAt(i);
            if (uchar[i] == '+') {
                operation = '+';
            }
            if (uchar[i] == '-') {
                operation = '-';
            }
            if (uchar[i] == '*') {
                operation = '*';
            }
            if (uchar[i] == '/') {
                operation = '/';
            }
        }
        return operation;
    }


    public static String calculated(String num1, char op, String num2) {
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                int resA = num1.length() - num2.length();
                if (num1.length() == num2.length()) {
                    result = "0";
                } else {
                    result = num1.substring(0, resA);
                }
        }
        return result;
    }

    public static String calculated(String num1, char op, int num) {
        switch (op) {
            case '*':
                for (int u = 0; u < num; u++) {
                    result = result + num1;
                } break;
            case '/':
                try {
                    int resB = num1.length() / num;
                    if (num1.length() == num) {
                        result = "1";
                    } else {
                        result = num1.substring(0, resB);
                    }
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception:" + e);
                    System.out.println("Only integer non-zero parameters allowed");
                    break;
                } finally {
                    if (num1.length() < num) {
                        System.out.println("Dividend is less than divisor");
                    }
                } break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
}
