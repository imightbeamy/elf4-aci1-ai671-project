package aiProj;

public abstract class Solver {
	
	StudentConceptFramework studentUnderstanding;
	boolean seeded = false;
	int numConcepts;
	int understandingLevels;
	
	public Solver(int numConcepts, int understandingLevels)
	{
		this.numConcepts = numConcepts;
		this.understandingLevels = understandingLevels;
		studentUnderstanding = new StudentConceptFramework(numConcepts);
	}
	
	public void predictResults(Exam exam)
	{
		if(seeded && !exam.hasResults())
		{
			double probabilityOfSuccess;
			for(Problem p: exam)
			{
				probabilityOfSuccess = 1;
				for (Concept c: p.getConcepts()){
					probabilityOfSuccess *= studentUnderstanding.getAbility(c);
				}
				p.setResults(probabilityOfSuccess >= .5);
			}
		}
	}
	
	public abstract void seed(Exam exam);
}
