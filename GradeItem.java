/**
 * GradeItem - Creates an object that holds info about graded items
 * @author Jerret Stovall
 * Project 01
 * Windows 10, Acer Aspire R3-131T, jGRASP IDE
 * Imagination: The faculty or action of forming new ideas, or images
 *              or concepts of external objects not present to the senses
 * "If you can visualize it, if you can dream it, there’s some way to do it." 
 *  - Walt Disney (1901 - 1966)
*/
import java.util.Objects;
public class GradeItem
{
   // Variable Declarations
   
   // Array to hold graded item types
   private static String[] gradeType = {"HW", "Quiz", "Class Work", 
                                        "Test", "Final"};
   private String studentID;             // Student ID
   private int itemID;                   // ID of graded item
   private String courseID;              // Course ID 
   private String itemType;              // Type of assignment
   private String gradeDate;             // Date the assignment was submitted
   private int maxScore;                 // Total points available for each 
                                         // graded item
   private int grade;                    // Points scored by the student
   private boolean itemMatch = false;    // Boolean value used to indicate 
                                         // valid item type
                                      
//*****************************************************************************************                                      
                                      
   /**
   
      GradeItem - Creates an object that holds info about graded items
   
      @param   studentID   Student ID#
      @param   itemID      Unique ID given to each assignment
      @param   courseID    Name of the course for each assignment
      @param   itemType    The type of graded assignment
      @param   gradeDate   Date the assignment was graded
      @param   maxScore    Total amount of points for each assignment
      @param   grade       The grade the student received
   
   */
   
   public GradeItem( String itemID, String studentID, String courseID, String itemType, 
                    String gradeDate, String maxScore, String grade ) 
                    throws IllegalArgumentException
   {
      
      // Check each field for empty, invalid or null strings
      
      if ( studentID == null || studentID.equals("") || !studentID.matches("[0-9]+") )
      {
         throw new IllegalArgumentException("Please re-enter student ID.");
      }
      
      if ( itemID == null || itemID.equals("")  || !itemID.matches("[0-9]+") || 
          Integer.parseInt(itemID) <= 0 )
      {
         throw new IllegalArgumentException("Please re-enter item ID.");
      }
      
      if ( courseID == null || courseID.equals("") )
      {
         throw new IllegalArgumentException("Please re-enter course ID.");
      }
      
      if ( itemType == null || itemType.equals("") )
      {
         throw new IllegalArgumentException("Please re-enter item type.");
      }      

      // Check the date for proper formatting by looking at string length and
      // wether there are any letters present
      
      if ( gradeDate == null || gradeDate.equals("") || gradeDate.length() != 8 || 
          !gradeDate.matches("[0-9]+") )
      {
         throw new IllegalArgumentException("Please re-enter date.");
      }
      
      // Check to see if the max score is less than zero
      
      if ( maxScore == null || maxScore.equals("") || !maxScore.matches("[0-9]+") || 
          Integer.parseInt(maxScore) <= 0 )
      {
         throw new IllegalArgumentException("Please re-enter max score.");
      }
      
      // Check to see if the max score is less than the grade recieved 
      
      if ( grade == null || grade.equals("") || !grade.matches("[0-9]+") || 
          Integer.parseInt(grade) <= 0 || Integer.parseInt(grade) > 
          Integer.parseInt(maxScore) )
      {
         throw new IllegalArgumentException("Please re-enter grade recieved.");
      }
      
      // Check the type of graded item against the array of valid item types
      
      for ( int i = 0; i < gradeType.length; i++ )
      {
         if ( gradeType[i].equals(itemType) )
         {
            itemMatch = true;
         }
      }
      
      if ( itemMatch == false )
      {
         throw new IllegalArgumentException("Please re-enter item type.");
      }
      
      // If all checks are passed, values are assigned to variables
      
      this.studentID = studentID;
      this.itemID = Integer.parseInt(itemID);
      this.courseID = courseID;
      this.itemType = itemType;
      this.gradeDate = gradeDate;
      this.maxScore = Integer.parseInt(maxScore);
      this.grade = Integer.parseInt(grade);
      
   }// End Constructor 
  
//*******************************************************************************************

   // Getter Methods

//*******************************************************************************************
   
   // Return the ID of the student to which the assignment belongs
   
   public String getStudentID()
   {
   
      return studentID;
   
   }// End getStudentID
   
   // Return graded object's unique ID
   
   public int getItemID()
   {
   
      return itemID;
   
   }// End getItemID
   
   // Return the course ID to which the graded object belongs
   
   public String getCourseID()
   {
   
      return courseID;
   
   }// End getCourseID
   
   // Return the graded item type
   
   public String getItemType()
   {
   
      return itemType;
   
   }// End getItemType
   
   // Return the date of the graded object
   
   public String getGradeDate()
   {
   
      return gradeDate;
   
   }// End getGradeDate
   
   // Return total points available 
   
   public int getMaxScore()
   {
   
      return maxScore;
   
   }// End getMaxScore
   
   // Return student's grade
   
   public int getGrade()
   {
   
      return grade;
   
   }// End getGrade
   
//*******************************************************************************************

   // End getter methods

//*******************************************************************************************
   /**
   
      equals - checks to see if two graded items are the same
   
      @param   object  the other graded item that is being compared
      @return          boolean result indicating if two graded items are the same
   
   */
   
   @Override
   public int hashCode() 
   {
      
      int hash = 3;
      hash += 97 * hash + Objects.hashCode(this.studentID);
      hash += 97 * hash + Objects.hashCode(this.itemID);
      hash += 97 * hash + Objects.hashCode(this.courseID);
      hash += 97 * hash + Objects.hashCode(this.itemType);
      hash += 97 * hash + Objects.hashCode(this.gradeDate);
      hash += 97 * hash + Objects.hashCode(this.maxScore);
      hash += 97 * hash + Objects.hashCode(this.grade);
    
      return hash;
    
    }
   
   @Override
   public boolean equals(Object obj)
   {
         
        boolean isEqual = false;
        
        if (this == obj) 
        {
    
            return true;
    
        }
    
        // Make sure the object is not null
    
        if (obj == null) 
        {
    
            return false;
    
        }
    
        // Makes sure class types are equivalent
    
        if (getClass() != obj.getClass()) 
        {
    
            return false;
    
        }

        GradeItem other = (GradeItem)obj;

        if ( Objects.equals(this.studentID, other.studentID) &&
             Objects.equals(this.itemID, other.itemID) &&
             Objects.equals(this.courseID,  other.courseID)  &&
             Objects.equals(this.itemType, other.itemType) &&
             Objects.equals(this.gradeDate, other.gradeDate) &&
             Objects.equals(this.maxScore, other.maxScore) &&
             Objects.equals(this.grade, other.grade)) 
        {
        
             isEqual = true;
        
        }
        
        return isEqual;
        
    } // End equals
      
//*******************************************************************************************

   public String toString() 
   {
   
      String msg = "Student ID: " + studentID + "\n"
                    + "Grade Item ID: " + itemID + "\n"
                    + "Course ID: " + courseID + "\n"
                    + "Item Type: " + itemType + "\n"
                    + "Date: " + gradeDate + "\n"
                    + "Max Score: " + maxScore + "\n"
                    + "Grade Recieved: " + grade ;
      return msg;
   
   } // End toString
   
}// End Class