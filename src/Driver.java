import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by HappySaila on 6/15/17.
 */
public class Driver {
    public static void main(String[] args) {
        String fileName = "Input.txt";
        ArrayList<Person> employees = new ArrayList<>();
        int numberPeople = 0;
        int numberBranches = 0;

        try {
            Scanner sc = new Scanner(new File(fileName)).useDelimiter("[^0-9]+");

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

                if (!inputLineForConstructor.equals("")){
                    employees.add(new Person(inputLineForConstructor));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File "+fileName+" was not found!");
            System.exit(0);
        }

        System.out.println("** Taxi Simulation **");
        //set trace level
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the trace level: ");
        Trace.TraceIndex = sc.nextInt();
//        Trace.TraceIndex = 31;

        //begin simulation
        System.out.println("Number of people: "+ numberPeople);
        System.out.println("Number of branches: "+ numberBranches);

        for (int i = 0; i < numberPeople; i++) {
            System.out.println(employees.get(i));
        }

        new Timer(employees);
        Trace.traces = new ArrayList<>();
        System.out.println("\n** Trace **");
        //create the taxi
        Taxi t = new Taxi(numberBranches, numberPeople);
        for (Person p: employees) {
            p.start();
        }
        t.start();

    }
}
