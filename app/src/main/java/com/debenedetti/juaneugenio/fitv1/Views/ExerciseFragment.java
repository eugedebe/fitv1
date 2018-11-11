package com.debenedetti.juaneugenio.fitv1.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.debenedetti.juaneugenio.fitv1.Model.POJO.Exercise;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseFragment extends Fragment implements AdapterRecyclerViewExercise.NotificatorExercise {

    private static final String ARG_EXERCISE = "exerciseToBeOpened";
    private RecyclerView recyclerView;
    private AdapterRecyclerViewExercise adapter;
    private ArrayList<Exercise> exercises;
    private CreateEditExercise createEditExercise;
    private LinearLayoutManager linearLayoutManager;


    public ExerciseFragment() {
            // Required empty public constructor
            }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_exercise, container, false);
            // Inflate the layout for this fragment

            recyclerView = view.findViewById(R.id.recycler_exercise);


            loadRecycle();


            return view;
            }

    @Override
    public void onAttach(Context context) {
            super.onAttach(context);
        // check if parent Fragment implements listener
        if (getParentFragment() instanceof CreateEditExercise) {
            createEditExercise = (CreateEditExercise) getParentFragment();
        } else {
            throw new RuntimeException("The parent fragment must implement OnChildFragmentInteractionListener");
        }

            }

    public static ExerciseFragment newInstance(ArrayList<Exercise> exercises) {
            ExerciseFragment exerciseFragment = new ExerciseFragment();
            Bundle args = new Bundle();
            args.putSerializable(ARG_EXERCISE, exercises);
            exerciseFragment.setArguments(args);
            return exerciseFragment;
            }



    private void loadRecycle() {
            adapter = new AdapterRecyclerViewExercise(exercises, this);
            adapter.addExercise(exercises);

            //el layout manager es la disposicion visual del recycler (lineal o grilla, con orientacion vertical u horizontal)

            linearLayoutManager = new LinearLayoutManager(getActivity(),
            LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);


            //si le puse match parent al alto y ancho del recycler, el setHasFixedSize mejora la performance
            recyclerView.setHasFixedSize(true);
            //le seteo el adapter creado al recycler view
            recyclerView.setAdapter(adapter);
    }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
            exercises = (ArrayList<Exercise>) getArguments().getSerializable(ARG_EXERCISE);
            }
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    @Override
    public void notificatorExercise(int position) {
        createEditExercise.editExerciseClicked(position);

    }
    public ArrayList<Exercise> getExerciseOnEdit() {
        return exercises;
    }


    public interface CreateEditExercise{
        public void editExerciseClicked(int positionExerciseToEdit);
        }
}