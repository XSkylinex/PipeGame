package classes;

import server.Solver;

import java.util.Collection;

public class Rotations{

    Collection<String> collection;
    public Rotations(PipeGameBoard problem,PipeGameBoard solution){
        Helper helper=null;
        collection.add(helper.toString());
        for(int i = 0 ; i < problem.getRows() ; i++ ){
            for(int j = 0 ; j < problem.getColumns() ; j++){

            }
        }
    }

    public Collection<String> returnAllRotations(){
        return collection;
    }

    private class Helper{
        public int row;
        public int col;
        public int round;

        public Helper(int row, int col, int round) {
            this.row = row;
            this.col = col;
            this.round = round;
        }

        @Override
        public String toString() {
            return row+","+col+","+round;
        }
    }
}
