package cls0097.auburn.edu.studybuddy.JSONObjects;

public class Course {
    private int id;
    private String name;
    private String course_code;
    private String workflow_state;
    private String start_at;
    private String end_at;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse_code() {
        return course_code;
    }

    //the current state of the course one of 'unpublished', 'available', 'completed', or 'deleted'
    public String getWorkflow_state() {
        return workflow_state;
    }

    public String getStart_at() {
        return start_at;
    }

    public String getEnd_at() {
        return end_at;
    }
}
