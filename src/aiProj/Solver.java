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
	
	public abstract void seed(Exam exam);
	public abstract void predictResults(Exam exam);
}
