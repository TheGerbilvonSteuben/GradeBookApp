/**
  * This program creates two linked lists, one for gradeItem objects
  * and one for Student objects. Then it performs operations
  * on the two lists.
  * @author Jerret Stovall
  * @author Thomas Miller
  * Project 04
  * Windows 10, Acer Aspire R3-131T, jGRASP IDE version 2.0.5_06
  * Technophile: a person who is enthusiastic about new technology.
  * "You may not realize it when it happens, but a kick in the teeth 
  *  may be the best thing in the world for you." 
  *            - Walt Disney (1901 - 1966)
*/
 
import java.util.*;
import java.io.*;

public class JerretStovall_04 
{
   
   private static Scanner input;
   private static PrintWriter output;         // Variable declarations                           
   private static Student student;                                    
   private static GradeItem gradeItem;                                
   private static LList<Student> listOfStudents;
   private static LList<GradeItem> listOfGradeItems; 
   private static String xx = "03";
   final static String INPUT_FILENAME = "Project_04_Input" + xx + ".txt";    
   final static String OUTPUT_FILENAME = "Project_04_Ouput" + xx + ".txt";
   private static boolean valid;

//************************************************************************

   // Main method

//************************************************************************

   public static void main(String[]args) 
   {
   
      listOfStudents = new LList<>();        // Instantiate the Student  
      listOfGradeItems = new LList<>();      // and GradeItem lists
   
      processInput();                        // Read the input file, 
                                             // populate the lists, and 
                                             // perform list operations   
      generateReport();                      // Create a report based on   
                                             // the input file and write 
                                             // it to a text file  
                                                     
   }// End main
   
//************************************************************************
   
   /**
    
     processInput - Reads a file then creates two lists and populates 
                    them according to type
     
     @exception     FileNotFoundException if input file can not be found
   
   */
   
   private static void processInput() 
   {
      
      String nextLine;               
      String[] inputArray;
      
      System.err.println("Reading data from file " 
                         + INPUT_FILENAME + "\n");
                                                 
      try
      {
         
         // Attempt to open input file
         
         input = new Scanner(new File(INPUT_FILENAME));
 
         // Begin reading input
         
         while(input.hasNextLine())      
         {

            nextLine = input.nextLine();     
            inputArray = nextLine.split(",");  
            
            valid = ((inputArray[0].equals("STUDENT")) || 
                     (inputArray[0].equals("GRADE ITEM")));
  
            // Construct objects and perform actions based on input
            
            if(!valid && !nextLine.isEmpty())
            {
            
               System.err.println("Invalid entry: " +
                                  "Not a Student or Grade Item.");
            
            }// End if
            
            if(inputArray[0].equals("STUDENT"))  
            {
            
               processStudentData(inputArray); 
                              
            }// End if
         
            if(inputArray[0].equals("GRADE ITEM")) 
            {
            
               processGradeItemData(inputArray);
            
            }// End if

         }// End while
      
      }// End try
      
      catch(FileNotFoundException e)    
      {                                 
      
         System.err.println("File " + INPUT_FILENAME 
                            + " not found." + 
                            " Program could not be executed.");
         System.exit(-1);
               
      }// End catch  
   
   }// End processInput
   
//************************************************************************

   /**
   
      processStudentData - Searches the line of data and adds or removes 
                           a student to a list.
                           
      @param   info   String array that holds the data needed to construct
                      Student objects
      
   */
   
   private static void processStudentData(String[] info)
   {

      boolean processed = true;
      
      try
      {
      
         student = new Student(info[2], info[3], info[4], info[5]);
      
      }// End try
      
      catch(IllegalArgumentException e) 
      {
         
         System.err.print(e.getMessage());
         processed = false;
         
      }// End catch
      
      // Perform actions to Student object based on input data
      
      if(!processed)
      {
      
         System.err.println(" Could not construct Student object.");
      
      }// End if

      else if((info[1].equals("ADD")) 
              && (!listOfStudents.contains(student))) 
      {
      
         listOfStudents.add(student);
         System.err.println(student.getFullName() 
                            + " was added to the List.");
         
      }// End else if     
      
      else if(info[1].equals("ADD") 
              && (listOfStudents.contains(student)))
      {
      
         System.err.println("Could not add " + student.getFullName() 
                            + " because they are already in the List.");
      
      }// End else if
      
      else if(info[1].equals("DEL") 
              && (listOfStudents.contains(student))) 
      {
            
         listOfStudents.remove(student);
         System.err.println(student.getFullName() 
                            + " was removed from the List.");                                                           
      
      }// End else if
      
      else if(info[1].equals("DEL") 
              && (!listOfStudents.contains(student)))
      {
      
         System.err.println("Could not remove " + student.getFullName() 
                            + " because they are not in the List.");
      
      }// End else if
      
      else if((!info[1].equals("ADD")) && (!info[1].equals("DEL"))) 
      {
      
         System.err.println("Invalid Action Field! Could not add " 
                            + student.getFullName()  + " to List.");
      
      }// End else if
      
   }// End processStudentData
     
//************************************************************************
   
   /**
   
      processGradeItemData - Searches the line of data and adds or removes 
                             a Grade Item to a list
                             
      @param   info   String array that holds the data needed to construct
                      GradeItem objects 
   
   */
   
