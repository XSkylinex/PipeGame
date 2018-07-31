package server;

import Interfaces.Searchable;

public interface Solver<T> {
    // the solve method
    public Solution<T> solve(Searchable<T> s);

}
