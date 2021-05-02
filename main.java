import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class main {
    
    public static void main(String[] args) {
        Planner planner = new Planner();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Computer Science Major Organizer\nSee commands.txt for acceptable commands");
        while (true){
            try{
                System.out.print(">");
                String command = scanner.nextLine();

                if (command.startsWith("adds")){
                    String parsed = command.replaceAll("adds", "").replaceAll(" ", "");
                    if (parsed.length() == 1){
                        planner.sems[Integer.parseInt(parsed)-1] = new Semester(new Course[]{});
                    }
                    else{
                        planner.addSem(new Semester(new Course[]{}));
                    }
                }

                else if(command.startsWith("addc")){
                    String[] parsed = command.replaceAll("addc", "").split(" ");
                    if (parsed.length == 4){
                        Course c = new Course(parsed[2].toUpperCase(), parsed[3].toUpperCase());
                        planner.sems[Integer.parseInt(parsed[1])-1].addCourse(c);
                    }
                    else{
                        System.out.println("bad command");
                    }
                }

                else if(command.startsWith("dropc")){
                    String parsed = command.replaceAll("dropc ", "").toUpperCase();
                    int[] location = planner.findCourse(parsed);
                    if (location != null){
                        planner.sems[location[0]].dropCourse(parsed);
                    }
                    else{
                        System.out.println("course doesn't exist");
                    }
                }

                else if(command.startsWith("drops")){
                    String parsed = command.replaceAll("drops", "").replaceAll(" ", "");
                    if (parsed.length() == 1){
                        planner.removeSem(Integer.parseInt(parsed)-1);
                    }
                }
                
                else if(command.startsWith("grade")){
                    int[] location = planner.findCourse(command.split(" ")[1].toUpperCase());
                    if (location != null){
                        planner.sems[location[0]].courses[location[1]].setGrade(command.split(" ")[2].toUpperCase());
                    }
                    else{
                        System.out.println("course doesn't exist");
                    }
                }

                else if (command.equals("assess")){
                    planner.assess();
                }

                else if (command.startsWith("viewc")){
                    int[] location = planner.findCourse(command.split(" ")[1].toUpperCase());
                    if (location != null){
                        Course c = planner.sems[location[0]].courses[location[1]];
                        System.out.printf("Course: %s\nType: %s\nGrade: %.2f\n",c.getCode(),c.getType(),c.getGrade());
                    }  
                }
                
                else if (command.startsWith("views")){
                    int sem = Integer.parseInt(command.replaceAll("views ", ""));
                    planner.sems[sem-1].print();
                }

                else if (command.equals("save")){
                    save(planner);
                }

                else if (command.equals("load")){
                    Planner x = load();
                    if (!(x == null)){
                        planner = x;
                    }
                    else{
                        System.out.println("no save file found");
                    }
                }

                else if (command.equals("quit")){
                    break;
                }

                else if (command.equals("cgpa")){
                    System.out.println(planner.getcGPA());
                }

            }
            catch(Exception e){
                System.out.println("Error");
                continue;
            }
        }

        scanner.close();
    }

    public static void save(Planner p){
        try {
            FileOutputStream fileout = new FileOutputStream("save");
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            out.writeObject(p);
            out.close();
            fileout.close();
            System.out.println("Saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Planner load(){
        try {
            FileInputStream filein = new FileInputStream("save");
            ObjectInputStream in = new ObjectInputStream(filein);
            Planner x = (Planner) in.readObject();
            in.close();
            filein.close();
            System.out.println("Loaded!");
            return x;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

}

