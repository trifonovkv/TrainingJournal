package com.trifonov.konstantin.trainingjournal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EntryParser {
    private CalendarParser calendarParser = new CalendarParser();

    public Entry getEntry(String[] s) {
        Entry entry = new Entry();
        entry.setDate(this.calendarParser.getCalendar(s[0]));
        entry.setWorkout(s[1]);
        entry.setLevel(s[2]);
        entry.setGoal(s[3]);
        entry.setSet(s[4]);
        entry.setNote(s[5]);
        return entry;
    }

    public String[] getStrings(Entry e) {
        String[] strings = new String[6];
        strings[0] = this.calendarParser.getString(e.getDate());
        strings[1] = e.getWorkout();
        strings[2] = e.getLevel();
        strings[3] = e.getGoal();
        strings[4] = e.getSet();
        strings[5] = e.getNote();
        return strings;
    }

    public LinkedList<Entry> getEntries(List<String[]> s) {
        LinkedList<Entry> entries = new LinkedList<>();
        for (String[] strings : s) {
            entries.add(this.getEntry(strings));
        }
        return entries;
    }

    public List<String[]> getListStrings(LinkedList<Entry> e) {
        List<String[]> strings = new ArrayList<>();
        for (Entry entry : e) {
            strings.add(this.getStrings(entry));
        }
        return strings;
    }

    public String getString(Entry e) {
        String[] strings = this.getStrings(e);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            stringBuilder.append(",");
            stringBuilder.append(strings[i]);
        }
        return stringBuilder.toString();
    }
}
