package asymmetrik.autocomplete;

import java.util.List;
import java.util.StringTokenizer;

public class AutocompleteProviderImpl implements AutocompleteProvider {

	private Tree wordbank;

	public AutocompleteProviderImpl() {
		wordbank = new Tree();
	}

	public List<Candidate> getWords(String fragment) {
		return wordbank.getCandidates(fragment);
	}

	public void train(String passage) {
		StringTokenizer tokenizer = new StringTokenizer(passage);

		while (tokenizer.hasMoreTokens()) {
			addWord(tokenizer.nextToken());
		}
	}

	private void addWord(String word) {
		wordbank.insert(word);
	}

}
