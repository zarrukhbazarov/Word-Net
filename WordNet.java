import java.util.*;
import java.io.*;

public class WordNet {
    private HashMap<Integer, String> idtonoun; // stores the id-noun pairs for id lookup
    private HashMap<String, Integer> nountoid; // stores noun-id pairs for noun lookup
    private Digraph network; // digraph acting to store word network

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        this.idtonoun = new HashMap<Integer, String>();
        this.nountoid = new HashMap<String, Integer>();

        try {
            File f = new File(synsets);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] elements = sc.nextLine().split(",");
                int id = Integer.parseInt(elements[0]);
                String[] versions = elements[1].split(" ");
                for (String s : versions) {// adding all the different synonyms for the words
                    idtonoun.put(id, elements[1]);
                    if (!nountoid.containsKey(s)) {
                        nountoid.put(s, id);
                    }
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.network = new Digraph(idtonoun.size());

        try {
            File f = new File(hypernyms);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] elements = sc.nextLine().split(",");
                int id = Integer.parseInt(elements[0]);
                int w;
                for (int i = 1; i < elements.length; i++) {
                    w = Integer.parseInt(elements[i]);
                    this.network.addEdge(id, w);
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // the set of all WordNet nouns
    public Iterable<String> nouns() {
        return this.nountoid.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return this.nountoid.containsKey(word);
    }

    // a synset (second field of synsets.txt) that is a shortest common ancestor
    // of noun1 and noun2 (defined below)
    public String sca(String noun1, String noun2) {
        ShortestCommonAncestor s = new ShortestCommonAncestor(this.network);
        int v = this.nountoid.get(noun1);
        int w = this.nountoid.get(noun2);
        int id = s.ancestor(v, w);

        return this.idtonoun.get(id);
    }

    // distance between noun1 and noun2 (defined below)
    public int distance(String noun1, String noun2) {
        if (!(isNoun(noun1) && isNoun(noun2))) {
            throw new IllegalArgumentException("Both nouns must be in the network");
        }

        int a = this.nountoid.get(noun1);
        int b = this.nountoid.get(noun2);
        return this.network.getDist(a, b);
    }

    public int distance(int id1, int id2) {
        int d = Integer.MAX_VALUE;

        if (this.network.getDist(id1, id2) > 0) {// first checking if id1 has a direct path to id2
            d = this.network.getDist(id1, id2);
        }

        for (int i = 0; i < network.V(); i++) {// then checking the shortest path going through other nodes
            int d1 = this.network.getDist(id1, i);
            int d2 = this.network.getDist(id2, i);
            if ((d1 > 0) && (d2 > 0)) {
                int test = d2 + d1;
                d = Math.min(d, test);
            }
        }
        return d;
    }

    // unit testing (required)
    public static void main(String[] args) {
        WordNet w = new WordNet("./Input/synsets.txt", "./Input/hypernyms.txt");
        // System.out.println(w.nouns());
        // System.out.println(w.distance("okonkwo", "country"));
        System.out.println(w.distance(0, 100));
        System.out.println(w.sca("'hood", "Abyssinian_banana"));

    }

}
