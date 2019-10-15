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

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Object class of file chooser to help the program to select files.
 */
public class ChooseDir {

	private DirectoryChooser dirChooser = new DirectoryChooser();
	private Stage dirStage;
	private Label lbTitle, lbDir, lbName, lbUrl;
	private TextField tfName;
	private Button btBrowse, btConfirm;
	private String fileName, filePath;
	private  Main main;
	private Stage stage;
	private boolean exit = false;

	/**
	 * The constructor class of ChooseDir object.
	 */
	public ChooseDir() {
		// Set the names of labels
		lbTitle = new Label("Choose Directory");
		lbDir = new Label("Dir: ");
		lbName = new Label("Name: ");
		lbUrl = new Label("null");

		tfName = new TextField();
		tfName.setMaxWidth(500);

		btBrowse = new Button("Browse");
		btConfirm = new Button("Confirm");

		btBrowse.setOnAction(event); // if separate only can this way
		btConfirm.setOnAction(add);
	}

	/**
	 * Method to choose the Directory with given stage, main, and exit. (For submit)
	 * 
	 * @param stage - stage of the chooseDir Object
	 * @param main - main class of the program
	 * @param exit - boolean of whether to exit
	 */
	public void chooseDir(Stage stage, Main main, boolean exit) {
		this.exit = exit;
		chooseDir(stage, main);
	}

	/**
	 * Method to choose the Directory with given stage and main. (For select)
	 * 
	 * @param stage - stage of the chooseDir Object.
	 * @param main - boolean of whether to exit.
	 */
	public void chooseDir(Stage stage, Main main) {
		dirStage = new Stage();
		this.main = main;
		this.stage = stage;
		// Set the gridPane for selecting the directory
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);

		gridPane.add(lbTitle, 1, 0);
		gridPane.add(lbDir, 0, 1);
		gridPane.add(lbUrl, 1, 1);
		gridPane.add(btBrowse, 2, 1);
		gridPane.add(lbName, 0, 2);
		gridPane.add(tfName, 1, 2);
		gridPane.add(btConfirm, 1, 3);

		gridPane.setPadding(new Insets(5, 5, 5, 5));
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		Scene scene = new Scene(gridPane, 700, 400);

		dirStage.setTitle("Choose Directory");
		dirStage.setScene(scene);
		dirStage.setMinHeight(500);
		dirStage.setMinWidth(800);
		// make sure that user can't operate on other screens.
		dirStage.initModality(Modality.APPLICATION_MODAL);
		dirStage.show();
	}

	/**
	 * The event handler to pop out the event of selecting directory.
	 */
	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			File dir = dirChooser.showDialog(dirStage);
			if (dir != null) {
				filePath = dir.getAbsolutePath();
				lbUrl.setText(filePath);
			} else {
				lbUrl.setText(null);
			}
		}
	};

	/**
	 * The event handler to select the file and show the directory in the dialogue.
	 */
	EventHandler<ActionEvent> add = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			fileName = tfName.getText();
			System.out.println("FileName = " + fileName);
			if (fileName.equals("") || filePath == null) {
				new ErrorScreen().start(stage, "Please fill in the blank");
			} else {
				String system = System.getProperty("os.name").toLowerCase();
				String fullPath;
				if (system.contains("win"))
					fullPath = filePath + "\\" + fileName + ".json";
				else
					fullPath = filePath + "/" + fileName + ".json";
				try {
					main.setSaveFile(fullPath);
					dirStage.close();
					if (exit)
						new ExitScreen().start(stage);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	};
}
