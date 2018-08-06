package classes;

import enums.Direction;

import java.util.*;
import java.util.function.Predicate;

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
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return gameBoardCollection;
    }

    public boolean isSolved(){ // is solve this function check if we any goal connected to start
        Collection<Tile> tilesgoals=getGoalsIndex(); // we create collection of all goals on the board
        boolean flag;
        for(Tile tgoal:tilesgoals){ // we ues for each to Search on goals
            flag=isTilesConnectedToStart(tgoal);
            if(!flag){
                return false; // not connected return false
            }
        }
        return true; //found return for start the game
    }

    public boolean isTilesConnectedToStart(Tile tile) {
        return isTilesConnected(tile, Tile::isStart); // !!!----here is lambda expression----!!! here we check if we connected to the start
    }
    public boolean isTilesConnectedToGoal(Tile tile) {
        return isTilesConnected(tile, Tile::isGoal); // !!!----here is lambda expression----!!! here we check if we connected to the goal
    }

    private boolean isTilesConnected(Tile tile, Predicate<Tile> tilePredicate){ // this function check the connects batten pipes
        Queue<Tile> queue=new LinkedList<>(); // Create queue
        HashSet<Tile> closedSet=new HashSet<>(); // Crate HashSet check the black hols for not step on the same tile twitch
        queue.add(tile); // add to queue
        while(!queue.isEmpty()){

            Tile n=queue.remove(); // algorithm 1 to be continue
            closedSet.add(n);
            try{
                if(tilePredicate.test(n)){
                    return true;
                }
                Collection<Tile> successors=getConnectedsTiles(n);
                for(Tile t:successors){
                    if(t!=null&&!closedSet.contains(t))
                    {
                        if(!queue.contains(t)){
                            queue.add(t);
                        }
                    }
                }
            }catch (Exception e){
                //e.printStackTrace();
            }
        }
        return false;
    }

    private Collection<Tile> getConnectedsTiles(Tile tile){ // get all connectors tiles on the board from specific tile
        Collection<Tile> arrayList=new ArrayList<>(); // Create Collection of tiles
        int row=tile.getRow();
        int col=tile.getColumn();
        Tile tile2;
        Collection<Direction> directions=tile.getDirections(); // get directions from this specific tile
        for(Direction dire:directions){
            try {
                tile2=null;
                switch (dire){
                    case up:
                    {
                        tile2=(this.getTile(row-1, col));
                        break;
                    }
                    case down:
                    {
                        tile2=(this.getTile(row+1, col));
                        break;
                    }
                    case left:
                    {
                        tile2=(this.getTile(row, col-1));
                        break;
                    }
                    case right:
                    {
                        tile2=(this.getTile(row, col+1));
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
                if(tile2!=null){ // if have tile this direction so add this tile to Collection
                    if(tile.isTilesAreConnect(tile2)){
                        arrayList.add(tile2);
                    }
                }
            }catch (Exception e) {
            }
        }

        return arrayList;
    }

    public Collection<Tile> getGoalsIndex() {
        return getTilesIndexByPredicate(Tile::isGoal);
    }
    public Collection<Tile> getStartsIndex() {
        return getTilesIndexByPredicate(Tile::isStart);
    }

    private Collection<Tile> getTilesIndexByPredicate(Predicate<Tile> tilePredicate)
    {
        Collection<Tile> tileCollection=new ArrayList<>(); // create collection of tiles
        for(int i = 0 ; i < this.rows ; i++) { // run on all row
            for (int j = 0; j < this.columns; j++){ // run on all columns
                try {
                    Tile tile=this.getTile(i,j); // get every tile - create new pointer
                    if(tilePredicate.test(tile)){ // check if this tile pass the test
                        tileCollection.add(tile); // if yes so add the goal to collection
                    }
                } catch (Exception ignored){ //java love to do that

                }
            }
        }
        return tileCollection;
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
