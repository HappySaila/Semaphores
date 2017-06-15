import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by HappySaila on 6/15/17.
 */
public class Person extends Thread{
    private int personID;
    private Queue<Task> Tasks;
    private int location;
    private boolean onTaxi;
    private int timeOnCurrentSite;


    public Person(String input){
        //create a person using input string
        //input string will contain person Id and all there tasks with durations
        Tasks = new LinkedList<>();
        Scanner sc = new Scanner(input);
        if (!sc.hasNext()){
            System.out.println("Cannot find employee ID in contruction.");
            System.exit(0);
        }
        personID = sc.nextInt();

        while(sc.hasNext()){
            Tasks.add(new Task(sc.nextInt(), sc.nextInt()));
        }

        //initially at HQ
        location = 0;
    }

    @Override
    public void run(){
        //contains all persons logic
        while(Tasks.size()>0){
            try {
                Taxi.getInstance().getTaxiMove().acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //while the user has tasks
            if (!onTaxi && canLeaveSite()){
                Hail();
            }
        }

        System.out.println("Person " + personID + " done for the day");
    }

    private boolean canLeaveSite(){
        //will return true if current person has been on the site longer than required duration

    }

    public void Hail(){
        //call taxi
        TravelRequest t = getTravelRequest();
        Taxi.getInstance().Hail(t);
    }

    public void Embark(){
        onTaxi = true;
    }

    public void Disembark(){
        onTaxi = false;
    }

    public TravelRequest getTravelRequest(){
        return new TravelRequest(this, location, Tasks.poll().getBranch());
    }

    public int getPersonID() {
        return personID;
    }

    @Override
    public String toString() {
        return "Person" + " " + personID + ": " + Tasks;
    }

}
