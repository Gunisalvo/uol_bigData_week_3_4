package edu.uol.bigData;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;


public class TITLETest{

	@Test
   public void testTitle() {
   		assertEquals(TITLE.parseTitle("   masTer "),"masters");
   		assertEquals(TITLE.parseTitle("   Ph D "),"doctorate");
   		assertEquals(TITLE.parseTitle("   phD "),"doctorate");
   		assertEquals(TITLE.parseTitle(" Doctorate "),"doctorate");
		
   }

}