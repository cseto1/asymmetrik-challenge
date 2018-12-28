package asymmetrik.autocomplete;

import java.util.List;

public interface AutocompleteProvider {

	/**
	 * 
	 * @param fragment - an incomplete word
	 * @return a list of potential candidates
	 */
	public List<Candidate> getWords(String fragment);
	
	/**
	 * Inputs a passage of words that are parsed and used to build a tree of potential candidates
	 * @param passage
	 */
	public void train(String passage);
}
