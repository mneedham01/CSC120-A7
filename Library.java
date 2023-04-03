import java.util.Hashtable;

/** Library class has a constructor, ability to add and remove books, check and return books, check if book is available/owned, and print out collection. 
 */
public class Library extends Building{

    private Hashtable <String, Boolean> collection;
    private boolean hasElevator;

    /*
     * Library constructor 
     * @param: name, address, number of floors 
     * @returns: Library 
     */
    public Library(String name, String address, int nFloors) {
      super(name, address, nFloors);
      this.collection = new Hashtable<String,Boolean>(); 
    }

    /** 
     * Method to add a book to library 
     * @param: book title 
     */
    public void addTitle(String title){
      if(this.collection.containsKey(title)){
        throw new RuntimeException(this.name+" library already owns "+title+"."); 
      } else{
        this.collection.put(title,true);
        System.out.println(title+" has been added to "+this.name+" library and is ready to be checked out.");
      }
    }

    /*
     * Method to remove title from library 
     * @param: Book title
     */
    public String removeTitle(String title){
      if (!this.collection.containsKey(title)){
        throw new RuntimeException(this.name+ " library does not own "+title+".");
      }else{
        this.collection.remove(title);
        System.out.println(title+" has been removed from "+this.name+" library.");
        return title;
      }
    }

    /*
     * Method to check out book if the library owns it and if it is available. 
     * @param: Book title 
     */
    public void checkOut(String title){
      //check whether the library owns the book 
      if (containsTitle(title)){
        //check whether the book is available 
        if (isAvailable(title)){
          this.collection.replace(title, false);
          System.out.println(title+" is checked out.");
        }
        //the book is not avaible -> throw runtime exception 
        else{
          throw new RuntimeException(title+" is already checked out.");
        }
      //the library does not own the book -> throw runtime exception 
      } else{
        throw new RuntimeException(this.name+" doesn't own "+title);
      }
    }

    /*
     * Method to return book if the library owns it and it has been checked out. 
     * @param: Book title 
     */
    public void returnBook(String title){
      //check whether the library owns the book 
      if (containsTitle(title)){
        //check whether the book has been checked out 
        if (!isAvailable(title)){
          this.collection.replace(title,true);
          System.out.println(title+" has been returned.");
        } 
        //the book has not been checked out already -> throw runtime exception 
        else{
          throw new RuntimeException(title+" has not been checked out.");
        }
      //the library does not own the book -> throw runtime exception 
      } else{
        throw new RuntimeException(this.name+" doesn't own "+title);
      }
    }

    /*
     * Checks whether library owns a book 
     * @param: Book title 
     * @returns: T/F 
     */
    public boolean containsTitle(String title){
      if (this.collection.containsKey(title)){
        return true;
      } else {
        return false;
      }
    }

    /*
     * Checks whether a booked is available 
     * @param: Book title
     * @returns: T/F
     */
    public boolean isAvailable(String title){
      if(this.collection.get(title)){
        return true;
      }else{
        return false;
      }
    }

    /** Prints collection */
    public void printCollection(){
      String toPrint="";
      toPrint+=this.collection.toString();
      toPrint=toPrint.replace("true"," Available");
      toPrint=toPrint.replace("false"," Checked Out");
      System.out.println(toPrint+"\n");
    }

    /*
     * 
     */
    public void showOptions() {
      super.showOptions();
      System.out.println(" + addTitle(String title) \n + removeTitle(String title) \n + checkOut(String title \n + returnBook(String title) \n");
  }

  /*
   * 
   */
  public void goToFloor(int floorNum) {
    if (!this.hasElevator){
      if (floorNum-this.activeFloor!=1 || this.activeFloor-floorNum!=1){
        throw new RuntimeException("As this building does not have an elevator, you cannot move up or down more than one floor at a time.");
      }
    } else{
      super.goToFloor(floorNum);
    }
    }
  
    public static void main(String[] args) {
      //create a library 
      Library neilson= new Library("Neilson", "7 Neilson Drive", 4);

      //try to add a book 
      neilson.addTitle("Charlotte's Web by E.B. White");
      //check if book is in collection 
      neilson.printCollection();
    
      //try to add another two books 
      neilson.addTitle("Harry Potter and the Sorcerer's Stone by J.K. Rowling");
      neilson.addTitle("The Lion, The Witch, and The Wardrobe by C.S. Lewis");
      neilson.printCollection();
      
      //try to add a book that has already been added (should throw a runtime exception)
      try{
        neilson.addTitle("Charlotte's Web by E.B. White");
      } catch (Exception e){
        System.out.println(e+"\n");
      }
      //try to remove a book 
      neilson.printCollection();
      neilson.removeTitle("The Lion, The Witch, and The Wardrobe by C.S. Lewis");
      neilson.printCollection();
      //try to remove a book that's not there (should throw a runtime exception)
      try{
        neilson.removeTitle("Harry Potter and the Chamber of Secrets by J.K. Rowling");
      } catch (Exception e){
        System.out.println(e+"\n");
      }
      
      //try to check out a book 
      neilson.checkOut("Charlotte's Web by E.B. White");
      neilson.printCollection();
      //try to return a book 
      neilson.returnBook("Charlotte's Web by E.B. White");
      neilson.printCollection();
      //try to check out a book that the library doesn't own (should throw a runtime exception)
      try{
        neilson.checkOut("Harry Potter and the Prisoner of Azkaban by J.K. Rowling");
      } catch (Exception e){
        System.out.println(e+"\n");
      }
      //try to check out a book that is already checked out (should throw a runtime exception)
      neilson.checkOut("Charlotte's Web by E.B. White");
      neilson.printCollection();
      try{
        neilson.checkOut("Charlotte's Web by E.B. White");
      } catch (Exception e){
        System.out.println(e+"\n");
      }
      //try to return a book that's already returned (shoudl throw a runtime exception)
      neilson.returnBook("Charlotte's Web by E.B. White");
      try{
        neilson.returnBook("Charlotte's Web by E.B. White");
      } catch (Exception e){
        System.out.println(e+"\n");
      }
      
      //check whether it returns containsTitle if the library owns the book 
      System.out.println("According to containsTitle, the fact that it owns Charlotte's Web is: "+neilson.containsTitle("Charlotte's Web"));
      neilson.showOptions();
  


      
    }
  
  }