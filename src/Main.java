import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите желаемую операцию");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(calc(input));
    }
    public static String calc(String input) {
        int firstNumber;
        int secondNumber;
        String operation;
        String[] stringsplit = input.split(" ");
        boolean flag = false;
        if (stringsplit.length != 3) {
            throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }


        try {
            firstNumber = Integer.parseInt(stringsplit[0]);
            operation = stringsplit[1];
            secondNumber = Integer.parseInt(stringsplit[2]);

        } catch (IllegalArgumentException e) {
            try {
                RomanNumbers romanNumbers = RomanNumbers.valueOf(stringsplit[0]);
                firstNumber = romanNumbers.getArabNumber();
                operation = stringsplit[1];
                romanNumbers = RomanNumbers.valueOf(stringsplit[2]);
                secondNumber = romanNumbers.getArabNumber();
                flag = true;
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        }
        if (firstNumber < 1 || firstNumber > 10 || secondNumber < 1 || secondNumber > 10) {
            throw new RuntimeException("введенные числа не попадают в ряд чисел от 1 до 10");
        }

        int result;
        // вычисление значения
        switch (operation){
            case "/":
                result = firstNumber / secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "+":
                result = firstNumber + secondNumber;
                break;
            default:
                throw new RuntimeException("формат математической операции не удовлетворяет заданию");
        }

        if (flag)
            if (result < 1)
                throw new RuntimeException("В римской системе исчесления нет отриц. чисел");
            else
                return ArabNumber.getByOrdinal(result).toString();
        return String.valueOf(result);
    }

}