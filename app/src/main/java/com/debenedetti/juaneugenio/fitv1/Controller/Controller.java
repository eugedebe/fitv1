package com.debenedetti.juaneugenio.fitv1.Controller;

import com.debenedetti.juaneugenio.fitv1.Model.DAO.DATA.TrainingRoomDataBase;
import com.debenedetti.juaneugenio.fitv1.Model.DAO.DaoFirebase.DaoFirebase;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Exercise;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.ExerciseLibrary;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Session;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.SessionOnEdit;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutLibrary;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutOnEdit;

import java.util.ArrayList;

public class Controller {
    private TrainingRoomDataBase trainingRoomDataBase;
    private static Controller controller = null;


    protected Controller() {
        trainingRoomDataBase =  TrainingRoomDataBase.getInstance();
        // Exists only to defeat instantiation.
    }


    public static  Controller getInstance(){
        if (controller == null){
            controller = new Controller();

        }
        return controller;
    }

    public void onSignedInInitialize() {

       //todo load information

    }

    public void onSingedOut(){
        DaoFirebase daoFirebase = new DaoFirebase();
        daoFirebase.onSignedOutCleanup();

    }



//hardcoded
    public ArrayList<Session> getSessionList(){
    return trainingRoomDataBase.getSessions();

    }
    public ArrayList<Workout> getWorkoutList(){
        return trainingRoomDataBase.getWorkouts();
    }

    public ArrayList<Exercise> getExerciseList(){
        return trainingRoomDataBase.getExercises();
    }

    public ArrayList<ExerciseLibrary> getExerciseLibraryList(){
        return trainingRoomDataBase.getExercisesLibrary();
    }


    public Session getSession(int idSession){
        return trainingRoomDataBase.getSession(idSession);

    }
    public Workout getWorkout(int idWorkout){
        return trainingRoomDataBase.getWorkout(idWorkout);

    }




    public void saveSessionOnEdit(SessionOnEdit sessionOnEdit){
         trainingRoomDataBase.saveSessionOnEdit(sessionOnEdit);

    }

    public int saveWorkout(Workout workout) {
        return trainingRoomDataBase.saveWorkout(workout);
    }


    public int saveExercise(Exercise exercise) {
        return trainingRoomDataBase.saveExercise(exercise);
    }

    public ArrayList<Workout> getWorkoutInSession(int sessionID) {
        return trainingRoomDataBase.getWorkoutInSession(sessionID);

    }

    public ArrayList<WorkoutLibrary> getWorkoutsFromLibrary(ArrayList<Integer> workoutIdToAdd) {
        return trainingRoomDataBase.getWorkoutsFromLibrary(workoutIdToAdd);
    }

    public void saveWorkoutiInSession(ArrayList<Workout> workoutInSession) {
        trainingRoomDataBase.saveWorkoutInSession(workoutInSession);
    }

    public ArrayList<Exercise> getExerciseInWorkout(int workoutID) {
        return trainingRoomDataBase.getExerciseInWorkout(workoutID);

    }

    public ArrayList<ExerciseLibrary> getExerciseFromLibrary(ArrayList<Integer> exerciseIdToAdd) {
        return trainingRoomDataBase.getExerciseFromLibrary(exerciseIdToAdd);
    }

    public void saveExerciseiInWorkout(ArrayList<Exercise> exercisesInWorkout) {
        trainingRoomDataBase.saveExerciseInWorkout(exercisesInWorkout);

    }

    public ArrayList<WorkoutLibrary> getWorkoutLibraryList() {
        return trainingRoomDataBase.getWorkoutLibraryList();
    }

    public SessionOnEdit getSessionOnEdit(Session session){
        return trainingRoomDataBase.getSessionOnEdit(session);
    }
    public WorkoutOnEdit getWorkoutOnEdit(Workout workout){
        return trainingRoomDataBase.getWorkoutOnEdit(workout);
    }
}
