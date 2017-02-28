import java.lang.String;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class ServerFault {

	public ServerFault() {
		users = new HashMap<String, User>();

	}

	// Represents an entry in users.xml
	// Some fields omitted due to laziness
	class User {
		public String Id;
		public String DisplayName;
		public int answers;
		public int acceptedAnswers;
		User(){
			DisplayName = "Default_Name";
			answers = 0;
			acceptedAnswers = 0;
		}
	};

	// Represents an entry in posts.xml
	// Some fields omitted due to laziness
	class Question {
		public String Id;
		public String AcceptedAnswerId;
	};


	String parseFieldFromLine(String line, String key) {
		// We're looking for a thing that looks like:
		// [key]="[value]"
		// as part of a larger String.
		// We are given [key], and want to return [value].

		// Find the start of the pattern
		String keyPattern = key + "=\"";
		int idx = line.indexOf(keyPattern);

		// No match
		if (idx == -1) return "";

		// Find the closing quote at the end of the pattern
		int start = idx + keyPattern.length();

		int end = start;
		while (line.charAt(end) != '"') {
			end++;
		}

		// Extract [value] from the overall String and return it
		return line.substring(start, end);
	}

	// Keep track of all users
	HashMap<String, User> users;

	void readUsers(String filename) throws FileNotFoundException, IOException {
		BufferedReader b = new BufferedReader(
				new InputStreamReader(new FileInputStream(filename), Charset.forName("UTF-8")));
		String line;
		while ((line = b.readLine()) != null) {
			String id = parseFieldFromLine(line, "Id");
			String displayName = parseFieldFromLine(line, "DisplayName");

			if(checkValue(id) && checkValue(displayName))
			{	
				User u = new User();
				u.Id = id;
				u.DisplayName = displayName;
				users.put(u.Id, u);
			}
		}
		b.close();
	}


	void readPosts(String filename) throws FileNotFoundException, IOException {
		// Counter to display process
		//int counter = 0;
		// Keep track of all posts
		HashMap<String, Question> questions = new HashMap<String, Question>();

		ArrayList<String> solved = new ArrayList<String>();

		BufferedReader b = new BufferedReader(
				new InputStreamReader(new FileInputStream(filename), Charset.forName("UTF-8")));
		String line;
		BufferedWriter b2 = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("t1"), Charset.forName("UTF-8")));


		while ((line = b.readLine()) != null) {
			/*
			 * Provides update for records processed
			System.out.print(counter);
			for(int i=0;i<String.valueOf(counter).length();i++)
				System.out.print("\b");
			counter++;
			*/
	
			String Id = parseFieldFromLine(line, "Id");
			String PostTypeId = parseFieldFromLine(line, "PostTypeId");
			String OwnerUserId = parseFieldFromLine(line, "OwnerUserId");
			String AcceptedAnswerId = parseFieldFromLine(line, "AcceptedAnswerId");
			String ParentId = parseFieldFromLine(line, "ParentId");
			if(checkValue(Id)&&checkValue(PostTypeId)&&checkValue(OwnerUserId)){
				// Answers
				if(PostTypeId.equals("2")){
					User u = users.get(OwnerUserId);
					// If user is not present in the user database create default user. We can Ignore this too.
					if(u == null)
						u = new User();
					u.answers++;
					if(solved.contains(ParentId))
						continue;
					if(questions.containsKey(ParentId))
					{
						if(questions.get(ParentId).AcceptedAnswerId.equals(Id)){
							solved.add(ParentId);
							questions.remove(ParentId);
							u.acceptedAnswers++;

						}
					} else {
						// Write records without questions for round two
						b2.write(Id+","+ParentId+","+OwnerUserId+"\n");
					}
				} else if (PostTypeId.equals("1")){
					// Store all the questions
					Question p = new Question();
					p.Id = Id;
					p.AcceptedAnswerId = AcceptedAnswerId;
					questions.put(p.Id, p);

				}
			}
		}
		b.close();
		b2.close();

		BufferedReader b3 = new BufferedReader(
				new InputStreamReader(new FileInputStream("t1"), Charset.forName("UTF-8")));
		while ((line = b3.readLine()) != null) {
			String[] splitedString = line.split(",");
			if(questions.containsKey(splitedString[1]))
			{
				if(questions.get(splitedString[1]).AcceptedAnswerId.equals(splitedString[0])){
					User u = users.get(splitedString[2]);
					// If user is not present in the user database create default user. We can Ignore this too.
					if(u == null)
						u = new User();
					u.acceptedAnswers++;
				}
			}
		}
		b3.close();
		questions.clear();
		solved.clear();
		File file = new File("t1");
		file.delete();


	}

	boolean checkValue(String val){
		if(!val.equals("") && val != null)
			return true;
		return false;
	}

	User findUser(String Id) {
		User user = users.get(Id);
		if (user != null) {
			return user;
		}
		return new User();
	}

	public void run() throws FileNotFoundException, IOException {
		// Load our data
		readUsers("users-short.xml");
		
		readPosts("posts-short.xml");

		myTop10answer();
		
		myTop10AcceptedAnswer();
		
		System.out.println();
	}
	
	
	private void myTop10AcceptedAnswer() {

		// TODO Auto-generated method stub
		System.out.println("Top 10 users with the most Accepted answers:");
		
		TreeMap<Integer,ArrayList<String>> treemap = new TreeMap<Integer,ArrayList<String>>();
		int count = 0;
		int size = 0;

		for (Entry<String, User> it : users.entrySet()) {
			if (it.getValue().acceptedAnswers > count) {
				if(size >=10) {
					count = treemap.firstKey(); 

					ArrayList<String> arr = treemap.get(count);
					arr.remove(0);
					size--;
					if(arr.isEmpty())
						treemap.remove(count);
					if(treemap.containsKey(it.getValue().acceptedAnswers)) {
						ArrayList<String> arr2 = treemap.get(it.getValue().acceptedAnswers);
						arr2.add(it.getValue().DisplayName);
						size++;
					} else {
						ArrayList<String> arr3 = new ArrayList<String>();
						arr3.add(it.getValue().DisplayName);
						size++;	
						treemap.put(it.getValue().acceptedAnswers, arr3);	
					}	
				} else {
					if(treemap.containsKey(it.getValue().acceptedAnswers)) {
						ArrayList<String> arr = treemap.get(it.getValue().acceptedAnswers);
						arr.add(it.getValue().DisplayName);
						size++;
					} else {
						ArrayList<String> arr = new ArrayList<String>();
						arr.add(it.getValue().DisplayName);
						treemap.put(it.getValue().acceptedAnswers, arr);
						size++;		
					}
				}
			}
		}

		for (Entry<Integer, ArrayList<String>> t : treemap.descendingMap().entrySet()) {
			for(String name: t.getValue())
			{
				System.out.print(t.getKey());
				System.out.print('\t');
				System.out.println(name);
			}
		}
	}
	
	private void myTop10answer() {

		// TODO Auto-generated method stub
		System.out.println("Top 10 users with the most answers:");
		
		TreeMap<Integer,ArrayList<String>> treemap = new TreeMap<Integer,ArrayList<String>>();
		int count = 0;
		int size = 0;

		for (Entry<String, User> it : users.entrySet()) {
			if (it.getValue().answers > count) {
				if(size >=10) {
					count = treemap.firstKey(); 

					ArrayList<String> arr = treemap.get(count);
					arr.remove(0);
					size--;
					if(arr.isEmpty())
						treemap.remove(count);
					if(treemap.containsKey(it.getValue().answers)) {
						ArrayList<String> arr2 = treemap.get(it.getValue().answers);
						arr2.add(it.getValue().DisplayName);
						size++;
					} else {
						ArrayList<String> arr3 = new ArrayList<String>();
						arr3.add(it.getValue().DisplayName);
						size++;	
						treemap.put(it.getValue().answers, arr3);	
					}	
				} else {
					if(treemap.containsKey(it.getValue().answers)) {
						ArrayList<String> arr = treemap.get(it.getValue().answers);
						arr.add(it.getValue().DisplayName);
						size++;
					} else {
						ArrayList<String> arr = new ArrayList<String>();
						arr.add(it.getValue().DisplayName);
						treemap.put(it.getValue().answers, arr);
						size++;		
					}
				}
			}
		}

		for (Entry<Integer, ArrayList<String>> t : treemap.descendingMap().entrySet()) {
			for(String name: t.getValue())
			{
				System.out.print(t.getKey());
				System.out.print('\t');
				System.out.println(name);
			}
		}
	}


	public static void main(String[] args) throws FileNotFoundException, IOException {
		ServerFault s = new ServerFault();
		s.run();
	}

}

