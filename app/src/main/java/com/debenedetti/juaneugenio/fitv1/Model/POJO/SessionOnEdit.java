package com.debenedetti.juaneugenio.fitv1.Model.POJO;

import java.io.Serializable;
import java.util.ArrayList;


public class SessionOnEdit implements Serializable {

    ArrayList<WorkoutOnEdit> workoutsTemp;
    Session session;

    public SessionOnEdit(ArrayList<WorkoutOnEdit> workoutsTemp, Session session) {
        this.workoutsTemp = workoutsTemp;
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }


    public ArrayList<WorkoutOnEdit> getWorkoutsTemp() {
        return workoutsTemp;
    }

    public void setWorkoutsTemp(ArrayList<WorkoutOnEdit> workoutsTemp) {
        this.workoutsTemp = workoutsTemp;
    }

    public ArrayList<Workout> getWorkoutCasted(){
        ArrayList<Workout> workouts = new ArrayList<>();
        for(WorkoutOnEdit currentWorkoutOnEdit: workoutsTemp){
            workouts.add(currentWorkoutOnEdit.getWorkoutTemp());
        }
        return workouts;
    }




}
