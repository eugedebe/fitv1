package com.debenedetti.juaneugenio.fitv1.Model.POJO;

import com.debenedetti.juaneugenio.fitv1.Views.ExerciseCMDFragment;

import java.io.Serializable;
import java.util.ArrayList;

public class ExerciseLibrary extends BasicDetailPlan implements Serializable{
    Integer library;

    protected Integer repetition; // how many repetitions

    public ExerciseLibrary(){
        super();
        library = -1;

        repetition = -1;
    }


    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public void setTotalActivityTime(Integer activityTime) {
        this.totalActivityTime = activityTime;
    }

    public void setTotalCalories(Integer calories) {
        this.totalCalories = calories;
    }

    public void setTotalRestTime(Integer restTime) {
        this.totalRestTime = restTime;
    }

    public void setTotalEffortPoint(Integer totalEffortPoint) {
        this.totalEffortPoint = totalEffortPoint;
    }

    public void setTypeComposition(ArrayList<Integer> typeComposition) {
        this.typeComposition = typeComposition;
    }

    @Override
    public ArrayList<Integer> getTypeComposition() {
        return typeComposition;
    }

    @Override
    public Integer getTotalcalories() {
        return totalCalories;
    }

    @Override
    public Integer getTotalTime() {
        return totalTime;
    }

    @Override
    public Integer getTotalActivityTime() {
        return totalActivityTime;
    }

    @Override
    public Integer getTotalRestTime() {
        return totalRestTime;
    }

    @Override
    public Integer getTotalEffortPoint() {
        return totalEffortPoint;
    }

    public Integer getRepetition() {
        return repetition;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }



    public Integer getLibrary() {
        return library;
    }

    public void setLibrary(Integer library) {
        this.library = library;
    }
}
