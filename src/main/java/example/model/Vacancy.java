package example.model;

public class Vacancy {
    public int id;
    public String title;
    public String company;

    public Vacancy() {}

    public Vacancy(int id, String title, String company) {
        this.id = id;
        this.title = title;
        this.company = company;
    }
}