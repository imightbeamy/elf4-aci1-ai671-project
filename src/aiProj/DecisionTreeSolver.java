package aiProj;

import java.util.ArrayList;

public class DecisionTreeSolver extends Solver {
	
	private DecisionNode root;

	public DecisionTreeSolver(int numConcepts, int understandingLevels) {
		super(numConcepts, understandingLevels);
	}


	@Override
	public void seed(Exam exam) {
		
		root = buildDecisionTree(exam.problems);
		
	}

	DecisionNode buildDecisionTree(ArrayList<Problem> dataset){
		if (dataset.size() == 0){
			return null;
		}
		int split = findBestSplit(dataset);
		//System.out.println(split);
		DecisionNode n = new DecisionNode(split);
		if (split != -1){
			ArrayList<Problem> positives = new ArrayList<Problem>();
			ArrayList<Problem> negatives = new ArrayList<Problem>();
			for (Problem p: dataset){
				if (p.hasConcept(split)){
					positives.add(p);
				} else {
					negatives.add(p);
				}
			}
			
			n.positiveNode = buildDecisionTree(positives);
			n.negativeNode = buildDecisionTree(negatives);
		}
		if (split == -1 || n.positiveNode == null || n.negativeNode == null){
			n.splitValue = -1;
			n.classification = dataset.get(0).results();
			//System.out.println(n.classification);
		}
		return n;
	}

	public int findBestSplit(ArrayList<Problem> dataset){
		int bestConcept = 0;
		double minEntropy = totalEntropy(dataset, 0);
	
		for (int c = 1; c < numConcepts; c++){
			System.out.println(totalEntropy(dataset, c));
			//System.out.println(c);
			if (totalEntropy(dataset, c) < minEntropy){
				minEntropy = totalEntropy(dataset, c);
				bestConcept = c;
			}
		}
		if (minEntropy == 0){
			return -1;
		}
		
		return bestConcept;
		
	}



	private double totalEntropy(ArrayList<Problem> dataset, int c) {
		ArrayList<Problem> positives = new ArrayList<Problem>();
		ArrayList<Problem> negatives = new ArrayList<Problem>();
		for (Problem p: dataset){
			if (p.hasConcept(c)){
				positives.add(p);
			} else {
				negatives.add(p);
			}
		}
		float totalSize = dataset.size();
		
		return positives.size()/totalSize * entropy(positives) + negatives.size()/totalSize * entropy(negatives);
	}


	private double entropy(ArrayList<Problem> problemSet) {
		double sucesses = 0;
		double trials = 0;
		for (Problem p: problemSet){
			if (p.results()){
				sucesses ++;
			}
			trials ++;
		}
		if (trials == 0 || sucesses == 0 || trials == sucesses){
			return 0;
		}
		double q = sucesses / trials;
		return - (q * Math.log(q)/Math.log(2) + (1 - q) *  Math.log(1 - q)/Math.log(2));
	}


	public void predictResults(Exam exam)
	{
		for (Problem p: exam){
			p.setResults(getClassification(p));
		}
	}
	
	boolean getClassification(Problem p){
		return root.getClassification(p);
	}
	
	
	private class DecisionNode{
		int splitValue;
		boolean classification;
		
		DecisionNode positiveNode;
		DecisionNode negativeNode;
		
		DecisionNode(int value){
			value = splitValue;
			classification = false;
		}
		
		boolean getClassification(Problem p) {
			if (isTerminalNode()){
				return classification;
			}
			else if (p.hasConcept(splitValue)){
				return positiveNode.getClassification(p);
			}
			return (negativeNode.getClassification(p));
		}

		boolean isTerminalNode(){
			return splitValue == -1;
		}

	}
	
}