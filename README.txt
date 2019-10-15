Project: Quiz Generator
Files: AddQuestion.java, Choice.java, ChooseDir.java, ChooseFile.java,
	DisplayResults.java, ErrorScreen.java, 
	ExitQuizGenerator.java, ExitScreen.java, Main.java,
	QuestionDatabase.java, QuestionDatabaseADT.java, Question.java, QuestionNode.java,
	StartQuestionScreen.java
Semester: Spring 2019
Course: CS400
Ateam: 49
Due Date: Before 10pm on May 3, 2019
Author: //Yu Yan Chua, Lec 001, Xteam 15, ychua7@wisc.edu
	//Yujie Wang, Lec 001, Xteam 15, ywang2327@wisc.edu
	//Ruiting Tong, Lec 004, Xteam 103, tong5@wisc.edu
	//Shiyu Zhu, Lec 002, Xteam 52, zhu227@wisc.edu
	//Yizhou Liu, Lec 002, Xteam 51, liu773@wisc.edu
Lecturers: Deb Deppeler, Andrew Kuemmel
Bugs: Unknown

Functions:
1. The user can successfully add questions with images.
2. All windows are not responsive until the user returns the most recent prompt.
3. Duplicate questions can be stored in the quiz generator, but they will only appear once in a quiz. 
4. The user can load questions from a valid JSON file.
5. The user can save questions to a JSON file.
6. The user can successfully load a JSON file that was saved by this program.
7. Images can be successfully displayed on a window.
8. Question numbers and total number of questions are displayed in the title.
9. The numbers of correct and incorrect answers are displayed in a quiz.
10. Error screens will pop up when invalid inputs are detected.

Limit:
1. We only allow 5 choices in a question.