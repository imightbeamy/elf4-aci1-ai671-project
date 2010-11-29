package aiProj;

public class BayesSolver extends Solver{

	private int CORRECTIDX = 0;
	private int ALLIDX     = 1;
	
	public BayesSolver(int numConcepts, int understandingLevels) {
		super(numConcepts, understandingLevels);
	}

	@Override
	public void seed(Exam exam) {
		
		if(exam.hasResults())
		{	
			int concepts[][] = new int[numConcepts][2];
			for(Problem p: exam)
			{
				for(Concept c: p.getConcepts())
				{
					if (p.results())
					{
						concepts[c.getId()][CORRECTIDX]++;
					}
					concepts[c.getId()][ALLIDX]++;
				}
			}
			
			double p;
			int corr,incorr, level = 0;
			
			for(int id = 0; id < numConcepts; id++)
			{
				double max_u = 0;
				level = 0;
				for(int i = 0; i < understandingLevels - 1; i++)
				{
					corr = concepts[id][CORRECTIDX];
					incorr = concepts[id][ALLIDX] - corr;
					p = Prob_evidence_given_ui(i,corr,incorr);
					if(p > max_u)
					{
						max_u = p;
						level = i;
					}
				}
				studentUnderstanding.setUnderstanding(id, level);
			}
			seeded = true;
		}
		
	}

	double Prob_ui_given_evidence(int i, int correct_j, int incorrect_k)
	{
		return Prob_evidence_given_ui(i,correct_j,incorrect_k)/Prob_evidence(correct_j, incorrect_k);
	}
	
	double Prob_evidence(int correct_j, int incorrect_k)
	{
		double p = 0;
		for(int i = 0; i < understandingLevels - 1; i++)
		{
			double a = (double)i / (understandingLevels - 1);
			p+=Math.pow(a,correct_j) * Math.pow(1 - a,incorrect_k);
		}
		return p;
	}
	
	double Prob_evidence_given_ui(int i, int correct_j, int incorrect_k)
	{
		double a = (double)i / (understandingLevels - 1);
		System.out.println(a + "     " + Math.pow(a,correct_j) +"  "+ Math.pow(1 - a,incorrect_k));
		return  Math.pow(a,correct_j) * Math.pow(1 - a,incorrect_k);
	}
}
