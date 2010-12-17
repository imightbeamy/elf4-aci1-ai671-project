package aiProj;

import java.util.ArrayList;
import java.util.Random;

public class ConceptFramework {
	
	private ArrayList<Concept> allConcepts;
	private Random gen = new Random();

	public ConceptFramework(int numConcepts) {
		allConcepts = new ArrayList<Concept>();
		for (int i = 0; i < numConcepts; i++){
			allConcepts.add(new Concept(i));
		}
	}
	
	public Student makeStudent(int levelsOfUnderstanding){
		StudentConceptFramework studentMind =
			new StudentConceptFramework(levelsOfUnderstanding, allConcepts.size());
		for (Concept c: allConcepts){
			StudentConcept s = new StudentConcept(c.getId(), 
					gen.nextInt(levelsOfUnderstanding));
			
			studentMind.add(s);
			studentMind.setUnderstanding(s, gen.nextInt(levelsOfUnderstanding));
			//System.out.println(s.getLevel());
		}
		return new Student(studentMind);
	}
	
	
	public Problem newProblem(int maxNumConcepts){
		int numConcepts = gen.nextInt(maxNumConcepts) + 1;
		ArrayList<Concept> problemConcepts = new ArrayList<Concept>();
		@SuppressWarnings("unchecked")
		ArrayList<Concept> temp = (ArrayList<Concept>) allConcepts.clone();
		int nextConcept;
		for (int i = 0; i < numConcepts; i++){
			nextConcept = gen.nextInt(temp.size());
			problemConcepts.add(temp.get(nextConcept));
			temp.remove(nextConcept);
		}
		return new Problem(problemConcepts);
		
	}

}
