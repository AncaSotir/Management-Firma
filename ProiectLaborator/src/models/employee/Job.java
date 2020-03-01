package models.employee;

public class Job {
    private Integer id;
    private String title;
    private Integer min_salary;
    private Integer max_salary;

    public Job() {
    }

    public Job(String title, Integer min_salary, Integer max_salary){
        this.title = title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(Integer min_salary) {
        this.min_salary = min_salary;
    }

    public Integer getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(Integer max_salary) {
        this.max_salary = max_salary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "title='" + title + '\'' +
                ", min_salary=" + min_salary +
                ", max_salary=" + max_salary +
                '}';
    }
}
