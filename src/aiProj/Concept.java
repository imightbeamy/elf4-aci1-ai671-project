package aiProj;

public class Concept {
	
	private int id;

	public Concept(int id) {
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public boolean equals(Object o){
		if(o instanceof Concept)
		{
			Concept c = (Concept)o;
			return c.id == id;
		}
		return false;
	}
	
	public String toString(){
		return "Concept " + id;
	}

}
