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
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the class which contains all questions (of type Question) from .json files and added
 * manually by user
 *
 */
public class QuestionDatabase implements QuestionDatabaseADT {

	private Map<String, List<Question>> database;
	private List<Question> allQuestionList = new ArrayList<>();
	private List<Choice> choiceList;

	/**
	 * This is the public constructor which initialize the private variable.
	 */
	public QuestionDatabase() {
		this.database = new HashMap<String, List<Question>>();
	}

	/**
	 * This is the public method which add question manually by user to the database
	 *
	 * @param topic    the topic of the question
	 * @param question the question
	 */
	@Override
	public void addQuestion(String topic, Question question) {
		// if the database contain the topic then add the question under that topic, otherwise create a
		// new question list
		if (database.containsKey(topic))
			this.database.get(topic).add(question);
		else {
			List<Question> quesList = new ArrayList<>();
			quesList.add(question);
			this.database.put(topic, quesList);
		}
		this.allQuestionList.add(question);
	}

	/**
	 * This is the public method which return the total number of question in the database.
	 * 
	 * @return the total number of question in the database
	 */
	public int getNumQuestion() {
		return allQuestionList.size();
	}

	/**
	 * This is the public method which save question as JSON File
	 * 
	 * @param file the destination file
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void saveQuestionsToJSON(File file) {
		try {
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonQuesArr = new JSONArray();
			PrintWriter pw = new PrintWriter(file);
			Iterator<String> it = database.keySet().iterator();

			// change the question add by user to the JSON style
			while (it.hasNext()) {
				String topic = it.next();
				List<Question> questionList = database.get(topic);
				JSONArray tempArr = getJSONQuesArr(questionList);
				for (int i = 0; i < tempArr.size(); i++) {
					jsonQuesArr.add(tempArr.get(i));
				}
			}
			jsonObj.put("questionArray", jsonQuesArr);
			pw.write(jsonObj.toJSONString());
			pw.flush();
			pw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * This is the private helper method which get the JSON question array
	 * 
	 * @param questionList
	 * @return the JSON array
	 */
	@SuppressWarnings("unchecked")
	private JSONArray getJSONQuesArr(List<Question> questionList) {
		JSONArray jsonQuesArr = new JSONArray();
		for (Question question : questionList) {
			JSONObject jsonQues = new JSONObject();

			jsonQues.put("meta-data", question.getMetaData());
			jsonQues.put("questionText", question.getContent());
			jsonQues.put("topic", question.getTopic());
			jsonQues.put("image", question.getImage());

			JSONArray jsonChoiceArr = new JSONArray();
			List<Choice> choiceList = question.getChoices();

			for (int i = 0; i < choiceList.size(); i++) {
				JSONObject jsonChoice = new JSONObject();
				Choice choice = choiceList.get(i);
				if (choice.getIsCorrect())
					jsonChoice.put("iscorrect", "T");
				else
					jsonChoice.put("iscorrect", "F");
				jsonChoice.put("choice", choice.getChoice());
				jsonChoiceArr.add(jsonChoice);
			}
			jsonQues.put("choiceArray", jsonChoiceArr);
			jsonQuesArr.add(jsonQues);
		}
		return jsonQuesArr;
	}


	/**
	 * This is the public method which get the question from the database
	 * 
	 * @param topic The topic which used to find the question
	 * @return return the question list belongs to one topic
	 */
	@Override
	public List<Question> getQuestions(String topic) {
		return database.get(topic);
	}

	/**
	 * This is the public method which get the question from the JSON file
	 * 
	 * @param file the source file
	 */
	@Override
	public void loadQuestionsFromJSON(File file) {
		try {
			Object obj = new JSONParser().parse(new FileReader(file));
			JSONObject jo = (JSONObject) obj;
			JSONArray jsonQuestionArr = (JSONArray) jo.get("questionArray");
			addDatabase(jsonQuestionArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is the private helper method which add the json question array to the database
	 * 
	 * @param jsonQuestionArr the list of the questions
	 */
	private void addDatabase(JSONArray jsonQuestionArr) {
		String topic = null;
		// add the question to the database one by one
		for (int i = 0; i < jsonQuestionArr.size(); i++) {
			JSONObject jsonQuestion = (JSONObject) jsonQuestionArr.get(i);
			String metaData = (String) jsonQuestion.get("meta-data");
			String questionText = (String) jsonQuestion.get("questionText");
			topic = (String) jsonQuestion.get("topic");
			topic = topic.toLowerCase();
			// check if the database contains the topic, if not create a new question list
			if (!database.containsKey(topic))
				database.put(topic, new ArrayList<>());
			String image = (String) jsonQuestion.get("image");
			JSONArray jsonChoiceArray = (JSONArray) jsonQuestion.get("choiceArray");

			String answerContent = getAnswerContent(jsonChoiceArray);

			Question question =
					new Question(metaData, questionText, topic, image, choiceList, answerContent);
			System.out.println(question.toString());
			allQuestionList.add(question);
			this.database.get(topic).add(question);
		}
	}

	/**
	 * This is the private helper method which get the answer content of the question
	 * 
	 * @param jsonChoiceArray
	 * @return the answer content and which choice is correct
	 */
	private String getAnswerContent(JSONArray jsonChoiceArray) {
		choiceList = new ArrayList<>();
		boolean ans;
		String answerContent = "";
		for (int j = 0; j < jsonChoiceArray.size(); j++) {
			JSONObject jsonChoice = (JSONObject) jsonChoiceArray.get(j);
			String isCorrect = (String) jsonChoice.get("iscorrect");
			String choiceContent = (String) jsonChoice.get("choice");

			if (isCorrect.equals("T")) {
				ans = true;
				answerContent = choiceContent;
			} else
				ans = false;

			Choice choice = new Choice(ans, choiceContent);
			choiceList.add(choice);
		}
		return answerContent;
	}

	/**
	 * This is the public method which get the list of the topic from the database
	 * 
	 * @return the list of the topic
	 */
	@Override
	public ObservableList<String> getTopics() {
		List<String> topicList = new ArrayList<>();
		Set<String> topicSets = database.keySet();
		Iterator<String> it = topicSets.iterator();
		while (it.hasNext())
			topicList.add(it.next().toLowerCase());

		Collections.sort(topicList);
		ObservableList<String> topicObsList = FXCollections.observableArrayList(topicList);
		return topicObsList;
	}


}
