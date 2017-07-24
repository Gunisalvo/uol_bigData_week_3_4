package edu.uol.bigData;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;


public class YEARS_OF_EXPERIENCETest{

	@Test
   public void testYearsOfExperience() {
   	    assertEquals("0",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 6 months teaching 123 nonsesnse teach 123"));
   	    assertEquals("1",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 12 months of teaching 123 nonsesnse teach 123"));
   	    assertEquals("1",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 18 months in teaching 123 nonsesnse teach 123"));
   	    assertEquals("0",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 6+ month in teaching 123 nonsesnse teach 123"));
   	    assertEquals("2",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 25plus month in teaching 123 nonsesnse teach 123"));
   	    assertEquals("4",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 48 plus month of teach 123 nonsesnse teach 123"));
   	    assertEquals("0",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 3 plus month of Cooking teach 123 nonsesnse teach 123"));
   	    assertEquals("0",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 11 plus month of Culinary School teach 123 nonsesnse teach 123"));
   	    assertEquals("6",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse 123 6+ years of teaching 123 nonsesnse 123"));
   	    assertEquals("2",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse 123 2 + years of teaching 123 nonsesnse teach 123"));
   	    assertEquals("45",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse 123 45 years teaching 123 nonsesnse teach 123"));
   	    assertEquals("12",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse 123 12 years of experience in High School teaching 123 nonsesnse 123"));
   	    assertEquals("0",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach nonsesnse 123 0 years of experience in colege teaching 123 nonsesnse 123"));
   	    assertEquals("6111",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse nonsesnse 6111 years of experience in high school teaching nonsesnse teach nonsesnse 123"));

		assertEquals("0",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 6 months teaching: 123 nonsesnse teach 123"));
		assertEquals("1",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 12 months of teach: 123+ nonsesnse teach 123"));
		assertEquals("1",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 18 months in teaching: 123 plus nonsesnse teach 123"));
		assertEquals("0",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 6+ month in teaching: 123 + nonsesnse teach 123"));
		assertEquals("2",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 25plus month in teaching: 123 nonsesnse teach 123"));
		assertEquals("4",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 48 plus month of teach: 123 nonsesnse teach 123"));
		assertEquals("0",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 3 plus month of Cooking teach: 123 nonsesnse teach 123"));
		assertEquals("0",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach 123 11 plus month of Culinary School teach: 123 nonsesnse teach 123"));
		assertEquals("6",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse 123 6+ years of teaching: 123 nonsesnse 123"));
		assertEquals("2",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse 123 2 + years of teaching: 123 nonsesnse teach 123"));
		assertEquals("45",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse 123 45 year teaching:123"));
		assertEquals("12",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse 123 12 years of experience in High School teaching: 123 nonsesnse 123"));
		assertEquals("0",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse teach nonsesnse 123 0 years of experience in colege teaching: 123 nonsesnse 123"));
		assertEquals("6111",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse nonsesnse 6111 years of experience in high school teaching: 123 nonsesnse teach nonsesnse"));
		assertEquals("15",YEARS_OF_EXPERIENCE.captureTeachingExperience("yrs of teaching experience - 15 years"));

		//You gotta be kidding me!
		assertEquals("4",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse nonsesnse two years and 24 months of experience in high school teaching: 123 nonsesnse teach nonsesnse"));
		assertEquals("7",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse nonsesnse 7+ year and eleven months of experience in high school teaching: 123 nonsesnse teach nonsesnse"));
		assertEquals("9",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse nonsesnse 9 plus year and 2 months of experience in high school teaching: 123 nonsesnse teach nonsesnse"));
		assertEquals("11",YEARS_OF_EXPERIENCE.captureTeachingExperience("123 nonsesnse nonsesnse 10 plus year and 12 plus months of experience in high school teaching: 123 nonsesnse teach nonsesnse"));

   }

}