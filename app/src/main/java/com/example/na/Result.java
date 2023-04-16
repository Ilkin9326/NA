package com.example.na;


import androidx.annotation.NonNull;

public class Result {
    @NonNull
    private int remainDay;
    @NonNull
    private int durationDay;
    @NonNull
    private int percentage;

    public Result() {
    }

    public Result(int remainDay, int durationDay, int percentage) {
        this.remainDay = remainDay;
        this.durationDay = durationDay;
        this.percentage = percentage;
    }

    public int getRemainDay() {
        return remainDay;
    }

    public void setRemainDay(int remainDay) {
        this.remainDay = remainDay;
    }

    public int getDurationDay() {
        return durationDay;
    }

    public void setDurationDay(int durationDay) {
        this.durationDay = durationDay;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
