import java.util.Scanner;
public class CrossWord {
    public static Scanner sc= new Scanner(System.in);
    public static void main(String[] args){
        char[][] words = getWords(5);
        for(char[] word: words){
            for(char i: word){
                System.out.print(i);
            }
            System.out.println();
        }
        char[][] crossWord = new char[100][100];
    }
    public static char[][] getWords(int numOfWords){
        char[][] words = new char[numOfWords][];
        for(int i=0;i<words.length;i++){
            System.out.print("Enter a word:");
            String userInput = sc.nextLine();
            while(userInput.length()<3){ //Check for length
                System.out.println("You need to enter a word that's 3 character or more:");
                userInput = sc.nextLine();
            }
            //Check for illegal words
            while(!validWord(userInput)){
                System.out.println("You enter a word with illegal letter. Try again:");
                userInput = sc.next();
            }
            words[i] = new char[userInput.length()];
            for(int j=0;j<userInput.length();j++){
                words[i][j] = userInput.charAt(j);
            }
        }
        return words;
    }
    public static boolean validWord(String word){
        for(int i=0;i<word.length();i++){
            if(!Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }
    public static int longestWord(String[] word){
        int longest = 0;
        int longestIndex = -1;
        for(int i = 0; i< word.length; i++){
            if(longest < word[i].length()) {
                longest = word[i].length();
                longestIndex = i;
            }
        }
        return word[longestIndex].length();
    }

}
