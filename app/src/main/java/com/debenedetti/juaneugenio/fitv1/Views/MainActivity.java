package com.debenedetti.juaneugenio.fitv1.Views
        ;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;

import com.debenedetti.juaneugenio.fitv1.Controller.Controller;

import com.debenedetti.juaneugenio.fitv1.Model.POJO.Exercise;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.ExerciseLibrary;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Session;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.SessionOnEdit;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutLibrary;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutOnEdit;
import com.debenedetti.juaneugenio.fitv1.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
SessionFragment.CreateEditSession,
SessionCMDFragment.AddWorkout,
SessionCMDFragment.SaveorCancelSessionEdition,
WorkoutCMDFragment.AddExercise,
WorkoutCMDFragment.SaveorCancelWorkoutEdition,
WorkoutSelectionFragment.InterfaceWorkoutSelection,

ExerciseSelectionFragment.InterfaceExerciseSelection,
ExerciseCMDFragment.ExerciseCMDInterface{

    private Controller controller;
    private BottomNavigationView mNavigationView;
    private NavigationView humburgerNavigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    //para el botón hamburguesa
    private ActionBarDrawerToggle mToogle;
    private int idFragmentContainer;
    private SessionOnEdit sessionOnEdit=null;
    private WorkoutOnEdit workoutOnEdit;
    private int positionWorkoutonEditWIP = -1;
    private int positionExerciseonEditWIP = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);


        // just to know the hash number in the log file
        printHash();
        controller = Controller.getInstance();
        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(this);

