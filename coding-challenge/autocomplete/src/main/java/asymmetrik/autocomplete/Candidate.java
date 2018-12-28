package asymmetrik.autocomplete;

public interface Candidate extends Comparable<Candidate> {
	
	/**
	 * @return the word of the Candidate
	 */
	public String getWord();
	
	/**
	 * @return the confidence level of the Candidate
	 */
	public Integer getConfidence();
}
