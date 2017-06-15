import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by HappySaila on 6/15/17.
 */
public class Person extends Thread{
    private int personID;
    private ArrayList<Task> Tasks;

    public Person(String input){
        //create a person using input string
        //input string will contain person Id and all there tasks with durations
        Tasks = new ArrayList<>();
        Scanner sc = new Scanner(input);
        if (!sc.hasNext()){
            System.out.println("Cannot find employee ID in contruction.");
            System.exit(0);
        }
        personID = sc.nextInt();

        while(sc.hasNext()){
            Tasks.add(new Task(sc.nextInt(), sc.nextInt()));
        }
    }

    public void run(){
        //contains all persons logic
        System.out.println("person running...");
        while(Tasks.size()>0){
            //while the user has tasks
        }
    }

    @Override
    public String toString() {
        return "Person" + " " + personID + ": " + Tasks;
    }
}
