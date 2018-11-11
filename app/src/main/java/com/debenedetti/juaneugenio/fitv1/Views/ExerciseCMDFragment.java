package com.debenedetti.juaneugenio.fitv1.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.debenedetti.juaneugenio.fitv1.Controller.Controller;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Exercise;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseCMDFragment extends Fragment  {

    private static final String ARG_EXERCISE_TO_BE_OPENED = "exerciseToBeOpen";

    private Exercise exercise;
    ExerciseCMDInterface exerciseCMDInterface;


    public ExerciseCMDFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exercise_cmd, container, false);
        //to trigger the onCreateOptionsMenu
        setHasOptionsMenu(true);


        if (exercise != null) {
            loadExercise();
        }


        return view;
    }


    public static ExerciseCMDFragment newInstance(Exercise exercise) {
        ExerciseCMDFragment exerciseCMDFragment = new ExerciseCMDFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_EXERCISE_TO_BE_OPENED, exercise);
        exerciseCMDFragment.setArguments(args);
        return exerciseCMDFragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            getActivity().setTitle(R.string.editor_exercise_edit);
            exercise= (Exercise) getArguments().getSerializable(ARG_EXERCISE_TO_BE_OPENED);
            if(exercise==null) {
                exercise = new Exercise(); //means that we have to create a new session
                getActivity().setTitle(R.string.editor_exercise_create);
            }
           }
        else {
            getActivity().setTitle(R.string.editor_exercise_create);
            exercise = new Exercise();
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
                exerciseCMDInterface.cancelOrSaveCMDExercise(exercise);
                return true;
            case R.id.menu_save_cancel_saveoperation:
                Controller controller = Controller.getInstance();
                controller.saveExercise(exercise);
                exerciseCMDInterface.cancelOrSaveCMDExercise(exercise);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            exerciseCMDInterface = (ExerciseCMDInterface) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    context.toString() + " implementar ExerciseCMDInterface: " + context.toString());
        }
    }


    private void loadExercise(){


    }


    public interface ExerciseCMDInterface{
        public void cancelOrSaveCMDExercise(Exercise exercise);
    }




}
