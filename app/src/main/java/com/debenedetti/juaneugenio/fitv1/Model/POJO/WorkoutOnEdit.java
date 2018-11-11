package com.debenedetti.juaneugenio.fitv1.Model.POJO;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutOnEdit implements Serializable {

    ArrayList<Exercise> exercisesTemp;
    Workout workoutTemp;


    public Workout getWorkoutTemp() {
        return workoutTemp;
    }

    public void setWorkoutTemp(Workout workoutTemp) {
        this.workoutTemp = workoutTemp;
    }

    public ArrayList<Exercise> getExercisesTemp() {
        return exercisesTemp;
    }

    public void setExercisesTemp(ArrayList<Exercise> exercisesTemp) {
        this.exercisesTemp = exercisesTemp;
    }



    public WorkoutOnEdit(ArrayList<Exercise> exercisesTemp, Workout workoutTemp) {
        this.exercisesTemp = exercisesTemp;
        this.workoutTemp = workoutTemp;
    }
}
