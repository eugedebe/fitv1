package com.debenedetti.juaneugenio.fitv1.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseSelectionFragment extends Fragment
    implements AdapterRecyclerViewExerciseSelection.NotificatorExerciseSelectionCell{

        private static final String ARG_WORKOUT = "workoutsToBeOpened";
        private static final String ARG_EXERCISETOADD = "exerciseToAddinEditingWorkouts";

        private RecyclerView recyclerView;
        private AdapterRecyclerViewExerciseSelection adapter;
        private ArrayList<ExerciseLibrary> exercisesAllCategories;
        private Controller controller;
        private InterfaceExerciseSelection interfaceExerciseSelection;
        private LinearLayoutManager linearLayoutManager;
        private ArrayList<ExerciseLibrary> exerciseListSelected;




    public ExerciseSelectionFragment() {
            // Required empty public constructor
        }




        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_excersice_selection, container, false);

            //to trigger the onCreateOptionsMenu
            setHasOptionsMenu(true);

            recyclerView = view.findViewById(R.id.recycler_exercise_selected);

            loadRecycle();

            return view;
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            try {
                interfaceExerciseSelection = (InterfaceExerciseSelection) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(
                        context.toString() + " implement interfaceExercise in: " + context.toString());
            }

        }

        public static ExerciseSelectionFragment newInstance() {
            ExerciseSelectionFragment exercesiSelectionFragment = new ExerciseSelectionFragment();
            return exercesiSelectionFragment;
        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = Controller.getInstance();
                    exerciseListSelected = new ArrayList<>();
            exercisesAllCategories = controller.getExerciseLibraryList();

    }





        private void loadRecycle() {
            adapter = new AdapterRecyclerViewExerciseSelection(exercisesAllCategories, this);
            adapter.addExerciseList(exercisesAllCategories);

            //el layout manager es la disposicion visual del recycler (lineal o grilla, con orientacion vertical u horizontal)

            linearLayoutManager = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            //si le puse match parent al alto y ancho del recycler, el setHasFixedSize mejora la performance
            recyclerView.setHasFixedSize(true);
            //le seteo el adapter creado al recycler view
            recyclerView.setAdapter(adapter);
        }


        @Override
        public void notificatorExerciseSelected(ExerciseLibrary exerciseLibrary) {
                exerciseListSelected.add(exerciseLibrary);

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
                    interfaceExerciseSelection.addExercisesToWorkout(new ArrayList<ExerciseLibrary>());
                    // );
                    return true;
                case R.id.menu_save_cancel_saveoperation:
                    interfaceExerciseSelection.addExercisesToWorkout(exerciseListSelected);
                default:
                    return super.onOptionsItemSelected(item);
            }
        }




        public interface InterfaceExerciseSelection{
            public void addExercisesToWorkout(ArrayList<ExerciseLibrary> exercisesSelected);
        }






}
