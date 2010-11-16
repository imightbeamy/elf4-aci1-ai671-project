package aiProj;

import java.util.ArrayList;



public class StudentConceptFramework{

	private ArrayList<StudentConcept> concepts;
	private int levels; 
	
	public StudentConceptFramework(int levels, int numConcepts) {
		concepts = new ArrayList<StudentConcept>(numConcepts);
		this.levels = levels;
	}

	public StudentConceptFramework(int numConcepts) {
		this(1,numConcepts);
	}
	
	public void add(StudentConcept c){
		int id = c.getId();
		if(id < concepts.size())
		{
			concepts.set(id,c);
		}
	}


	public double getAbility(Concept c) {		
		int id = c.getId();
		if(id < concepts.size() && concepts.get(id) != null)
		{
			return (concepts.get(id).getLevel() + 1) / levels;
		}
		
		return 1;
	}
	
	public void setUnderstanding(StudentConcept c, int level)
	{
		setUnderstanding(c.getId(), (float)level/levels);
	}
	
	public void setUnderstanding(StudentConcept c, float level)
	{
		setUnderstanding(c.getId(), level);
	}

	public void setUnderstanding(int studentConceptID, float level)
	{
		if(studentConceptID < concepts.size() && concepts.get(studentConceptID) != null)
		{
			concepts.get(studentConceptID).setLevel(level);
		}
	}
	
	public int numberofConcepts()
	{
		return concepts.size();
	}
}
