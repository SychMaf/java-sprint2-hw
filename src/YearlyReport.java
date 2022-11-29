import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {

    public ArrayList<Year> years = new ArrayList<>();
    public void loadFile(String path) {
        String content =readFileContents(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean  isExpense = Boolean.parseBoolean(parts[2]);

            Year year = new Year(month, amount, isExpense);
            years.add(year);
        }
    }

    public String infYear() {
        String backResult = "2021 год \n";
        if (!years.isEmpty()) {
            int srPribl = 0, srRash = 0, peremenaya = 0;

            for (int i = 0; i < years.size(); i++) {
                Year cickl = years.get(i);
                if (!cickl.isExpense) {
                    peremenaya += cickl.amount;
                    srPribl += cickl.amount;
                } else {
                    peremenaya -= cickl.amount;
                    srRash += cickl.amount;
                }
                if (i % 2 == 1) {
                    backResult += "Прибыль за " + cickl.mounth + " месяц = " + peremenaya + "\n";
                    peremenaya = 0;
                }
            }
            srPribl = srPribl / (years.size()/2);
            srRash = srRash / (years.size()/2);
            backResult += "Средний расход за все месяцы в году = " + srRash + "\n" + "Средний доход за все месяцы в году = " + srPribl;
            return backResult;
        }
        else {
            backResult="Файлы не считанны";
            return backResult;
        }
    }

    public String readFileContents (String path){
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}