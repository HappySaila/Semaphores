import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by HappySaila on 6/15/17.
 */
public class Driver {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String fileName = "Input.txt";


        try {
            Scanner sc = new Scanner(new File(fileName)).useDelimiter("[^0-9]+");
            int numberPeople = 0;
            int numberBranches = 0;
            ArrayList<Person> employees;

            //get number of people
            if (!sc.hasNext()){
                System.out.println("Number of people not found.");
                System.exit(0);
            }
            numberPeople = sc.nextInt();

            //get number of branches
            if (!sc.hasNext()){
                System.out.println("Number of branches not found.");
                System.exit(0);
            }
            numberBranches = sc.nextInt();

            while(sc.hasNext()){
                //create all people
                String line = sc.nextLine();
                Scanner subSc = new Scanner(line).useDelimiter("[^0-9]+");

                //create person
                String inputLineForConstructor = "";
                while (subSc.hasNext()){
                    inputLineForConstructor += subSc.nextInt()+" ";
                }
                employees.add(new Person(inputLineForConstructor));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File "+fileName+" was not found!");
            System.exit(0);
        }
    }
}
