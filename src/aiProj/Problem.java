package aiProj;

import java.util.ArrayList;

public class Problem {
	
	private ArrayList<Concept> myConcepts;
	private boolean suceeded;

	public Problem(ArrayList<Concept> problemConcepts) {
		myConcepts = problemConcepts;
		suceeded = false;
	}
	
	public Problem clone(){
		Problem p = new Problem(myConcepts);
		return p;
	}

	public String toString(){
		StringBuilder description = new StringBuilder("Problem Concepts: ");
		for (Concept c: myConcepts){
			description.append(c.getId() + ", ");
		}
		description.append("\n\tSucceeded? " + suceeded);
		return description.toString();
	}

	public ArrayList<Concept> getConcepts(){
		return myConcepts;
	}
	
	public void setResults(boolean attempt) {
		suceeded = attempt;
		
	}
	
	

}
