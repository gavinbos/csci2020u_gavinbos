package com.example.lab_05;

public class StudentRecord {
    private String id;
    private Float assignmentGrade;
    private Float midtermGrade;
    private Float finalExam;
    private Float totalGrade;
    private char letterGrade;

    public StudentRecord(String id, float assignmentGrade, float midtermGrade, float finalExam) {
        this.id = id;
        this.assignmentGrade = assignmentGrade;
        this.midtermGrade = midtermGrade;
        this.finalExam = finalExam;
        this.totalGrade = assignmentGrade*0.2f + midtermGrade*0.3f + finalExam*0.5f;
        this.letterGrade = generateLetterGrade(this.totalGrade);
    }

    public char generateLetterGrade(float x) {
        if (x < 50.0f){
            return 'F';
        }
        else if (x < 60.0f){
            return 'D';
        }
        else if (x < 70.0f){
            return 'C';
        }
        else if (x < 80.0f){
            return 'B';
        }
        else {
            return 'A';
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getAssignmentGrade() {
        return assignmentGrade;
    }

    public void setAssignmentGrade(float assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
    }

    public float getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(float midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(float finalExam) {
        this.finalExam = finalExam;
    }

    public float getTotalGrade() {
        return totalGrade;
    }

    public char getLetterGrade() {
        return generateLetterGrade(getTotalGrade());
    }
}
