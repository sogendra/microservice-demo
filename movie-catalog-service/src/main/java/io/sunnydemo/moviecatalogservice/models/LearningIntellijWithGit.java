package io.sunnydemo.moviecatalogservice.models;

public class LearningIntellijWithGit {
    private String userName;
    private String email;
    private String gitUrl;

    public LearningIntellijWithGit() {
    }

    public LearningIntellijWithGit(String userName, String email, String gitUrl) {
        this.userName = userName;
        this.email = email;
        this.gitUrl = gitUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    @Override
    public String toString() {
        return "LearningIntellijWithGit{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                '}';
    }
}
