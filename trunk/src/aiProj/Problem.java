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
	
	public boolean results() {
		return suceeded;
	}

	public boolean hasConcept(int conceptNum) {
		for (Concept c: myConcepts){
			if (c.getId() == conceptNum){
				return true;
			}
		}
		return false;
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Problem)
		{
			Problem p = (Problem) o;
			if(p.myConcepts.size() != myConcepts.size())
				return false;
			
			for(Concept c: p.myConcepts)
			{
				if(!myConcepts.contains(c))
					return false;
			}
			return true;
		}
		return false;
	}
	

}
