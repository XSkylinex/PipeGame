package classes;

public class State<T> {

    private T state; // the state represented by a T
    private double cost; // cost to reach this state
    private State<T> cameFrom; // the state we came from to this state

}