//Hamburguer menu and drawer menu configuration
        humburgerNavigationView = findViewById(R.id.navigation_view_activitymain);
        drawerLayout = findViewById(R.id.drawer_layout_activitymain);

        humburgerNavigationView.setNavigationItemSelectedListener(this);
        mToogle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//////////////////////////

        if(AccessToken.getCurrentAccessToken()==null ){
            
            //todo sign out
             LoginManager.getInstance().logOut();
            Controller.getInstance().onSingedOut();
            goLoginScreen();

        }else
        {


            //INICIO LA ACTIVIDAD


             openSessionFragment();



        }


    }


    private void loadFragment(int idFragmentContainer, Fragment fragment) {



        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(idFragmentContainer, fragment).commit();
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
    }
    public void logOut(){
        LoginManager.getInstance().logOut();
        Controller.getInstance().onSingedOut();

        goLoginScreen();
    }
    private void goLoginScreen(){

        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void printHash() {
        try {

            PackageInfo info =
                    getPackageManager().getPackageInfo(getApplicationContext().getPackageName(),
                            PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.v("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
    }
    public void openSessionFragment(){
        ArrayList<Session> sessions = controller.getSessionList();
        SessionFragment sessionFragment = SessionFragment.newInstance(sessions);
        loadFragment(R.id.container_fragment_main_activity,sessionFragment);
    }


    //for the drawer menu and Burger button
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if (mToogle.onOptionsItemSelected((item))){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragmentACargar = null;
        //close and hide the menu
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.drawer_log_out:
                logOut();
                break;
            case R.id.boton_a_definir:
                break;
            case R.id.navigation_home:
                break;
            case R.id.navigation_plan:
                break;

        }

        return true;

    }
    // end of configuration of app bar


    ///////// implementation of CreateEditSession from SessionFragment
    @Override
    public void editSession(Session session) {
        sessionOnEdit = Controller.getInstance().getSessionOnEdit(session);
        SessionCMDFragment sessionCMDFragment = SessionCMDFragment.newInstance(sessionOnEdit);
        loadFragment(R.id.container_fragment_main_activity,sessionCMDFragment);

        }
    @Override
    public void createSession() {
        SessionCMDFragment sessionCMDFragment= new SessionCMDFragment();
        loadFragment(R.id.container_fragment_main_activity,sessionCMDFragment);

        }
        ///END OF implementation CreateEditSession



    ///// end of imlementation CreateEditWorkout



//////////end of implementaion of CreateEditExercise





    ////////IMPLEMENTATINO OF InterfaceWorkoutSelection FROM WorkoutSelectionFragment
    @Override
    public void addWorkoutsToSession(ArrayList<WorkoutLibrary> workoutSelected) {
        for(WorkoutLibrary currentWorkoutLibrary:workoutSelected){
            ArrayList<Exercise> exercisesWorkout = Controller.getInstance().getExerciseInWorkout(currentWorkoutLibrary.getId());
            Workout workout = new Workout(currentWorkoutLibrary, sessionOnEdit.getSession().getId());
            WorkoutOnEdit workoutOnEdit = new WorkoutOnEdit(exercisesWorkout, workout);
            sessionOnEdit.getWorkoutsTemp().add(workoutOnEdit);
        }
        SessionCMDFragment sessionCMDFragment = SessionCMDFragment.newInstance(sessionOnEdit);
        sessionOnEdit=null;
        loadFragment(R.id.container_fragment_main_activity,sessionCMDFragment);
    }
    //// end of impleentation of InterfaceWorkoutSelection





    /////////implementation of AddWorkout from the fragment SessionCMDFragment.addWorkout
   //

    @Override
    public void openWorkoutSelection(SessionOnEdit sessionTemp) {
        sessionOnEdit = sessionTemp;
        WorkoutSelectionFragment workoutSelectionFragment = WorkoutSelectionFragment.newInstance();
        loadFragment(R.id.container_fragment_main_activity,workoutSelectionFragment);
    }


    public void editWorkoutofSession(SessionOnEdit sessionOnEdit, int positionOfworkoutOnEdit) {
        this.sessionOnEdit = sessionOnEdit;
        positionWorkoutonEditWIP = positionOfworkoutOnEdit;

        if (positionOfworkoutOnEdit !=SessionCMDFragment.NEW_WORKOUT) {
            workoutOnEdit = sessionOnEdit.getWorkoutsTemp().get(positionOfworkoutOnEdit);
        }else{
            workoutOnEdit = new WorkoutOnEdit(new ArrayList<Exercise>(),new Workout());

        }
        WorkoutOnEdit workoutOnEditAux;
        workoutOnEditAux = new WorkoutOnEdit(workoutOnEdit.getExercisesTemp(),workoutOnEdit.getWorkoutTemp());
        WorkoutCMDFragment workoutCMDFragment = WorkoutCMDFragment.newInstance(workoutOnEditAux);
        loadFragment(R.id.container_fragment_main_activity,workoutCMDFragment);
    }

///////// end of implementation AddWorkout


    /////////implementation of AddWorkout from the fragment SessionCMDFragment.SaveorCancelSession

    @Override
    public void saveOrCancelSession(SessionOnEdit sessionOnEdit){
        if(sessionOnEdit!=null) {
            Controller.getInstance().saveSessionOnEdit(sessionOnEdit);
        }
        resetAllEditionVariables();
        openSessionFragment();
    }

    private void resetAllEditionVariables(){
        this.sessionOnEdit = null;
        this.positionExerciseonEditWIP=-1;
        this.positionWorkoutonEditWIP=-1;
        this.workoutOnEdit=null;
    }




    private Boolean EditingSessionInProgress(){
        return (sessionOnEdit!=null);
    }
    private Boolean WorkoutInEditioIsNew(){
        return (positionWorkoutonEditWIP==SessionCMDFragment.NEW_WORKOUT);
    }



    //// end of implentation of WorkoutCMDFragment


    /////////implementation of AddExercise from the fragment InterfaceExerciseSelection
    @Override
    public void openExerciseSelection(WorkoutOnEdit workoutOnEdit) {
        this.workoutOnEdit = workoutOnEdit;
        ExerciseSelectionFragment exerciseSelectionFragment = ExerciseSelectionFragment.newInstance();
        loadFragment(R.id.container_fragment_main_activity,exerciseSelectionFragment);
    }

    @Override
    public void editExerciseofWorkout(WorkoutOnEdit workoutOnEdit, int positionOfExerciseOnEdit){
        this.workoutOnEdit = workoutOnEdit;
        positionExerciseonEditWIP = positionOfExerciseOnEdit;
        Exercise exercise;
        if (positionOfExerciseOnEdit != WorkoutCMDFragment.NEW_WORKOUT) {
            exercise = workoutOnEdit.getExercisesTemp().get(positionOfExerciseOnEdit);
        }else{
            exercise = new Exercise();
        };
        ExerciseCMDFragment exerciseCMDFragment = ExerciseCMDFragment.newInstance(exercise);
        loadFragment(R.id.container_fragment_main_activity,exerciseCMDFragment);
        }


    @Override
    public void saveOrCancelWorkout(WorkoutOnEdit workoutOnEdit) {
        if(workoutOnEdit!=null) {
            if (sessionOnEdit != null) {
                if (positionWorkoutonEditWIP == -1) {
                    sessionOnEdit.getWorkoutsTemp().add(workoutOnEdit);
                } else {
                    sessionOnEdit.getWorkoutsTemp().set(positionWorkoutonEditWIP, workoutOnEdit);
                }
            }
        }

                this.workoutOnEdit = null;
                positionWorkoutonEditWIP = -1;
                SessionCMDFragment sessionCMDFragment = SessionCMDFragment.newInstance(sessionOnEdit);
                loadFragment(R.id.container_fragment_main_activity, sessionCMDFragment);

    }



        //// end of implentation of InterfaceExerciseSelection


        @Override
        public void cancelOrSaveCMDExercise (Exercise exercise){
            Workout workout = controller.getWorkout(exercise.getIdWorkout());
            //  editWorkout(workout);


        }

    @Override
    public void addExercisesToWorkout(ArrayList<ExerciseLibrary> exercisesSelected) {
        if(exercisesSelected!=null && exercisesSelected.size()>0) {
            for (ExerciseLibrary currentExercise : exercisesSelected) {
                workoutOnEdit.getExercisesTemp().add(new Exercise(currentExercise, workoutOnEdit.getWorkoutTemp().getId()));
            }
        }
            WorkoutCMDFragment workoutCMDFragment = WorkoutCMDFragment.newInstance(workoutOnEdit);
            workoutOnEdit = null;
            loadFragment(R.id.container_fragment_main_activity, workoutCMDFragment);
        }

}

    /////////implementation of ExerciseCMDInterface from the fragment ExerciseCMDFragment



    //// end of implentation of ExerciseCMDInterface


