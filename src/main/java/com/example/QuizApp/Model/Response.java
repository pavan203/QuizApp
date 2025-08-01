package com.example.QuizApp.Model;

public class Response {
    private int id;
    private String response;
    public Response(int id, String response) {
        this.id = id;
        this.response = response;
    }
    public int getId() {
        return id;
    }
    public String getResponse() {
        return response;
    }
}
