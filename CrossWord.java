import java.util.*;
public class CrossWord {
    public static Scanner sc= new Scanner(System.in);
    public static Random random = new Random();
    public static void main(String[] args){
        ArrayList<Word> words = getWords(5);
        Board grid = new Board(15, 15);
        //Add the first word
        words.getFirst().setStartingCoordinate(grid.length/2, grid.width/2- words.getFirst().getLength()/2);
        words.getFirst().setHorOrVert(true);
        grid.addWord(words.getFirst());

        findIntersection(grid, words);
        grid.printBoard();
    }
    public static ArrayList<Word> getWords(int numOfWords){
        ArrayList<Word> words = new ArrayList<Word>();
        for(int i=0;i<numOfWords; i++){
            System.out.print("Enter a Word:");
            String userInput = sc.nextLine();
            while(userInput.length()<3){ //Check for length
                System.out.println("You need to enter a Word that's 3 character or more:");
                userInput = sc.nextLine();
            }
            //Check for illegal words
            while(!validWord(userInput)){
                System.out.println("You enter a Word with illegal letter. Try again:");
                userInput = sc.nextLine();
            }
            words.add(new Word(userInput));
        }

        return sortWord(words);
    }
    public static boolean validWord(String word){
        for(int i=0;i<word.length();i++){
            if(!Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }

    public static ArrayList<Word> sortWord(ArrayList<Word> words){
        // loop through the ArrayList and compare adjacent elements
        for (int i = 0; i < words.size(); i++) {
            for (int j = words.size() - 1; j > i; j--) {
                // if the element at i is greater than the element at j, swap them
                if (words.get(i).getLength() < words.get(j).getLength()){
                    Word tmp = words.get(i);
                    words.set(i, words.get(j));
                    words.set(j, tmp);
                }
            }
        }
        return words;

    }
    public static ArrayList<Word> findIntersection(Board currentBoard, ArrayList<Word> applicantWords){ //return words not placed
        ArrayList<Word> placedWords;
        for(Word term: applicantWords) { //Taking one word at a time and trying to fit it inside the puzzle
            placedWords = currentBoard.getPlacedWords(); //update the wordBank every loop
            nextTerm:
            for(Word placedWord: placedWords){
                if (placedWord.getIsHori()) { //if the word is horizontal, we have to increment the col to check
                    for(int placedWordIndex =0;placedWordIndex<placedWord.getLength();placedWordIndex++){ //loop through every char in the placedWord
                        char charOnBoard = placedWord.toString().charAt(placedWordIndex);
                        if(term.toString().contains(String.valueOf(charOnBoard))){ //check if the term contains this char
                            boolean placed = false;
                            for(int i=0;i<term.getLength();i++){ // find all the matches in the word we want to place and check each of them
                                if(term.charAt(i)==charOnBoard){
                                    if(placedWord.getStartingRow()-i>=0 && placedWord.getStartingRow()+term.getLength()-i<currentBoard.length){//check for out of bound
                                        term.setHorOrVert(false);
                                        term.setStartingCoordinate(placedWord.getStartingRow()-i, placedWord.getStartingCol()+placedWordIndex);
                                        placed = currentBoard.addWord(term); //will check for overlapping and then place the word if there are no overlap
                                        if(placed){
                                            applicantWords.remove(term);
                                            break nextTerm;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                else{ //The word we are looking at on the board is vertical
                    for(int placedWordIndex =0;placedWordIndex<placedWord.getLength();placedWordIndex++){ //loop through every char in the placedWord
                        char charOnBoard = placedWord.toString().charAt(placedWordIndex);
                        if(term.toString().contains(String.valueOf(charOnBoard))){ //check if the term contains this char
                            boolean placed = false;
                            for(int i=0;i<term.getLength();i++){ // find all the matches in the word we want to place and check each of them
                                if(term.charAt(i)==charOnBoard){
                                    if(placedWord.getStartingCol()-i>=0 && placedWord.getStartingCol()+term.getLength()-i<currentBoard.width){//check for out of bound
                                        term.setHorOrVert(true);
                                        term.setStartingCoordinate(placedWord.getStartingRow()+placedWordIndex, placedWord.getStartingCol()-i);
                                        placed = currentBoard.addWord(term); //will check for overlapping and then place the word if there are no overlap
                                        if(placed){
                                            applicantWords.remove(term);
                                            break nextTerm;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return applicantWords;
    }

}
