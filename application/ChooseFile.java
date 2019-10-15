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
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This the public method which choose the correct file
 */
public class ChooseFile {

	private String filePath = null;
	private List<String> imgFileExtension;
	private Stage fileStage;
	private Stage stage;
	private Main main;
	private FileChooser fileChooser = new FileChooser();
	private AddQuestion addQues;
	private Label lbFile, lbUrl;
	private boolean json = false;

	/**
	 * This is the public constructor which initialize the private elements
	 */
	public ChooseFile() {
		setImgFileList();
	}

	/**
	 * This is the private helper method which set the image file list
	 */
	private void setImgFileList() {
		imgFileExtension = new ArrayList<String>();
		imgFileExtension.add("*.BMP");
		imgFileExtension.add("*.JPG");
		imgFileExtension.add("*.JPEG");
		imgFileExtension.add("*.PNG");
		imgFileExtension.add("*.GIF");
	}

	/**
	 * This is the public method which show the path to the user
	 */
	public void showPath() {
		System.out.println(filePath);
	}

	/**
	 * This is the public method which get the path
	 * 
	 * @return the path of the file
	 */
	public String getPath() {
		return filePath;
	}

	/**
	 * This is the public method which choose the file from the user computer.
	 * 
	 * @param stage Last stage
	 * @param addQues AddQuestion class
	 */
	public void chooseFile(Stage stage, AddQuestion addQues) {
		this.stage = stage;
		fileStage = new Stage();
		this.addQues = addQues;
		fileChooser.setTitle("Select Image File");
		fileChooser.getExtensionFilters()
		.add(new FileChooser.ExtensionFilter("Image File", imgFileExtension));

		chooseFile();
	}

	/**
	 * This is the public method which choose the file from the user computer
	 * 
	 * @param stage Last stage 
	 * @param main Main class of the program
	 */
	public void chooseFile(Stage stage, Main main) {
		this.stage = stage;
		fileStage = new Stage();
		this.main = main;

		fileChooser.setTitle("Select JSON File");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON File", "*.json"));

		json = true;
		chooseFile();
	}

	/**
	 * This is the private helper method which choose the file from the user computer
	 */
	private void chooseFile() {

		BorderPane pane = new BorderPane();

		lbFile = new Label("File: ");
		lbUrl = new Label("null");
		lbUrl.setMaxWidth(250);

		FlowPane flowLb = new FlowPane();
		flowLb.getChildren().addAll(lbFile, lbUrl);
		flowLb.setPadding(new Insets(5, 5, 5, 5));
		flowLb.setHgap(5);

		Button btBrowse = new Button("Browse File");
		Button btSelect = new Button("Select");

		FlowPane flowButton = new FlowPane();
		flowButton.getChildren().addAll(btBrowse, btSelect);
		flowButton.setHgap(5);
		flowButton.setAlignment(Pos.CENTER);
		flowButton.setPadding(new Insets(5, 5, 5, 5));

		btBrowse.setOnAction(e -> {
			File file = fileChooser.showOpenDialog(stage);
			if (file != null) {
				printLog(file);
			}
		});

		btSelect.setOnAction(e -> returnFile());

		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(flowLb, flowButton);
		vBox.setPadding(new Insets(5, 5, 5, 5));
		vBox.setSpacing(5);

		pane.setCenter(vBox);
		Scene scene = new Scene(pane, 400, 200);

		fileStage.setTitle("Choose File");
		fileStage.setScene(scene);
		fileStage.setMinHeight(300);
		fileStage.setMinWidth(500);
		fileStage.initModality(Modality.APPLICATION_MODAL);
		fileStage.show();
	}

	/**
	 * this is the private helper method which return the file
	 */
	private void returnFile() {
		if (filePath == null)
			new ErrorScreen().start(stage, "No file is chosen");
		else {
			if (!json)
				addQues.setImageFile("file:///" + filePath);
			else
				main.setLoadFile(filePath);
			fileStage.close();
		}
	}

	/**
	 * this is the private helper method which print the log
	 * 
	 * @param file File that have been selected
	 */
	private void printLog(File file) {
		if (file == null)
			lbUrl.setText("null");
		filePath = file.getAbsolutePath();
		lbUrl.setText(filePath);

	}

}
