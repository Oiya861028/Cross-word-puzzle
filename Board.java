import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private char[][] board;
    protected final int length;
    protected final int width;
    protected int score = 0; //to be continued(if I come back to this program)
    private ArrayList<Word> placedWords = new ArrayList<>();
    public Board(int length, int width){
        this.length = length;
        this.width = width;
        board = new char[length][width];
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }
    }
    public boolean addWord(Word w){
        if(w.getIsHori()){
            //check if the word we're trying to add overlapped another word
            int index = 0;
            for(int i=w.getStartingCol();i<w.getStartingCol()+w.getLength();i++){
                if(board[w.getStartingRow()][i]!=' ' && board[w.getStartingRow()][i]!=w.toString().charAt(index)) return false;
                index++;
            }
            //Add the word
            int k=0;
            for(int j = w.getStartingCol(); j <w.getLength()+w.getStartingCol(); j++){
                board[w.getStartingRow()][j] = w.toString().charAt(k);
                k++;
            }
            placedWords.addFirst(w); //so it always start from the most recently placed Word

        } else{ //vertical word
            int index = 0;
            for(int i=w.getStartingRow();i<w.getStartingRow()+w.getLength();i++){ //check if Word collide with another Word on the board
                if(board[i][w.getStartingCol()]!=' ' && board[i][w.getStartingCol()]!=w.toString().charAt(index)) return false;
                index++;
            }
            //Adding Word to the grid after all the tests
            int k=0;
            for(int j = w.getStartingRow(); j <w.getLength()+w.getStartingRow(); j++){
                board[j][w.getStartingCol()] = w.toString().charAt(k);
                k++;
            }
            placedWords.addFirst(w);
        }
        return true;
    }
    public void printBoard(){
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }
    public ArrayList<Word> getPlacedWords(){
        return placedWords;
    }
}
