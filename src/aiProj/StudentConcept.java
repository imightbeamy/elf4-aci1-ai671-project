package aiProj;



public class StudentConcept extends Concept {
	
	private int level; 

	public StudentConcept(int id, int level) {
		super(id);
		this.level = level;
	}
	
	public int getLevel(){
		return level;
	}

}
