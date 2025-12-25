package persistence;

import ProblemDomain.IStorage;
import ProblemDomain.SudokuGame;

import java.io.*;

public class LocalStorageImpl implements IStorage {

    private static final File GAME_DATA =
            new File(System.getProperty("user.home"), "gamedata.txt");

    @Override
    public void UpdateGame(SudokuGame game) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(GAME_DATA);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(game);

        } catch (IOException e) {
            throw new IOException("Unable to save game data!", e);
        }
    }

    public void clearGameData() {
    if (GAME_DATA.exists()) {
        GAME_DATA.delete();
    }
}
    @Override
    public SudokuGame getGameData() throws IOException {

        if (!GAME_DATA.exists()) {
            // File does NOT exist → force SudokuBuildLogic to create new game
            throw new IOException("Save file does not exist!");
        }

        try (FileInputStream fis = new FileInputStream(GAME_DATA);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            SudokuGame game = (SudokuGame) ois.readObject();

            if (game == null) {
                throw new IOException("Saved game is empty!");
            }

            return game;

        } catch (ClassNotFoundException | StreamCorruptedException e) {
            throw new IOException("Saved game is corrupted!", e);
        }
    }
}
