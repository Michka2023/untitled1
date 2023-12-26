import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();// принимаем введенное с клавиатуры выражение
        String[] w = input.split("(?=[+\\-*/])|(?<=[+\\-*/])");// представляем введенное выражение в качестве строки и разбиваем его на переменные
        String word1 = w[0];
        String word2 = w[2];
        String operand = w[1];
        if (w.length > 3){
            throw new IllegalArgumentException("введенная математическая опперация не соответствует заданию");
        }
        String bet= word1.replaceAll("(\\p{Punct}+$|^\\p{Punct}+)","");//удаляяем все знаки препинания
        String surf= bet.trim();//  убираем пробелы
        if(surf.length()>10){// проверряем что введенноя строка не более 10 символов
            throw new IllegalArgumentException("вы ввели 1 выражение,более 10 символов");
        }
        String bet2= word2.replaceAll("(\\p{Punct}+$|^\\p{Punct}+)","");//удаляяем все знаки препинания
        String surf2= bet2.trim();//  убираем пробелы
        if(word2.length()>10) {
            throw new IllegalArgumentException("вы ввели 2 выражение,более 10 символов");
        }
        if (word1.startsWith("\"") && word1.endsWith("\"")) {
            Calkulat colc = new Calkulat(word1, word2, operand);
            switch (operand) {
                case "+":
                    System.out.println("\"" + colc.addition() + "\"");
                    break;
                case "*":
                    String result = colc.multiplication();
                    int quantity = result.length();
                    if (quantity <= 40) {
                        System.out.print("\"" + result + "\"");
                    } else {
                        System.out.print("\"" + result + "..." + "\"");
                    }
                    break;
                case "-":
                    System.out.print("\"" + colc.subtraction() + "\"");
                    break;
                case "/":
                    System.out.print("\"" + colc.division() + "\"");
                    break;
                default:
                    throw new IllegalArgumentException("не соответствующая операция");
            }
        } else {
            throw new IllegalArgumentException("выражение должно быть заключено в кавычки");
        }
    }

}
class Calkulat {
    private String word1;
    private String word2;
    private String operand;
    private String[] a = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public Calkulat(String word1, String word2, String operand) {
        this.word1 = word1.substring(1, word1.length() - 1);
        this.word2 = word2;
        this.operand = operand;

    }


    String multiplication() {
        String name = word1;
        if ((Arrays.asList(a).contains(word2))) {
            int variable = Integer.parseInt(word2);
            for (int i = 1; i < variable; i++) {
                name = name + word1;
            }
        } else {
            throw new IllegalArgumentException("не подходящее число");
        }
        return name;

    }

    String subtraction() {
        if (word2.startsWith("\"") && word2.endsWith("\"")) {
            word2 = word2.substring(1, word2.length() - 1);
            String  name2 = word1;
            if (word1.contains(word2)) {
                name2 = word1.replace(word2, "");
                return name2;
            } else {
                return name2;
            }
        }else {
            throw new IllegalArgumentException("выражение должно быть заключено в кавычки");
        }
    }
    String division() {
        String between = word1.replaceAll("(\\p{Punct}+$|^\\p{Punct}+)","");//удаляяем все знаки препинания
        String name3= between.trim();//  убираем пробелы
        int variable = 0;
        if ((Arrays.asList(a).contains(word2))) {
            variable = Integer.parseInt(word2);
        }else {
            throw new IllegalArgumentException("не подходящее число");
        }
        if (variable <= name3.length()){
            name3 = word1.substring(0, name3.length() - variable);// отнимаем необходимое количество символов из строки
        } else {
            throw new IllegalArgumentException("введенное число больше количества символов в строке");
        }
        return name3;
    }
    String addition(){
        String name4 = null;
        if (word2.startsWith("\"") && word2.endsWith("\"")) {// проверяем что второе выражение напечатано в кавычках
            word2 = word2.substring(1, word2.length() - 1);// убираем кавычки у второго выражения
            name4 = word1 + word2;
        } else {
            throw new IllegalArgumentException("выражение должно быть заключено в кавычки");
        }
        return name4;
    }

}