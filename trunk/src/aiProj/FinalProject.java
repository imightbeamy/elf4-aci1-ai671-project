package aiProj;

public class FinalProject {

	public static void main(String[] args){
		int numConcepts = 50;
		int numProblems = 500;
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
		int correctPredictions = 0;
		int numFalse = 0;
		for(int p = 0; p < numProblems; p++){
			System.out.println(baseLineCopy.getProblem(p).results()
					+ "\t\t" + trailExam.getProblem(p).results());
			if (baseLineCopy.getProblem(p).results()
					== trailExam.getProblem(p).results())
			{
				correctPredictions ++;
			}
			if (! trailExam.getProblem(p).results())
			{
				numFalse ++;
			}
		}
		System.out.println("# student answered wrong: " + numFalse);
		System.out.println("# correctly predicted: " + correctPredictions);
		
	}
}
