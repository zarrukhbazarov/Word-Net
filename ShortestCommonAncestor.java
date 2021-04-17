import java.util.*;

public class ShortestCommonAncestor {
    Digraph graph;

    // constructor takes a rooted DAG as argument
    public ShortestCommonAncestor(Digraph G) {
        this.graph = new Digraph(G);
        if (!isDAG()) {
            throw new IllegalArgumentException("This isn't a rooted DAG");
        }
    }

    private boolean isDAG() {
        DirectedCycle d = new DirectedCycle(this.graph);
        ArrayList<Integer> roots = new ArrayList<Integer>();
        for (int i = 0; i < graph.V(); i++) {// loops through the graph
            if (!graph.adj(i).iterator().hasNext()) {// checks if vertex points to anything
                roots.add(i);
            }
        }
        // if there is no root, more than one root or there is a cycle then the graph
        // isn't a rooted DAG
        if (roots.size() == 0 || roots.size() > 1 || d.hasCycle()) {
            return false;
        } else
            return true;
    }

    // length of shortest ancestral path between v and w
    public int length(int v, int w) {// this can just use the distance method we wrote in Digraph
        return graph.getDist(v, w);
    }

    // a shortest common ancestor of vertices v and w
    public int ancestor(int v, int w) {// this will use the distance algorithm from wordnet and return the index which
                                       // gave us the shortest path
        int d = Integer.MAX_VALUE;
        int ca = 0;

        for (int i = 0; i < graph.V(); i++) {// then checking the shortest path going through other nodes
            int d1 = this.graph.getDist(v, i);
            int d2 = this.graph.getDist(w, i);
            if ((d1 > 0) && (d2 > 0)) {
                int test = d2 + d1;
                if (test < d) {
                    ca = i;
                }
                d = Math.min(d, test);
            }
        }
        if (d == Integer.MAX_VALUE) {
            ca = -1;
        }
        return ca;
    }

    // length of shortest ancestral path of vertex subsets A and B
    public int lengthSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        // loop through all the values in each subset and return the largest shortest
        // path
        int cp = 0;
        for (int i : subsetA) {
            for (int j : subsetB) {
                int sp = length(i, j);
                cp = Math.max(cp, sp);
            }
        }
        return cp;
    }

    // a shortest common ancestor of vertex subsets A and B
    public int ancestorSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        // return the vertex that gives the largest shortest path
        int cp = 0;
        int cv = 0;
        for (int i : subsetA) {
            for (int j : subsetB) {
                int sp = length(i, j);
                if (sp > cp) {
                    cv = ancestor(i, j);
                }
                cp = Math.max(cp, sp);
            }
        }
        return cv;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
