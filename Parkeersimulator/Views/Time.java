package Parkeersimulator.Views;

/**
 * Created by Gebruiker on 31-1-2017.
 */
public class Time{
    private Time(){}

    public static String getDate(int tick){
        int week=tick/10080;
        int dag= (tick%10080)/1440;
        String day;
        switch(dag){
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
        int uur = ((tick%10080)%1440)/60;
        int minuut = (((tick%10080)%1440)%60);
        String date = " week: " +week+" " +day+" " + uur+ ":" + minuut;
        return date;
    }
}
