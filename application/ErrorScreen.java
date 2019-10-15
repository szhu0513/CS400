/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: Quiz Generator
// Files: AddQuestion.java, Choice.java, ChooseDir.java, ChooseFile.java, Confirm.java,
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
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * The JavaFx screen to display error messages.
 *
 */
public class ErrorScreen {

	/**
	 * Public method to launch the error dialogue screen.
	 * 
	 * @param primaryStage Main stage of the program
	 * @param errorMsg error message that needed to be printed
	 */
	public void start(Stage primaryStage, String errorMsg) {
		Button btOk = new Button("Ok");
		Text text = new Text(errorMsg);
		text.setTextAlignment(TextAlignment.CENTER);
		BorderPane border = new BorderPane();

		border.setCenter(text);
		border.setBottom(btOk);
		BorderPane.setAlignment(text, Pos.CENTER);
		BorderPane.setAlignment(btOk, Pos.CENTER);
		border.setPadding(new Insets(10, 10, 10, 10));
		// Set the size of the screen
		Scene scene = new Scene(border, 300, 100);
		// Set the stage of event.
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setMinHeight(200);
		stage.setMinWidth(400);
		stage.setTitle("Warning");
		stage.show();
		stage.setAlwaysOnTop(true);
		btOk.setOnAction(event -> stage.close());
	}
}
