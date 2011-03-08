package aiProj;

import java.util.ArrayList;

public class MultipleChoiceProblem extends Problem{

	private int numAnswers;
	
	public MultipleChoiceProblem(ArrayList<Concept> problemConcepts, ArrayList<Integer> conceptDifficulties) {
		super(problemConcepts, conceptDifficulties);
		numAnswers = 25;
	}
	
	
	public MultipleChoiceProblem(ArrayList<Concept> problemConcepts, ArrayList<Integer> conceptDifficulties, int numAnswers) {
		super(problemConcepts, conceptDifficulties);
		this.numAnswers = numAnswers;
	}
	
	public double falsePositive() {
		return 1.0 / numAnswers;
	}

}
