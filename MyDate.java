/* Fady Zaki SDEV200 1/26/24
-------------------------------------
|             MyDate                |
-------------------------------------
| - year: int                        |
| - month: int (0-based)            |
| - day: int                         |
-------------------------------------
| + MyDate()                         |
| + MyDate(elapsedTime: long)        |
| + MyDate(year: int, month: int,    |
|    day: int)                       |
| + getYear(): int                   |
| + getMonth(): int                  |
| + getDay(): int                    |
| + setDate(elapsedTime: long): void |
-------------------------------------
*/
import java.util.Date;

public class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate() {
        this(System.currentTimeMillis());
    }

    public MyDate(long elapsedTime) {
        setDate(elapsedTime);
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setDate(long elapsedTime) {

        long totalMilliseconds = elapsedTime;
        long totalSeconds = totalMilliseconds / 1000;
        long currentSecond = totalSeconds % 60;
        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;
        long totalHours = totalMinutes / 60;
        long currentHour = totalHours % 24;

        int daysSince1970 = (int) (totalHours / 24);

        year = 1970;
        while (daysSince1970 >= (isLeapYear(year) ? 366 : 365)) {
            daysSince1970 -= isLeapYear(year) ? 366 : 365;
            year++;
        }

        int[] daysPerMonth = {31, (isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (month = 0; month < 12; month++) {
            if (daysSince1970 < daysPerMonth[month]) {
                break;
            }
            daysSince1970 -= daysPerMonth[month];
        }
        day = daysSince1970 + 1;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        MyDate date1 = new MyDate();
        MyDate date2 = new MyDate(34355555133101L);

        System.out.println("Date 1: " + date1.getYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDay());
        System.out.println("Date 2: " + date2.getYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDay());
    }
}
