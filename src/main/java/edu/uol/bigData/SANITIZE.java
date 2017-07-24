package edu.uol.bigData;

import java.io.IOException;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;


public class SANITIZE extends EvalFunc<String>{

	public String exec(Tuple input) throws IOException {

		if (input == null || input.size() == 0)
            return null;
        try{
        	String str = (String)input.get(0);
        	return str.replace("�", "").trim();
        }catch(Exception e){
        	throw new IOException("Caught exception processing input row ", e);
        }

	}

}