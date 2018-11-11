package com.debenedetti.juaneugenio.fitv1.Model.DAO.DATA;

import android.widget.Toast;

import com.debenedetti.juaneugenio.fitv1.Model.POJO.Exercise;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.ExerciseLibrary;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Session;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.SessionOnEdit;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutLibrary;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutOnEdit;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TrainingRoomDataBase {

    private static TrainingRoomDataBase trainingRoomDatabase = null;
    public static ArrayList<Session> sessions;
    public static ArrayList<WorkoutLibrary> workoutsLibrary;
    public static ArrayList<Workout> workouts;
    public static ArrayList<ExerciseLibrary> exercisesLibrary;
    public static ArrayList<Exercise> exercises;
    public static int id=0;



        protected TrainingRoomDataBase() {
            sessions = generateSessionList();
            workoutsLibrary = generateWorkoutLibraryList();
            exercisesLibrary = generateExerciseLibraryList();
            workouts=generateWorkoutList();
            exercises = generateExerciseList();

            // Exists only to defeat instantiation.

        }

        public static  TrainingRoomDataBase getInstance(){
            if (trainingRoomDatabase == null){
                trainingRoomDatabase = new TrainingRoomDataBase();

            }
            return trainingRoomDatabase;
        }





        //hardcoded

    public static ArrayList<Session> getSessions() {
            return sessions;
        }
    public static ArrayList<WorkoutLibrary> getWorkoutLibrary() {
        return workoutsLibrary;
    }
    public static ArrayList<ExerciseLibrary> getExercisesLibrary() {
        return exercisesLibrary;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }


    private ArrayList<ExerciseLibrary> generateExerciseLibraryList(){
        ArrayList<ExerciseLibrary> exercisesLibrary = new ArrayList<>();
        for (int k = 0; k < 25; k++) {
            ExerciseLibrary exerciseLibrary = new ExerciseLibrary();
            exerciseLibrary.setId(id++);
            exerciseLibrary.setName("Ex Name num: " + Integer.toString(k));
            exerciseLibrary.setDescription("Ex Descr num: " + Integer.toString(k));
            exercisesLibrary.add(exerciseLibrary);
        }
        return exercisesLibrary;

    }


    private ArrayList<Exercise> generateExerciseList(){
        ArrayList<Exercise> exercises = new ArrayList<>();
        int k = 0;
        for (ExerciseLibrary currentExerciseLibrary : exercisesLibrary) {
            k++;
            if (k < 15) {
                Exercise exercise = new Exercise(currentExerciseLibrary, workouts.get(k).getId());
                exercise.setId(id++);
                exercises.add(exercise);
            }
            else{
                Exercise exercise = new Exercise(currentExerciseLibrary, 1);
                exercises.add(exercise);
            }
        }
        return exercises;

    }


    private ArrayList<WorkoutLibrary> generateWorkoutLibraryList(){
        ArrayList<WorkoutLibrary> workoutsLibrary = new ArrayList<>();
        for (int j=1;j<16;j++) {
            WorkoutLibrary workoutLibrary = new WorkoutLibrary();
            workoutLibrary.setId(id++);
            workoutLibrary.setName("Workout Name num: " + Integer.toString(j));
            workoutLibrary.setDescription("Workout Descr num: " + Integer.toString(j));
            workoutsLibrary.add(workoutLibrary);
        }
        return workoutsLibrary;

    }

    private ArrayList<Workout> generateWorkoutList(){
        ArrayList<Workout> workouts = new ArrayList<>();
        int k = 0;
        for (WorkoutLibrary currentWorkoutLibrary : workoutsLibrary) {
            k++;
            if (k < 10) {
                Workout workout = new Workout(currentWorkoutLibrary, sessions.get(k).getId());
                workout.setId(id++);
                workouts.add(workout);
            }
            else{
                Workout workout = new Workout(currentWorkoutLibrary, 1);
                workout.setId(id++);
                workouts.add(workout);
            }
        }
        return workouts;

    }




    private ArrayList<Session> generateSessionList(){
            ArrayList<Session> sessions = new ArrayList<>();
            for (int i=0;i<10;i++){
                Session session = new Session();
                session.setId(id++);
                session.setName("Session Name num: " + Integer.toString(i));
                session.setDescription("Session Descript num: " + Integer.toString(i));
                sessions.add(session);
            }
            return sessions;
        }








        public ArrayList<Workout> findWorkouts(ArrayList<Integer> workoutIdList){
            ArrayList<Workout> workouts = new ArrayList<>();
            for(Integer currentWorkoutId : workoutIdList){
                Workout workout = getWorkout(currentWorkoutId);
                if (workout != null) {
                    workouts.add(workout);
                }
            }
            return workouts;

        }
        public ArrayList<Exercise> findExercises(ArrayList<Integer> exercisesIdList) {
            ArrayList<Exercise> exercises = new ArrayList<>();
            for (Integer currentExerciseId : exercisesIdList) {
                Exercise exercise = getExercise(currentExerciseId);
                if (exercise != null) {
                    exercises.add(exercise);
                }
            }
                return exercises;
        }

        private ArrayList<Integer> listId(int i){
            ArrayList<Integer> listId = new ArrayList<>();
            listId.add(i+1);
            listId.add(i+2);
            listId.add(i+3);
            return listId;
        }

        public Session getSession(int idSession) {

            for (Session currentSession : sessions) {
                if (currentSession.getId() == idSession) {
                    return currentSession;
                }
            }
            return null;
        }

        public Workout getWorkout(int idWorkout) {
           Workout workout = null;
           for(Workout currentWorkout:workouts){
               if (currentWorkout.getId() == idWorkout) {
                   return currentWorkout;
               }
           }
            return null;
        }


        public Exercise getExercise(int idExercise){
            for (Exercise currentExercise: exercises){
                if(currentExercise.getId()==idExercise){
                    return currentExercise;
                }
            }
            return null;
        }


   public  void saveSessionOnEdit(SessionOnEdit sessionOnEdit){
            int idSession = saveSession(sessionOnEdit.getSession());
            int idWorkout;

            for(WorkoutOnEdit currentWorkoutOnEdit : sessionOnEdit.getWorkoutsTemp()){
                removeAllWorkoutsFromSession(idSession);
                currentWorkoutOnEdit.getWorkoutTemp().setIdSession(idSession);
                idWorkout  = saveWorkout(currentWorkoutOnEdit.getWorkoutTemp());
                for(Exercise currentExercise : currentWorkoutOnEdit.getExercisesTemp()){
                    currentExercise.setIdWorkout(idWorkout);
                    saveExercise(currentExercise);
                }
            }
        }

    private void removeAllWorkoutsFromSession(int idSession) {

        ArrayList<Workout> workoutToDelete = new ArrayList<>();
        for (Workout currentWorkout : workouts) {
            if (currentWorkout.getIdSession()
                    == idSession) {
                workoutToDelete.add(currentWorkout);
            }
        }
        for (Workout currentWorkout : workoutToDelete){
            exercises.remove(currentWorkout);
        }
    }

    private void removeAllExerciseFromWorkout(int idWorkout) {
            ArrayList<Exercise> exerciseToDelete = new ArrayList<>();
        for (Exercise currentExercise : exercises) {
            if (currentExercise.getIdWorkout() == idWorkout) {
                exerciseToDelete.add(currentExercise);
            }
        }
        for (Exercise currentExercise : exerciseToDelete){
            exercises.remove(currentExercise);
        }
    }

    private void removeWorkoutFromSession(int sessionID) {
            int index;
        ArrayList<Workout> workoutsInSessionTBR = getWorkoutInSession(sessionID);
        for (Workout currentWorkout : workoutsInSessionTBR){
            removeExerciseFromWorkout(currentWorkout.getId());
            index = workouts.indexOf(currentWorkout);
            if (index !=-1){
                workouts.remove(index);
            }
        }
        }

        private void removeExerciseFromWorkout(int workoutID){
            int index;
            ArrayList<Exercise> exercisesInWorkoutTBR = getExerciseInWorkout(workoutID);
            for(Exercise currentExercise: exercisesInWorkoutTBR){
                index = exercises.indexOf(currentExercise);
                if(index!=-1){
                    exercises.remove(index);
                }
            }
        }

    public int saveSession(Session session) {
        int index = sessions.indexOf(session);
        if(index!=-1) {
            sessions.set(index,session);
            return session.getId();
        }else {
            id = id + 1;
            session.setId(id);
            sessions.add(session);
            return id;
        }
        }


    private void removeSession(Session session){
        int index = sessions.indexOf(session);
        if(index!=-1) {
            removeWorkoutFromSession(session.getId());
            sessions.remove(index);
        }
    }

    public int getSessionNewId() {
        return sessions.get(sessions.size()-1).getId()+1;

    }

    public int saveWorkout(Workout workout) {
        int index = workouts.indexOf(workout);
        if (index!=-1){ //means the session is new
            workouts.set(index,workout);
            return workout.getId();
        }else{
            id = id+1;
            workout.setId(id);
            workouts.add(workout);
            return id;
        }

    }



    public int saveExercise(Exercise exercise) {
            int index = exercises.indexOf(exercise);
            if (index!=-1) { //means the session is new
                exercises.set(index,exercise);
                return exercise.getId();
            }
            else{
                id=id+1;
                exercise.setId(id);
                exercises.add(exercise);
                return id;
            }
        }







    public ArrayList<Workout> getWorkoutInSession(int sessionID) {
            ArrayList<Workout> workoutInSession = new ArrayList<>();
            for(Workout currentWokrout: workouts){
                if(currentWokrout.getIdSession()==sessionID){
                    workoutInSession.add(currentWokrout);
                }
            }
        return workoutInSession;
        }


     public ArrayList<WorkoutLibrary> getWorkoutsFromLibrary(ArrayList<Integer> idWorkoutLibrary){
            ArrayList<WorkoutLibrary> workoutsFromLibraries = new ArrayList<>();
            for (Integer currentId : idWorkoutLibrary){
                for(WorkoutLibrary currentWorkoutLibrary :this.workoutsLibrary){
                    if(currentId == currentWorkoutLibrary.getId()){
                        workoutsFromLibraries.add(currentWorkoutLibrary);
                    }
                }
            }
            return workoutsFromLibraries;
     }

    public void saveWorkoutInSession(ArrayList<Workout> workoutInSession) {
            int index =-1;
                for (Workout currentWorkoutInSession : workoutInSession){
                    index = workouts.indexOf(currentWorkoutInSession);
                    if(index!= -1){
                        workouts.add(index,currentWorkoutInSession);
                }else {
                        workouts.add(currentWorkoutInSession);
                    }
            }
    }

    public ArrayList<Exercise> getExerciseInWorkout(int workoutID) {
        ArrayList<Exercise> exercisesInWorkout = new ArrayList<>();
        for(Exercise currentExercise: exercises){
            if(currentExercise.getIdWorkout()==workoutID){
                exercisesInWorkout.add(currentExercise);
            }
        }
        return exercisesInWorkout;
    }

    public ArrayList<ExerciseLibrary> getExerciseFromLibrary(ArrayList<Integer> exerciseIdToAdd) {
        ArrayList<ExerciseLibrary> exerciseLibraries = new ArrayList<>();
        for (Integer currentId : exerciseIdToAdd){
            for(ExerciseLibrary currentExerciseLibrary :this.exercisesLibrary){
                if(currentId == currentExerciseLibrary.getId()){
                    exerciseLibraries.add(currentExerciseLibrary);
                }
            }
        }
        return exerciseLibraries;

    }

    public void saveExerciseInWorkout(ArrayList<Exercise> exercisesInWorkout) {
        int index =-1;
        for (Exercise currentExerciseInWorkout : exercisesInWorkout){
            index = exercises.indexOf(currentExerciseInWorkout);
            if(index!= -1){
                exercises.add(index,currentExerciseInWorkout);
            }else {
                exercises.add(currentExerciseInWorkout);
            }
        }
    }

    public ArrayList<WorkoutLibrary> getWorkoutLibraryList() {
        return workoutsLibrary;
    }

    public SessionOnEdit getSessionOnEdit(Session session) {
            ArrayList<Workout> workoutsOnSession = getWorkoutInSession(session.getId());
            ArrayList<WorkoutOnEdit> workoutOnEditList = new ArrayList<>();
            for(Workout currentWorkout : workoutsOnSession){
                workoutOnEditList.add(getWorkoutOnEdit(currentWorkout));
            }
            SessionOnEdit sessionOnEdit = new SessionOnEdit(workoutOnEditList,session);
            return  sessionOnEdit;
    }

    public WorkoutOnEdit getWorkoutOnEdit(Workout workout){
            ArrayList<Exercise> exercisesOnWorkout =  getExerciseInWorkout(workout.getId());
            return new WorkoutOnEdit(exercisesOnWorkout,workout);
    }
}





