import java.util.HashMap;
import java.util.Map;

class Course implements java.io.Serializable{

    public Boolean taken = false;
    private String type;
    private Double grade;
    private String code;
    private Map<String,Double> let2gp = new HashMap<String,Double>(){{
        put("A+", 4.33);
        put("A", 4.00);
        put("A-", 3.67);
        put("B+", 3.33);
        put("B", 3.00);
        put("B-", 2.67);
        put("C+", 2.33);
        put("C", 2.00);
        put("C-", 1.67);
        put("D+", 1.33);
        put("D", 1.00);
        put("D-", 0.67);
        put("F",0.00);
        put("nil",-1.00);
    }};

    Course(String code, String type){
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public Double getGrade() {
        return grade;
    }

    public String getType() {
        return type;
    }

    public void setGrade(String i){
        if (let2gp.keySet().contains(i)){
            grade = let2gp.get(i);
        }
        else{
            System.out.println("Invalid Grade");
        }
    }

    public void enroll(){
        taken = true;
    }

}