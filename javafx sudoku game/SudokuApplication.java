import javafx.application.Application;
import java.io.IOException;
import buildLogic.SudokuBuildLogic;
import UserInterface.IUserInterfaceContract.View;
import UserInterface.UserInterfaceImpl;
import javafx.stage.Stage;

public class SudokuApplication extends Application{
    
    private View uiImpl;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        uiImpl = new UserInterfaceImpl(primaryStage);
       try{
        SudokuBuildLogic.build(uiImpl);
       }
       catch(IOException e){
            e.printStackTrace();
            throw e;     
       }
    }
}
