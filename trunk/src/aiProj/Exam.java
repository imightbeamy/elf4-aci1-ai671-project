package aiProj;

import java.util.ArrayList;
import java.util.Iterator;

public class Exam implements Iterable<Problem>{
	
	ArrayList<Problem> problems;
	
	public Exam(){
		problems = new ArrayList<Problem>();
	}

	public void add(Problem newProblem) {
		problems.add(newProblem.clone());
	}
	
	public String toString(){
		String description = "Exam:\n";
		for (Problem p: problems){
			description += p.toString() + '\n';
		}
		return description;
	}

	@Override
	public Iterator<Problem> iterator() {
		return problems.iterator();
	}
	
}
