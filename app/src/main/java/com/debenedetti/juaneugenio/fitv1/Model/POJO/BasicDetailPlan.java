package com.debenedetti.juaneugenio.fitv1.Model.POJO;

import java.io.Serializable;
import java.util.ArrayList;

public abstract  class BasicDetailPlan implements Serializable {

    protected int id;
    protected String name;
    protected String description; // a descrition of the exercise
    protected Integer totalTime; // duration of the exercise
    protected Integer totalEffortPoint; //points that represents the effort of the excersie
    protected ArrayList<Integer> typeComposition; //choosing between the differents
    protected Integer totalActivityTime;
    protected Integer totalRestTime; //after doing this excersice how long has to wait the person to start the next one
    protected Integer totalCalories;



    public BasicDetailPlan(){
        this.id = -1;
        name = new String("No Title");
        description = new String("No description");
        totalTime=-1;
        totalEffortPoint=-1;
        totalActivityTime=-1;
        totalRestTime=-1;
        totalCalories=-1;
    }




    protected void duplicate(BasicDetailPlan basicDetailPlan){
        this.id = basicDetailPlan.getId();
        this.name = basicDetailPlan.getName();
        this.description = basicDetailPlan.getDescription();
        this.totalTime = basicDetailPlan.getTotalTime();
        this.totalEffortPoint = basicDetailPlan.getTotalEffortPoint();
        this.typeComposition = basicDetailPlan.typeComposition;
        this.totalActivityTime = basicDetailPlan.totalActivityTime;
        this.totalRestTime = basicDetailPlan.getTotalRestTime();
        this.totalCalories = basicDetailPlan.getTotalcalories();

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    public abstract Integer getTotalTime();
    public abstract Integer getTotalActivityTime();
    public abstract Integer getTotalRestTime();
    public abstract Integer getTotalEffortPoint();
    public abstract ArrayList<Integer> getTypeComposition();
    public abstract Integer getTotalcalories();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicDetailPlan that = (BasicDetailPlan) o;
        return id == that.id;
    }

}
