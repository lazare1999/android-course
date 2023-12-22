package ge.msda.myapplication.api.models;

public class PostUserResponseModel {

    private Long id;
    private String name;
    private String job;
    private String createdAt;


    public PostUserResponseModel() {
    }

    public PostUserResponseModel(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public PostUserResponseModel(Long id, String name, String job, String createdAt) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

}
