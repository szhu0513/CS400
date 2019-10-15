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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This is the JavaFX elements for displaying a window to show the display result of the quiz
 */
public class DisplayResults {

	private Label label1, label2, label3, label4, label5, label6;
	private Button button;
	private GridPane pane;
	private VBox vbox;
	private BorderPane root;

	/**
	 * This is the public method which displays the main window of the display result of the quiz
	 * 
	 * @param primaryStage Main stage 
	 * @param index Number of answered question
	 * @param correctNum Number of correct answer
	 * @param main Main class
	 */
	public void start(Stage primaryStage, int index, int correctNum, Main main) {

		Stage resultStage = new Stage();
		root = new BorderPane();

		// Button to get to Exit Quiz Generator
		button = new Button("Next");
		button.setOnAction(e -> {
			new ExitQuizGenerator().start(primaryStage, main);
			resultStage.close();
		});
		setLabel(index, correctNum);
		setGridPane();

		root.setCenter(setVBox());
		root.setLeft(null);
		//Set the scene for the score result screen.
		Scene scene = new Scene(root, 500, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		resultStage.setScene(scene);
		resultStage.setTitle("Quiz Generator");
		resultStage.initModality(Modality.APPLICATION_MODAL);
		resultStage.setMinHeight(400);
		resultStage.setMinWidth(500);
		resultStage.show();


	}

	/**
	 * This is the private helper method which set the label of each elements
	 * 
	 * @param index Number of answered question
	 * @param correctNum Number of correct answer
	 */
	private void setLabel(Integer index, Integer correctNum) {
		label1 = new Label("Number of Correct Answers: ");
		label2 = new Label(correctNum.toString());

		// Total Number of Questions Answered (Label)
		// After the quiz is completed, displays the number of questions in the quiz.
		label3 = new Label("Total Number of Questions Answered: ");
		label4 = new Label(index.toString());

		// Score (Label)
		// After the quiz is completed, displays the users score as a percentage.
		label5 = new Label("Score: ");
		double score_double = (double) correctNum / index;
		Integer score = (int) (score_double * 100);
		label6 = new Label(score.toString());
	}

	/**
	 * This is the private helper method which set the position of the elements
	 */
	private void setGridPane() {
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.add(label1, 0, 0);
		pane.add(label2, 1, 0);
		pane.add(label3, 0, 1);
		pane.add(label4, 1, 1);
		pane.add(label5, 0, 2);
		pane.add(label6, 1, 2);
	}

	/**
	 * This is the private helper method which set the layout of the set of the bottom
	 * 
	 * @return the layout of some element
	 */
	private VBox setVBox() {
		vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(5, 5, 5, 5));

		vbox.getChildren().addAll(pane, button);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}

}
