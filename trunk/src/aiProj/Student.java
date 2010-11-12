package aiProj;

import java.util.Iterator;
import java.util.Random;

public class Student {
	
	StudentConceptFramework mind;
	Random gen;

	
	public Student(StudentConceptFramework studentMind) {
		mind = studentMind;
		gen = new Random();
	}

	public void take(Exam exam) {
		Problem current;
		for (Iterator<Problem> it = exam.iterator(); it.hasNext();){
			current = it.next();
			current.setResults(attempt(current));
		}
	}

	private boolean attempt(Problem current) {
		double probabilityOfSuccess = .5;
		for (Concept c: current.getConcepts()){
			probabilityOfSuccess *= mind.getAbility(c);
		}
		System.out.println(probabilityOfSuccess);
		return probabilityOfSuccess >= gen.nextDouble();
	}

}
