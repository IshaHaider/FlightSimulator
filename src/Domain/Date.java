package Domain;

public class Date {
    private int month; 
    private int day;
    private int year;

    public void setMonth (final int m)  { month = m; }
    public void setDay (final int d)  { day = d; }
    public void setYear (final int y)  { year = y; }
    public final int getMonth ()  { return month; }
    public final int getDay ()  { return day; }
    public final int getYear ()  { return year; }
}
