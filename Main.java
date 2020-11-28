import java.io.*;
import java.util.*;


class Products {
  String name;
  int cost;

  public Products(String name, int cost) {
    this.name = name;
    this.cost = cost;
  }
	
  @Override
  public String toString() {
      return this.name + ": " + this.cost;
  }
}


public class Main {
  
  public static void main(String[] args) throws Exception {
   
    //to read input.txt  
    FileInputStream input=new FileInputStream("input.txt");      
    Scanner sc=new Scanner(input);

    //Reading no of employees
    int no_of_emp = Integer.parseInt(sc.nextLine().split(":")[1].trim());
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Products> goodies_Productss = new ArrayList<Products>();

    //Products name & cost
    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_Productss.add(new Products(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    //Sorting the Productss
    Collections.sort(goodies_Productss, new Comparator<Products>(){
      public int compare(Products a, Products b) {
        return a.cost - b.cost;
      }
    });

    int min_diff = goodies_Productss.get(goodies_Productss.size()-1).cost;
    int min_index = 0;
    
    // list to find least difference between two Productss
    for(int i=0;i<goodies_Productss.size()-no_of_emp+1;i++) {
      int diff = goodies_Productss.get(no_of_emp+i-1).cost-goodies_Productss.get(i).cost;

      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }

   //Stream to write into output.txt
   FileWriter fw = new FileWriter("output.txt");
    fw.write("The goodies selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + no_of_emp; i++) {
      fw.write(goodies_Productss.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest cost and the lowest cost is " + min_diff);
    fw.close();
  }
}
