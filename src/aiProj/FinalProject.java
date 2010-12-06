package aiProj;

import java.io.FileWriter;

public class FinalProject {

	public static void main(String[] args){
	
		int numConcepts = 8;
		int numProblems = 500;
		int levelsOfUnderstanding = 4;
		int maxProblemConcepts = 2;
	 	
		if(args.length == 4)
		{
			numConcepts = Integer.parseInt(args[0]);
			numProblems = Integer.parseInt(args[1]);
			levelsOfUnderstanding = Integer.parseInt(args[2]);
			maxProblemConcepts = Integer.parseInt(args[3]);
			System.out.println("Concepts " + numConcepts);
			System.out.println("Problems " + numProblems);
			System.out.println("U Levels " + levelsOfUnderstanding);
			System.out.println("Max P Concepts " + maxProblemConcepts);
		}
		
		
		ConceptFramework framework = new ConceptFramework(numConcepts);
		
		Student originalStudent = framework.makeStudent(levelsOfUnderstanding);
		
//		System.out.println("Make training exam.");
		Exam trainingExam = new Exam(framework, numProblems, maxProblemConcepts);
//		System.out.println("Students takes training exam.");
		originalStudent.take(trainingExam);
//		System.out.println("***********Training Exam Results***********");
//		System.out.println(trainingExam.toString());
		
//		System.out.print("Seed Baseline Solver...");		
		BaseLineSolver bls = new BaseLineSolver(numConcepts);
		bls.seed(trainingExam);
//		System.out.println("done.");
		
//		System.out.print("Seed Bayse Solver...");		
		BayesSolver bayess = new BayesSolver(numConcepts, levelsOfUnderstanding);
		long start = System.currentTimeMillis();
		bayess.seed(trainingExam);
		long end = System.currentTimeMillis();
		long time = (end - start)/100;
		System.out.println("Bayes seedtime " + time + " s");
		
		
//		System.out.println("Make trial exam.");
		Exam trailExam = new Exam(framework, numProblems, maxProblemConcepts);
		Exam cloneExam = new Exam(trailExam);
		Exam baseLineCopy = new Exam(trailExam);
		Exam bayesCopy =  new Exam(trailExam);
		
		originalStudent.take(trailExam);
		Student originalStudentII = (Student) originalStudent.clone();
		originalStudentII.take(cloneExam);
		bls.predictResults(baseLineCopy);
		bayess.predictResults(bayesCopy);
		
		System.out.println("Baseline, Bayes, Student, Clone");
		int correctPredictions_bl = 0;
		int correctPredictions_bayes = 0;
		int numFalse = 0;
		int clone_same = 0;
		for(int p = 0; p < numProblems; p++){
			System.out.println(baseLineCopy.getProblem(p).results() + ", " 
							+ bayesCopy.getProblem(p).results() + ", "
							+	trailExam.getProblem(p).results() + ", "
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
		
		System.out.println("Totals");
		System.out.println(correctPredictions_bl + ", " + 
							correctPredictions_bayes + ", " + 
							numFalse + ", " + clone_same);
							
		System.out.println("# student answered wrong: " + numFalse);
		System.out.println("# base line correctly predicted: " + correctPredictions_bl);
		System.out.println("# bayes correctly predicted: " + correctPredictions_bayes);
		System.out.println("# clone the same: " + clone_same);
		
		
		try {
			// Create file
			FileWriter fstream = new FileWriter("summery.csv",true);
			fstream.write(numConcepts + ", " +
						numProblems + ", " +
						correctPredictions_bl + ", " +
						correctPredictions_bayes + ", " +
						numFalse + ", " +
						clone_same + ", " + time + "\n");
			fstream.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}
