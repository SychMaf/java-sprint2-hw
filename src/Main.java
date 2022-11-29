import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        YearlyReport yearManager = new YearlyReport();
        MonthlyReport monthManager = new MonthlyReport();

        Checker checker = new Checker(monthManager, yearManager);

        while (true){
            printMenu();
            int command = scan.nextInt();

            if (command ==1){
                for (int i = 1; i < 4; i++) {
                    monthManager.loadFile(i,"resources/m.20210" + i + ".csv");
                }
                System.out.println("Месячные отчеты считанны.");
            }

            else if (command ==2){
                yearManager.loadFile("resources/y.2021.csv");
                System.out.println("Годовой отчет считан.");
            }

            else if (command ==3){
                String answer = checker.check();
                System.out.println(answer);
            }

            else if (command ==4){
                System.out.println(monthManager.poiskMouth());
            }

            else if (command ==5){
                System.out.println(yearManager.infYear());
            }

            else if (command ==0){
                System.out.println("Выход.");
                break;
            } else
                System.out.println("Введенно некорректное значение.");
        }

    }

    public static void printMenu(){
        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("0. Выйти из приложения");
    }
}
