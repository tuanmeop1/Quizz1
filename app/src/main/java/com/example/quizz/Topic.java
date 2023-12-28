package com.example.quizz;

public class Topic {
    private String topicName;
    private String description;
    private int topicImage;

    public Topic(String topicName, String description, int topicImage) {
        this.topicName = topicName;
        this.description = description;
        this.topicImage = topicImage;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(int topicImage) {
        this.topicImage = topicImage;
    }
}
