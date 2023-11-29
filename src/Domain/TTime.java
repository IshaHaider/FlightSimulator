package src.Domain;
import java.time.LocalTime;
import java.sql.Time;

public class TTime {
    private int hours;
    private int minutes;
    private int seconds;
    private Time specificTime;
    

    public TTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

        LocalTime specificLocalTime = LocalTime.of(hours, minutes, seconds);
        this.specificTime = java.sql.Time.valueOf(specificLocalTime);
    }

    public final Time getTime() {
        return specificTime;
    }

    public void setTime(final TTime time) {
        validateTime(time);
        this.hours = time.hours;
        this.minutes = time.minutes;
        this.seconds = time.seconds;
        this.specificTime = time.getTime();

    }

    void validateTime(TTime time) {
        if (time.hours < 0 || time.hours > 23 || time.minutes < 0 || time.minutes > 59) {
            throw new IllegalArgumentException("Time must be between 00:00 and 23:59");
        }
    }
}