package aiProj;

import java.util.ArrayList;



public class StudentConceptFramework{

	private ArrayList<StudentConcept> concepts;
	private int levels;
	public StudentConceptFramework(int levels) {
		concepts = new ArrayList<StudentConcept>();
		this.levels = levels;
	}

	
	public void add(StudentConcept c){
		concepts.add(c);
	}


	public double getAbility(Concept c) {
		for (StudentConcept myConcept: concepts){
			if (myConcept.equals(c)){
				return (myConcept.getLevel() + 1.0) / levels;
			}
		}
		return 1;
	}
}
