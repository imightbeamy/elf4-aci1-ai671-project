package aiProj;

import java.util.Random;

public class BaseLineSolver extends Solver {

	private Random gen;
	
	public BaseLineSolver(int numConcepts) {
		super(numConcepts);
		gen = new Random();
		// TODO Auto-generated constructor stub
	}

	public void seed(Exam exam) {
		if(exam.hasResults())
		{	
			int problemsperconcept[] = new int[numConcepts];
			for(Problem p: exam)
			{
				for(Concept c: p.getConcepts())
				{
					problemsperconcept[c.getId()]++;
				}
			}
			
			for(int id = 0; id < numConcepts; id++)
			{
				studentUnderstanding.setUnderstanding(id, (float)problemsperconcept[id]);
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
				//p.setResults(probabilityOfSuccess >= .5);
				p.setResults(probabilityOfSuccess >= gen.nextDouble());
				
			}
		}
	}
}
