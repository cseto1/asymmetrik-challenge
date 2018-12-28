package asymmetrik.autocomplete;

import java.util.Comparator;

public class CandidateImpl implements Candidate, Comparable<Candidate>{

	private String word;
	private Integer confidence;
	
	
	public CandidateImpl(String word, Integer confidence) {
		this.word = word;
		this.confidence = confidence;
	}
	/**
	 * returns the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * returns confidence level
	 */
	public Integer getConfidence() {
		return confidence;
	}

	
	public String toString() {
		return String.format("\"%s\" (%d)", word, confidence);
	}
	
	public int compareTo(Candidate otherCandidate) {
		return this.getConfidence().compareTo(otherCandidate.getConfidence());
	}
	
	public static Comparator<Candidate> CandidateComparator = new Comparator<Candidate>() {
		
		public int compare(Candidate candidate1, Candidate candidate2) {
			int comparison = candidate2.compareTo(candidate1);
			if(comparison != 0) {
				return comparison;
			}
			
			return candidate1.getWord().compareTo(candidate2.getWord());
			
		}
	};
	
}
