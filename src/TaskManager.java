import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int choice;

        System.out.println("\nWelcome to your To-Do List\n");

        do {
            showMenu();
            choice = getValidMenuChoice(scanner);

            switch (choice) {
                case 1:
                    viewAllTask(tasks);
                    break;
                case 2:
                    addTask(tasks, scanner);
                    break;
                case 3:
                    updateTaskStatus(tasks, scanner);
                    break;

            }

        } while (choice != 5);

    }

    public static void showMenu() {

        System.out.println("\nMain Menu");
        System.out.println("---------------------");
        System.out.println("1. View All Task");
        System.out.println("2. Add a Task");
        System.out.println("3. Mark task as complete");
        System.out.println("4. Remove Task");
        System.out.println("5. Exit");
    }

    public static int getValidMenuChoice(Scanner scanner) {
        int choice;
        System.out.print("Enter valid menu choice 1-5: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please use numerical values only");
            scanner.next();
            System.out.print("Enter valid menu choice 1-5: ");
        }

        choice = scanner.nextInt();

        while (choice < 1 || choice > 5) {
            System.out.println("Please enter a value only between 1-5");
            System.out.print("Enter valid menu choice 1-5: ");
            choice = scanner.nextInt();
        }

        scanner.nextLine();

        return choice;
    }


    public static void viewAllTask(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no task.");
        } else {
            System.out.println("\nTask | Status");
            System.out.println("---------------------------");
            for (Task task : tasks) {
                System.out.print(task.getTask() + " ");

                if (task.isCompleted()) {
                    System.out.print("| 🟢Completed\n");
                } else {
                    System.out.print("| 🔴Not Completed\n");
                }
            }
        }
    }

    public static void addTask(ArrayList<Task> tasks, Scanner scanner) {
        String task;
        System.out.print("Enter task: ");
        task = scanner.nextLine();

        while (task.isEmpty()){
            System.out.println("Task cannot be empty");
            System.out.print("Enter task: ");
            task = scanner.nextLine();
        }

        tasks.add(new Task(task));
        System.out.println("Your task: \"" + task + "\" has been added.");
    }

    private static void updateTaskStatus(ArrayList<Task> tasks, Scanner scanner) {

        int selection;
        ArrayList<Integer> indexes = new ArrayList<>();

        if (tasks.isEmpty()) {
            System.out.println("There are no task");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).isCompleted()) {
                indexes.add(i);
                System.out.println(indexes.size() + ". " + tasks.get(i).getTask());
            }
        }

        System.out.print("Enter corresponding task number that you would like to mark complete: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Enter numerical values only");
            System.out.print("Enter corresponding task number that you would like to mark complete: ");
        }

        selection = scanner.nextInt();

        while (selection < 1 || selection > indexes.size()) {
            System.out.println("Enter value within range of task");
            System.out.print("Enter corresponding task number that you would like to mark complete: ");
            selection = scanner.nextInt();
        }

        tasks.get(indexes.get(selection - 1)).setCompleted(true);
    }

}
