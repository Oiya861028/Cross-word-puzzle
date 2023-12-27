import java.util.ArrayList;

public class Board {
    private char[][] board;
    protected final int length;
    protected final int width;
    private ArrayList<word> horizontalWords = new ArrayList<word>();
    private ArrayList<word> verticalWords = new ArrayList<word>();
    private ArrayList<word> placedWords = new ArrayList<word>();
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
            horizontalWords.add(w);
            placedWords.add(w);

        } else{
            if(w.getLength()+w.getStartingRow()>length) return false; //check if word will go off the grid
            for(int i=w.getStartingRow();i<w.getStartingRow()+w.getLength();i++){ //check if word collide with another word on the board
                if(board[i][w.getStartingCol()]!=' ' && board[i][w.getStartingCol()]!=w.toString().charAt(i)) return false;
            }
            //Adding word to the grid after all the tests
            int k=0;
            for(int j = w.getStartingRow(); j <w.getLength()+w.getStartingRow(); j++){
                board[j][w.getStartingRow()] = w.toString().charAt(k);
                k++;
            }
            horizontalWords.add(w);
            placedWords.add(w);
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
    public ArrayList<word> getPlacedWords(){
        return placedWords;
    }
}
