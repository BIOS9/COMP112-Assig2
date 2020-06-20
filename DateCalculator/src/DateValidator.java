package src;

// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP112 - 2018T1, Assignment 2
 * Name: Matthew Corfiatis
 * Username: CorfiaMatt
 * ID: 300447277
 */

import ecs100.*;

import java.awt.*;

/**
 * Reads a date from the user as three integers, and then checks that the date
 * is valid
 * Finds the day of the week
 * Display a monthly calendar
 */

public class DateValidator {

    public static final String[] WEEK_DAYS = new String[] {
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
    };

    public static final String[] MONTHS = new String[] {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    public static final double CELL_WIDTH = 100;
    public static final double CELL_HEIGHT = 60;
    public static final double HEADER_HEIGHT = 20;

    /**
     * Asks user for three integer and then checks if it is a valid date.
     */
    public void doCore(){
        int day = UI.askInt("Day: ");
        int month = UI.askInt("Month: ");
        int year = UI.askInt("Year: ");
        if (this.validateDate(day, month, year))
            UI.printf("The date %d/%d/%d is valid%n", day, month, year);
        else
            UI.printf("%d/%d/%d is not a valid date%n", day, month, year);
    }

    /** CORE
     * Determines whether the date specified by the three integers is a valid date.
     * - Months have the correct number of days
     * - Years are greater than 1600 (roughly when the Gregorian calendar was adopted)
     * - On leap years February should have 29 days.
     *    A year is a leap year if:
     *       - The year can be evenly divided by 4 but not 100,  OR
     *       - The year can be evenly divided by 400
     *  Returns a boolean
     */
    public boolean validateDate(int day, int month, int year){ //Algorithm validated from year 1601-10000 by comparing values with C# DateTime library.
        return day > 0 &&
                month > 0 &&
                month <= 12 &&
                year > 1600 &&
                day <= monthDays(month, isLeap(year));
    }

    /**
     * Returns number of days in specified month taking leap years into account.
     * @param month Month of year from 1-12
     * @param leap Weather the year is a leap year
     * @return Integer representing the number of days in that month
     */
    public int monthDays(int month, boolean leap)
    {
        if (month == 2) return leap ? 29 : 28; //If February, return 29 if leap year and 28 if not
        return ((month < 8 && month % 2 != 0)  || (month >= 8 && (month + 1) % 2 != 0)) ? 31 : 30; //If not Feb, return 30 or 31 depending on month
    }

    /**
     * Calculates weather a specified year is a leap year
     * @param year Year to check
     * @return Boolean value indicating weather year is a leap year
     */
    public boolean isLeap(int year)
    {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /** COMPLETION
     * Work out the day of the week assuming the date is valid and is after
     * 1st January 2000
     * Return the day as a String
     */
    public String findDay(int day, int month, int year){
        return WEEK_DAYS[dayOfWeek(day, month, year)];
    }

    /**
     *  Calculates the day of the week for any date past 1/1/2000
     * @param day Day of month
     * @param month Month of year
     * @param year Year in gregorian calendar
     * @return Integer from 0-6 inclusive representing the day of week
     */
    public int dayOfWeek(int day, int month, int year)
    {
        int dayCount = day + 4;

        for(int y = 2000; y <= year; y++)
            for (int m = 1; (y == year && m <= month) || (y != year && m <= 12); m++)
                if(m != month || y != year)
                    dayCount += monthDays(m, isLeap(y));

        return dayCount % 7;
    }

    /**
     * Asks user for three integer and if it is a valid date and after
     *   1st January 2000, prints on which day of the week it falls.
     */
    public void doCompletion(){
        int day = UI.askInt("Day: ");
        int month = UI.askInt("Month: ");
        int year = UI.askInt("Year (4 digits): ");
        if (this.validateDate(day, month, year) && year >= 2000)
            UI.printf("%d/%d/%d falls on a %s%n", day, month, year,
                    this.findDay(day, month, year));
        else
            UI.printf("%d/%d/%d is not a valid date%n", day, month, year);
    }

    /**
     * Asks user for three integer and if it is a valid date and after
     *   1st January 2000, displays a monthly calendar, highlighting the
     *   given date.
     */
    public void doChallenge(){
        int day = UI.askInt("Day: ");
        int month = UI.askInt("Month: ");
        int year = UI.askInt("Year (4 digits): ");
        if (!validateDate(day, month, year) || year < 2000)
        {
            UI.printf("%d/%d/%d is not a valid date after 1/1/2000%n", day, month, year);
            return;
        }

        int firstDay = dayOfWeek(1, month, year);
        int dayCount = monthDays(month, isLeap(year));

        int rowsRequired = (firstDay + dayCount - 1) / 7;

        UI.clearGraphics();
        UI.setFontSize(16);
        
        UI.setColor(Color.decode("0xCCCCCC"));
        UI.fillRect(20, 35, CELL_WIDTH * 7, HEADER_HEIGHT + (CELL_HEIGHT * (rowsRequired + 1))); //Header fill
        UI.setColor(Color.black);

        for(int i = 1; i <= dayCount; i++)
        {
            int column = (firstDay + i - 1) % 7;
            int row = (firstDay + i - 1) / 7;
            UI.setColor((i == day) ? Color.decode("0xFFE100") : Color.white);
            UI.fillRect((CELL_WIDTH * column) + 20, (CELL_HEIGHT * row) + HEADER_HEIGHT + 35, CELL_WIDTH, CELL_HEIGHT);
            UI.setColor(Color.black);
            UI.drawString(Integer.toString(i), (CELL_WIDTH * column) + 25, (CELL_HEIGHT * row) + HEADER_HEIGHT + 55);
        }

        UI.setFontSize(23);
        UI.drawString(String.format("%s - %d",  MONTHS[month - 1], year), 20, 30);
        UI.setFontSize(16);
        UI.drawLine(20, 35, (CELL_WIDTH * 7) + 20, 35); //Topmost line
        UI.drawLine((CELL_WIDTH * 7) + 20, 35, (CELL_WIDTH * 7) + 20, (CELL_HEIGHT * (rowsRequired + 1)) + HEADER_HEIGHT + 35); //Rightmost line

        for(int i = 0; i < 7; i++)
        {
            UI.drawString(WEEK_DAYS[i], (CELL_WIDTH * i) + 25, 50); //Draw days
            UI.drawLine((CELL_WIDTH * i) + 20, 35, (CELL_WIDTH * i) + 20, (CELL_HEIGHT * (rowsRequired + 1)) + HEADER_HEIGHT + 35);
        }

        for(int i = 0; i <= rowsRequired + 1; i++)
        {
            UI.drawLine(20, 35 + HEADER_HEIGHT + (CELL_HEIGHT * i), (CELL_WIDTH * 7) + 20, 35 + HEADER_HEIGHT + (CELL_HEIGHT * i));
        }
    }
}
