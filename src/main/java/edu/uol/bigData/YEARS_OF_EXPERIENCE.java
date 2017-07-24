package edu.uol.bigData;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class YEARS_OF_EXPERIENCE extends EvalFunc<String>{

	private static Pattern valueLastRegex = Pattern.compile("(?<Unit>month|year)(s)?( in| of)?( exp)?(erience)?( in)?( \\w+)?( \\w+)?( )?(teach)(ing)?( exp)?(erience)?( )*(\\:)?( )?(?<Experience>\\d+)");
	private static Pattern valueFirstRegex = Pattern.compile("(?<Experience>\\d+)( )?(\\+|plus)?( )?(?<Unit>month|year)(s)?( in| of)?( exp)?(erience)?( in)?( \\w+)?( \\w+)?( )?(teach)");
	private static Pattern valueCleanUpRegex = Pattern.compile("(?<ContaminatedValue>(?<Years>\\w+|\\d+)( )?(\\+|plus)?( )?(year)(s)?( )?(and)( )?(?<Months>\\w+|\\d+)( )?(\\+|plus)?( )?(month)(s)?)");

	private static Map<String,Integer> numericalConversion;

	static{
		numericalConversion = new HashMap<String,Integer>();
		numericalConversion.put("one", 1);
		numericalConversion.put("two", 2);
		numericalConversion.put("three", 3);
		numericalConversion.put("four", 4);
		numericalConversion.put("five", 5);
		numericalConversion.put("six", 6);
		numericalConversion.put("seven", 7);
		numericalConversion.put("eight", 8);
		numericalConversion.put("nine", 9);
		numericalConversion.put("ten", 10);
		numericalConversion.put("eleven", 11);
		numericalConversion.put("twelve", 12);
	}

	public String exec(Tuple input) throws IOException {

		if (input == null || input.size() == 0)
            return null;
        try{
        	String str = (String) input.get(0);
        	return captureTeachingExperience(str);
        }catch(Exception e){
        	throw new IOException("Could not parse: " + (String) input.get(0), e);
        }

	}

	public static Integer extractValue(String year, String month){
		//Considering only one to twelve, extend this algorithm for more complicated cases
		int years = 0;
		int months = 0;

		try{
			if(numericalConversion.containsKey(year)){
				years = numericalConversion.get(year);
			}else{
				years = Integer.parseInt(year);
			}

			if(numericalConversion.containsKey(month)){
				months = numericalConversion.get(month);
			}else{
				months = Integer.parseInt(month);
			}
		}catch(NumberFormatException ex){
			//Problem with regex
			throw ex;
		}
		
		return years + ( months / 12 );
	}

	public static String captureTeachingExperience(String experience){
		String sanitized = experience.toLowerCase()
									.replaceAll(" yr ", " year ")
									.replaceAll(" yrs ", " year ")
									.replaceAll(" y ", "year")
									.replaceAll("^yr ", "year ")
									.replaceAll("^yrs ", "year ")
									.replaceAll(" mth ", " month ")
									.replaceAll(" mths ", " month ")
									.replaceAll(" m ", "month")
									.replaceAll("^mth ", "month ")
									.replaceAll("^mths ", "month ")
									.replaceAll("\\:", "")
									.replaceAll("\\+", "")
									.replaceAll("-", " ")
									.replaceAll("\\s+", " ")
									.trim();
		System.out.println(sanitized);
		Matcher matchUncleanValues = valueCleanUpRegex.matcher(sanitized);
		if(matchUncleanValues.find()){
			String year = matchUncleanValues.group("Years");
			String month = matchUncleanValues.group("Months");
			Integer value = extractValue(year, month);
			sanitized = sanitized.replace(matchUncleanValues.group("ContaminatedValue"), value.toString() + " year");
		}
		
		Matcher matchValueFirst = valueFirstRegex.matcher(sanitized);
		if(matchValueFirst.find()){
			String unit = matchValueFirst.group("Unit");
			if(unit != null && unit.equals("month")){
				Integer monthsToYears = Integer.parseInt(matchValueFirst.group("Experience"))/12;
				return monthsToYears.toString();
			}else{
				return matchValueFirst.group("Experience");
			}
		}

		Matcher matchValueLast = valueLastRegex.matcher(sanitized);
		if(matchValueLast.find()){
			String unit = matchValueLast.group("Unit");
			if(unit !=  null && unit.equals("month")){
				Integer monthsToYears = Integer.parseInt(matchValueLast.group("Experience"))/12;
				return monthsToYears.toString();
			}else{
				return matchValueLast.group("Experience");	
			}
		}

		return null;
	}
}