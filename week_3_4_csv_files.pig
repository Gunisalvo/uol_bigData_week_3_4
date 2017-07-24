REGISTER piggybank.jar;
REGISTER udf.jar;

faculty =   LOAD '$INPUT_STORE' 
            USING org.apache.pig.piggybank.storage.CSVExcelStorage(',', 'YES_MULTILINE', 'UNIX') 
            AS (id: chararray, 
                Name: chararray, 
                Location: chararray, 
                Grade: chararray, 
                Title: chararray, 
                Join_Date: chararray, 
                LWD: chararray, 
                Type: chararray, 
                Divison: chararray, 
                Reports_To: chararray, 
                Highest_Qualification_Level: chararray, 
                Highest_Qualification: chararray, 
                Major: chararray, 
                University: chararray, 
                All_Qualification: chararray, 
                Courses: chararray, 
                Major_Teaching: chararray,
                Experience: chararray);

faculty = FILTER faculty BY NOT ( id MATCHES 'ID' );

sanitizedFaculty = FOREACH faculty GENERATE edu.uol.bigData.SANITIZE(Name) as Name,
                                            edu.uol.bigData.SANITIZE(Highest_Qualification_Level) as Highest_Qualification_Level,
                                            edu.uol.bigData.SANITIZE(Experience) as Experience,
                                            edu.uol.bigData.SANITIZE(University) as University;

universities =  LOAD '$UNIVERSITIES_STORE' 
                USING org.apache.pig.piggybank.storage.CSVExcelStorage(',', 'YES_MULTILINE', 'UNIX')
                AS ( Country_Code: chararray,
                     Name: chararray);

universities = FILTER universities BY NOT ( Name MATCHES 'done!');

sanitizedUniversities = FOREACH universities GENERATE edu.uol.bigData.TO_CODE(edu.uol.bigData.SANITIZE(Name)) AS Name,
                                                      Country_Code AS Location;

sanitizedUniversities = DISTINCT sanitizedUniversities;
                                                      
                                        
sanitizedFaculty = FOREACH sanitizedFaculty GENERATE    Name as Name,
                                                        edu.uol.bigData.TITLE(Highest_Qualification_Level) as Highest_Qualification_Level,
                                                        edu.uol.bigData.YEARS_OF_EXPERIENCE(Experience) as Experience,
                                                        edu.uol.bigData.TO_CODE(University) as University;

result = JOIN sanitizedFaculty BY University LEFT OUTER, sanitizedUniversities by Name;

result = FOREACH result GENERATE $0, $1, ((int) $2 >= 5 ? 'more than 5 years' : 'less than 5 years' ), ( $5 is not null ? $5 : ( $3 is not null ? 'Elsewhere' : null ));

result = ORDER result BY $0 ASC;

STORE result INTO '$OUTPUT_STORE' USING org.apache.pig.piggybank.storage.CSVExcelStorage(',', 'YES_MULTILINE', 'UNIX');
