import java.util.*;
import java.lang.*;

public class Wordsearch {
    private static boolean runRule = true;
    private static char[][] array;
    private static ArrayList<String> words;

    public Wordsearch(int[] size, ArrayList<String> aWords) {
        array = new char[size[0]][size[1]];
        words = aWords;

        java.util.Random random = new java.util.Random();

        for (String word : words) {
            char[] wordInChar = word.toCharArray();

            int rowChoice = random.nextInt(array.length);
            int colChoice = random.nextInt(array.length);

            int direction = random.nextInt(6);

            ArrayList<String> reWords = new ArrayList<String>();
            for (int i = 0; i < wordInChar.length; i++) {
                int[][] dir = {
                    {0, i},
                    {0, -i},
                    {i, 0},
                    {-i, 0},
                    {i, i},
                    {-i, -i}
                };
                if (runRule) {
                    rule(rowChoice+dir[direction][0], rowChoice+dir[direction][1], i, wordInChar, reWords, array, word);
                }
                else {
                    break;
                }
            }   
            for (String str : reWords) {
                words.add(str);
            }
        }

        populate();
    }
    
    private static char rndChar () {
        int rnd = (int) (Math.random() * 52); // or use Random or whatever
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);
    
    }

    private static void populate() {
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if((int) array[i][j] == 0) {
                    array[i][j] = rndChar();
                }
            }
        }
    }

    private static void rule(int rowChoice, int colChoice, int i, char[] wordInChar, ArrayList<String> reWords, char[][] array, String word) {
        ArrayList<int[]> past = new ArrayList<int[]>();
        try {    
            if (array[rowChoice][colChoice] != ' ' || array[rowChoice][colChoice] == wordInChar[i]) {
                array[rowChoice][colChoice] = wordInChar[i];
                int[] curr = {rowChoice, colChoice};
                past.add(curr);
            }
            else {
                for (int[] j : past) {
                    array[j[0]][j[1]] = ' ';
                }
                reWords.add(word);
                runRule = false;
        }
        } catch (Exception ArrayIndexOutOfBoundsException) {
            for (int[] j : past) {
                array[j[0]][j[1]] = ' ';
            }
            reWords.add(word);
            runRule = false;
        }

    }

    public static char[][] getWordsearch() {
        return array;
    }
}

