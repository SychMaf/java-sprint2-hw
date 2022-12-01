import java.util.ArrayList;

public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }
    public String check() {
        String result = "";
        if (!yearlyReport.years.isEmpty() && !monthlyReport.monthAndMore.isEmpty()) {
            ArrayList<Year> raspredYear = yearlyReport.years;
            ArrayList<Integer> pribl = new ArrayList<>();
            ArrayList<Integer> rash = new ArrayList<>();
            for (int i = 0; i < raspredYear.size(); i++) {
                Year cickl = raspredYear.get(i);
                if (!cickl.isExpense) {
                    pribl.add(cickl.amount);
                } else {
                    rash.add(cickl.amount);
                }
            }                                                                          //два списка с прибылью и убылью по годовому
            for (int s : monthlyReport.monthAndMore.keySet()) {

                ArrayList<Month> mesCikl = monthlyReport.monthAndMore.get(s);
                int pribls = 0, rashod = 0;

                for (int i = 0; i < mesCikl.size(); i++) {
                    Month podCikl = mesCikl.get(i);
                    if (podCikl.isExpense == false) {
                        pribls += podCikl.getTotalSum();
                    } else {
                        rashod += podCikl.getTotalSum();
                    }                                                                     // сумма расх и приходов за месяц
                }
                if (pribls != pribl.get(s - 1)) {
                    result += "В "+s+" месяце прибыль не сойдется, по годовому: " + pribl.get(s - 1) + " ,по месчному: " + pribls + "\n";
                }
                if (rashod != rash.get(s - 1)) {
                    result += "В "+s+" месяце расход не сойдется, по годовому:" + rash.get(s - 1) + " ,по месчному: " + rashod + "\n";
                }
            }
            result += "Результат проверки: \n Проверка завершенна.\n";
            return result;
        } else {
            result += "Один из файлов не считан";
            return result;
        }
    }
}
