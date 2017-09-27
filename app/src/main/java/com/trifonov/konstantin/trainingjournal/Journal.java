package com.trifonov.konstantin.trainingjournal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Journal {
    private static final Journal ourInstance = new Journal();
    private LinkedList<Entry> entries = new LinkedList<>();
    private List<Observer> observers = new ArrayList<>();
    private Calendar currentDate = Calendar.getInstance();

    static Journal getInstance() {
        return ourInstance;
    }

    private Journal() {
    }

    void write(LinkedList<Entry> entries) {
        this.entries = entries;
    }

    void setCurrentDate(Calendar calendar) {
        this.currentDate = calendar;
    }

    Calendar getCurrentDate() {
        return this.currentDate;
    }
    Entry getEntry() {
        Iterator<Entry> iterator = this.entries.descendingIterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (this.isEqual(entry.getDate(), this.currentDate)) {
                return entry;
            }
        }
        return new Entry();
    }

    void add(Entry entry) {
        this.entries.add(entry);
    }

    List<Entry> getAll() {
        return this.entries;
    }

    void register(Observer observer) {
        this.observers.add(observer);
    }

    void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update();
        }
    }

    private boolean isEqual(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }
}
