public class Word {
    private final String word;
    private final int length;
    private boolean isHori;
    private int startingRow, startingCol;

    public Word(String word){
        this.word = word;
        length = word.length();
    }

    public void setHorOrVert(boolean horizontalOrVertical){
        isHori = horizontalOrVertical;
    }
    public void setStartingCoordinate(int row, int col){
        startingRow = row;
        startingCol = col;
    }
    public String toString(){
        return word;
    }
    public int getLength() {
        return length;
    }
    public int getStartingRow(){
        return startingRow;
    }
    public int getStartingCol(){
        return startingCol;
    }
    public boolean getIsHori(){
        return isHori;
    }

    public char charAt(int i) { //added charAt method to remove the middleMan toString
        return word.charAt(i);
    }

}
