/**
 * Created by HappySaila on 6/15/17.
 */
public class TravelRequest {
    private Person person;
    private int source;
    private int destination;

    public TravelRequest(Person person, int source, int destination) {
        this.person = person;
        this.source = source;
        this.destination = destination;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public Person getPerson() {
        return person;
    }
}
