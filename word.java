import java.util.Scanner;
public class word {
    private String word;
    private int length;
    private boolean isHorizontal;
    private Point startingCoordinate;

    public word(String word){
        this.word = word;
        length = word.length();
    }
    public int getLength() {
        return length;
    }
    public void setHorOrVert(boolean isHorizontal){
        this.isHorizontal = isHorizontal;
    }
    public void setStartingCoordinate(int row, int col){
        startingCoordinate = new Point(row, col);
    }
    public String toString(){
        return word;
    }
}
