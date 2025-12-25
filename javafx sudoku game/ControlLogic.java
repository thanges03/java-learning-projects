package UserInterface.logic;

import java.io.IOException;

import Constants.GameState;
import Constants.Message;
import ProblemDomain.IStorage;
import ProblemDomain.SudokuGame;
import UserInterface.IUserInterfaceContract;
import computationLogic.GameLogic;

public class ControlLogic implements IUserInterfaceContract.EventListener{
    
    private IStorage storage;
    private IUserInterfaceContract.View view;
    public ControlLogic(IStorage storage,IUserInterfaceContract.View view){
        this.storage = storage;
        this.view = view;
    }
    
    public void onRestartClicked() {
    try {
        // 1. Clear old save
        storage.clearGameData();

        // 2. Create new game
        SudokuGame newGame = GameLogic.getNewGame();

        // 3. Update UI
        view.updateBoard(newGame);

        // 4. Save immediately
        storage.UpdateGame(newGame);

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void onSudokuInput(int x,int y,int input){
        try{
            SudokuGame gameData = storage.getGameData();
            int[][] newGridState = gameData.getCopyOfGridState();
            newGridState[x][y] = input;
            gameData = new SudokuGame(GameLogic.checkForCompletion(newGridState),newGridState);
            storage.UpdateGame(gameData);
            view.updateSquare(x, y, input);
            if(gameData.getGameState() == GameState.COMPLETE){
                view.showDialog(Message.GAME_COMPLETE);
            } 
        }
        catch(IOException e){
              e.printStackTrace();
              view.showError(Message.ERROR);
        }
    }
    public void onDialogClick(){
        try{
          storage.UpdateGame(GameLogic.getNewGame());
          view.updateBoard(storage.getGameData());
        }
        catch(IOException e){
           view.showError(Message.ERROR);
        }
    }

   
}
