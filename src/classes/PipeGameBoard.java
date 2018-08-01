package classes;

import Interfaces.Searchable;

import java.util.Collection;

public class PipeGameBoard implements Searchable<char[][]> {

    private Tile[][] board;
    private int rows;
    private int columns;



    public PipeGameBoard(String stBoard)
    {

    }


    @Override
    public State<char[][]> getInitialState() {
        return null;
    }

    @Override
    public Collection<State<char[][]>> getAllPossibleStates(State<char[][]> s) {
        return null;
    }

    @Override
    public Boolean IsGoalState(State<char[][]> s) {
        return null;
    }
}
