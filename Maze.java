/*
Maze
by Yasmeen Awad

This module creates a maze based on information from a text file and prints the maze to
the screen.
*/

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Stack;
import java.lang.IndexOutOfBoundsException;
import java.util.EmptyStackException;

public class Maze {
    private ArrayList<ArrayList<MazeSquare>> rowList = new ArrayList<ArrayList<MazeSquare>>();
    
    // Creates the object Maze
    public Maze(){ 
        this.rowList = rowList;
    }
    // Returns the ArrayList of ArrayLists of MazeSquares
    public ArrayList<ArrayList<MazeSquare>> getSquares(){
        return this.rowList;
    }
    // Adds an ArrayList of MazeSquare objects to Maze
    public void addRowOfSquares(ArrayList<MazeSquare> squares){
        this.rowList.add(squares);
    }
    // Returns the MazeSquare at the specified location (x, y)
    public MazeSquare getSquare(int x, int y){
        return this.rowList.get(y).get(x);
    }
    // Takes in a char ('S' or 'F'), indicating whether you seek the start ('S') or finish ('F')
    // Returns the MazeSquare at the indicated location
    public MazeSquare getStartOrFinish(char startOrFinish){
        for (int y = 0; y < this.rowList.size(); y++){
            for (int x = 0; x < this.rowList.get(y).size(); x++){
                if (this.rowList.get(y).get(x).getSOrF() == startOrFinish){
                    return this.rowList.get(y).get(x);
                }
            }
        }
        // The function will never get this far, but Java would not compile this file unless there
        // was a definite return statement.
        return this.rowList.get(0).get(0);
    }
    // Returns true if the MazeSquare has a right wall
    public boolean hasRightWall(MazeSquare square){
        int x = square.getX();
        int y = square.getY();
        // Condition for rightmost edge of maze
        if (x == this.rowList.get(0).size() - 1){
            return true;
        }
        // Condition for adjacent square with left wall
        else if (this.getSquare(x + 1, y).hasLeftWall()){
            return true;
        }
        // Otherwise it should not have a right wall
        else {
            return false;
        }
    }
    // Returns true if the MazeSquare has a left wall
    public boolean hasTopWall(MazeSquare square){
        int x = square.getX();
        int y = square.getY();
        // Condition for top edge of maze
        if (y == 0){
            return true;
        }
        // Condition for adjacent square with bottom wall
        else if (this.getSquare(x, y - 1).hasBottomWall()){
            return true;
        }
        // Otherwise it should not have a top wall
        else {
            return false;
        }
    }
    // Reads the given file and creates MazeSquares with given info
    public void load(String fileName){
        // Create File object
        File file = new File(fileName);
        // Create scanner object attatched to file
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e){
            System.err.println(e);
            System.exit(1);
        }
        // Retrieve dimensions from first line
        String line1 = scanner.nextLine();
        String[] dimensions = line1.split(" ");
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);
        
        // Retrieve coordinates of start
        String line2 = scanner.nextLine();
        String[] startCoords = line2.split(" ");
        int startX = Integer.parseInt(startCoords[0]);
        int startY = Integer.parseInt(startCoords[1]);
        
        // Retrieve coordinates of finish
        String line3 = scanner.nextLine();
        String[] finishCoords = line3.split(" ");
        int finishX = Integer.parseInt(finishCoords[0]);
        int finishY = Integer.parseInt(finishCoords[1]);
        
        // Loop through the next lines and store the information as MazeSquares
        for (int i = 0; i < height; i++){
            String line = scanner.nextLine();
            ArrayList<MazeSquare> squareList = new ArrayList<MazeSquare>();
            for (int j = 0; j < width; j++){
                // Check if the square is the start or finish
                if (startX == j && startY == i){
                    MazeSquare square = new MazeSquare(j, i, line.charAt(j), true, false);
                    squareList.add(square);
                } else if (finishX == j && finishY == i){
                    MazeSquare square = new MazeSquare(j, i, line.charAt(j), false, true);
                    squareList.add(square);
                } else {
                    MazeSquare square = new MazeSquare(j, i, line.charAt(j), false, false);
                    squareList.add(square);
                }  
            }
            // Add these MazeSquares to the Maze 
            addRowOfSquares(squareList);
        }
    }
    // Returns first line of the inputted square
    public static String getLine1(MazeSquare square){
            return "+-----";
    }
    // Returns second, third, or fourth line of the inputted square (they are the same)
    public static String getLine234(MazeSquare square){
        if (square.hasLeftWall()){
            return "|     ";
        } else {
            return "      ";
        }
    }
    // Returns fifth line of the inputted square
    public static String getLine5(MazeSquare square){
        if (square.hasBottomWall()){
            return "+-----";
        } else {
            return "+     ";
        }
    }
    // Returns third line of square when the box is the start
    public String getStartLine(MazeSquare square){
        if (square.hasLeftWall()){
            return "|  S  ";
        } else {
            return "   S  ";
        }
    }
    // Returns third line of square when the box is the finish
    public String getFinishLine(MazeSquare square){
        if (square.hasLeftWall()){
            return "|  F  ";
        } else {
            return "   F  ";
        }
    }
    // Returns third line of square when it is marked
    public String getMarkedLine(MazeSquare square){
        if (square.hasLeftWall()){
            return "|  *  ";
        } else {
            return "   *  ";
        }
    }
    // Prints the maze
    public void print(){
        ArrayList<ArrayList<MazeSquare>> listOfLists = getSquares();
        // Loop through the list of lists of squares, printing the squares as you go
        for (int x = 0; x < listOfLists.size(); x++){
            // First line of square only should be done once with the first row of squares
            if (x == 0){
                for (int i = 0; i < listOfLists.get(x).size(); i++){
                    MazeSquare square = listOfLists.get(x).get(i);
                    System.out.print(getLine1(square));
                } System.out.println("+");
            }
            // Second line of square
            for (int j = 0; j < listOfLists.get(x).size(); j++){
                MazeSquare square = listOfLists.get(x).get(j);
                System.out.print(getLine234(square));
            } System.out.println("|");
            
            // Third line of square
            for (int k = 0; k < listOfLists.get(x).size(); k++){
                MazeSquare square = listOfLists.get(x).get(k);
                if (square.getSOrF() == 'S'){
                    System.out.print(getStartLine(square));
                } else if (square.getSOrF() == 'F'){
                    System.out.print(getFinishLine(square));
                } else if (square.marked()){
                    System.out.print(getMarkedLine(square));
                } else {
                    System.out.print(getLine234(square));
                }
            } System.out.println("|");
            
            // Fourth line of square
            for (int l = 0; l < listOfLists.get(x).size(); l++){
                MazeSquare square = listOfLists.get(x).get(l);
                System.out.print(getLine234(square));
            } System.out.println("|");
            
            // Fifth line of square
            for (int m = 0; m < listOfLists.get(x).size(); m++){
                MazeSquare square = listOfLists.get(x).get(m);
                System.out.print(getLine5(square));
            } System.out.println("+");
        }
    }
    // Solves the maze and returns a stack of MazeSquares containing the solution pathway
    public Stack<MazeSquare> getSolution(){
        Stack<MazeSquare> stack = new Stack<MazeSquare>();
        MazeSquare start = this.getStartOrFinish('S');
        stack.push(start);
        start.visit();
        MazeSquare currentSquare = stack.peek();
        MazeSquare finish = this.getStartOrFinish('F');
        // Loop through MazeSquares until the end is reached
        while (currentSquare != finish){
            boolean done = false;
            int x = currentSquare.getX();
            int y = currentSquare.getY();
            MazeSquare adjacentSquare;
            // Check to see if next square to the right is accessible
            if (!this.hasRightWall(currentSquare)){
                adjacentSquare = this.getSquare(x + 1, y);
                if(!adjacentSquare.visited()){
                    stack.push(adjacentSquare);
                    adjacentSquare.visit();
                    done = true;
                }
            } 
            // Check to see if square below is accessible
            if (!currentSquare.hasBottomWall() && !done){
                adjacentSquare = this.getSquare(x, y + 1);
                if(!adjacentSquare.visited()){
                    stack.push(adjacentSquare);
                    adjacentSquare.visit();
                    done = true;
                }
            }
            // Check to see if square to the left is accessible
            if (!currentSquare.hasLeftWall() && !done){
                adjacentSquare = this.getSquare(x - 1, y);
                if(!adjacentSquare.visited()){
                    stack.push(this.getSquare(x - 1, y));
                    adjacentSquare.visit();
                    done = true;
                }
            }
            // Check to see if square above is accessible
            if (!this.hasTopWall(currentSquare) && !done){
                adjacentSquare = this.getSquare(x, y - 1);
                if (!adjacentSquare.visited()){
                    stack.push(this.getSquare(x, y - 1));
                    adjacentSquare.visit();
                    done = true;
                }
            }
            // Otherwise backtrack in maze
            if (!done){
                stack.pop();
            }
            try {
                currentSquare = stack.peek();
            } catch (EmptyStackException e){
                return stack;
            }
        }
        return stack;
    } 
    // Prints maze solution
    public void printSolution(){
        Stack<MazeSquare> solution = this.getSolution();
        for (MazeSquare square : solution){
            square.mark();
        }
        this.print();
    }
    // Main function loads info from inputted file to create and print Maze
    public static void main(String[] args){
        // Make sure user types in a text file
        if (args.length == 0){
            System.err.println("Usage: java Maze mazeFile");
            System.exit(1);
        } 
        // Create maze
        Maze maze = new Maze();
        maze.load(args[0]);
        
        try{
            String solution = args[1];
            // Print maze solution
            maze.printSolution();
        } catch (ArrayIndexOutOfBoundsException e){
            // Print maze
            maze.print();
        }
    }
}