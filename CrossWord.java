import java.util.*;
public class CrossWord {
    public static Scanner sc= new Scanner(System.in);
    public static Random random = new Random();
    public static Board currentBoard;
    public static void main(String[] args){
        System.out.println("Welcome to the crossWord puzzle generator");
        //get length
        System.out.println("Please enter the length of your grid:");
        int length = sc.nextInt();
        //get width
        System.out.println("Please enter the width of your grid:");
        int width = sc.nextInt();
        currentBoard = new Board(length, width);
        //get the number of words that's going to be entered
        System.out.println("How many word would you like to place in your grid? ");
        int numOfWords = sc.nextInt();
        ArrayList<Word> words = getWords(numOfWords);
        //Add the first word
        Word firstWord = words.get(random.nextInt(words.toArray().length));
        firstWord.setStartingCoordinate(currentBoard.length/2, currentBoard.width/2- words.getFirst().getLength()/2);
        firstWord.setHorOrVert(true);
        currentBoard.addWord(words.getFirst());
        words.remove(firstWord);

        words = findIntersection(words);
        while(!words.isEmpty()){ //Prompt the user until all words are placed
            int previousSize = words.size();
            words = findIntersection(words);
            while(words.size()<previousSize){ //Keep finding intersections until no longer possible
                previousSize = words.size();
                words = findIntersection(words);
            }
            currentBoard.printBoard();
            if(words.isEmpty()) break;
            System.out.print("The following words are not able to fit on the board: ");
            for(Word word: words){
                System.out.print(word+" ");
            }
            System.out.println();
            int replaceWordIndex = -1;
            //this do-while was done with some help from chatGPT, I think although try-except also work, it avoided a lot of hassles
            do {
                System.out.println("Enter the index of the word you want to replace(Starting at 0):");
                if (sc.hasNextInt()) {
                    replaceWordIndex = sc.nextInt();
                    if (replaceWordIndex < 0 || replaceWordIndex >= words.size()) {
                        System.out.println("Invalid index, try again:");
                    }
                } else {
                    System.out.println("Invalid input, try again:");
                    sc.nextLine();
                }
            } while (replaceWordIndex < 0 || replaceWordIndex >= words.size());

            Word newWord = new Word(getOneWord());
            words.set(replaceWordIndex, newWord);
        }
        currentBoard.printBoard();
    }
    public static ArrayList<Word> getWords(int numOfWords){//get userInput for the amount of words that will be placed
        ArrayList<Word> words = new ArrayList<Word>();
        for(int i=0;i<numOfWords; i++){
            words.add(new Word(getOneWord()));
        }
        return sortWord(words);
    }
    public static String getOneWord(){
        System.out.print("Enter a Word:");
        String userInput = sc.nextLine();
        while(userInput.length()<3 || userInput.length()>currentBoard.length){ //Check for length
            System.out.println("You need to enter a Word that's 3 character or more:");
            userInput = sc.nextLine();
        }
        //Check for illegal words
        while(!validWord(userInput)){
            System.out.println("You enter a Word with illegal letter. Try again:");
            userInput = sc.nextLine();
        }
        return userInput.toUpperCase();
    }
    public static boolean validWord(String word){ //check if the word contians non-alphabetic letters
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
    public static ArrayList<Word> findIntersection(ArrayList<Word> applicantWords){ //return words not placed
        ArrayList<Word> placedWords;
        ArrayList<Word> unplacedWord = new ArrayList<>(applicantWords);
        for(Word term: applicantWords) { //Taking one word at a time and trying to fit it inside the puzzle
            placedWords = currentBoard.getPlacedWords();//update the wordBank every loop
            Collections.shuffle(placedWords);
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
                                            unplacedWord.remove(term);
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
                                            unplacedWord.remove(term);
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

        return unplacedWord;
    }

}
