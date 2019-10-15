/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: Quiz Generator(M2 GUI)
// Files: AddQuestion.java, Choice.java, Confirm.java, DisplayResult.java,
// ExitQuizGenerator.java, ExitScreen.java, PrimaryGUI.java,
// Question.java, QuestionNode.java, StartQuestionScreen.java
// Semester: Spring 2019
// Course: CS400
// Due Date: Before 10pm on March 19, 2019
// Author: Yu Yan Chua(Lec 001),Yujie Wang(Lec 001) Ruiting Tong(Lec 004), Shiyu Zhu(Lec 002),
// Yizhou Liu(Lec 002),
// Email: ywang2327@wisc.edu szhu227@wisc.edu liu773@wisc.edu, ychua7@wisc.edu
// Lecture's Name: Deb Deppeler,
// Bugs: Unknown
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////

package application;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Object class of QuestionNode presenting each question.
 * JavaFX elements for displaying a single Question to the user.
 *
 */
public class QuestionNode {
	private VBox node;
	private ToggleGroup choices;
	private String image;

	/**
	 * Constructor of QuestionNode by taking in a question q.
	 * @param q - the question taken.
	 */
	public QuestionNode(Question q) {
		choices = new ToggleGroup();
		node = new VBox();
		image = q.getImage();
		ArrayList<Choice> choiceList = (ArrayList<Choice>) q.getChoices();
		node.getChildren().add(setTopHBox(q));
		node.getChildren().add(setLeftVBox(choiceList));
	}

	/**
	 * Public accessor to node.
	 * @return VBox 
	 */
	public VBox getNode() {
		return node;
	}

	/**
	 * Public accessor to choices.
	 * @return ToggleGroup of choices
	 */
	public ToggleGroup getChoices() {
		return choices;
	}

	/**
	 * Private helper method to set the choiceList of the question.
	 * @param choiceList List of choice of question
	 * @return - the question node 
	 */
	private VBox setLeftVBox(ArrayList<Choice> choiceList) {
		VBox leftVBox = new VBox();
		int choiceNum = choiceList.size();
		RadioButton[] btList = new RadioButton[choiceNum];
		for (int i = 0; i < choiceNum; i++) {
			btList[i] = new RadioButton(choiceList.get(i).getChoice());
			btList[i].setToggleGroup(choices);
		}
		// go through the choice list
		for (int i = 0; i < choiceNum; i++) {
			leftVBox.getChildren().addAll(btList[i]);
		}

		leftVBox.setPadding(new Insets(5, 5, 5, 5));
		leftVBox.setSpacing(5);
		return leftVBox;
	}

	/**
	 * Private helper method to set the top box be the question content.
	 * @param q Question
	 * @return the hbox for displaying question.
	 */
	private HBox setTopHBox(Question q) {
		HBox contentBox = new HBox();
		Label label = new Label(q.getContent());
		label.setWrapText(true);
		contentBox.getChildren().add(label);
		contentBox.setPadding(new Insets(5, 5, 5, 5));
		return contentBox;
	}

	/**
	 * Public method to show the image.
	 * @return file path of image
	 */
	public String getImage() {
		return image;
	}

}
