package aiProj;

public class BayesSolver extends Solver{

	public BayesSolver(int numConcepts) {
		super(numConcepts);
	}

	@Override
	public void seed(Exam exam) {
		
		
	}

	double Prob_ui_given_evidence(int i, int correct_j, int incorrect_k)
	{
		return Prob_evidence_given_ui(i,correct_j,incorrect_k)/Prob_evidence(correct_j, incorrect_k);
	}
	
	double Prob_evidence(int correct_j, int incorrect_k)
	{
		double p = 0;
		for(int i = 0; i < numConcepts - 1; i++)
		{
			float a = i / (numConcepts - 1);
			p+=Math.pow(a,correct_j) * Math.pow(1 - a,incorrect_k);
		}
		return 0;
	}
	
	double Prob_evidence_given_ui(int i, int correct_j, int incorrect_k)
	{
		float a = i / (numConcepts - 1);
		return  Math.pow(a,correct_j) * Math.pow(1 - a,incorrect_k);
	}
}
