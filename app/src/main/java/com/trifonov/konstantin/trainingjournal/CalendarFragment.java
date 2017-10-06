package com.trifonov.konstantin.trainingjournal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.LinkedHashMap;

public class CalendarFragment extends Fragment {
    private int lastTextColor;
    private TextView today;
    private TextView lastSelected;
    private LinkedHashMap<Integer, Calendar> days = new LinkedHashMap<>();
    OnCalendarSelectedListener listener;

    interface OnCalendarSelectedListener {
        void onDaySelected(Calendar calendar);
    }

    View.OnClickListener onDayClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            TextView textView = (TextView) view;
            setSelected(textView);
            int dayNumber = Integer.parseInt((String) textView.getText());
            listener.onDaySelected(days.get(dayNumber));
        }
    };

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (OnCalendarSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnCalendarSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        LinearLayout ll1 = root.findViewById(R.id.weekOneLayout);
        LinearLayout ll2 = root.findViewById(R.id.weekTwoLayout);
        LinkedHashMap<Integer, Calendar> weekOne = this.getAllDaysFromWeek(-1);
        LinkedHashMap<Integer, Calendar> weekTwo = this.getAllDaysFromWeek(0);
        this.populate(ll1, weekOne.keySet().toArray(new Integer[0]));
        this.populate(ll2, weekTwo.keySet().toArray(new Integer[0]));
        this.days.putAll(weekOne);
        this.days.putAll(weekTwo);
        this.today.setTextColor(Color.parseColor("#ffd15c"));
        this.saveStyle(this.today);
        this.setSelected(this.today);
        return root;
    }

    private void populate(LinearLayout ll, Integer[] days) {
        for (int i = 0; i < ll.getChildCount(); i++) {
            TextView day = (TextView) ll.getChildAt(i);
            day.setText(String.valueOf(days[i]));
            if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == days[i]) {
                this.today = day;
            }
            day.setOnClickListener(this.onDayClickListener);
        }
    }

    void setSelected(TextView day) {
        this.restore(this.lastSelected);
        this.saveStyle(day);
        day.setBackgroundResource(R.drawable.circle);
    }

    void restore(TextView day) {
        day.setTextColor(this.lastTextColor);
        day.setBackgroundResource(android.R.color.transparent);
    }

    private void saveStyle(TextView day) {
        this.lastSelected = day;
        this.lastTextColor = day.getCurrentTextColor();
    }

    private LinkedHashMap<Integer, Calendar> getAllDaysFromWeek(int shift) {
        int daysCount = 7;
        LinkedHashMap<Integer, Calendar> week = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, shift);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (int i = 0; i < daysCount; i++) {
            week.put(calendar.get(Calendar.DAY_OF_MONTH), (Calendar) calendar.clone());
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
        return week;
    }
}


