/**
 * Created by HappySaila on 6/15/17.
 */
public class Task {
    private int branch;
    private int duration;

    public Task(int branch, int duration){
        this.branch = branch;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "(" + branch + ", " + duration + ')';
    }

    public int getBranch() {
        return branch;
    }

    public int getDuration() {
        return duration;
    }
}
