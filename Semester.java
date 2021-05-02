public class Semester implements java.io.Serializable {
    
    public boolean active = false; //to be used by Planner class
    public Course[] courses = new Course[7];
    private int enrolled = 0;
    // course assumed to be length 7
    Semester(Course[] courses){
        int i = 0;
        while(i < courses.length){
            this.courses[i] = courses[i];
            if (!courses[i].equals(null)){
                this.courses[i].taken = true;
                enrolled++;
            }
            i++;
            if (i == courses.length){
                break;
            }
        }
    }
    
    void print(){
        for (Course c : courses){
            if (c != null){
                System.out.printf("%s\nType: %s\nGrade: %.2f\n", c.getCode(),c.getType(),c.getGrade());
                String roll;
                if (c.taken){
                    roll = "Enrolled";
                }
                else{
                    roll = "Not Enrolled";
                }
                System.out.printf("Status: %s\n\n",roll);
            }
        }
        System.out.printf("GPA: %.2f\n",this.getGPA()[0]/this.getGPA()[1]);
    }

    void dropCourse(String code){
        if (enrolled == 3){
            System.out.println("Insufficent Course Load; Course Not Removed");
        }
        else{
            for (int i = 0; i <= enrolled; i++){
                if (courses[i].getCode().equals(code)){
                    for (int j = i; j <= 5; j++){
                        courses[j] = courses[j+1];
                    }
                    courses[6] = null;
                    enrolled--;
                    System.out.println("Course Removed");
                    return;
                }
            }
            System.out.println("Course Not Found");
        }
    }

    void addCourse(Course c){
        if (enrolled == 7){
            System.out.println("Excessive Course Load; Course Not Added");
        }
        else{
            c.taken = true;
            courses[enrolled] = c;
            enrolled++;
            System.out.println("Course Enrolled") ;
        }
    }

    double[] getGPA(){
        double gpa = 0.00;
        for (int i = 0; i < enrolled; i++){
            if (!(courses[i].getGrade() == null)){
                gpa += courses[i].getGrade();
            }
        }
        return new double[]{gpa,enrolled/1.0};
    }

    void setCourseGrade(String code, String g){
        for (int i = 0; i < enrolled; i++){
            if (courses[i].getCode().equals(code)){
                courses[i].setGrade(g);
            }
        }
    }

    Course getCourse(int i){
        try {
            return courses[i];
        } catch (Exception e) {
            System.out.println("Illegal");
            return null;
        }
    }

}
