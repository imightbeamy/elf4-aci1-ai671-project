package aiProj;

public abstract class Solver {
	
	StudentConceptFramework studentUnderstanding;
	boolean seeded = false;
	int numConcepts;
	public Solver(int numConcepts)
	{
		this.numConcepts = numConcepts;
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
