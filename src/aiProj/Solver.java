package aiProj;

public abstract class Solver {
	
	StudentConceptFramework studentUnderstanding;
	boolean seeded = false;
	
	public Solver(int numConcepts)
	{
		studentUnderstanding = new StudentConceptFramework(numConcepts);
	}
	
	public abstract void seed(Exam exam);
	public abstract void predictResults(Exam exam);
}
