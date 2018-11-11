package com.debenedetti.juaneugenio.fitv1.Model.POJO;


import java.util.ArrayList;

public class Exercise extends ExerciseLibrary {


    private int idWorkout;


    public Exercise(){
        super();
        idWorkout = -1;
    }

    public Exercise(ExerciseLibrary exerciseLibrary, int idWorkout){
        this.duplicate(exerciseLibrary);
        this.repetition = exerciseLibrary.getRepetition();
        this.library = exerciseLibrary.getLibrary();
        this.idWorkout = idWorkout;

    }


    public int getIdWorkout() {
        return idWorkout;
    }

    public void setIdWorkout(int idWorkout) {
        this.idWorkout = idWorkout;
    }


}
