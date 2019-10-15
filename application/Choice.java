/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: Quiz Generator
// Files: AddQuestion.java, Choice.java, ChooseDir.java, ChooseFile.java,
// DisplayResults.java, ErrorScreen.java, ExitQuizGenerator.java, ExitScreen.java, Main.java,
// QuestionDatabase.java, QuestionDatabaseADT.java, Question.java, QuestionNode.java,
// StartQuestionScreen.java
//
// Semester: Spring 2019
// Course: CS400
// Due Date: Before 10pm on May 2, 2019
// Author: Yu Yan Chua(Lec 001), Yujie Wang(Lec 001), Ruiting Tong(Lec 004), Shiyu Zhu(Lec 002),
// Yizhou Liu(Lec 002),
// Email: ywang2327@wisc.edu, szhu227@wisc.edu, liu773@wisc.edu, ychua7@wisc.edu
// Lecture's Name: Deb Deppeler, Andrew Kuemmel
// Bugs: Unknown
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////

package application;

/**
 * This is the Choice class which contains one of the several choices in a question
 *
 */
public class Choice {
	private boolean isCorrect;
	private String choice;

	/**
	 * This is the public constructor which initialize private variable
	 * 
	 * @param isCorrect whether the choice is correct
	 * @param choice Content of choice
	 */
	public Choice(boolean isCorrect, String choice) {
		this.isCorrect = isCorrect;
		this.choice = choice;
	}

	/**
	 * This is the public method which get the choices of the question
	 * 
	 * @return the choices of the question
	 */
	public String getChoice() {
		return choice;
	}

	/**
	 * This is the public method which return whether the choice that user choiced is correct or not
	 * 
	 * @return the result of the choice
	 */
	public boolean getIsCorrect() {
		return isCorrect;
	}
}
