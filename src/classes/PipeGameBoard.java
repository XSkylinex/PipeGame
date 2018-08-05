package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class PipeGameBoard{

    private Tile[][] board;
    private int rows;
    private int columns;

    public PipeGameBoard(){
        this(new Tile[0][0],0,0);
    }

    public PipeGameBoard(Tile[][] board, int rows, int columns){
        setRows(0);
        setColumns(0);
        setBoard(board);
    }

    public PipeGameBoard(PipeGameBoard pipeGameBoard) {
        this(pipeGameBoard.getBoard(),pipeGameBoard.getRows(),pipeGameBoard.getColumns());
    }

    public PipeGameBoard(String stBoard){
        if(stBoard == null || stBoard.isEmpty()){
            setRows(0);
            setColumns(0);
            setBoard(new Tile[this.rows][this.columns]);
        }
        else{
            String[] strboard=stBoard.split("\n");
            char[][] chsboard=new char[strboard.length][];
            for(int i=0;i<strboard.length;i++){
                chsboard[i]=strboard[i].replaceAll("[\n\r]", "").toCharArray();
            }
            setBoard(chsboard);
        }
    }

    protected Tile getTile(int row,int col) throws Exception {
        if(0<=row&&row<this.rows&&0<=col&&col<this.columns)
            return this.board[row][col];
        throw new Exception("Out of Board");
    }

    public PipeGameBoard defaultPipeGameBoard(){
        PipeGameBoard clone = this.clone();
        for(int i = 0 ; i < this.rows ; i++){
            for(int j = 0 ; j < this.columns ; j++){
                try {
                    clone.board[i][j]=clone.getTile(i,j).defaultTile();
                } catch (Exception ignored) { }
            }
        }

        return clone;
    }

    public Collection<PipeGameBoard> getAllRotation(){
        Collection<PipeGameBoard> gameBoardCollection=new ArrayList<>();
        for(int i = 0 ; i < this.rows ; i++){
            for(int j = 0 ; j < this.columns ; j++){
                PipeGameBoard clone = this.clone();
                try {
                    Tile tile = clone.getTile(i, j);
                    if (!tile.isBlank() && !tile.isStart() && !tile.isGoal()) {
                        int rotationcount = tile.countRotations();
                        for (int r = 0; r < rotationcount; r++) {
                            clone.getTile(i, j).rotate();
                            PipeGameBoard pipeGameBoard = clone.clone();
                            gameBoardCollection.add(pipeGameBoard);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return gameBoardCollection;
    }

    public boolean isSolved(){
        return true;
    }

    protected PipeGameBoard clone(){
        return new PipeGameBoard(this);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        for(int i = 0 ; i < this.rows ; i++) {
            for (int j = 0; j < this.columns; j++){
                try {
                    stringBuilder.append(this.getTile(i,j).toString());
                } catch (Exception ignored) { }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board.clone();//////!!!!!!!!!!!!!!!!!!!
    }
    private void setBoard(char[][] chsboard) {
        setRows(chsboard.length);
        setColumns(chsboard[0].length);
        this.board=new Tile[this.rows][this.columns];
        for(int row=0;row<this.rows;row++)
        {
            for(int column=0;column<this.columns;column++)
            {
                this.board[row][column]=new Tile(chsboard[row][column],row,column);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    private void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    private void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PipeGameBoard that = (PipeGameBoard) o;
        return rows == that.rows &&
                columns == that.columns &&
                Arrays.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, columns);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }


}
