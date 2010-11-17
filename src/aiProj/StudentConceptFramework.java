package aiProj;

import java.util.ArrayList;



public class StudentConceptFramework{

	private ArrayList<StudentConcept> concepts;
	private int levels; 
	private int numConcepts;
	
	public StudentConceptFramework(int levels, int numConcepts) {
		concepts = new ArrayList<StudentConcept>(numConcepts);
		for (int i = 0; i < numConcepts; i++){
			concepts.add(null);
		}
		this.levels = levels;
		this.numConcepts = numConcepts;
	}

	public StudentConceptFramework(int numConcepts) {
		this(1,numConcepts);
	}
	
	public void add(StudentConcept c){
		concepts.ensureCapacity(numConcepts);
		int id = c.getId();
		//System.out.println(c.getId());
		if(id < concepts.size())
		{
			concepts.set(id,c);
		}
	}


	public double getAbility(Concept c) {		
		int id = c.getId();
		if(id < concepts.size() && concepts.get(id) != null)
		{
			return concepts.get(id).getLevel();
		}
		
		return 1;
	}
	
	public void setUnderstanding(StudentConcept c, int level)
	{
		setUnderstanding(c.getId(), level/(levels - 1.0));
	}

	public void setUnderstanding(int studentConceptID, double level)
	{
		if(studentConceptID < concepts.size() &&
				concepts.get(studentConceptID) != null)
		{
			concepts.get(studentConceptID).setLevel((float)level);
		}
		else {
			add(new StudentConcept(studentConceptID, (float) level));
		}
	}
	
	public int numberofConcepts()
	{
		return concepts.size();
	}
}
