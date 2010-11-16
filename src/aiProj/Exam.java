package aiProj;

import java.util.ArrayList;
import java.util.Iterator;

public class Exam implements Iterable<Problem>{
	
	private ArrayList<Problem> problems;
	private boolean completed = false;
	
	public Exam(){
		problems = new ArrayList<Problem>();
	}
	
	public Exam(ConceptFramework framework, int numProblems, int maxProblemConcepts)
	{
		this();
		for (int i = 0; i < numProblems; i++){
			this.add(framework.newProblem(maxProblemConcepts));
		}
	}
	
	public Exam(Exam e)
	{
		this();
		for(Problem p: e.problems)
			this.add(p.clone());
	}

	public void add(Problem newProblem) {
		problems.add(newProblem.clone());
	}
	
	public Problem getProblem(int p)
	{
		if(p >= 0 && p <  problems.size())
			return problems.get(p);
		return null;
	}
	
	public String toString(){
		String description = "Exam:\n";
		for (Problem p: problems){
			description += p.toString() + '\n';
		}
		return description;
	}
	
	public boolean hasResults()
	{
		return completed;
	}

	public void wasTaken()
	{
		completed = true;
	}
	
	@Override
	public Iterator<Problem> iterator() {
		return problems.iterator();
	}
	
}
