package aiProj;

public class FinalProject {

	public static void main(String[] args){
		int numConcepts = 5;
		int numProblems = 50;
		int levelsOfUnderstanding = 100;
		int maxProblemConcepts = 2;
		
		ConceptFramework framework = new ConceptFramework(numConcepts);
		
		Student originalStudent = framework.makeStudent(levelsOfUnderstanding);
		
		System.out.println("Make training exam.");
		Exam trainingExam = new Exam(framework, numProblems, maxProblemConcepts);
		System.out.println("Students takes training exam.");
		originalStudent.take(trainingExam);
		System.out.println("***********Training Exam Results***********");
		System.out.println(trainingExam.toString());
		
		System.out.println("Seed Baseline Solver.");		
		BaseLineSolver bls = new BaseLineSolver(numConcepts);
		bls.seed(trainingExam);
		
		System.out.println("Make trial exam.");
		Exam trailExam = new Exam(framework, numProblems, maxProblemConcepts);
		Exam baseLineCopy = new Exam(trailExam);
		
		originalStudent.take(trailExam);
		bls.predictResults(baseLineCopy);
		
		System.out.println("Predictions\tResults");
		for(int p = 0; p < numProblems; p++)
			System.out.println(baseLineCopy.getProblem(p).results() + "\t\t" + trailExam.getProblem(p).results());
		



		
		
	}
}
