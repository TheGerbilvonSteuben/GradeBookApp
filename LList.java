/** This program creates a linked list 
  * @author Jerret Stovall
  * @author Thomas Miller
  * Project 04
  * Windows 10, Acer Aspire R3-131T, jGRASP IDE version 2.0.5_06
  * Eccentric:(of a person or their behavior) unconventional 
  * and slightly strange.
  * "The only thing predictable about life is its unpredictability." 
  *                                         -- Remy (Ratatouille)
*/

//************************************************************************

public class LList<T> implements MyCollectionInterfaceProject04<T> 
{
   
   // Declarations 
   
   private Node<T> firstNode;   // References the first node in the list
   private int numberOfNodes;   // Keeps track of how long the list is
   
   // Constructor
   
   public LList() 
   {
   
      firstNode = null;         // To initialize the list, 
                                // set the first node
      numberOfNodes = 0;        // to null and the number of nodes to 0
      
   } // End constructor 

//************************************************************************
 
   /** 
   
     add -             Adds an item to the end of the list
     
     @param  newItem   Generic type item to be added to the list
     
     @return           Boolean result indicating whether an item 
                       was added or not
     
   */
   
   public boolean add(T newEntry) 
   {
         
         // Create a new node that contains the item to be added
               
         Node<T> newNode = new Node<T>(newEntry);
         
         // The boolean result will indicate if the item was 
         // successfully added to the list
         
         boolean result = false;
         
         if(isEmpty())
         {
                                 // Check to see if the list is 
            firstNode = newNode; // empty and take appropriate 
            result = true;       // action if it is
         
         }// End if
         else                    // Else add the entry to the 
         {                       // end of the list
         
            Node<T> lastNode = getNodeAt(numberOfNodes);
            lastNode.setNextNode(newNode);
            result = true;
         
         }// End else
         
         numberOfNodes++;        // Increase the number of nodes after
         return result;          // each successful addition and return
                                 // true or false to indicate a successful
   } // End add                  // operation
   
//************************************************************************
   
   /**
    
     add               Adds a new item to the list at a given index
     
     @param   newItem  Generic type item to be added to the list
     
     @param   index    Index of where that item will be placed in the list
     
     @return           Boolean result indicating whether an item was added
     
     @throws           IndexOutOfBoundsException if an invalid position is 
                       given to the add operation
                     
   */
   
   public boolean add(T newEntry, int index) 
   throws IndexOutOfBoundsException 
   {
   
      boolean result = false;   // Indicates a successful addition 
      
      // Check if the given index is a valid position within the list
      
      if(index >= 1 && index <= numberOfNodes + 1) 
      {     
         
         Node<T> newNode = new Node<T>(newEntry);
         
         // Check where this new node will be added within the list
            
         if(index == 1)     // Add to the beginning of the list
         {
         
            newNode.setNextNode(firstNode);
            firstNode = newNode;
            result = true;
         
         }// End if   
         else               // Add to the middle or end of the list
         {
         
            Node<T> nodeBefore = getNodeAt(index - 1);
            Node<T> nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
            result = true;
            
         }// End else
         
         numberOfNodes++;   // Increment the number of nodes
            
      }// End if
      else                  // Throw an exception if the given 
      {                     // position is invalid

         throw new IndexOutOfBoundsException("Illegal position given " +
                                             "to add operation");
      
      }// End else
      
      return result;        // True or false if the node was added or not
   
   }// End add
   
//************************************************************************
   
   /**
   
      remove -     Removes the last item added to the list
      
      @throws      IllegalStateException if the list is empty
   
   */
   
   public void remove() throws IllegalStateException
   {
   
      if(!isEmpty()) // Check to see if the list is empty
      {
         
         // Remove the last node added and decrease the number of nodes
         
         firstNode = firstNode.getNextNode();
         numberOfNodes--;
      
      }// End if
      else
      {
      
         // Throw an exception if the list is empty
         
         throw new IllegalStateException("Could not perform remove " +
                                         "operation because the list " +
                                         "is empty");
      
      }// End else
   
   }// End remove

//************************************************************************

   /** 
   
     remove -            Removes every occurrence a given entry from the
                         list
     
     @param    anEntry   Generic type item to be removed from the list
     
     @return   boolean   Returns true if item was removed from the list 
     
     @throws             IllegalArgumentException if the list does not
                         contain the given item
   
   */
   
