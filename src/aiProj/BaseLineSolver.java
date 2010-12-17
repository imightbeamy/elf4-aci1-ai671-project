package aiProj;

public class BaseLineSolver extends Solver {

	private int CORRECTIDX = 0;
	private int ALLIDX = 1;

	public BaseLineSolver(int numConcepts) {
		super(numConcepts, 0);
	}

	public void seed(Exam exam) {

		if (exam.hasResults()) {
			int concepts[][] = new int[numConcepts][2];
			for (Problem p : exam) {
				for (Concept c : p.getConcepts()) {
					if (p.results()) {
						concepts[c.getId()][CORRECTIDX]++;
					}
					concepts[c.getId()][ALLIDX]++;
				}
			}

			for (int id = 0; id < numConcepts; id++) {

				float understanding = 1;
				if (concepts[id][ALLIDX] != 0) {
					// System.out.println(concepts[id][CORRECTIDX]+ "/"
					// +(float)concepts[id][ALLIDX]);
					understanding = concepts[id][CORRECTIDX]
							/ (float) concepts[id][ALLIDX];
				}
				// System.out.println(understanding);
				studentUnderstanding.setUnderstanding(id, understanding);
			}
			seeded = true;
		}
	}

}
