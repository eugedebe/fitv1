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


import com.debenedetti.juaneugenio.fitv1.Controller.Controller;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Exercise;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.ExerciseLibrary;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutOnEdit;
import com.debenedetti.juaneugenio.fitv1.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutCMDFragment extends Fragment implements DialogAddWorkouts.OnOptionDialogAddWorkoutSelected,
        ExerciseFragment.CreateEditExercise{

    private static final String ARG_WORKOUT_ON_EDIT_TBO = "workoutToBeOpen";
    private static final String  ARG_EXERCISE_ID_LIST_TO_ADD = "exerciseIdListToAdd";
    public static final int NEW_WORKOUT = -1;
    private WorkoutOnEdit workoutOnEdit;
    ExerciseFragment exerciseFragment;

    AddExercise addExercise;
    SaveorCancelWorkoutEdition saveorCancelWorkoutEdition;



    public WorkoutCMDFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout_cmd, container, false);
        //to trigger the onCreateOptionsMenu
        setHasOptionsMenu(true);

        int idContainer = R.id.recycler_container_exercises_cmd;

        if (workoutOnEdit == null) {
            int i=0;
        }
        else{
            loadDataExercices(workoutOnEdit.getExercisesTemp(),idContainer);
        }


        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add_exercise_to_workout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddWorkouts dialogAddWorkouts = new DialogAddWorkouts();
                dialogAddWorkouts.setTargetFragment(WorkoutCMDFragment.this,1);
                dialogAddWorkouts.show(getFragmentManager(),"DialogAddExercise");
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            addExercise = (AddExercise) context;
            saveorCancelWorkoutEdition = (SaveorCancelWorkoutEdition) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    context.toString() + " implementar AddExrcise in: " + context.toString());
        }

    }

    private void loadDataExercices(ArrayList<Exercise> exercises , int idContainer) {
        exerciseFragment = ExerciseFragment.newInstance(exercises);
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.recycler_container_exercises_cmd, exerciseFragment).commit();
    }


    public static WorkoutCMDFragment newInstance(WorkoutOnEdit workoutOnEdit) {
        WorkoutCMDFragment workoutCMDFragment = new WorkoutCMDFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_WORKOUT_ON_EDIT_TBO, workoutOnEdit);
        workoutCMDFragment.setArguments(args);
        return workoutCMDFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getActivity().setTitle(R.string.editor_workout_edit);
            workoutOnEdit = (WorkoutOnEdit) getArguments().getSerializable(ARG_WORKOUT_ON_EDIT_TBO);
            if (workoutOnEdit == null) {
                workoutOnEdit = new WorkoutOnEdit(new ArrayList<Exercise>(),new Workout()); //means that we have to create a new session
            }
        }
        else {
            getActivity().setTitle(R.string.editor_workout_create);
            workoutOnEdit = new WorkoutOnEdit(new ArrayList<Exercise>(),new Workout()); //means that we have to create a new session
        }
    }



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
                saveorCancelWorkoutEdition.saveOrCancelWorkout(null);
                return true;
            case R.id.menu_save_cancel_saveoperation:
                saveorCancelWorkoutEdition.saveOrCancelWorkout(workoutOnEdit);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //////implementation of OnOptionDialogAddWorkoutSelected from DialogAdd
    @Override
    public void sendAdd() {
        workoutOnEdit.setExercisesTemp(exerciseFragment.getExercises());
        addExercise.openExerciseSelection(workoutOnEdit);
    }
    @Override
    public void sendNew() {
        workoutOnEdit.setExercisesTemp(exerciseFragment.getExercises());
        addExercise.editExerciseofWorkout(workoutOnEdit,NEW_WORKOUT);
    }
    @Override
    public void sendCancel() {

    }

    @Override
    public String messageoptionAddNew() {
        return getActivity().getString(R.string.dialog_answer_new_exercise);
    }

    @Override
    public String question() {
        return getActivity().getString(R.string.dialog_question_new_or_add_existing_exercise);
    }

    @Override
    public void editExerciseClicked(int positionExerciseToEdit) {
        workoutOnEdit.setExercisesTemp(exerciseFragment.getExerciseOnEdit());
        addExercise.editExerciseofWorkout(workoutOnEdit,positionExerciseToEdit);
    }
/////// end of immplementation


    public interface AddExercise{
        public void openExerciseSelection(WorkoutOnEdit workoutOnEdit);
        public void editExerciseofWorkout(WorkoutOnEdit workoutOnEdit, int IdSession);
    }
    public interface SaveorCancelWorkoutEdition{
        public void saveOrCancelWorkout(WorkoutOnEdit workoutOnEdit);

    }


}
