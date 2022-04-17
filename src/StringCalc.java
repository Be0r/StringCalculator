import java.util.InputMismatchException;
import java.util.Scanner;


public class StringCalc {
    static Scanner scanner = new Scanner(System.in);
    static int number;
    static char operation;
    static String result = "";

    public static void main(String[] args) {
        System.out.println("Enter an expression [\"a\" + \"b\", \"a\" - \"b\", \"a\" *x, \"a\" /x] where a and b are strings, and x is a number from 1 to 10 + Enter");
//      Считываем строку userInput которую ввёл пользователь
        String userInput = scanner.nextLine();
        operation = metodOperation(userInput);
        splitLengthString(userInput);
    }

    //обрезаем на 40 символах
    private static void splitLengthString(String userInput) {
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
//      Заполняем символьный массив символами строки которую ввел пользователь и ищем знак операции
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
            case '+' -> result = num1 + num2;
            case '-' -> {
                for (int i = 0; i < num2.length(); ) {
                    char r = num2.charAt(i);
                    num1 = num1.replaceFirst("" + r, "");
                    num2 = num2.replaceFirst("" + r, "");
                }
                result = num1;
            }
        }
        return result;
    }

    public static String calculated(String num1, char op, int num) {
        switch (op) {
            case '*':
                if (num > 10) {
                    System.out.println("Слишком большая цифра");
                } else
                for (int u = 0; u < num; u++) {
                    result += num1;

//Не знаю как убрать null при умножении
                } break;
            case '/':
                if (num > 10) {
                    System.out.println("Слишком большая цифра");
                } else
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
                throw new IllegalArgumentException("Invalid operation sign");
        }
        return result;
    }
}