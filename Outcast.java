import java.util.*;
import java.io.*;

public class Outcast {
    private WordNet wordNet;
    //constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;

    }

    //given an array of WordNet nouns, returns an outcast
    public String outcast(String[] nouns) {
        int maxValue = 0;
        String outcastNoun = "";

        for (String sampleNounA : nouns) {  //sampleNounA is checking noun
            int distance = 0;
            for (String sampleNounB : nouns) {   //sampleNounB is other noun that is being checked
                if (sampleNounA != sampleNounB) {    //since if they are equal, distance is zero
                    distance += wordNet.distance(sampleNounA, sampleNounB);
                }
            }
            if (distance > maxValue) {
                maxValue = distance;
                outcastNoun = sampleNounA;
            }
        }
        return outcastNoun;
    }

    //test client, given by professor Wu
    public static void main(String[] args) throws FileNotFoundException {        //NOT checked yet, need to check!!!
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);

        for (int t = 0; t < 3; t++) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the file name: ");
            String fileName = input.nextLine();
            System.out.print("Enter the file size: ");
            int fileSize = input.nextInt();

            File inFile = new File(fileName);
            Scanner input1 = new Scanner(inFile);
            String[] nouns = new String[fileSize];

            int i=0;
            while(input1.hasNextLine()){
                nouns[i] = input1.nextLine();
                i++;
            }

            System.out.println(fileName + ": " + outcast.outcast(nouns));
        }

    }
}
