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

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * 
 * The Object class for the program to exit quiz and closing the program.
 */
public class ExitQuizGenerator {

	private HBox buttonBox;
	// Upon exiting the program, allows the user to save the question databse to a .json file.
	// Upon exiting the program, allows the user to exit without saving the question database to a
	// file.
	private Button saveToFile, exitWithoutSaving;
	private Stage exitStage, primaryStage;
	private  Main main;

	/**
	 * Public method to trigger the screen of exit.
	 * @param primaryStage Main stage of the program
	 * @param main Main class of the program
	 */
	public void start(Stage primaryStage, Main main) {
		this.main = main;
		exitStage = new Stage();
		this.primaryStage = primaryStage;
		BorderPane root = new BorderPane();
		root.setCenter(setHBox());
		//set the screen
		Scene scene = new Scene(root, 400, 100);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		exitStage.setMinHeight(200);
		exitStage.setMinWidth(500);
		exitStage.setTitle("Exit Quiz Generator");
		exitStage.setScene(scene);
		exitStage.initModality(Modality.APPLICATION_MODAL);
		exitStage.show();

	}

	/**
	 * Public method to set HBox for the buttons
	 * @return HBox contains the buttons
	 */
	private HBox setHBox() {
		buttonBox = new HBox();

		// create new button
		saveToFile = new Button("Save to File");
		exitWithoutSaving = new Button("Exit Without Saving");

		// add buttons to the HBox
		buttonBox.getChildren().addAll(saveToFile, exitWithoutSaving);
		buttonBox.setPadding(new Insets(5, 5, 5, 5));
		buttonBox.setSpacing(5);
		buttonBox.setAlignment(Pos.CENTER);

		saveToFile.setOnAction(e -> {
			// add one save question to JSON
			new ChooseDir().chooseDir(primaryStage, main, true);
			exitStage.close();
		});
		exitWithoutSaving.setOnAction(e -> {
			new ExitScreen().start(primaryStage);
			exitStage.close();
		});
		return buttonBox;
	}
}