   public boolean remove(T anEntry) throws IllegalArgumentException
   {
      
      while(contains(anEntry))
      {
      
         remove(findLocation(anEntry));
      
      }// End while
      
      return contains(anEntry);
   
   } // End remove

//************************************************************************
  
   /**
   
     remove -                  Removes and returns an entry from a given 
                               position within the list
     
     @param    givenPosition   Location of the entry to be removed
     
     @return   result          Removed entry
     
     @throws                   IndexOutOfBoundsException if an invalid 
                               position is given
   
   */
   
   public T remove(int givenPosition) throws IndexOutOfBoundsException
   {
   
      T result = null;
      
      if((givenPosition >= 1) && (givenPosition <= numberOfNodes))
      {
      
         // Assertion !isEmpty();
         
         if(givenPosition == 1)
         {
         
            result = firstNode.getData();
            firstNode = firstNode.getNextNode();
         
         }// End if
         else
         {
         
            Node<T> nodeBefore = getNodeAt(givenPosition - 1);
            Node<T> nodeToRemove = nodeBefore.getNextNode();
            result = nodeToRemove.getData();
            Node<T> nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);
            nodeToRemove = null;
         
         }// End else
         
         numberOfNodes--;
         return result;
      
      }// End if
      else
      {
      
         throw new IndexOutOfBoundsException("Illegal position: " +
                                             givenPosition + " given " +
                                             "to remove operation");
      
      }// End else
   
   }// End remove

//************************************************************************
   
   /**
   
      replace -                Replaces an entry at a given position with 
                               one provided by the client
                
      @param   givenPosition   Location of the entry to be replaced
      
      @param   newEntry        Generic type item to be swapped
   
   */
   
   public T replace(int givenPosition, T newEntry)
   {
   
      if((givenPosition >= 1) && (givenPosition <= numberOfNodes))
      {
      
         // Assertion !isEmpty();
         
         Node<T> desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
         desiredNode.setData(newEntry);
         
         return originalEntry;
      
      }//End if
      else
      {
      
         throw new IndexOutOfBoundsException("Illegal position: " + 
                                             givenPosition + " given to " 
                                             + "replace operation");
      
      }// End else
   
   }// End replace

//************************************************************************
   
   /**
   
      clear - Removes every entry from the list
   
   */
   
   public void clear() 
   {
   
      firstNode = null;  // Essentially just re-initializing the list
      numberOfNodes = 0; 
   
   } // End clear
   
//************************************************************************

   /** 
     
     getCurrentSize - Returns the current size of the list
     
     @return   int    Current size of the list 
   
   */
   
   public int getCurrentSize() 
   {
                                        
      return numberOfNodes; // The number of nodes is also how                                                                                                                                                                     
                            // long the list is
   } // End getCurrentSize

//************************************************************************

   /**
     
     isEmpty -           Checks to see if the list is empty
     
     @return   boolean   Returns true if the list is empty 
   
   */
   
   public boolean isEmpty() 
   {
      
      boolean result = false;
         
      if(numberOfNodes == 0)
      {
      
         // Assertion firstNode == null;
         result = true;
      
      }// End if
      else
      {
      
         //Assertion firstNode != null
         result = false;
      
      }// End else
      
      return result;
   
   } // End isEmpty
   
//************************************************************************
   
   /**
   
      getEntry -               Retreives an entry at a given position
      
      @param   givenPosition   The location of the entry to be retrieved
      
      @throws                  IndexOutOfBoundsException is the given 
                               position is invalid
   
   */
   
   public T getEntry(int givenPosition) throws IndexOutOfBoundsException
   {
      
      // Check to see if the given position is valid
      
      if((givenPosition >= 1) && (givenPosition <= numberOfNodes))
      {
         
         return getNodeAt(givenPosition).getData();
      
      }// End if
      else
      {
         
         // Throw an exception if the given position is invalid
         throw new IndexOutOfBoundsException("Illegal position: " + 
                                             givenPosition + " given " +
                                             "to getEntry operation");
      
      }// End else
   
   }// End getEntry

//************************************************************************

   /** 
   
     getFrequencyOf -   Compares a given item to the list and returns how 
                        many times that item appears in the list
                      
     @param    anEntry  Generic type item to be compared to list contents
     
     @return   int      Number of times the given item appears in the list 
   
   */
   
   public int getFrequencyOf(T anEntry) 
   {
   
      int frequency = 0;
      
      Node<T> currentNode = firstNode;   
      
      // Traverse the chain and record how many times
      // a given entry appears
      
      while(currentNode != null)
      {
         T target = currentNode.getData();
         
         if(target.equals(anEntry))
         {
         
            frequency++;
         
         }// End if
         
         currentNode = currentNode.getNextNode();
      
      }// End while
         
      return frequency;
      
   } // End getFrequencyOf
   
