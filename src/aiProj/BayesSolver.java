package aiProj;

import java.util.ArrayList;

public class BayesSolver extends Solver{
	
	public BayesSolver(int numConcepts, int understandingLevels) {
		super(numConcepts, understandingLevels);
	}

	@Override
	public void seed(Exam exam) {
		
		ArrayList<StudentConceptFramework> minds = 
			new ArrayList<StudentConceptFramework>();

		
		minds = GenerateMinds();
		if(exam.hasResults())
		{	
			double maxProb = 0;
			StudentConceptFramework maxMind = null;
		    for (StudentConceptFramework framework: minds){
		    	if (examProbability(framework, exam) > maxProb){
		    		maxMind = framework;
		    		maxProb = examProbability(framework, exam);
		    	}
		    }
		    studentUnderstanding = maxMind;
			seeded = true;
		}
		
	}

	private double examProbability(StudentConceptFramework framework, Exam exam) {
		double probability = 1;
		for (Problem p: exam.problems){
			if (p.results()) {
				probability *= Probabilty(framework, p);
			}
			else {
				probability *= (1 - Probabilty(framework, p));
			}
				
		}
		return probability;
	}

	private double Probabilty(StudentConceptFramework framework, Problem p) {
		double probability = 1;
		for (Concept c: p.getConcepts()){
			probability *= framework.getAbility(c);
		}
		return probability;
	}

	private ArrayList<StudentConceptFramework> GenerateMinds() {
		ArrayList<StudentConceptFramework> minds = new ArrayList<StudentConceptFramework>();
		ArrayList<ArrayList<Integer>> settings = new ArrayList<ArrayList<Integer>>();
		settings = generateSettings(numConcepts);
		for (ArrayList<Integer> setting: settings){
			minds.add(new StudentConceptFramework(setting, understandingLevels));
		}
		return minds;
	}

	private ArrayList<ArrayList<Integer>> generateSettings(int concepts) {
		if (concepts == 0){
			ArrayList<ArrayList<Integer>> first = new ArrayList<ArrayList<Integer>>();
			for (int num = 0; num < understandingLevels; num++){
				ArrayList<Integer> a = new ArrayList<Integer>();
				a.add(num);
				first.add(a);
			}
			return first;
		} else {
			ArrayList<ArrayList<Integer>> prevSettings = 
				generateSettings(concepts - 1);
			ArrayList<ArrayList<Integer>> settings = 
				new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> current;
			for (ArrayList<Integer> setting: prevSettings){
				for (int num = 0; num < understandingLevels; num++){
					current = (ArrayList<Integer>) setting.clone();
					current.add(num);
					settings.add(current);
				}
			}
			
			return settings;
			
		}
	}

}
