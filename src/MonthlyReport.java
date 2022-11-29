import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    HashMap<Integer, ArrayList> monthAndMore = new HashMap<>();

    public void loadFile(int numOfMouth,String path){
        String content = readFileContents(path);
        String[] lines = content.split("\r?\n");
        ArrayList<Mounth> mounthsSostav = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity=Integer.parseInt(parts[2]);
            int sumOfOne=Integer.parseInt(parts[3]);
            Mounth mounth = new Mounth(itemName, isExpense, quantity, sumOfOne);
            mounthsSostav.add(mounth);
        }
        monthAndMore.put(numOfMouth,mounthsSostav);
    }

    public String poiskMouth(){
        String result = "";
        if (!monthAndMore.isEmpty()) {
            for (int s : monthAndMore.keySet()) {

                int priblMax = 0, rashodMax = 0, pribl, rash;
                String maxTovar = "", minTovar = "";
                ArrayList<Mounth> monthCikl = monthAndMore.get(s);

                for (int i = 0; i < monthCikl.size(); i++) {
                    Mounth cikl = monthCikl.get(i);
                    if (cikl.isExpense == false) {
                        pribl = cikl.quantity * cikl.sumOfOne;
                        if (pribl >= priblMax) {
                            priblMax = pribl;
                            maxTovar = cikl.itemName;
                        }
                    } else {
                        rash = cikl.quantity * cikl.sumOfOne;
                        if (rash >= rashodMax) {
                            rashodMax = rash;
                            minTovar = cikl.itemName;
                        }
                    }
                }
                result += s + " месяц \n Cамый прибыльный пункт: " + maxTovar + " Принес: " + priblMax + "\n Самый убыльный пункт: " + minTovar + " Убыток: " + rashodMax + "\n";
            }
            return result;
        }
        else{
            result +="Файлы не считанны";
            return result;
        }
    }

    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}
