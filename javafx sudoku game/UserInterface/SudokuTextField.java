package UserInterface;

import javafx.scene.control.TextField;

public class SudokuTextField extends TextField{
 private final int x,y;
 public SudokuTextField(int x,int y){
    this.x = x;
    this.y = y;
 }    

 public int getx(){
    return x;
 }

 public int gety(){
 return y;
}
  @Override
  public void replaceText(int i,int il,String s){
    if(!s.matches("[0-9]") || s.isEmpty()){
        super.replaceText(i,il,s);
    }
  }

  @Override
  public void replaceSelection(String s){
    if(!s.matches("[0-9]") || s.isEmpty()){
        super.replaceSelection(s);
    }
  }
}
