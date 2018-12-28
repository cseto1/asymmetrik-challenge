package asymmetrik.autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree {
	private Node root;

	public Tree() {
		root = new Node();
	}

	/**
	 * Converts the word to lowercase and clears out any punctuation
	 * 
	 * @param word
	 * @return
	 */
	private static String prepWord(String word) {
		String preppedWord = word.toLowerCase().replaceAll("\\pP", "");
		return preppedWord;
	}
	
	

	public void insert(String word) {
		insert(root, prepWord(word));
	}

	/**
	 * Recursive method that checks the current tree to see if the word exists.
	 * If it does, then add a confidence point, if to doesn't, create a new node.
	 * @param n - Current node in the recursive method. The node goes down a branch for each level in the recursion
	 * @param word - Current word being inserted in the recursive method. The first letter is popped off 
	 * for each level in the recursion
	 */
	private void insert(Node n, String word) {
		if (word.length() > 0) {
			char firstLetter = word.charAt(0);
			// character arithmetic gives location in the array
			// e.g. a=0, b=1, c=2, etc., etc.
			int index = firstLetter - 'a';

			// if the letter doesn't exist, create a new node.
			if (n.children[index] == null) {

				Node node = new Node();
				node.setLetter(firstLetter);

				// call insert() recursively removing the first letter
				String adjustedWord = word.substring(1);
				insert(node, adjustedWord);

				n.children[index] = node;
				// if the letter does exist, traverse that node.
			} else {
				String adjustedWord = word.substring(1);
				insert(n.children[index], adjustedWord);
			}
		}
		// when we've reached the end of the word
		else {
			n.setEnd(true);
			n.addConfidence();
		}
	}

	/**
	 * @param word - word fragment that is being searched
	 * @return a sorted list of candidates ordered by highest confidence choices
	 */
	public List<Candidate> getCandidates(String word) {

		String prefix = prepWord(word);
		
		List<Candidate> candidates = buildCandidatesFromNode(prefix);

		Collections.sort(candidates, CandidateImpl.CandidateComparator);
		
		return candidates;

	}

	/**
	 * traverses through the nodes to find the node that the last letter of the fragment
	 * @param word - fragment being searched
	 * @return the node that the fragment ends on
	 */
	private Node traverseNodes(String word) {

		Node lowestNode = root;

		for (int i = 0; i < word.length(); i++) {
			char character = word.charAt(i);
			int position = character - 'a';

			if (lowestNode.children[position] != null) {

				lowestNode = lowestNode.children[position];
			}
		}
		return lowestNode;
	}

	/**
	 * Overloaded method to allow for the option of no candidates (since the mothership method is recursive)
	 * @param prefix - word fragment being searched
	 * @param starterNode - node to start on.
	 * @return a list of candidates based off of the prefix
	 */
	private List<Candidate> buildCandidatesFromNode(String prefix) {
		
		Node starterNode = traverseNodes(prefix);
		return buildCandidatesFromNode(prefix, starterNode, new ArrayList<Candidate>());
	}
	
	/**
	 * Recursive method to generate candidates based off of prefix
	 * @param prefix - fragment to provide suggestions for
	 * @param node - current node
	 * @param candidates - current cumulated list of candidates
	 * @return list of candidates 
	 */
	private List<Candidate> buildCandidatesFromNode(String prefix, Node node, List<Candidate> candidates) {
		for (Node child : node.children) {
			
			if (child != null) {
				String suffix = "";
				suffix = suffix.concat(String.valueOf(child.getLetter()));

				if (child.isEnd()) {
					Candidate candidate = new CandidateImpl(prefix.concat(String.valueOf(child.getLetter())), child.getConfidence());
					candidates.add(candidate);
				}

				buildCandidatesFromNode(prefix.concat(suffix), child, candidates);

			}
		}

		return candidates;
	}
}

class Node {

	private static final Integer LETTERS_IN_ALPHABET = 26;

	private boolean isEnd;
	Node[] children;
	private Integer confidence;
	private char letter;

	public Node() {
		isEnd = false;
		children = new Node[LETTERS_IN_ALPHABET];
		confidence = 0;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Integer getConfidence() {
		return confidence;
	}

	public void addConfidence() {
		this.confidence++;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public String toString() {
		return String.valueOf(letter);
	}
}
