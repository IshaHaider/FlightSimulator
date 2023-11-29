package src.Domain;
import java.time.LocalDate;

public class Date {
    private int day;
    private int month;
    private int year;
    private LocalDate specificDate;

    public Date(){
        this.day = 0;
        this.month = 0;
        this.year = 0;
        this.specificDate = LocalDate.of(0,0,0);
    }

    public Date(int day, int month, int year) {
        this.specificDate = LocalDate.of(year, month, day);
        this.day = day;
        this.month = month;
        this.year = year;
        validateDate(this);
    }

    public final LocalDate getDate() {
        return specificDate;
    }

    public void setDate(final Date date) {
        validateDate(date);
        this.day = date.day;
        this.month = date.month;
        this.year = date.year;
        this.specificDate = date.getDate();
    }


    void validateDate(Date date) {
        if (date.day < 1 || date.day > 31 || date.month < 1 || date.month > 12 || date.year < 1900 || date.year > 2030) {
            throw new IllegalArgumentException("Date must be between 01-01-1900 and 31-12-2030");
        }
    }
}