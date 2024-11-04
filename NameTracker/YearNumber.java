public class YearNumber {
    private final int year;
    private int number;

    public YearNumber(int year, int number) {
        this.year = year;
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public int getNumber() {
        return number;
    }

    public void add(int number) {
        this.number += number;
    }
}
