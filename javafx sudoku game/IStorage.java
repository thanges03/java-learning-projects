package ProblemDomain;

import java.io.IOException;

public interface IStorage {
    void UpdateGame(SudokuGame game) throws IOException;
    SudokuGame getGameData() throws IOException;
    public void clearGameData();
}

