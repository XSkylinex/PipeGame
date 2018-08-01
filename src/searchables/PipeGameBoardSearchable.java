package searchables;

import Interfaces.Searchable;
import classes.PipeGameBoard;
import classes.State;

import java.util.ArrayList;
import java.util.Collection;

public class PipeGameBoardSearchable implements Searchable<PipeGameBoard> {

    private PipeGameBoard pipeGameBoard;

    public PipeGameBoardSearchable(PipeGameBoard pipeGameBoard) {
        this.pipeGameBoard = pipeGameBoard;
    }

    @Override
    public State<PipeGameBoard> getInitialState() {
        return new State<>(pipeGameBoard);
    }

    @Override
    public Collection<State<PipeGameBoard>> getAllPossibleStates(State<PipeGameBoard> s) {
        Collection<PipeGameBoard> gameBoardCollection=s.getState().getAllRotation();
        Collection<State<PipeGameBoard>> stateCollection=new ArrayList<>();
        for(PipeGameBoard pipeGameBoard:gameBoardCollection){
            State<PipeGameBoard> boardState=new State<>(pipeGameBoard,s.getCost()+1,s);
            stateCollection.add(boardState);
        }
        return stateCollection;
    }

    @Override
    public Boolean IsGoalState(State<PipeGameBoard> s) {
        return s.getState().isSolved();
    }
}
