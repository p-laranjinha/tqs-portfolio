1e)

My project has passed the defined quality gate with 1 bug, 0 vulnerabilities, 1 security hotspot, 2h14mins of debt with 26 code smells, 71.8% coverage with 8 unit tests and 0.0% duplications with 0 duplicated blocks.

1f)

Issue | Problem description | How to solve
--- | --- | ---
Bug | Save and re-use this "Random" | In the file "src/main/java/euromillions/Dip.java", I should make the new Random() on line 50 into a class variable I can reuse, so that it is more consistently random
Code Smell (major) | Refactor the code in order to not assign to this loop counter from within the loop body. | In the file "src/main/java/euromillions/Dip.java", the for loops on the lines 53 and 60 should have the loop counter "i++" removed from the body of the loop and be places into the head.
Code Smell (minor) | Make this final field static too. | In the file "src/main/java/euromillions/Dip.java", on the lines 16, 17, 18 and 19 make the class variables static.
Code Smell (info) | Remove this 'public' modifier'. | In the file "src/test/java/euromillions/DipTest.java", on the lines 14, 33 and 71 remove the 'public' modifier from the class/method.

2a)

Technical debt (which i got 2h14min) is the extra work time that code smells may cause in the future to refactor the code.

2d)

After the changes, I have 17+8+7+8+2 uncovered lines with 2+2+4+6+0 uncovered conditions.