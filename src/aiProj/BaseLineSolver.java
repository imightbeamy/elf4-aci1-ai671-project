package aiProj;

public class BaseLineSolver extends Solver {

	public BaseLineSolver(int numConcepts) {
		super(numConcepts);
		// TODO Auto-generated constructor stub
	}

	public void seed(Exam exam) {
		if(exam.hasResults())
		{
			int numCon = studentUnderstanding.numberofConcepts();
			int problemsperconcept[] = new int[numCon];
			for(Problem p: exam)
			{
				for(Concept c: p.getConcepts())
				{
					problemsperconcept[c.getId()]++;
				}
			}
			
			for(int id = 0; id < numCon; id++)
			{
				studentUnderstanding.setUnderstanding(id, 1/(float)problemsperconcept[id]);
			}
			seeded = true;
		}
	}

	public void predictResults(Exam exam)
	{
		if(seeded && !exam.hasResults())
		{
			double probabilityOfSuccess;
			for(Problem p: exam)
			{
				probabilityOfSuccess = .5;
				for (Concept c: p.getConcepts()){
					probabilityOfSuccess *= studentUnderstanding.getAbility(c);
				}
				p.setResults(probabilityOfSuccess >= .5);
			}
		}
	}
}
