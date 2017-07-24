package edu.uol.bigData;

import java.io.IOException;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import java.text.Normalizer;


public class TO_CODE extends EvalFunc<String>{

	public String exec(Tuple input) throws IOException {

		if (input == null || input.size() == 0)
            return null;
        try{
        	String str = (String)input.get(0);
        	return toCode(str);
        }catch(Exception e){
        	throw new IOException("Caught exception processing input row ", e);
        }

	}

    public static String toCode(String string){
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                        .trim()
                        .replaceAll("\\s+", " ")
                        .replaceAll(" ", "_")
                        .replaceAll("\"", "")
                        .replaceAll("'", "")
                        .replaceAll("`", "")
                        .toLowerCase()
                        .split(",")[0]
                        .split("[(]")[0]
                        .trim();
    }

}