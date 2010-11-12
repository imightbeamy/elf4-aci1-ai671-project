package aiProj;

public class FinalProject {
	
	public static void main(String[] args){
		int numConcepts = 5;
		int numProblems = 5;
		int levelsOfUnderstanding = 3;
		int maxProblemConcepts = 3;
		ConceptFramework framework = new ConceptFramework(numConcepts);
		Student originalStudent = framework.makeStudent(levelsOfUnderstanding);
		Exam trainingExam = makeExam(framework, numProblems, maxProblemConcepts);
		
		originalStudent.take(trainingExam);
		System.out.println(trainingExam.toString());
		
	}

	public static Exam makeExam(ConceptFramework framework,
			int numProblems, int maxProblemConcepts){
		Exam newExam = new Exam();
		for (int i = 0; i < numProblems; i++){
			newExam.add(framework.newProblem(maxProblemConcepts));
		}
		return newExam;
	}

}
