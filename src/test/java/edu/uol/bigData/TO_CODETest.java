package edu.uol.bigData;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;


public class TO_CODETest{

	@Test
   public void testToCode() {
   		assertEquals(TO_CODE.toCode("áé"),"ae");
   		assertEquals(TO_CODE.toCode("   á é   "),"a_e");
   		assertEquals(TO_CODE.toCode("   Á Ç   é"),"a_c_e");
   }

}