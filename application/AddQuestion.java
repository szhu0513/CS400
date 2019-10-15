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

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This is the JavaFX elements for displaying the add question button to the user and implement it.
 *
 */
public class AddQuestion {

	private HBox buttonBox;
	private VBox contentBox, leftVBox;

	private String imageFile = null;
	private TextField metaDataField;
	private TextField questionField;
	private TextField topicField;
	private List<TextField> choiceTexts;
	private List<Choice> choiceGroups;
	private ToggleGroup group;
	private Stage addStage;
	private Button btBrowse;
	private Label lbImg, lbImgUrl;
	private FlowPane flow;
	private Main main;

	/**
	 * This public method set the layout of the AddQuestion
	 * 
	 * @param primaryStage
	 * @param main
	 */
	public void start(Stage primaryStage, Main main) {
		try {
			this.main = main;
			addStage = new Stage();
			choiceGroups = new ArrayList<>();
			choiceTexts = new ArrayList<>();
			BorderPane root = new BorderPane();
			root.setTop(null);
			root.setLeft(setLeftVBox());
			root.setTop(setTopHBox());
			root.setBottom(setBottomHBox());
			root.setPadding(new Insets(20));
			Scene scene = new Scene(root, 500, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			addStage.setTitle("AddQuestionScreen");
			addStage.setScene(scene);
			addStage.setMinHeight(700);
			addStage.setMinWidth(600);
			addStage.initModality(Modality.APPLICATION_MODAL);
			addStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is the private helper method which put the bottoms together
	 * 
	 * @return a layout of set of bottoms (horizontal)
	 */
	private HBox setBottomHBox() {
		buttonBox = new HBox();
		Button btConfirm = new Button("SUBMIT");
		buttonBox.getChildren().addAll(btConfirm);
		buttonBox.setPadding(new Insets(5, 5, 5, 5));
		buttonBox.setSpacing(5);
		buttonBox.setAlignment(Pos.CENTER);
		btConfirm.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean judge = true; // judge decides if the user's question is good to go.
				String metadata = metaDataField.getText(); // question id
				String content = questionField.getText(); // question content
				String topic = topicField.getText(); // question topic
				int index = -1; // the index of the correct choice
				String answer = ""; // the content of the correct choice

				/*
				 * Read the index of the correct choice based on the RadioBox clicked.
				 */
				try {
					// get the index of the correct choice
					index = group.getToggles().indexOf(group.getSelectedToggle());
					// get the content of the correct choice
					answer = choiceTexts.get(index).getText();
				} catch (Exception e) {
					// This line is not expected, but set judge to false
					// if exception is detected.
					judge = false;
				}

				// If a choice is not entered, set judge to false.
				for (int i = 0; i < choiceTexts.size(); i++) {
					if (choiceTexts.get(i).getText().equals(""))
						judge = false;
				}

				// Before adding choices, empty choiceGroups
				choiceGroups.clear();

				/*
				 * Add choices to choceGroups. When index == i, the choice is the correct one.
				 */
				for (int i = 0; i < choiceTexts.size(); i++) {
					if (index == i)
						choiceGroups.add(new Choice(true, choiceTexts.get(i).getText()));
					else
						choiceGroups.add(new Choice(false, choiceTexts.get(i).getText()));
				}

				/*
				 * Prevents metadata, content, or topic to be empty strings.
				 */
				if (metadata.equals("") || content.equals("") || topic.equals("")) {
					judge = false;
				}

				/*
				 * Prevents metadata, content, or topic to be null. This should not happen, but we check
				 * these variables here just in case.
				 */
				if (metadata.equals(null) || content.equals(null) || topic.equals(null)) {
					judge = false;
				}

				/*
				 * Add to database If any of these elements is null, call errorScreen
				 */
				if (!judge) {
					new ErrorScreen().start(addStage, "Please check your input");
				} else {
					Question question =
							new Question(metadata, content, topic, imageFile, choiceGroups, answer);
					main.addQuestionToDB(topic, question);
					addStage.close();
				}
			}
		});

		return buttonBox;
	}

	/**
	 * This is the private helper method which put the bottoms together
	 * 
	 * @return a layout of set of bottoms (vertical)
	 */
	private VBox setTopHBox() {
		contentBox = new VBox();
		metaDataField = new TextField();
		metaDataField.setPromptText("Enter question ID:");
		questionField = new TextField();
		questionField.setPromptText("Enter question: ");
		topicField = new TextField();
		topicField.setPromptText("Enter topic:");
		lbImg = new Label("Image: ");
		lbImgUrl = new Label("null");
		btBrowse = new Button("Browse");
		btBrowse.setOnAction(e -> browseFile());
		flow = new FlowPane();
		flow.setPadding(new Insets(5, 5, 5, 5));
		flow.setHgap(10);
		flow.getChildren().addAll(lbImg, lbImgUrl, btBrowse);
		contentBox.getChildren().addAll(metaDataField, questionField, topicField, flow);
		contentBox.setSpacing(10);
		contentBox.setPadding(new Insets(5, 5, 5, 5));
		contentBox.setAlignment(Pos.CENTER);

		return contentBox;
	}

	/**
	 * This is the private method which help to choose the file
	 */
	private void browseFile() {
		new ChooseFile().chooseFile(addStage, this);
	}

	/**
	 * This is the private helper method which put all the bottoms together
	 * 
	 * @return a layout of set of bottoms (left vertical)
	 */
	private VBox setLeftVBox() {
		leftVBox = new VBox();
		group = new ToggleGroup();
		RadioButton btA = new RadioButton("A");
		btA.setToggleGroup(group);
		TextField tfA = new TextField();
		tfA.setPromptText("Enter Choice");
		RadioButton btB = new RadioButton("B");
		btB.setToggleGroup(group);
		TextField tfB = new TextField();
		tfB.setPromptText("Enter Choice");
		RadioButton btC = new RadioButton("C");
		btC.setToggleGroup(group);
		TextField tfC = new TextField();
		tfC.setPromptText("Enter Choice");
		RadioButton btD = new RadioButton("D");
		btD.setToggleGroup(group);
		TextField tfD = new TextField();
		tfD.setPromptText("Enter Choice");
		RadioButton btE = new RadioButton("E");
		btE.setToggleGroup(group);
		TextField tfE = new TextField();
		tfE.setPromptText("Enter Choice");
		choiceTexts.add(tfA);
		choiceTexts.add(tfB);
		choiceTexts.add(tfC);
		choiceTexts.add(tfD);
		choiceTexts.add(tfE);
		leftVBox.getChildren().addAll(btA, tfA, btB, tfB, btC, tfC, btD, tfD, btE, tfE);
		leftVBox.setPadding(new Insets(5, 5, 5, 5));
		leftVBox.setSpacing(5);
		return leftVBox;
	}

	/**
	 * This is the public method which set the image file
	 * 
	 * @param imageFile the source image file
	 */
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
		lbImgUrl.setText(imageFile);
		ImageView image = new ImageView(new Image(imageFile));
		image.setFitHeight(100);
		image.setPreserveRatio(true);
	}
}