//************************************************************************

   /** 
     
     contains -          Checks to see if a given entry is in the list
     
     @param    anEntry   Generic type item to be compared to the list
     
     @return   boolean   Returns true if the list contains the item 
   
   */
   
   public boolean contains(T anEntry)
   {
   
      boolean result = false; 
      Node<T> currentNode = firstNode;
      
      // Traverse the chain and check if it has the given entry
      
      while(currentNode != null && !result)
      {
      
         if(anEntry.equals(currentNode.getData()))
         {
         
            // If a matching entry has been detected,
            // activate the trigger boolean to end the loop
            
            result = true;
         
         }// End if
         else
         {
         
            currentNode = currentNode.getNextNode();     

         }// End else
      
      }// End while
      
      return result;
      
   } // End contains
   
//************************************************************************
   
   /**
   
      getNodeAt -             Retrieves a node at a given location
                              (does not remove the node)
      
      @param   givenposition  Location of the node to be retrieved
      
      @return  Node<T>        Desired node from given location
      
      @throws                 IllegalArgumentException if the given
                              location is not in the list
   
   */
   
   private Node<T> getNodeAt(int givenPosition) 
   throws IllegalArgumentException
   {
      
      Node<T> currentNode = firstNode;
      
      if((givenPosition >= 1) && (givenPosition <= numberOfNodes))
      {
         
         for(int counter = 1; counter < givenPosition; counter++)
         {
         
            // Traverse the chain until we land on the node we want
            
            currentNode = currentNode.getNextNode();
         
         }// End for
      
      }// End if
      else
      {
         
         // Throw an exception the given position is not on the list
         
         throw new IndexOutOfBoundsException("Illegal position given " + 
                                             "to getNodeAt operation");
      
      }// End else
   
      return currentNode;
      
   }// End getNodeAt

//************************************************************************
   
   /**
   
      findLocation -     Returns the location of a given entry within 
                         the list
                           
      @param   anEntry   Genreic type item to be found
      
      @return  int       Location of the given entry within the list
      
      @throws            IllegalArgumentException if the given entry is
                         not in the list
   
   */
   
   public int findLocation(T anEntry) throws IllegalArgumentException
   {
   
      // Set up neccsessary variables 
      
      Node<T> currentNode = firstNode;
      int index = 0;
      boolean done = false;
      
      // Traverse the chain
      
      while(currentNode != null && !done)
      {
      
         if(!currentNode.getData().equals(anEntry))
         {
         
            // Index will keep track of how many nodes
            // we have traversed
            
            index++;
         
         }// End if
         else if(currentNode.getData().equals(anEntry))
         {
            
            // Once a node containing the desired entry is located
            // its position is stored into index and the trigger
            // boolean is activated to end the loop
            
            index++;
            done = true;
         
         }// End else if
         
         currentNode = currentNode.getNextNode();
      
      }// End while
      
      // Let the client/user know if the entry is
      // not in the list and set index to zero
      
      if(!done)
      {
         
         throw new IllegalArgumentException(anEntry.toString() + 
                                            " is not in the list.");
   
      }// End if
      
      return index;
      
   }// End findLocation

//************************************************************************

   /** 
   
     toArray -           Takes the current list and copies its 
                         data into an array to be used by the client
               
     @return   Object[]  Array containing data from each node in the list 
   
   */
   
   public Object[] toArray()
   {
   
      // Keep the compiler happy by throwing in a 
      // SuppressWarnings because we know what
      // we are about to do is safe
      
      @SuppressWarnings("unchecked")
      Object[] objectArray = new Object[numberOfNodes];
      Node<T> currentNode = firstNode;
      
      for( int i = 0; i < numberOfNodes; i++) 
      {
      
         // Fill the array
         
         objectArray[i] = currentNode.getData();
         
         // Traverse the chain 
         
         currentNode = currentNode.getNextNode();
      
      }// End for
      
      if(numberOfNodes == 0 && firstNode == null)
      {
      
         // Let the client/user know if the array is empty
         
         System.err.println("An empty array has been generated");
      
      }// End if
      
      // Return the finished array
      
      return objectArray;
      
   } // End toArray
   
//************************************************************************
} // End class