package buildLogic;

import java.io.IOException;

import ProblemDomain.IStorage;
import ProblemDomain.SudokuGame;
import UserInterface.IUserInterfaceContract;
import UserInterface.logic.ControlLogic;
import computationLogic.GameLogic;
import persistence.LocalStorageImpl;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException{
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();
        try{
            initialState = storage.getGameData();
            if (initialState == null) {   // failsafe
            initialState = GameLogic.getNewGame();
            storage.UpdateGame(initialState);
        }
        }
        catch(IOException e){
            initialState = GameLogic.getNewGame();
            storage.UpdateGame((initialState));
        }
        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
        
    }
}
