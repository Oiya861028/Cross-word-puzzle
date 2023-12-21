import java.util.ArrayList;

public class Board {
    public char[][] board;
    public final int length, width;
    public ArrayList<String> horizontalWords = new ArrayList<String>();
    public ArrayList<String> verticalWords = new ArrayList<String>();
    public word[] placedWords = new word[5];
    public Board(int length, int width){
        this.length = length;
        this.width = width;
        board = new char[length][width];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=' ';
            }
        }
    }
    public boolean addWord(word w){
        if(w.getIsHori()){
            if(w.getLength()+w.getStartingCol()>width) return false; //check if word will go off the grid
            for(int i=w.getStartingCol();i<w.getStartingCol()+w.getLength();i++){ //check if word collide with another word on the board
                if(board[w.getStartingRow()][i]!=' ' && board[w.getStartingRow()][i]!=w.toString().charAt(i)) return false;
            }
            int k=0;
            for(int j = w.getStartingCol(); j <w.getLength()+w.getStartingCol(); j++){
                board[w.getStartingRow()][j] = w.toString().charAt(k);
                k++;
            }
        }
        return true;
    }
    public void printBoard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public word[] getPlacedWords(){
        return placedWords;
    }
}
