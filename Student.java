/**
  * Student - Creates a student object that holds info like ID Number and Name
  * @author Jerret Stovall
  * Project 01
  * Windows 10, Acer Aspire R3-131T, jGRASP IDE
  * Imagineer: Person who can turn the imaginary into reality. Possibly through actual magic. 
  * "It's kind of fun to do the impossible." - Walt Disney (1901 - 1966)
*/
import java.util.Objects;
public class Student
{
   // Variable Declarations
   
   private String studentID;        // Stores the student's ID
   private String firstName;      // Stores the student's first name
   private String lastName;      // Stores the student's last name
   private String emailAddress;   // Stores the student's email address
   
//*******************************************************************************************   
   
   /**
   
      Student - Builds Student object and takes four parameters
   
      @param   id           the student's ID number
      @param   firstName    the student's first name
      @param   lastName     the student's last name
      @param   email        the student's email
   
   
   */
   
   public Student (String iD, String firstName, String lastName, String email) 
   throws IllegalArgumentException
   {
      
      // Check for null and empty strings
      
      if ( iD == null || iD.equals("") )
      {
         throw new IllegalArgumentException("Please re-enter student ID.");
      }
      
      if ( firstName == null || firstName.equals("") )
      {
         throw new IllegalArgumentException("Please re-enter first name.");
      }
      
      if ( lastName == null || lastName.equals(""))
      {
         throw new IllegalArgumentException("Please re-enter last name.");
      }
      
      if (email == null || email.equals(""))
      {
         throw new IllegalArgumentException("Please re-enter email.");
      } 
      
      // Check for "@" in the email address 
      
      if (!email.contains("@"))
      {
         throw new IllegalArgumentException("Please re-enter email address. " +
                                             "Missing @ Symbol!");   
      }
      
      // If no exceptions are thrown Student object is created and fields are initialized
      
      this.studentID = iD;
      this.firstName = firstName;
      this.lastName = lastName;
      this.emailAddress = email;
      
   } // End Student Constructor
  
//******************************************************************************************* 

   // Getter methods

//******************************************************************************************* 
   
   // Return student's id number
   
   public String getID()
   {
      return studentID;
   }
   
   // Return student's first name
   
   public String getFirstName()
   {
      return firstName;
   }
   
   // Return student's last name
   
   public String getLastName()
   {
      return lastName;
   }
   
   // Return student's full name
   
   public String getFullName()
   {
   
      String fullName = firstName + " " + lastName;
      return fullName;
   
   }
   
   // Return student's email address
   
   public String getEAddress()
   {
   
      return emailAddress;
   
   }

//*******************************************************************************************

   // End getter methods

//*******************************************************************************************

   /**
   
      equals - checks to see if two students are the same
   
      @param   other  the other student that is being compared
      @return         boolean result indicating if two students are the same
   
   */
   
   @Override
   public int hashCode() 
   {
        int hash = 3;
        hash += 97 * hash + Objects.hashCode(this.studentID);
        hash += 97 * hash + Objects.hashCode(this.firstName);
        hash += 97 * hash + Objects.hashCode(this.lastName);
        hash += 97 * hash + Objects.hashCode(this.emailAddress);
        return hash;
    } // End hashCode
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

        // Cast the object to that of the same type as this object
        // Student would cast as Student, and GradeItem as GradeItem
        Student other = (Student)obj;

        // Here, the Objects.equals method requires the objects to be of 
        // the same type to compare them
        if ( Objects.equals(this.studentID, other.studentID) &&
             Objects.equals(this.firstName, other.firstName) &&
             Objects.equals(this.lastName,  other.lastName)  &&
             Objects.equals(this.emailAddress, other.emailAddress)) 
        {
         isEqual = true;
        }
        return isEqual;
    } // End equals
   
//*******************************************************************************************
   
   // Return student's full name, id number, and email
   
   public String toString() 
   {
   
      String msg = "Name: " + firstName + " " + lastName + "\nStudent ID: " + studentID
                    + "\nStudent Email: " + emailAddress;
      return msg;
      
   } // End toString
   
} // End Class