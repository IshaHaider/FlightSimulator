package src.Domain;
import java.time.LocalDate;
import java.sql.Date;

public class DDate {
    private int day;
    private int month;
    private int year;
    private Date specificDate;

    public DDate(){
        this.day = 0;
        this.month = 0;
        this.year = 0;
        LocalDate specificLocalDate = LocalDate.of(0,0,0);
        this.specificDate = java.sql.Date.valueOf(specificLocalDate);
    }
    
    public DDate(Date date){
        LocalDate temp = date.toLocalDate();
        this.day = temp.getDayOfMonth();
        this.month = temp.getMonthValue();
        this.year = temp.getYear();
        this.specificDate = date;
    }

    public DDate(int day, int month, int year) {
        LocalDate specificLocalDate = LocalDate.of(year, month, day);
        this.specificDate = java.sql.Date.valueOf(specificLocalDate);
        this.day = day;
        this.month = month;
        this.year = year;
        validateDate(this);
    }

    public final Date getDate() {
        return specificDate;
    }

    public void setDate(final DDate date) {
        validateDate(date);
        this.day = date.day;
        this.month = date.month;
        this.year = date.year;
        this.specificDate = date.getDate();
    }


    void validateDate(DDate date) {
        if (date.day < 1 || date.day > 31 || date.month < 1 || date.month > 12 || date.year < 1900 || date.year > 2030) {
            throw new IllegalArgumentException("Date must be between 01-01-1900 and 31-12-2030");
        }
    }
}