import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;//File/FileWriter/BufferedWriter/IOException
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class ToDoList {
	public static void main(String[] args) {
		//intro
		System.out.println("--Welcome to Your To-Do List--");
		//Scanner
		Scanner keyboard = new Scanner(System.in);
		//variables
		String newTask;
		int choice;
		String menu = ("1. Add to To-Do List\n2. Remove a task for To-Do List\n3. Display To-Do List\n4. Exit\n");
		boolean exit = false;
		String pathname = " ";
		
		
		
		//ask for file
		System.out.println("Would you like to make a new list of tasks: (Yes-1 No-2)");
		int newList = keyboard.nextInt();
		// String skipableLine = keyboard.nextLine();
		if (newList == 1) {
			System.out.println("What would you like to name the list(Please add .txt): ");
			pathname = keyboard.next();
			//write new pathname to a file of txt files so 
		}
		else if (newList == 2) {
			System.out.println("What is the name of the list (Please add .txt): ");
			pathname = keyboard.next();
		}
		else {
			System.out.println("Invalid input.");
			exit = true; //temporary 
		}
		
		File file1 = new File(pathname);
		
		//Bufferedwriter
		BufferedWriter bw = null;
		//ArrayList
		ArrayList<String> tasks = new ArrayList<String>();
		
		
		//Add existing tasks
		try{
			Scanner reader = new Scanner(file1);
			while(reader.hasNextLine()){
				tasks.add(reader.nextLine());
			}
			reader.close();
		}catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			e.printStackTrace();
			System.exit(0);
		}
			System.out.println("Tasks: " + tasks);
			

		//ToDo Options
		while(exit != true){
			System.out.print(menu);
			choice = keyboard.nextInt();
			switch(choice){
				case 1: //Add
						System.out.println("<What would you like to add?>");
						newTask = keyboard.next();
						tasks.add(newTask);

						break;
				case 2: //Remove
						for (int i = 0; i < (tasks.size()); i++){
                     		System.out.println((i+1) + ". " + (tasks.get(i)));
                  			}
                  		System.out.println("Which of your tasks would you like to remove?");
                  		int removal = keyboard.nextInt();
                  		tasks.remove((removal - 1));
                  		break;
                case 3: //Display
                		System.out.println(tasks);
                		break;
                case 4: //Exit
                		exit = true;
			}//end of switch
		}//end of while

		
		
		//writing tasks into file
		try{
			FileWriter writer = new FileWriter(file1);
			bw = new BufferedWriter(writer);
			for(int i = 0; i <tasks.size(); i++){
				bw.write(tasks.get(i));
				bw.newLine();
			}
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}finally{
			try{
				if (bw != null)
				{
					bw.flush();
					bw.close();
				}
			}catch (IOException ex){
				ex.printStackTrace();
			}
		}
		
		
		

		System.out.println("All Tasks are in the " + file1.getName() + " file.");
		keyboard.close();
	}
}