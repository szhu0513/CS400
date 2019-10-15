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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main application class responsible for creation of the question database and displaying the
 * graphical user interface.
 *
 */
public class Main extends Application {
	private QuestionDatabase questionDB = new QuestionDatabase();
	// for display Question used

	private int totalNumQuestion = 0;

	private File file;

	Label lbTitle, lbQuesNumTitle, lbQuesNum, lbRequestNum;
	Button btLoad, btAdd, btSave, btGenerate;
	ListView<String> topicList;
	TextField tfQuesNum;
	Spinner<Integer> numSpinner;
	HBox buttonBox;
	VBox vbox;
	BorderPane root;
	GridPane gPane;
	Stage stage;
	ObservableList<String> topicName;
	List<String> topicNameList = new ArrayList<>();

	/**
	 * Public method to launch the primary GUI screen.
	 */
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		Scene scene = new Scene(root, 500, 400);
		topicName = FXCollections.observableArrayList(topicNameList);
		setupInterface();

		root.setPadding(new Insets(10, 10, 10, 10));

		root.setTop(lbTitle);
		root.setCenter(topicList);
		root.setBottom(vbox);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Quiz Generator");
		primaryStage.setMinHeight(400);
		primaryStage.setMinWidth(500);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * Private method to help set up look of Primary GUI.
	 */
	private void setupInterface() {
		lbQuesNumTitle = new Label("TotalNumber of Questions: ");
		lbRequestNum = new Label("Question in the quiz: ");
		lbQuesNum = new Label(Integer.toString(totalNumQuestion));

		gPane = new GridPane();
		gPane.add(lbQuesNumTitle, 0, 0);
		gPane.add(lbRequestNum, 0, 1);
		gPane.add(lbQuesNum, 1, 0);

		setSpinner();

		vbox = new VBox();
		vbox.setSpacing(5);

		setButtonBox();

		vbox.getChildren().addAll(gPane, buttonBox);

		lbTitle = new Label("Choose Topic");

		topicList = new ListView<String>();
		topicList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		topicList.setMinHeight(200);
		topicList.setItems(topicName);

	}

	/**
	 * Private Helper method to set the spinner of the total question number.
	 */
	private void setSpinner() {
		int initial = 0;
		if (totalNumQuestion > 0)
			initial = 1;
		numSpinner = new Spinner<Integer>(0, totalNumQuestion, initial);
		numSpinner.setEditable(true);
		numSpinner.setMaxWidth(150);
		gPane.add(numSpinner, 1, 1);
	}

	/**
	 * Private Helper method to set the hbox of buttons on the primary GUI.
	 */
	private void setButtonBox() {
		buttonBox = new HBox();

		btLoad = new Button("Load Data");
		btAdd = new Button("Add Question");
		btSave = new Button("Save to File");
		btGenerate = new Button("Generate Quiz");

		buttonBox.getChildren().addAll(btLoad, btAdd, btSave, btGenerate);
		buttonBox.setPadding(new Insets(5, 5, 5, 5));
		buttonBox.setSpacing(5);

		btLoad.setOnAction(e -> new ChooseFile().chooseFile(stage, this));
		btAdd.setOnAction(e -> displayAddQuestionForm());
		btSave.setOnAction(e -> new ChooseDir().chooseDir(stage, this));
		btGenerate.setOnAction(e -> displayQuestion());
	}

	/**
	 * Public method to load the file when press the button and call the method.
	 * 
	 * @param filePath - the path of the file needs to load.
	 */
	public void setLoadFile(String filePath) {
		this.file = new File(filePath);
		questionDB.loadQuestionsFromJSON(file);
		updateList();
	}

	/**
	 * Public method to save the file when press the button and call the method.
	 * 
	 * @param filePath
	 */
	public void setSaveFile(String filePath) {
		this.file = new File(filePath);
		questionDB.saveQuestionsToJSON(file);
	}

	/**
	 * Private method to display the add question interface when press the button and call the method.
	 */
	private void displayAddQuestionForm() {
		new AddQuestion().start(stage, this);
	}

	/**
	 * Public method to add Question to data base when press the button and call the method.
	 * 
	 * @param topic
	 * @param question
	 */
	public void addQuestionToDB(String topic, Question question) {
		questionDB.addQuestion(topic, question);
		updateList();
	}

	/**
	 * Public method to update the list of spinner and topic list.
	 */
	public void updateList() {
		totalNumQuestion = questionDB.getNumQuestion();
		lbQuesNum.setText(Integer.toString(totalNumQuestion));
		setSpinner();
		topicName = questionDB.getTopics();
		topicList.setItems(topicName);
	}


	/**
	 * Private helper method to display the question when press the button and call the method.
	 * Throw error is the any operation goes wrong.
	 */

	private void displayQuestion() {
		ObservableList<String> topics = topicList.getSelectionModel().getSelectedItems();
		try {
			if (topics.isEmpty()) {
				throw new NumberFormatException();
			}
			ArrayList<Question> questionList = new ArrayList<>();
			// Add the topic to the topic list without duplicate and in alphabetical order.
			for (String topic : topics) {
				ArrayList<String> metaDataList = new ArrayList<String>();
				ArrayList<String> questionContent = new ArrayList<String>();
				for (Question q : questionDB.getQuestions(topic)) {
					boolean notDuplicate =
							metaDataList.contains(q.getMetaData()) && questionContent.contains(q.getContent());
					if (!notDuplicate) {
						questionList.add(q);
						metaDataList.add(q.getMetaData());
						questionContent.add(q.getContent());
					}
				}
			}
			int questionNum = numSpinner.getValue();
			Collections.shuffle(questionList);
			questionList =
					(ArrayList<Question>) questionList.stream().limit(questionNum).collect(Collectors.toList());
			StartQuestionScreen.resetIndex();
			new StartQuestionScreen(stage, questionList, this);
		} catch (NumberFormatException e) {
			new ErrorScreen().start(stage, "You selected no topics!");
		}
	}

	/**
	 * Public Main method to execute the program.
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
