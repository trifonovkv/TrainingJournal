package com.trifonov.konstantin.trainingjournal;

import java.util.Calendar;

class Entry {
    private Calendar date;
    private String exercise;
    private String level;
    private String goal;
    private String set;
    private String note;

    Calendar getDate() {
        return date;
    }

    String getExercise() {
        return exercise;
    }

    String getLevel() {
        return level;
    }

    String getGoal() {
        return goal;
    }

    String getSet() {
        return set;
    }

    String getNote() {
        return note;
    }

    void setDate(Calendar date) {
        this.date = date;
    }

    void setExercise(String exercise) {
        this.exercise = exercise;
    }

    void setLevel(String level) {
        this.level = level;
    }

    void setGoal(String goal) {
        this.goal = goal;
    }

    void setSet(String set) {
        this.set = set;
    }

    void setNote(String note) {
        this.note = note;
    }
}
