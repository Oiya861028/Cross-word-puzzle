import java.util.Scanner;
public class CrossWord {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        String[] words = new String[5];
        // Ask user to enter 5 words
        for(int i=0;i<words.length;i++){
            System.out.print("Enter a word:");
            words[i]= sc.next();
            while(words[i].length()<3){ //Check for length
                System.out.println("You need to enter a word that's 3 character or more:");
                words[i] = sc.next();
            }
            //Check for illegal words
            while(!validWord(words[i])){
                System.out.println("You enter a word with illegal letter. Try again:");
                words[i] = sc.next();
            }
        }

    }

    public static boolean validWord(String word){
        for(int i=0;i<word.length();i++){
            if(!Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }
}
