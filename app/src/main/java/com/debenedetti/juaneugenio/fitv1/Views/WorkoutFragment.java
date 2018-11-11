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


import com.debenedetti.juaneugenio.fitv1.Model.POJO.Session;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutOnEdit;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutFragment extends Fragment implements AdapterRecyclerViewWorkout.NotificatorWorkoutCell {

    private static final String ARG_WORKOUTS = "workoutsToBeOpened";
    private RecyclerView recyclerView;
    private AdapterRecyclerViewWorkout adapter;


    private ArrayList<WorkoutOnEdit> workoutsOnEdit;
    private CreateEditWorkout createEditWorkout;
    private LinearLayoutManager linearLayoutManager;






    public WorkoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.recycler_workout);
        loadRecycle();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // check if parent Fragment implements listener
        if (getParentFragment() instanceof CreateEditWorkout) {
            createEditWorkout = (CreateEditWorkout) getParentFragment();
        } else {
            throw new RuntimeException("The parent fragment must implement OnChildFragmentInteractionListener");
        }



    }

    public static WorkoutFragment newInstance(ArrayList<WorkoutOnEdit> workoutsOnEdit) {
        WorkoutFragment workoutFragment = new WorkoutFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_WORKOUTS, workoutsOnEdit);
        workoutFragment.setArguments(args);
        return workoutFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            workoutsOnEdit = (ArrayList<WorkoutOnEdit>) getArguments().getSerializable(ARG_WORKOUTS);
        }
    }



    private void loadRecycle() {
        adapter = new AdapterRecyclerViewWorkout(workoutsOnEdit, this);
        adapter.addWorkoutList(workoutsOnEdit);

        //el layout manager es la disposicion visual del recycler (lineal o grilla, con orientacion vertical u horizontal)

        linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        //si le puse match parent al alto y ancho del recycler, el setHasFixedSize mejora la performance
        recyclerView.setHasFixedSize(true);
        //le seteo el adapter creado al recycler view
        recyclerView.setAdapter(adapter);
    }


    public ArrayList<WorkoutOnEdit> getWorkoutsOnEdit() {
        return workoutsOnEdit;
    }

    public void setWorkoutsOnEdit(ArrayList<WorkoutOnEdit> workoutsOnEdit) {
        this.workoutsOnEdit = workoutsOnEdit;
    }


    @Override
    public void notificatorWorkoutClicked(int position) {
        createEditWorkout.editWorkoutClicked(position);
    }

    public interface CreateEditWorkout{
        public void editWorkoutClicked(int position);
    }




}