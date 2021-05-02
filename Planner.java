import java.util.HashMap;
import java.util.Map;

public class Planner implements java.io.Serializable{
    
    public Semester[] sems = new Semester[8];

    public Planner(){};

    public void addSem(Semester s){
        for (int i = 0; i <= 7; i++){
            if (sems[i]==null){
                s.active = true;;
                sems[i] = s;
                return;
            }
        }
        System.out.println("Semester Capacity Reached");
    }

    public void removeSem(int i){
        if (i > 8 || i < 1){
            System.out.println("Improper Bound Given");
        }
        else{
            sems[i] = null;
        }
    }

    public double getcGPA(){
        double cgpa = 0.0;
        double enrolled = 0.0;
        for (int i = 0; i <= 7; i++){
            if (!(sems[i]==null)){
                cgpa += sems[i].getGPA()[0];
                enrolled += sems[i].getGPA()[1];
                System.out.printf("");
            }
        }
        try {
            return cgpa/enrolled;
        } catch (Exception e) {
            System.out.println("You weren't even enrolled in any courses!");
            return -1.0;
        }
    }

    public int[] findCourse(String code){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 7; j++){
                if (sems[i] != null){
                    Course c = sems[i].getCourse(j);
                    if (c != null){
                        if (c.getCode().equals(code)){
                            return new int[]{i,j};
                        }
                    }
                }
            }
        }
        return null;
    }

    public void assess(){
        Map<String,Integer[]> reqs = new HashMap<String,Integer[]>(){{
            put("LL",new Integer[]{3}); //Lower-Liberal; 3 required
            put("UL",new Integer[]{3}); //Upper-Liberal; 3 required
            put("REQ",new Integer[]{21}); //Required Courses; 21 required 
            put("OE",new Integer[]{4}); //Open Elective; 4 required
            put("PR-CS",new Integer[]{5,7}); //PR-CS group: 5-7 required
            put("PR-ESB",new Integer[]{1,3}); //PR-Eng/Sc/Bus group: 1-3 required
            put("PR-MTH",new Integer[]{1,3}); //PR-Math group: 1-3 required
        }};
        Map<String,Integer> have = new HashMap<String,Integer>(){{
            put("LL",0); //Lower-Liberal; 3 required
            put("UL",0); //Upper-Liberal; 3 required
            put("REQ",0); //Required Courses; 21 required 
            put("OE",0); //Open Elective; 4 required
            put("PR-CS",0); //PR-CS group: 5-7 required
            put("PR-ESB",0); //PR-Eng/Sc/Bus group: 1-3 required
            put("PR-MTH",0); //PR-Math group: 1-3 required
        }};
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 7; j++){
                if (! (sems[i]==null)  ){
                    Course c = sems[i].getCourse(j);
                    if (!(c == null)){
                        have.put(c.getType(),have.get(c.getType())+1);
                    } 
                }
            }
        }
        for (String key : reqs.keySet()){
            Integer[] limits = reqs.get(key);
            int upper,lower;
            if (limits.length == 1){
                upper = limits[0];
                lower = limits[0];
            }
            else{
                upper = limits[1];
                lower = limits[0];
            }
            int has = have.get(key);
            System.out.printf("%s: %d taken; {%d -> %d} needed\n",key,has,lower,upper);
        }
    }

}
