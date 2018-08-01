package classes;

import java.util.Objects;

public class State<T> {

    private T state; // the state represented by a T
    private double cost; // cost to reach this state
    private State<T> cameFrom; // the state we came from to this state

    public State(T state) {
        this.state = state;
        this.cost=0;
        this.cameFrom=null;
    }

    public State(T state, double cost, State<T> cameFrom) {
        this.state = state;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public State<T> getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(State<T> cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State<?> state1 = (State<?>) o;
        return Double.compare(state1.cost, cost) == 0 &&
                Objects.equals(state, state1.state) &&
                Objects.equals(cameFrom, state1.cameFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, cost, cameFrom);
    }

    @Override
    public String toString() {
        return state.toString();
    }


}
