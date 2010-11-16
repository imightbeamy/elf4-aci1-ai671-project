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
		int id = c.getId();
		if(id < concepts.size())
		{
			return (concepts.get(id).getLevel() + 1) / levels;
		}
		
		return 1;
	}
	
	public void setUnderstanding(StudentConcept c, int level)
	{
		setUnderstanding(c, (float)level/levels);
	}
	
	public void setUnderstanding(StudentConcept c, float level)
	{
		int id = c.getId();
		if(id < concepts.size())
		{
			concepts.get(c.getId()).setLevel(level);
		}
	}
}
