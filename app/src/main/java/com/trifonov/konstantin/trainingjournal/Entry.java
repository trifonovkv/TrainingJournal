package com.trifonov.konstantin.trainingjournal;

import java.util.Calendar;

class Entry {
    private Calendar date;
    private String workout;
    private String level;
    private String goal;
    private String set;
    private String note;

    Calendar getDate() {
        return date;
    }

    String getWorkout() {
        return workout;
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

    void setWorkout(String workout) {
        this.workout = workout;
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
