/* *****************************************************************************
 *  Name:   Robert Sabum 
 *  NetID:  1198640
 *
 *  Partner Name:   Zarrukh Bazarov
 *  Partner NetID:
 *
 *  Hours to complete assignment (optional):
 *
 **************************************************************************** */

Programming Assignment 4: WordNet


/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 **************************************************************************** */
two hashmaps with, one that stores ids to nouns and one that stores nouns to ids.
This was done to take advantage of the O(1) average lookup time of a hashtable improving
the speed of the overall program. the nouns to id hashmap contains a set because some nouns
are found in different ids and some ids have multiple nouns with the same definition


/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 **************************************************************************** */
The Digraph because all that is needed for the hypernyms is a directed graph that connects
hyponyms to their hypernyms.


/* *****************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithm? Express your answer as a function of the
 *  number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) 
 **************************************************************************** */

Description: 
Loops through the graph and checks if the given vertex points to any others
vertices i.e. does it have any hyponyms. If it does not it is added to an array which stores these
vertices. Then the method checks the size of the array storing the roots. if it is greater than one
or equal to zero then there is not a single root and the graph is not a DAG. it also checks if it is
acyclic using the DirectedCycle datatype and returns false if a cycle is found. The order for growth is
O(V) since we check each vertex to see if it's a potential root



Order of growth of running time:
The for loop traversing the graph means its O(N) where n is the size of the graph


/* *****************************************************************************
 *  Describe concisely your algorithm to compute the shortest common ancestor
 *  in ShortestCommonAncestor. For each method, give the order of growth of
 *  the best- and worst-case running times. Express your answers as functions
 *  of the number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) 
 *
 *  If you use hashing, assume the uniform hashing assumption so that put()
 *  and get() take constant time per operation.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't forget
 *  to count the time needed to initialize the marked[], edgeTo[], and
 *  distTo[] arrays.
 **************************************************************************** */

Description: First created an algorithm to find the shortest path. Since the graph is directed we
had to traverse the graph using breadth first search and for each vertex i, we calculated the sum of the distance from i to v
and i to w keeping track of the lowest sum. when we finally found the lowest sum we took the id
of the vertex i that gave us that shortest path and that is the shortest common ancestor


                                 running time
method                  best case           worst case
--------------------------------------------------------
length()                O(V)                O(EV) 

ancestor()              O(V^2)              O(V^2 * EV) -> O(V^2)

lengthSubset()          O(ABV)              O(ABEV)

ancestorSubset()        O(ABV^2)            O(ABV^2)

A = subsetA.length
B = subsetB.length


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
When ids would have multiple versions/variations of a word. the overlaps would cause some
problems when looking up ids via their associated nouns

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings and lectures, but do
 *  include any help from people (including
 *  classmates and friends) and attribute them by name.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe any serious problems you encountered.                    
 **************************************************************************** */


/* *****************************************************************************
 *  If you worked with a partner, give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */




/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **************************************************************************** */
This assignment was pretty fun like the others. It was a good challenge.


/* *****************************************************************************
 *  Include the screenshots of your output.
 **************************************************************************** */
