package aiProj;

public class FinalProject {

	public static void main(String[] args){
		int numConcepts = 5;
		int numProblems = 5;
		int levelsOfUnderstanding = 3;
		int maxProblemConcepts = 3;
		ConceptFramework framework = new ConceptFramework(numConcepts);
		Student originalStudent = framework.makeStudent(levelsOfUnderstanding);
		Exam trainingExam = new Exam(framework, numProblems, maxProblemConcepts);
		
		originalStudent.take(trainingExam);
		System.out.println(trainingExam.toString());
		
	}
}
