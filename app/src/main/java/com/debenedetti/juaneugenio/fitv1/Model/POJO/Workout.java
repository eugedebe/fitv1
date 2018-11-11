package com.debenedetti.juaneugenio.fitv1.Model.POJO;

import java.io.Serializable;
import java.util.ArrayList;

public class Workout extends WorkoutLibrary implements Serializable {

    private int idSession;

    public Workout(){
        super();
        idSession = -1;
    }

    public Workout(WorkoutLibrary workoutLibrary, int idSession){
        this.duplicate(workoutLibrary);
        this.category = workoutLibrary.getCategory();
        this.library = workoutLibrary.getLibrary();
        this.idSession = idSession;

    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }



}
