package edu.uol.bigData;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class TITLE extends EvalFunc<String>{

	private static Pattern mastersPattern = Pattern.compile("(master|mba)");
	private static Pattern doctoratePattern = Pattern.compile("(ph)(\\.)?( )?(d)|doc(torate)?");
	private static Pattern bachelorPattern = Pattern.compile("(bachelor)");

	public String exec(Tuple input) throws IOException {

		if (input == null || input.size() == 0)
            return null;
        try{
        	String str = (String)input.get(0);
        	return parseTitle(str);
        }catch(Exception e){
        	throw new IOException("Caught exception processing input row ", e);
        }

	}

	public static String parseTitle(String experience){
		String sanitized = experience.toLowerCase().replaceAll("\\:", "").replaceAll("\\+", "").replaceAll("\\s+", " ");
		Matcher a = doctoratePattern.matcher(sanitized);
		if(a.find()){
			return "Doctorate";
		}
		Matcher b = mastersPattern.matcher(sanitized);
		if(b.find()){
			return "Masters";	
		}
		Matcher c = bachelorPattern.matcher(sanitized);
		if(c.find()){
			return "Bachelor";	
		}
		return null;
	}
}