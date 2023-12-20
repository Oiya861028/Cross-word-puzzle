import java.util.Scanner;
public class CrossWord {
    public static Scanner sc= new Scanner(System.in);
    public static void main(String[] args){
        word[] CrossWords = getWords(5);

        for(word w: CrossWords){
            System.out.println(w);
        }
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
