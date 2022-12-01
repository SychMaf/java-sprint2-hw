import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class YearlyReport {

    public ArrayList<Year> years = new ArrayList<>();
    public void loadFile(String path) {
        years.clear();
        List<String> content =readFileContents(path);
        if (content.isEmpty()){
            System.out.println("Файл " + path + " пуст");
            return;
        }

        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
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
            int srIncome = 0, srSpending = 0, variable = 0;

            for (int i = 0; i < years.size(); i++) {
                Year cickl = years.get(i);
                if (!cickl.isExpense) {
                    variable += cickl.amount;
                    srIncome += cickl.amount;
                } else {
                    variable -= cickl.amount;
                    srSpending += cickl.amount;
                }
                if (i % 2 == 1) {
                    backResult += "Прибыль за " + cickl.mounth + " месяц = " + variable + "\n";
                    variable = 0;
                }
            }
            srIncome = srIncome / (years.size()/2);
            srSpending = srSpending / (years.size()/2);
            backResult += "Средний расход за все месяцы в году = " + srSpending + "\n" + "Средний доход за все месяцы в году = " + srIncome;
            return backResult;
        }
        else {
            backResult="Файлы не считанны";
            return backResult;
        }
    }

    public List<String> readFileContents (String path){
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}