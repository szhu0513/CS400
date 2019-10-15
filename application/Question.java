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

import java.util.List;

/**
 * This is the Question Node Class
 *
 */
public class Question {
	private String question;
	private String topic;
	private List<Choice> choices;
	private String answer;
	private String metaData;
	private String image;

	/**
	 * This is the public constructor which initialize private variables.
	 * 
	 * @param metaData The metaData type of the Question
	 * @param content  The Question body
	 * @param topic    the topic of the question
	 * @param image    the image include in the question
	 * @param choices  the five choices of the question
	 * @param answer   the correct answer of the question
	 */
	public Question(String metaData, String content, String topic, String image, List<Choice> choices,
			String answer) {
		setMetaData(metaData);
		setContent(content);
		setTopic(topic);
		setImage(image);
		setChoiceContents(choices);
		setAnswer(answer);

	}

	/**
	 * This private helper method is mainly to set the content of the question
	 * 
	 * @param question the body of the question
	 */
	private void setContent(String content) {
		this.question = content;
	}

	/**
	 * This private helper method is mainly to set the topic of the question
	 * 
	 * @param topic the topic of the question
	 */
	private void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * This private helper method is mainly to set the five choices of the question
	 * 
	 * @param choices the different choice of the question
	 */
	private void setChoiceContents(List<Choice> choiceContents) {
		this.choices = choiceContents;
	}

	/**
	 * This private helper method is mainly to set the correct answer of the question
	 * 
	 * @param answer the correct answer of the question
	 */
	private void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * This private helper method is mainly to set the image of the question
	 * 
	 * @param imaget he image used by question
	 */
	private void setImage(String image) {
		this.image = image;
	}

	/**
	 * This private helper method is mainly to set the meta data of the question
	 * 
	 * @param metaData the meta data of the question
	 */
	private void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	/**
	 * This private helper method is mainly to get the content of the question
	 * 
	 * @return the content of the question
	 */
	public String getContent() {
		return question;
	}

	/**
	 * This private helper method is mainly to get the topic of the question
	 * 
	 * @return the topic of the question
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * This private helper method is mainly to get the answer of the question
	 * 
	 * @return the answer of the question
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * This private helper method is mainly to get the choice list of the question
	 * 
	 * @return the choice list of the question
	 */
	public List<Choice> getChoices() {
		return choices;
	}

	/**
	 * This private helper method is mainly to get the image of the question
	 * 
	 * @return the image of the question
	 */
	public String getImage() {
		return image;
	}

	/**
	 * This private helper method is mainly to get the meta data of the question
	 * 
	 * @return the meta data of the question
	 */
	public String getMetaData() {
		return metaData;
	}
}
