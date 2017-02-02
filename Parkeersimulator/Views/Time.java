package Parkeersimulator.Views;

/**
 * Created by Gebruiker on 31-1-2017.
 */
public class Time{
    private Time(){}

    public static String getDate(int tick){
        String date = " week: " +getWeek(tick)+" " +getDay(tick)+" " + getHour(tick)+ ":" + getMinute(tick);
        return date;
    }
    public static int getWeek(int tick){
        int week=tick/10080;
        return week;
    }
    public static int getDayNumber(int tick){
        int day= (tick%10080)/1440;
        return day;
    }

    public static int getHour(int tick){
        int uur = ((tick%10080)%1440)/60;
        return uur;
    }
    public static int getMinute(int tick){
        int minuut = ((tick%10080)%1440)%60;
        return minuut;
    }
    public static String getDay(int tick){
        String day;
        switch(getDayNumber(tick)){
            case(0): day = "maandag ";
                break;
            case(1):day = "dinsdag ";
                break;
            case(2):day = "woensdag ";
                break;
            case(3):day = "donderdag ";
                break;
            case(4):day = "vrijdag ";
                break;
            case(5):day = "zaterdag ";
                break;
            case(6):day = "zondag ";
                break;
            default:day ="swag-- day could not be found";
        }
        return day;
    }
}
