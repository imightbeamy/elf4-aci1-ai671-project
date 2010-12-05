package aiProj;

public class FinalProject {

	public static void main(String[] args){
		int numConcepts = 8;
		int numProblems = 100;
		int levelsOfUnderstanding = 4;
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

		System.out.println("Seed Baseline Solver.");		
		BayesSolver bayess = new BayesSolver(numConcepts, levelsOfUnderstanding);
		bayess.seed(trainingExam);
		
		System.out.println("Make trial exam.");
		Exam trailExam = new Exam(framework, numProblems, maxProblemConcepts);
		Exam cloneExam = new Exam(trailExam);
		Exam baseLineCopy = new Exam(trailExam);
		Exam bayesCopy =  new Exam(trailExam);
		
		originalStudent.take(trailExam);
		Student originalStudentII = (Student) originalStudent.clone();
		originalStudentII.take(cloneExam);
		bls.predictResults(baseLineCopy);
		bayess.predictResults(bayesCopy);
		
		System.out.println("Predictions\t\tResults");
		System.out.println("Baseline\tBayes");
		int correctPredictions_bl = 0;
		int correctPredictions_bayes = 0;
		int numFalse = 0;
		int clone_same = 0;
		for(int p = 0; p < numProblems; p++){
			System.out.println(baseLineCopy.getProblem(p).results() + "\t\t" 
							+ bayesCopy.getProblem(p).results() + "\t\t"
							+	trailExam.getProblem(p).results() + "\t\t"
							+    cloneExam.getProblem(p).results());
			
			if (baseLineCopy.getProblem(p).results()
					== trailExam.getProblem(p).results())
			{
				correctPredictions_bl ++;
			}
			if (bayesCopy.getProblem(p).results()
					== trailExam.getProblem(p).results())
			{
				correctPredictions_bayes ++;
			}
			if (! trailExam.getProblem(p).results())
			{
				numFalse ++;
			}
			if (cloneExam.getProblem(p).results()
					== trailExam.getProblem(p).results())
			{
				clone_same ++;
			}
		}
		System.out.println("# student answered wrong: " + numFalse);
		System.out.println("# base line correctly predicted: " + correctPredictions_bl);
		System.out.println("# bayes correctly predicted: " + correctPredictions_bayes);
		System.out.println("# clone the same: " + clone_same);
		
	}
}
