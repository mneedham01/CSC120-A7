/** Cafe class has a constructor, method to sell coffee, method to restock, and method to print information. */
public class Cafe extends Building{
    private int nCoffeeOunces;
    private int nSugarPackets;
    private int nCreams;
    private int nCups;

    /*
     * Constructor for Cafe class
     * @param: name, address, number of floor 
     * @returns: Cafe 
     */
    public Cafe(String name, String address, int nFloors) {
        super(name, address, nFloors);
        this.nCoffeeOunces=100;
        this.nSugarPackets=100;
        this.nCreams=50;
        this.nCups=100;
    }

    /*
     * Method to sell coffee; restocks if necessary, decreases stock  
     * @param: size of coffee, number of sugar packets, number of creams 
     */
    private void sellCoffee(int size, int nSugarPackets, int nCreams){
        if (this.nCoffeeOunces<size){
            System.out.println("Not enough coffee, restocking now.");
            this.restock(50,0,0,0);
        } 
        if (this.nSugarPackets<nSugarPackets){
            System.out.println("Not enough sugar packets, restocking now.");
            this.restock(0,50,0,0);

        }
        if (this.nCreams<nCreams){
            System.out.println("Not enough creams, restocking now.");
            this.restock(0,0,25,0);
        }
        if (this.nCups<1){
            System.out.println("Not enough cups, restocking now.");
            this.restock(0,0,0,50);
        }
        this.nCoffeeOunces -= size;
        this.nSugarPackets -= nSugarPackets;
        this.nCreams -= nCreams;
        this.nCups -=1; 
        System.out.println("Cup of coffee sold.");
    }

    /*
     * Method to restock  
     * @param: number of coffee ounces, number of sugar packets, number of creams, number of cups 
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups){
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
    }
    
    /** Method to print out information and current stock levels in the cafe.  */
    public String toString(){
        String toReturn= this.name + " Cafe is a " + this.nFloors + "-story building located at " + this.address+"\n";
        toReturn+="Currently there are "+this.nCoffeeOunces+" ounces of coffee in stock. \n";
        toReturn+="Currently there are "+this.nSugarPackets+" packets of sugar in stock. \n";
        toReturn+="Currently there are "+this.nCreams+" creams in stock. \n";
        toReturn+="Currently there are "+this.nCups+" cups in stock. \n";
        return toReturn;
    }

    public void showOptions() {
        super.showOptions();
        System.out.println(" + restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) \n");
    }
    public static void main(String[] args) {
        Cafe compass= new Cafe("Compass","7 Neilson Drive",1);
        //try to sell coffee & get it to run out 
        for (int i = 0; i < 10; i++) {
            compass.sellCoffee(12,2,2);
            System.out.println(compass);
          } 
        compass.showOptions();
        
    }
    
}
