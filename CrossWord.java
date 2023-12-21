import java.util.*;
public class CrossWord {
    public static Scanner sc= new Scanner(System.in);
    public static Random random = new Random();
    public static void main(String[] args){
        word[] words = getWords(5);
        Board grid = new Board(15, 15);

        for(int n = 5;n>0;n--){
            word[] placedWords = grid.getPlacedWords();
            if(placedWords.length==0){
              words[n].setHorOrVert(true);
              words[n].setStartingCoordinate(grid.length/2,grid.width/2-words[n].getLength()/2);
              System.out.println(grid.addWord(words[n]));
            }
            for(word w: placedWords){
                for(int i = 0; i< words[n].getLength(); i++){
                    for(int j=0;j<w.getLength();j++){
                        if(words[n].toString().charAt(i)==w.toString().charAt(j)){

                        }
                    }
                }
            }
        }
        grid.printBoard();
    }
    public static word[] getWords(int numOfWords){
        word[] words = new word[numOfWords];
        for(int i=0;i<numOfWords; i++){
            System.out.print("Enter a word:");
            String userInput = sc.nextLine();
            while(userInput.length()<3){ //Check for length
                System.out.println("You need to enter a word that's 3 character or more:");
                userInput = sc.nextLine();
            }
            //Check for illegal words
            while(!validWord(userInput)){
                System.out.println("You enter a word with illegal letter. Try again:");
                userInput = sc.nextLine();
            }
            words[i] = new word(userInput);
        }

        return sortWord(words);
    }
    public static boolean validWord(String word){
        for(int i=0;i<word.length();i++){
            if(!Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }

    public static word[] sortWord(word[] words){
        for(int i=0;i<words.length-1;i++){
            for(int j=i+1;j<words.length;j++){
                if(words[j].toString().length()>words[i].toString().length()){
                    word temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }
        return words;

    }

}
