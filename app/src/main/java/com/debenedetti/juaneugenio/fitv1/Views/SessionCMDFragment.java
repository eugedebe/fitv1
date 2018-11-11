package com.debenedetti.juaneugenio.fitv1.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.debenedetti.juaneugenio.fitv1.Controller.Controller;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.BasicDetailPlan;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Session;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.SessionOnEdit;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutLibrary;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutOnEdit;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class SessionCMDFragment extends Fragment implements DialogAddWorkouts.OnOptionDialogAddWorkoutSelected,
        WorkoutFragment.CreateEditWorkout{

    private static final String ARG_SESSION_ID_TBO = "sessionToBeOpened"; //(TBO = TO BE OPENED
    public static final int NEW_WORKOUT = -1;
    private SessionOnEdit sessionOnEdit;
    WorkoutFragment workoutFragment;
    AddWorkout addWorkout; //interface to add new or existing workout
    SaveorCancelSessionEdition saveorCancelSession; // interface to save or Cancel de Session that is under edition
   // private ArrayList<WorkoutOnEdit> workouts;


    public SessionCMDFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_session_cmd, container, false);
        //to trigger the onCreateOptionsMenu
        setHasOptionsMenu(true);

        int idContainer = R.id.workouts_container_in_session_cmd;



        if (sessionOnEdit == null) {
            int i=0;
        }
        else{
            loadDataWorkoutFragment( sessionOnEdit.getWorkoutsTemp(),idContainer);
        }

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add_workout_to_session);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogAddWorkouts dialogAddWorkouts = new DialogAddWorkouts();
                dialogAddWorkouts.setTargetFragment(SessionCMDFragment.this,1);
                dialogAddWorkouts.show(getFragmentManager(),"DialogAddWorkouts");


            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            addWorkout = (AddWorkout) context;
            saveorCancelSession = (SaveorCancelSessionEdition) context;

        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    context.toString() + " implementar Create  Session in: " + context.toString());
        }

    }

    private void loadDataWorkoutFragment(ArrayList<WorkoutOnEdit> workoutsOnEdit, int idContainer) {
        workoutFragment = WorkoutFragment.newInstance(workoutsOnEdit);
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.workouts_container_in_session_cmd, workoutFragment).commit();
    }


    public static SessionCMDFragment newInstance(SessionOnEdit sessionOnEdit) {
        SessionCMDFragment sessionCMDFragment = new SessionCMDFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SESSION_ID_TBO, sessionOnEdit);
        sessionCMDFragment.setArguments(args);
        return sessionCMDFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getActivity().setTitle(R.string.editor_session_edit);
            sessionOnEdit= (SessionOnEdit) getArguments().getSerializable(ARG_SESSION_ID_TBO);
            if(sessionOnEdit==null) {
                sessionOnEdit = new SessionOnEdit(new ArrayList<WorkoutOnEdit>(),new Session()); //means that we have to create a new session

            }
        }else {
            getActivity().setTitle(R.string.editor_session_create);
            sessionOnEdit= new SessionOnEdit(new ArrayList<WorkoutOnEdit>(), new Session());
        }
    }




        /////START THE IMPLEMENTATIONS OF INTERFACES
//implementation of the interface with DialogAdd
    @Override
    public void sendAdd() {
        sessionOnEdit.setWorkoutsTemp(workoutFragment.getWorkoutsOnEdit());
        addWorkout.openWorkoutSelection(sessionOnEdit);

    }
    @Override
    public void sendNew() {
        sessionOnEdit.setWorkoutsTemp(workoutFragment.getWorkoutsOnEdit());
        addWorkout.editWorkoutofSession(sessionOnEdit,NEW_WORKOUT);

    }
    @Override
    public void sendCancel() {

    }



    // En of the implemention DialogAdd


    //configuration of special buttons in the appbar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_save_cancel,menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.menu_save_cancel_canceloperation:

                saveorCancelSession.saveOrCancelSession(null);
                return true;
            case R.id.menu_save_cancel_saveoperation:
                saveorCancelSession.saveOrCancelSession(sessionOnEdit);
                return true;
                default:
                return super.onOptionsItemSelected(item);
        }
    }
    //END OF CONFIGURATION OF SPECIAL BUTTONS IN THE APP BAR
    @Override
    public String messageoptionAddNew() {
        return getActivity().getString(R.string.dialog_answer_new_wkt);
    }

    @Override
    public String question() {
        return getActivity().getString(R.string.dialog_question_new_or_add_existing_workout);
    }

    @Override
    public void editWorkoutClicked(int positionWorkoutToEdit) {
        sessionOnEdit.setWorkoutsTemp(workoutFragment.getWorkoutsOnEdit());
        addWorkout.editWorkoutofSession(sessionOnEdit,positionWorkoutToEdit);
        }


    public interface AddWorkout{
        public void openWorkoutSelection(SessionOnEdit sessionTemp);
        public void editWorkoutofSession(SessionOnEdit sessionTemp, int position);
    }
    public interface SaveorCancelSessionEdition{
        public void saveOrCancelSession(SessionOnEdit sessionOnEdit);
    }
}
