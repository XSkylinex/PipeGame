package classes;

import enums.Direction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Tile {

    private char ch;
    private int row;
    private int column;

    public Tile(char ch, int row, int column) {
        this.ch = ch;
        this.row = row;
        this.column = column;
    }

    public Tile rotate()
    {
        switch (this.ch) {
            case '-': {
                this.ch = '|';
                break;
            }
            case '|': {
                this.ch = '-';
                break;
            }
            case '7': {
                this.ch = 'J';
                break;
            }
            case 'J': {
                this.ch = 'L';
                break;
            }
            case 'L': {
                this.ch = 'F';
                break;
            }
            case 'F': {
                this.ch = '7';
                break;
            }
            default:
            {
                break;
            }
        }
        return this;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isStart(){
        return getCh()=='s';
    }

    public boolean isGoal(){

        return getCh()=='g';
    }
    public boolean isBlank(){

        return getCh()==' ';
    }
    public boolean isStraightLine(){
        return getCh()=='-'||getCh()=='|';
    }
    public boolean isRightAngle(){
        return getCh()=='7'|| getCh()=='J'|| getCh()=='L'|| getCh()=='F';
    }

    public Collection<Direction> getDirections(){
        Collection<Direction> directions=new ArrayList<Direction>();
        switch (this.ch) {
            case 's': {
                directions.add(Direction.up);
                directions.add(Direction.down);
                directions.add(Direction.right);
                directions.add(Direction.left);
                break;
            }
            case 'g': {
                directions.add(Direction.up);
                directions.add(Direction.down);
                directions.add(Direction.right);
                directions.add(Direction.left);
                break;
            }
            case '7': {
                directions.add(Direction.left);
                directions.add(Direction.down);
                break;
            }
            case 'J': {
                directions.add(Direction.left);
                directions.add(Direction.up);
                break;
            }
            case 'L': {
                directions.add(Direction.up);
                directions.add(Direction.right);
                break;
            }
            case 'F': {
                directions.add(Direction.down);
                directions.add(Direction.right);
                break;
            }
            case '-': {
                directions.add(Direction.left);
                directions.add(Direction.right);
                break;
            }
            case '|': {
                directions.add(Direction.up);
                directions.add(Direction.down);
                break;
            }
        }
        return directions;
    }

    public int countRotations(){
        if(this.ch == '|' || this.ch == '-'){
            return 2;
        }
        if(this.ch == 's' || this.ch == 'g' || this.ch ==' '){
            return 1;
        }
        return 4;
    }

    public Direction isTilesNeighbors(Tile tile){ //If the tiles ​​are neighbors
        //if(this.row==tile.row && this.column == tile.column){
        //    throw new Exception("Same tile");
        //}
        if(this.row==tile.row+1 && this.column == tile.column){
            return Direction.down;
        }
        if(this.row==tile.row && this.column == tile.column+1){
            return Direction.right;
        }
        if(this.row==tile.row-1 && this.column == tile.column){
            return Direction.up;
        }
        if(this.row==tile.row && this.column == tile.column-1){
            return Direction.left;
        }
        return null;
    }

    public boolean tilesAreConnect(Tile tile){ // Checks if tiles are connected
        return this.getDirections().contains(this.isTilesNeighbors(tile))&&
                tile.getDirections().contains(tile.isTilesNeighbors(this));
    }

    protected Tile clone(){ // create new instance
        return new Tile(ch,row,column);
    }

    public Tile defaultTile(){ // set to default tile
        Tile tile=this.clone();
        switch (this.ch) {
            case '7': case 'J': case 'L': case 'F': {
                tile.setCh('7');
                break;
            }
            case '-': case '|': {
                tile.setCh('-');
                break;
            }
        }
        return tile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return ch == tile.ch &&
                row == tile.row &&
                column == tile.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ch, row, column);
    }

    @Override
    public String toString() {
        return ch+"";
    }
    
    public Double countDistance(Tile tile){
        return Math.hypot(tile.row-this.row,tile.column-this.column);
    }

}
