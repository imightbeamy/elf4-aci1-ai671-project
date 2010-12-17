package aiProj;

public class StudentConcept extends Concept {

	private float level;

	public StudentConcept(int id, float level) {
		super(id);
		this.level = level;
	}

	public float getLevel() {
		return level;
	}

	public void setLevel(float level) {
		this.level = level;
	}

}
