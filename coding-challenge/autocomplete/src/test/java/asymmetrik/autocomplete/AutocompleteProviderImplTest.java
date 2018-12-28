package asymmetrik.autocomplete;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AutocompleteProviderImplTest {

	private static String passage = "The third thing that I need to tell you is that this thing does not think thoroughly.";
	private AutocompleteProvider provider;
	
	@Before
	public void setUp() throws Exception {
		provider = new AutocompleteProviderImpl();
		provider.train(passage);
	}

	@Test
	public void getWords_test1() {
		List<Candidate> expectedCandidates = new ArrayList<Candidate>();
		expectedCandidates.add(new CandidateImpl("thing", 2));
		expectedCandidates.add(new CandidateImpl("think", 1));
		expectedCandidates.add(new CandidateImpl("third", 1));
		expectedCandidates.add(new CandidateImpl("this", 1));
		Collections.sort(expectedCandidates, CandidateImpl.CandidateComparator);
		
		List<Candidate> actualCandidates = provider.getWords("thi");
		Collections.sort(actualCandidates, CandidateImpl.CandidateComparator);
		
		
		for(int i = 0; i < actualCandidates.size(); i++) {
			assertEquals(expectedCandidates.get(i).getWord(), actualCandidates.get(i).getWord());
			assertEquals(expectedCandidates.get(i).getConfidence(), actualCandidates.get(i).getConfidence());
		}	
	}
	
	@Test
	public void getWords_test2() {
		List<Candidate> expectedCandidates = new ArrayList<Candidate>();
		expectedCandidates.add(new CandidateImpl("need", 1));
		Collections.sort(expectedCandidates, CandidateImpl.CandidateComparator);
		
		List<Candidate> actualCandidates = provider.getWords("nee");
		Collections.sort(actualCandidates, CandidateImpl.CandidateComparator);
		
		
		for(int i = 0; i < actualCandidates.size(); i++) {
			assertEquals(expectedCandidates.get(i).getWord(), actualCandidates.get(i).getWord());
			assertEquals(expectedCandidates.get(i).getConfidence(), actualCandidates.get(i).getConfidence());
		}	
	}
	
	@Test
	public void getWords_test3() {
		List<Candidate> expectedCandidates = new ArrayList<Candidate>();
		expectedCandidates.add(new CandidateImpl("that", 2));
		expectedCandidates.add(new CandidateImpl("thing", 2));
		expectedCandidates.add(new CandidateImpl("think", 1));
		expectedCandidates.add(new CandidateImpl("this", 1));
		expectedCandidates.add(new CandidateImpl("third", 1));
		expectedCandidates.add(new CandidateImpl("the", 1));
		expectedCandidates.add(new CandidateImpl("thoroughly", 1));
		Collections.sort(expectedCandidates, CandidateImpl.CandidateComparator);
		
		
		List<Candidate> actualCandidates = provider.getWords("th");
		Collections.sort(actualCandidates, CandidateImpl.CandidateComparator);
		
		
		
		for(int i = 0; i < actualCandidates.size(); i++) {
			assertEquals(expectedCandidates.get(i).getWord(), actualCandidates.get(i).getWord());
			assertEquals(expectedCandidates.get(i).getConfidence(), actualCandidates.get(i).getConfidence());
		}	
	}

}
