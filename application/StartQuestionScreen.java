/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: Quiz Generator(M2 GUI)
// Files: AddQuestion.java, Choice.java, DisplayResult.java,
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

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * The JavaFx element to display the start question screen.
 */
public class StartQuestionScreen {

	private HBox buttonBox;
	private Stage stage, quesStage;
	private ArrayList<Question> questionList;
	private static int index = 0;
	private static int correctNum = 0;
	private QuestionNode qN;
	private Main main;

	/**
	 * Public method 
	 * @param primaryStage
	 * @param questionList
	 * @param main
	 */
	public StartQuestionScreen(Stage primaryStage, ArrayList<Question> questionList, Main main) {
		this.main = main;
		start(primaryStage, questionList);
	}

	public void start(Stage primaryStage, ArrayList<Question> questionList) {
		try {
			this.questionList = questionList;
			qN = setQuestionNode(questionList.get(index));

			stage = primaryStage;
			quesStage = new Stage();

			BorderPane root = new BorderPane();
			root.setBottom(setLeftVBox());
			root.setLeft(qN.getNode());
			if (qN.getImage() != null) {
				root.setRight(setRightBox(qN.getImage()));
			}
			root.setPadding(new Insets(20));
			Scene scene = new Scene(root, 400, 300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			quesStage.setTitle("StartQuestionScreen" + "(" + (index + 1) + "/" + questionList.size() + ")");
			quesStage.setScene(scene);
			quesStage.setMinHeight(400);
			quesStage.setMinWidth(500);
			quesStage.initModality(Modality.APPLICATION_MODAL);
			quesStage.show();
		} catch (IndexOutOfBoundsException e) {
			new ErrorScreen().start(stage, "You selected no questions!");
		}
	}

	/**
	 * Private method to set the question.
	 * @param q Question that need to be added
	 * @return QuestionNode
	 */
	private QuestionNode setQuestionNode(Question q) {
		return new QuestionNode(q);
	}

	/**
	 * Public method to set the questionList.
	 * @param questionList
	 */
	public void setQuestionList(ArrayList<Question> questionList) {
		this.questionList = questionList;
	}

	/**
	 * Private method to set the boxes of the screen.
	 * Display results and scores, etc.
	 * @return HBox for the button
	 */
	private HBox setBottomHBox() {
		buttonBox = new HBox();
		Button btConfirm = new Button("SUBMIT");
		Button btFinish = new Button("FINISH");
		Button btNext = new Button("NEXT");
		Text result = new Text("Result: ");
		btNext.setDisable(true);
		buttonBox.getChildren().addAll(btConfirm, btNext, btFinish, result);
		buttonBox.setPadding(new Insets(5, 5, 5, 5));
		buttonBox.setSpacing(5);
		btConfirm.setOnAction(e -> {
			ToggleGroup choices = qN.getChoices();
			try {
				int chosenIndex = choices.getToggles().indexOf((choices.getSelectedToggle()));
				if (questionList.get(index).getChoices().get(chosenIndex).getIsCorrect()) {
					correctNum++;
					result.setText("Result: Correct!");
				} else {
					result.setText("Result: Incorrect");
				}
				if (index < questionList.size() - 1)
					btNext.setDisable(false);
				btConfirm.setDisable(true);
			} catch (ArrayIndexOutOfBoundsException error) {
				new ErrorScreen().start(stage, "Please make a choice!");
			}
		});
		btFinish.setOnAction(e -> {
			new DisplayResults().start(stage, index + 1, correctNum, main);
			quesStage.close();
		});
		btNext.setOnAction(e -> {
			index++;
			quesStage.close();
			start(stage, questionList);
		});
		return buttonBox;
	}

	/**
	 * Private helper method to set the image display on the screen.
	 * @param image File path of image
	 * @return HBox for image
	 */
	private HBox setRightBox(String image) {
		HBox imageBox = new HBox();
		try {
			ImageView imageView = new ImageView(new Image(image));
			imageView.setFitHeight(100);
			imageView.setPreserveRatio(true);
			imageBox.getChildren().add(imageView);
			return imageBox;
		} catch (IllegalArgumentException e) {
			return imageBox;
		}
	}

	/**
	 * Private helper method to set the left box to display correct and incorrect nums.
	 * @return VBox for the label of result numbers
	 */
	private VBox setLeftVBox() {
		Text correctQuestions = new Text("Correct: " + correctNum);
		Text incorrectQuestions = new Text("Incorrect: " + (index - correctNum));
		VBox leftVBox = new VBox();
		leftVBox.getChildren().add(correctQuestions);
		leftVBox.getChildren().add(incorrectQuestions);
		leftVBox.getChildren().add(setBottomHBox());
		return leftVBox;
	}

	/**
	 * Public method to initiate the index
	 */
	public static void resetIndex() {
		index = 0;
		correctNum = 0;
	}
}