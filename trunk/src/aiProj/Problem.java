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
		String description = "Problem: ";
		for (Concept c: myConcepts){
			description += c.toString() + ", ";
		}
		description += "\n\tSucceeded?" + suceeded;
		return description;
	}

	public ArrayList<Concept> getConcepts(){
		return myConcepts;
	}
	
	public void setResults(boolean attempt) {
		suceeded = attempt;
		
	}
	
	

}
