import os
import time

problems = [10, 50, 100, 500]
concepts = [2, 3, 4, 5, 6, 7, 8]
ulevels = 4
maxProblemConcepts = 2
runs = 100
path = "./bin"
os.system("rm -rf summery.csv")

for r in range(runs):
	for p in problems:
		for c in concepts:
			filename = "test_p" + str(p) +  "_c" + str(c) + ".txt"
			os.system("echo " + str(p) + " " + str(c))
			args = str(c) +  " " + \
					str(p) + " " + \
					str(ulevels) + " " + \
					str(maxProblemConcepts)
			os.system("java -Xms32m -Xmx600m -cp " + path + " aiProj/FinalProject "\
						+ args + " > " + filename)
	print "Test " + str(r) + " finished" 