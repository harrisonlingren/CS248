/**
Dating Game Program - MyDate class
@author Harrison Lingren
*/

import java.io.*;
import java.util.*;
import java.text.*;

public class MyDate implements DateInterface
{
	/**
		@param month the month of the year (1-12)
		@param day the day of the mongth (1-31)
		@param year the year (four digits)
		@param dayOfWeek the day of the week (0-6) */
	
	static int month;
	static int day;
	static int year;
	static int dayOfWeek;

	/** @return the day of the month (1-31) */
	public int getDay()
	{
		return day;
	}
	
	/** @return the day of the week (0-6) */
	public int getDow()
	{
		return dayOfWeek;
	}
	
	/** @return the month of the year (1-12) */
	public int getMonth()
	{
		return month;
	}
	
	/** @return the year (four digits) */
	public int getYear()
	{
		return year;
	}
	
	/** sets the date
		@param m the month of the year (1-12)
		@param d the day of the mongth (1-31)
		@param y the year (four digits)
		@param dow the day of the week (0-6) */
	public void set(int m, int d, int y, int dow)
	{
		/*month = m;
		day = d;
		year = y;
		dayOfWeek = dow;
		
		DateFormat monthFormat = new SimpleDateFormat("MM");
		DateFormat dayFormat = new SimpleDateFormat("dd");
		DateFormat yearFormat = new SimpleDateFormat("yyyy");
		DateFormat dowFormat = new SimpleDateFormat("E");
		
		Date today=new Date();
		
		month = Integer.valueOf( monthFormat.format(today) );
		day = Integer.valueOf( dayFormat.format(today) );
		year = Integer.valueOf( yearFormat.format(today) );
		String dowString = dowFormat.format(today);
		
		switch(dowString)
		{
			case "Sunday" :
				dayOfWeek=0;
				break;
			case "Monday" :
				dayOfWeek=1;
				break;
			case "Tuesday" :
				dayOfWeek=2;
				break;
			case "Wednesday" :
				dayOfWeek=3;
				break;
			case "Thursday" :
				dayOfWeek=4;
				break;
			case "Friday" :
				dayOfWeek=5;
				break;
			case "Saturday" :
				dayOfWeek=6;
				break;
		}*/
		
		Calendar cal = new GregorianCalendar(2015,0,31);
		
		cal = Calendar.getInstance();
    Date date =  cal.getTime();
		
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		year = cal.get(Calendar.YEAR);
		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
	}
	
	/** moves the date forward by exactly one day */
	public void tomorrow()
	{
		
		if (checkMonth()==false)
		{
			if (checkDay()==false)
			{
				day++;
				nextDOW();
			}
			else
			{
				day=1;
				month++;
				nextDOW();
			}
		}
		else
		{
			if (checkDay()==false)
			{
				day++;
				nextDOW();
			}
			else
			{
				day=1;
				month=1;
				year++;
				nextDOW();
			}
		}
	}

	/** @return true if day is 31/30/29/28 or above (depending on month), false if not */
	public boolean checkDay()
	{
	
		switch(month)
		{
		
			case 1 :
				if (day>=31) {return true;}
				else {return false;}
				
			case 2 :
				
				if (checkLeap()==false)
				{
					if (day>=28) {return true;}
					else {return false;}
				}
				
				else
				{
					if (day>=29) {return true;}
					else {return false;}
				}
				
			case 3 :
				if (day>=31) {return true;}
				else {return false;}
				
			case 4 :
				if (day>=30) {return true;}
				else {return false;}
				
			case 5 :
				if (day>=31) {return true;}
				else {return false;}
				
			case 6 :
				if (day>=30) {return true;}
				else {return false;}
				
			case 7 :
				if (day>=31) {return true;}
				else {return false;}
				
			case 8 :
				if (day>=31) {return true;}
				else {return false;}
				
			case 9 :
				if (day>=30) {return true;}
				else {return false;}
				
			case 10 :
				if (day>=31) {return true;}
				else {return false;}
				
			case 11 :
				if (day>=30) {return true;}
				else {return false;}
				
			case 12 :
				if (day>=31) {return true;}
				else {return false;}
		}
		
		return true;
		
	}
	
	/** @return true if month is December (or higher value...), false if not */
	public boolean checkMonth()
	{
	
		if (month>=12) {return true;}
		else {return false;}
	
	}
	
	public boolean checkLeap()
	{
	
		if ( (year % 4)==0 && (year % 100)!=0 ) {return true;}
		
		else if ( (year % 400)==0 ) {return true;}
		
		else{return false;}
	
	}
	
	/** @return true if day of week is Saturday (or higher value...), false if not */
	public boolean checkDOW()
	{
	
		if (dayOfWeek>=7) {return true;}
		else {return false;}
	
	}
	
	/** advances the day of week */
	public void nextDOW()
	{
		if (checkDOW()==false)
		{
			dayOfWeek++;
		}
		else
		{
			dayOfWeek=1;
		}
	}
	
	/** @return the date as a String in the format "Monday, March 18, 2002" */
	public String toString()
	{
		
		String dateString="";
		String dowString="";
		String monthString="";
		
		switch(dayOfWeek)
		{
			case 1 :
				dowString="Sunday";
				break;
			case 2 :
				dowString="Monday";
				break;
			case 3 :
				dowString="Tuesday";
				break;
			case 4 :
				dowString="Wednesday";
				break;
			case 5 :
				dowString="Thursday";
				break;
			case 6 :
				dowString="Friday";
				break;
			case 7 :
				dowString="Saturday";
				break;
		}
		
		switch(month)
		{
			case 1 :
				monthString="January";
				break;
			case 2 :
				monthString="February";
				break;
			case 3 :
				monthString="March";
				break;
			case 4 :
				monthString="April";
				break;
			case 5 :
				monthString="May";
				break;
			case 6 :
				monthString="June";
				break;
			case 7 :
				monthString="July";
				break;
			case 8 :
				monthString="August";
				break;
			case 9 :
				monthString="September";
				break;
			case 10 :
				monthString="October";
				break;
			case 11 :
				monthString="November";
				break;
			case 12 :
				monthString="December";
				break;
		}
		
		dateString=(dowString+", "+monthString+" "+day+", "+year);
		
		return dateString;
	}
	
	/** not used */
	public void today() {}
	/** not used */
	public void yesterday() {}
}