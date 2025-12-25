package computationLogic;

import static ProblemDomain.SudokuGame.GRID_BOUNDARY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Constants.GameState;
import Constants.Rows;
import ProblemDomain.SudokuGame;

public class GameLogic {
public static SudokuGame getNewGame(){
    return new SudokuGame(GameState.NEW, GameGenerator.getNewGameGrid());
}

public static GameState checkForCompletion(int[][] grid){
    if(sudokuIsInvalid(grid)){
        return GameState.ACTIVE;
    }
    if(titlesAreNotFilled(grid)) return GameState.ACTIVE;
    return GameState.COMPLETE;
}

public static boolean sudokuIsInvalid(int[][] grid) {
   if(rowsAreInvalid(grid)) return true;
   if(columnsAreInvalid(grid)) return true;
   if(squaresAreInvalid(grid)) return true;
   return false;
}
private static boolean squaresAreInvalid(int[][] grid) {
    if(rowOfSquareIsInvalid(Rows.TOP,grid)) return true;
    if(rowOfSquareIsInvalid(Rows.MIDDLE,grid)) return true;
    if(rowOfSquareIsInvalid(Rows.BOTTOM,grid)) return true;
    return false;
}

private static boolean rowOfSquareIsInvalid(Rows value, int[][] grid) {
   switch ((value)) {
    case TOP:
        if(SquareIsValid(0,0,grid)) return true;
        if(SquareIsValid(0,3,grid)) return true;
        if(SquareIsValid(0,6,grid)) return true;
        return false;
    case MIDDLE:
        if(SquareIsValid(3,0,grid)) return true;
        if(SquareIsValid(3,3,grid)) return true;
        if(SquareIsValid(3,6,grid)) return true;
        return false;
    case BOTTOM:
        if(SquareIsValid(6,0,grid)) return true;
        if(SquareIsValid(6,3,grid)) return true;
        if(SquareIsValid(6,6,grid)) return true;
        return false;   
    default:
        return false;
   }
   
}

public static boolean SquareIsValid(int yIndex, int xIndex, int[][] grid) {
   int yIndexEnd = yIndex + 3;
   int xIndexEnd = xIndex + 3;
   List<Integer> square = new ArrayList<>();
   while (yIndex<yIndexEnd) {
    while(xIndex<xIndexEnd){
        square.add(grid[xIndex][yIndex]);
        xIndex++;
    }
    xIndex -= 3;
    yIndex++;
    
   }
   if(collectionHashRepeat(square)) return true;
   return false;
}

private static boolean collectionHashRepeat(List<Integer> collection) {
    for(int index = 1;index <= GRID_BOUNDARY;index++){
        if(Collections.frequency(collection, index) > 1) return true;
    }
    return false;
}

private static boolean columnsAreInvalid(int[][] grid) {
    for(int xIndex = 0;xIndex<GRID_BOUNDARY;xIndex++){
        List<Integer> row = new ArrayList<>();
        for(int yIndex =0;yIndex<GRID_BOUNDARY;yIndex++){
            row.add(grid[xIndex][yIndex]);
        }
        if(collectionHashRepeat(row)) return true;
    }
    return false;
}

private static boolean rowsAreInvalid(int[][] grid) {
     for(int yIndex = 0;yIndex<GRID_BOUNDARY;yIndex++){
        List<Integer> row = new ArrayList<>();
        for(int xIndex =0;xIndex<GRID_BOUNDARY;xIndex++){
            row.add(grid[xIndex][yIndex]);
        }
        if(collectionHashRepeat(row)) return true;
    }
    return false;
}



private static boolean titlesAreNotFilled(int[][] grid) {
    for(int xIndex = 0;xIndex <GRID_BOUNDARY;xIndex++){
        for(int yIndex = 0;yIndex<GRID_BOUNDARY;yIndex++){
            if(grid[xIndex][yIndex] == 0) return true;
        }
    }
    return false;
}
}
