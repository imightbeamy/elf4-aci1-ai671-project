package aiProj;

public class Concept {
	
	private int id;

	public Concept(int id) {
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public boolean equals(Concept c){
		return c.id == id;
	}
	
	public String toString(){
		return "Concept " + id;
	}

}