   private static void processGradeItemData(String[] info)
   {            
      
      // Construct GradeItem object to be manipulated 
      
      boolean processed = true;
      
      try
      {
         gradeItem = new GradeItem(info[2], info[3], info[4], info[5], 
                                   info[6], info[7], info[8]);
      
      }// End try
      
      catch(IllegalArgumentException e) 
      {
         
         System.err.println(e.getMessage());
         processed = false;
         
      }// End catch
      
      // Perform actions to GradeItem object based on input data
      
      if(!processed)
      {
      
         System.err.println(" Could not construct Grade Item object.");
      
      }// End if
      
      else if(info[1].equals("ADD") 
              && !listOfGradeItems.contains(gradeItem)) 
      {
      
         listOfGradeItems.add(gradeItem);
         System.err.println("Grade Item matching Student ID " 
                            + gradeItem.getStudentID() 
                            + " was added to the List.");
         
      }// End else if
      
      else if(info[1].equals("ADD") 
              && listOfGradeItems.contains(gradeItem))
      {
      
         System.err.println("Could not add Grade Item because Grade Item "
                            + "is already in the List.");
      
      }// End else if
        
      else if(info[1].equals("DEL") 
              && listOfGradeItems.contains(gradeItem)) 
      {
            
         listOfGradeItems.remove(gradeItem);
         System.err.println("Grade Item matching Student ID: " 
                            + gradeItem.getStudentID() 
                            + " was removed from the List.");                                                    
      
      }// End else if
      
      else if(info[1].equals("DEL") && 
              !listOfGradeItems.contains(gradeItem))
      {
      
         System.err.println("Could not remove Grade Item " +
                            "because it is not in the List.");
      
      }// End else if
           
      else if(!info[1].equals("ADD") && !info[1].equals("DEL"))
      {
      
         System.err.println("Invalid Action Field! " +
                            "Could not add Grade Item to List.");
      
      }// End else if
            
   }// End processGradeItemData
    
//************************************************************************
   
   /**
   
     generateReport - Creates an output file reporting recorded students 
                      and their grade items along with grade earned.
     
   
   */

   public static void generateReport() 
   {
      
      try
      {
      
         FileWriter fw = new FileWriter(OUTPUT_FILENAME);       
         output = new PrintWriter(fw);
      
      }// End try
      
      catch(IOException e)
      {
      
         System.err.println(e.getMessage() 
                            + "Failed to find/create output file.");
      
      }// End catch
      
      Object[] studentArray = listOfStudents.toArray();      
      Object[] gradeItemArray = listOfGradeItems.toArray();
      
      for(int i = 0; i < listOfStudents.getCurrentSize(); i++)
      {
      
         student = (Student) studentArray[i];
         
         if(submittedWork(student) && listOfStudents.contains(student))
         {
         
            addEntry(student);
         
         }// End if
         
         else
         {
            
            output.println("===========================================" +
                           "=======\n");
            output.println(student.getFullName() + 
                           " has not submitted any work.\n");
            output.println("===========================================" +
                           "======");
         
         }// End else
      
      }// End for                        

   // Close printwriter and display report generated message 
      
   output.close();
   System.err.println("\nGenerating report to file " + OUTPUT_FILENAME 
                      + "... done.");
         
   }// End generateReport
   
//************************************************************************
   
   /**
   
      submittedWork -     Checks to see is a student has submitted any 
                          assignments.
                        
      @param   aStudent   The student whose assignments we are looking
                          for
   
   */
   
   private static boolean submittedWork(Student aStudent)
   {
      
      boolean result = false;
      GradeItem listedItem;
      Object[] gradeItemArray = listOfGradeItems.toArray();
      String match = aStudent.getID();
      
      for(int index = 0; index < listOfGradeItems.getCurrentSize(); 
          index++)
      {
         
         listedItem = (GradeItem) gradeItemArray[index];
         String target = listedItem.getStudentID();

         if(target.equals(match))
         {
         
            result = true;
         
         }// End if
      
      }// End for
      
      return result;      
   
   }// End submittedWork
   
//************************************************************************
   
   /**
   
      addEntry -          Adds an entry containing student info and their 
                          graded items to an output file.
                 
      @param   aStudent   The Student object to be displayed in an output 
                          file
   
   */
   
   private static void addEntry(Student aStudent)
   {
      
      // Contruct a header containing student info
      
      Object[] gradeItemArray = listOfGradeItems.toArray();
   
      output.print("=================================================\n");
      output.print("Student: ");
      output.printf("%s %s %s %s%nGraded Items%n", aStudent.getID(), 
                    aStudent.getFirstName(), aStudent.getLastName(),  
                    aStudent.getEAddress()); 
      
      int maxTotal = 0;             // Cumulative sum of all max scores
      int actualTotal = 0;          // Cumulative sum of all actual scores
      double gradePercent = 0;      // Final grade percent 
            
      for(int index = 0; index < listOfGradeItems.getCurrentSize(); 
          index++)
      {
      
         gradeItem = (GradeItem) gradeItemArray[index];   
         
         // Search list for assignments matching student's ID number
         
         if(student.getID().equals(gradeItem.getStudentID()))
         {
               
            output.printf("%-4s%-10s%-12s%-11s%-6s%s%n", 
                          gradeItem.getItemID(), 
                          gradeItem.getCourseID(), 
                          gradeItem.getItemType(),
                          gradeItem.getGradeDate(), 
                          gradeItem.getMaxScore(), 
                          gradeItem.getGrade());
                          maxTotal += gradeItem.getMaxScore();
                          actualTotal += gradeItem.getGrade();
                          
         }// End if
               
      }// End for
    
    // Output total grade achieved
            
    gradePercent = ((double) actualTotal / maxTotal) * 100;  
    output.printf("================================================="
                  + "%nTotals%34d%6d%nClass Grade%36.2f%%%n%n",
                  maxTotal, actualTotal, gradePercent);
   
   }// End addEntry 
   
//************************************************************************
} // End class