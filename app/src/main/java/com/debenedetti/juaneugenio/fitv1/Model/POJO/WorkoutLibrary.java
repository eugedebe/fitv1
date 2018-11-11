package com.debenedetti.juaneugenio.fitv1.Model.POJO;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutLibrary extends BasicDetailPlan implements Serializable {




    protected Category category;
    protected Integer library = -1;



    public WorkoutLibrary(){
        super();
        library = -1;
    }



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public Integer getTotalTime() {
        return null;
    }

    @Override
    public Integer getTotalActivityTime() {
        return null;
    }

    @Override
    public Integer getTotalRestTime() {
        return null;
    }

    @Override
    public Integer getTotalEffortPoint() {
        return null;
    }

    @Override
    public ArrayList<Integer> getTypeComposition() {
        return null;
    }

    @Override
    public Integer getTotalcalories() {
        return null;
    }
    public Integer getLibrary() {
        return library;
    }

    public void setLibrary(Integer library) {
        this.library = library;
    }







}
