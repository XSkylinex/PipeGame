package classes;

import enums.Direction;

import java.util.ArrayList;
import java.util.Collection;

public class Tile {

    private char ch;
    private int row;
    private int column;



    public boolean isStart()
    {
        return getCh()=='s';
    }
    public boolean isGoal()
    {
        return getCh()=='g';
    }
    public boolean isBlank()
    {
        return getCh()==' ';
    }
    public boolean isStraightLine()
    {
        return getCh()=='-'||getCh()=='|';
    }
    public boolean isRightAngle()
    {
        return getCh()=='7'|| getCh()=='J'|| getCh()=='L'|| getCh()=='F';
    }

    public Collection<Direction> getDirections()
    {
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
}
