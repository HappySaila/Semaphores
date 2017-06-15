import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by HappySaila on 6/15/17.
 */
public class Person {
    private static int personID;
    private ArrayList<Task> Tasks;

    public Person(String input){
        //create a person using input string
        //input string will contain person Id and all there tasks with durations
        Scanner sc = new Scanner(input);

        personID = sc.nextInt();
        Tasks.add(new Task(sc.nextInt(), sc.nextInt()));
    }
}
