/*
MazeSquare
by Yasmeen Awad

This module defines the object MazeSquare by its location in the Maze, it's type, and whether
or not it is the start or finish of the maze. Accompanies Maze.java.
*/

public class MazeSquare{
    private int xCoord;
    private int yCoord;
    private char squareType;
    private boolean start;
    private boolean finish;
    private boolean visited;
    private boolean marked;
    
    public MazeSquare(int xCoord, int yCoord, char squareType, boolean start,
                     boolean finish){
        // (x, y) are the coordinates of the location of the square
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        // Square type refers to which walls the square should have
        this.squareType = squareType;
        // Start and finish indicate whether or not the square is the start or finish of the maze
        this.start = start;
        this.finish = finish;
        // All MazeSquares are, by default, unvisited and unmarked
        this.visited = false;
        this.marked = false;
    }
    // Returns x-coordinate
    public int getX(){
        return this.xCoord;
    }
    // Takes in an integer and sets it as that square's x-coordinate
    public void setX(int x){
        this.xCoord = x;
    }
    // Returns y-coordinate
    public int getY(){
        return this.yCoord;
    }
    // Takes in an integer and sets it as that square's y-coordinate
    public void setY(int y){
        this.yCoord = y;
    }
    // Returns the type of the MazeSquare
    public char getType(){
        return this.squareType;
    }
    // Takes in a character and sets it as the square's type
    public void setType(char c){
        this.squareType = c;
    }
    // Returns a character code that represents whether the square is the start ('S'), 
    // finish ('F'), or neither ('N')
    public char getSOrF(){
        if (this.start){
            return 'S';
        } else if (this.finish){
            return 'F';
        } else {
            return 'N';
        }
    }
    // Returns true if the square has been visited
    public boolean visited(){
        return this.visited;
    }
    // Marks the MazeSquare as visited
    public void visit(){
        this.visited = true;
    }
    // Returns true if the square is marked as a solution pathway
    public boolean marked(){
        return this.marked;
    }
    // Marks the MazeSquare as marked as a solution pathway
    public void mark(){
        this.marked = true;
    }
    // Returns true if the MazeSquare has a top wall
    public boolean hasTopWall(){
        if (this.yCoord == 0){
            return true;
        } else {
            return false;
        }
    }
    // Returns true if the MazeSquare has a bottom wall
    public boolean hasBottomWall(){
        if (this.squareType == 'L' || this.squareType == '_'){
            return true;
        } else {
            return false;
        }
    }
    // Returns true if the MazeSquare has a left wall
    public boolean hasLeftWall(){
        if (this.squareType == 'L' || this.squareType == '|'){
            return true;
        } else {
            return false;
        }
    }
}