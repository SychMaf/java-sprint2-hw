public class Month {
    public String itemName;
    public boolean isExpense;
    public int quantity, sumOfOne;

    public Month(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public int getTotalSum() {
        return quantity * sumOfOne;
    }
}


