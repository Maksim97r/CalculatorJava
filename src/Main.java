
import java.util.Scanner;

public class Main {

    static String[] romanNumbers = new String[] {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static int calc(String input) {
        String[] splitInput = input.split(" ");

        if (splitInput.length > 3) {
            throw new ExpressionException("throws Exception //т.к формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (splitInput.length < 3) {
            throw new ExpressionException("throws Exception //т.к строка не является математической операцией");
        }

        String firstElement = splitInput[0];
        String operation = splitInput[1];
        String secondElement = splitInput[2];

        String firstElementType = checkType(firstElement);
        String secondElementType = checkType(secondElement);

        if (firstElementType.equals("Unknown") || secondElementType.equals("Unknown")) {
            throw new ExpressionException("throws Exception //т.к. не соответствует условиям задачи");
        } else if (!firstElementType.equals(secondElementType)){
            throw new ExpressionException("throws Exception //т.к. используются одновременно разные системы счисления");
        }

        int firstElementValueArabic, secondElementValueArabic;
        int firstElementValueRoman, secondElementValueRoman;
        if (firstElementType.equals("Arabic")) {
            firstElementValueArabic = transformationArabic(firstElement);
            secondElementValueArabic = transformationArabic(secondElement);
            if (firstElementValueArabic < 10 && firstElementValueArabic > 1 && secondElementValueArabic < 10 && secondElementValueArabic > 1){
                switch (operation) {
                    case "+": {return (firstElementValueArabic + secondElementValueArabic);}
                    case "-": {return firstElementValueArabic - secondElementValueArabic;}
                    case "/": {return firstElementValueArabic / secondElementValueArabic;}
                    case "*": {return firstElementValueArabic * secondElementValueArabic;}
                    default: {throw new ExpressionException("throws Exception //т.к. неизвестная операция");}
                }

            }else {
                throw new ExpressionException("throws Exception //т.к. заданное число не соответствует условиям задачи");


            }

        } else if (firstElementType.equals("Roman")) {
            firstElementValueRoman = transformationRoman(firstElement);
            secondElementValueRoman = transformationRoman(secondElement);
            switch (operation) {
                case "+": {return firstElementValueRoman + secondElementValueRoman;}
                case "-": {if(firstElementValueRoman > secondElementValueRoman){return firstElementValueRoman - secondElementValueRoman;}else{throw new ExpressionException("trows Exception //т.к в римской системе нет отрицательных чисел");}}
                case "/": {return firstElementValueRoman / secondElementValueRoman;}
                case "*": {return firstElementValueRoman * secondElementValueRoman;}
                default: {throw new ExpressionException("throws Exception //т.к. неизвестная операция");}

            }
        } else{
            throw new ExpressionException("throws Exception //т.к. неизвесный тип элемента");
        }
    }





    static String checkType(String element) {
        boolean isArabic = true;
        for (int i = 0; i < element.length(); i++) {
            if (!Character.isDigit(element.charAt(i))) {
                isArabic = false;
                break;
            }
        }
        if (isArabic) {
            return "Arabic";
        }

        boolean isRoman = false;
        for (int i = 0; i < 11; i++) {
            if (element.equals(romanNumbers[i])) {
                isRoman = true;
                break;
            }
        }
        if (isRoman) {
            return "Roman";
        }

        return "Unknown";
    }

    static String checkType2(String element) {
        String[] splitInput = element.split(" ");

        String firstElement1 = splitInput[0];
        String secondElement2 = splitInput[2];

        boolean isRoman1 = false;
        for (int i = 0; i < romanNumbers.length; i++) {
            if (firstElement1.equals(romanNumbers[i])) {
                isRoman1 = true;
                break;
            }
        }
        boolean isRoman2 = false;
        for (int i = 0; i < romanNumbers.length; i++) {
            if (secondElement2.equals(romanNumbers[i])) {
                isRoman2 = true;
                break;
            }
        }
        if (isRoman1 && isRoman2 && true) {
            return "Roman";
        } else {
            return "Arabic";
        }
    }

    static int transformationArabic(String arabic) {
        return Integer.parseInt(arabic);
    }

    static int transformationRoman(String roman){
        for(int i = 0; i < romanNumbers.length; i++){
            if (roman.equals(romanNumbers[i])){
                return i;
            }
        }

        throw new IllegalArgumentException("throw Exception //т.к. введеное значение не соответствует условию задачи");
    }



    public static void main(String[] args) {

        System.out.println("Введите пример:");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        if (checkType2(str).equals("Roman")) {
            System.out.println(romanNumbers[calc(str)]);
        }
        else if (checkType2(str).equals("Arabic")){
            System.out.println(calc(str));
        }

    }
}
class ExpressionException extends RuntimeException {
    public ExpressionException() {}

    public ExpressionException(String message) {
        super(message);
    }
}