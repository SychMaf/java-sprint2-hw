import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MonthlyReport {
    HashMap<Integer, ArrayList<Month>> monthAndMore = new HashMap<>();
    String[] nameMonth = {"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"};

    public void loadFile(int numOfMouth,String path){
        List<String> content = readFileContents(path);
        if (content == null){
            System.out.println("Файл " + path + " пуст");
            return;
        }

        ArrayList<Month> monthsSostav = new ArrayList<>();
        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity=Integer.parseInt(parts[2]);
            int sumOfOne=Integer.parseInt(parts[3]);
            Month month = new Month(itemName, isExpense, quantity, sumOfOne);
            monthsSostav.add(month);

        }
        monthAndMore.put(numOfMouth,monthsSostav);
    }

    public String poiskMouth(){
        String result = "";
        if (!monthAndMore.isEmpty()) {
            for (int numMonth : monthAndMore.keySet()) {

                int priblMax = 0, rashodMax = 0, pribl, rash;
                String maxTovar = "", minTovar = "";
                ArrayList<Month> monthCikl = monthAndMore.get(numMonth);

                for (int i = 0; i < monthCikl.size(); i++) {
                    Month cikl = monthCikl.get(i);
                    if (cikl.isExpense == false) {
                        pribl = cikl.getTotalSum();
                        if (pribl >= priblMax) {
                            priblMax = pribl;
                            maxTovar = cikl.itemName;
                        }
                    } else {
                        rash = cikl.getTotalSum();
                        if (rash >= rashodMax) {
                            rashodMax = rash;
                            minTovar = cikl.itemName;
                        }
                    }
                }
                result += nameMonth[numMonth-1] + " месяц \n Cамый прибыльный пункт: " + maxTovar + " Принес: " + priblMax + "\n Самый убыльный пункт: " + minTovar + " Убыток: " + rashodMax + "\n";
            }
            return result;
        }
        else{
            result +="Файлы не считанны";
            return result;
        }
    }

    public List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}
