public class Word {
    private String word;
    private String wordWithoutTakenLetters;
    private int length;
    private boolean isHori;
    private int startingRow, startingCol;

    public Word(String word){
        this.word = word;
        length = word.length();
        wordWithoutTakenLetters = word;
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
    public void takeLettersAway(int index){
        wordWithoutTakenLetters = wordWithoutTakenLetters.substring(0,index)+wordWithoutTakenLetters.substring(index+1);
    }

    public char charAt(int i) {
        return word.charAt(i);
    }

    public char[] toCharArray() {
        char[] charArray = new char[word.length()];
        for(int i=0;i<word.length();i++){
            charArray[i] = word.charAt(i);
        }
        return charArray;
    }
}
