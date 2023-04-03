import java.util.ArrayList;

/*
 * House class has a constructor; accessors; and moveIn, moveOut, and toString methods. 
 */
public class House extends Building {

  private ArrayList<String> residents;
  private boolean hasDiningRoom;
  private boolean hasElevator;
  private boolean enterOnNongroundFloor;

  /*
   * House constructor 
   * @param: String name, String address, int nFloors, boolean hasDining Room 
   * @returns: House 
   */
  public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator, boolean enterOnNongroundFloor) {
    super(name, address, nFloors);
    this.residents = new ArrayList<String>();
    this.hasDiningRoom = hasDiningRoom;
    this.hasElevator =hasElevator;
    this.enterOnNongroundFloor = enterOnNongroundFloor;
  }

  /** Accessor for hasDiningRoom */
  public boolean hasDiningRoom() {
    return this.hasDiningRoom;
  }

  /** Accessor for number of residents */
  public int nResidents() {
    return this.residents.size();
  }

  /** Method to add resident to House if they are not already 
   * @param: name 
  */
  public void moveIn(String name) {
    // check if this.residents contains name
    if (this.isResident(name)) {
      //   if so: throw and exception
      throw new RuntimeException(name + " is already a resident of " + this.name);
    }
    // if we're good to go, add to roster
    this.residents.add(name);
    System.out.println(name + " has just moved into " + this.name + "! Go say hello :-)");
  }

  /** Method to remove resident from House if they are a resident
   * @param: name 
   */
  public String moveOut(String name){
    if (this.isResident(name)){
      residents.remove(name);
      System.out.println(name+ " has just moved out of "+this.name+".");
      return name;
  } else{
    throw new RuntimeException(name+" is not a resident of "+this.name);
  }
}
 /*
  * Method to check if a person is a resident
  * @param: person 
  * @returns: T/F
  */
  public boolean isResident(String person){
    if (residents.contains(person)){
      return true;
    }else{
      return false;
    }
  }


  /** Converts House to string  
   * returns: String
  */
  public String toString() {
    String description = super.toString();
    description += " There are currently " + this.nResidents() + " people living in this house.";
    description += " This house ";
    if (this.hasDiningRoom) {
      description += "has";
    } else {
      description += "does not have";
    }
    description += " an active dining room.";
    return description;
  }

  /*
   * 
   */
  public void showOptions() {
    super.showOptions();
    System.out.println(" + moveIn(String name) \n + moveOut(String name) \n");
}

  /*
   * 
   */
  public void goToFloor(int floorNum) {
    if (!this.hasElevator){
      if (floorNum-this.activeFloor!=1 || this.activeFloor-floorNum!=1){
        throw new RuntimeException("As "+this.name+ " House does not have an elevator, you cannot move up or down more than one floor at a time.");
      }
    } else{
      super.goToFloor(floorNum);
    }
    }

  /*
   * 
   */
  public Building enter(int floorNum){
    if (this.enterOnNongroundFloor){
      this.activeFloor=floorNum;
      System.out.println("You are now inside "+ this.name+ " on floor "+ floorNum);
      return this;
    } else{
      super.enter();
    }
  }

  
  public static void main(String[] args) {
    House morrow = new House("Morrow", "The Quad", 4, false, false,false);
    System.out.println(morrow);
    morrow.moveIn("Jordan");
    morrow.moveOut("Jordan");
    System.out.println(morrow);
    House king = new House("King", "The Quad", 3, true, true,false);
    System.out.println(king);
    king.showOptions();
    king.enter();
    king.goToFloor(1);
    morrow.enter();
    morrow.goToFloor(5);
    House parsons= new House("Parsons","Behind Helen Hills Hills", 4, false, false, true);
    parsons.enter(2);
  }

}