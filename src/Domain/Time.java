package src.Domain;

public class Time {
    private int hours;
    private int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public final String getTime() {
        return this.hours + ":" + this.minutes;
    }

    public void setTime(final Time time) {
        validateTime(time);
        this.hours = time.hours;
        this.minutes = time.minutes;
    }

    void validateTime(Time time) {
        if (time.hours < 0 || time.hours > 23 || time.minutes < 0 || time.minutes > 59) {
            throw new IllegalArgumentException("Time must be between 00:00 and 23:59");
        }
    }
}