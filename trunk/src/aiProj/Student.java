package aiProj;

import java.util.Random;

public class Student {
	
	StudentConceptFramework mind;
	Random gen;

	
	public Student(StudentConceptFramework studentMind) {
		mind = studentMind;
		gen = new Random();
	}

	public void take(Exam exam) {
		for (Problem p: exam){
			p.setResults(attempt(p));
		}
		exam.wasTaken();
	}

	private boolean attempt(Problem current) {
		double probabilityOfSuccess = 1;
		for (Concept c: current.getConcepts()){
			probabilityOfSuccess *= mind.getAbility(c);
		}
		//System.out.println(probabilityOfSuccess);
		return probabilityOfSuccess >= gen.nextDouble();
	}
	
	public Student clone(){
		Student student = new Student((StudentConceptFramework)mind.clone());
		return student;
		
	}
}
