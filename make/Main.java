import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>();
        words.add("test");

        int[] size = {6, 6};

        Wordsearch search = new Wordsearch(size, words);
        char[][] array = search.getWordsearch();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println("");
        }
    }
}
