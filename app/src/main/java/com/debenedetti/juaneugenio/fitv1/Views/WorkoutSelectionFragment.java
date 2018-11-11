package com.debenedetti.juaneugenio.fitv1.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Session;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutLibrary;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutSelectionFragment extends Fragment
        implements AdapterRecyclerViewWorkoutSelection.NotificatorWorkoutSelectionCell{

    private static final String ARG_IDSECTION = "sessionToBeOpened";
    private static final String ARG_WORKOUTALREADYSELECTED = "cumulativeWorkoutsToAdd";
    private RecyclerView recyclerView;
    private AdapterRecyclerViewWorkoutSelection adapter;
    private ArrayList<WorkoutLibrary> workoutsAllCategories;
    private Controller controller;
  //  private Session sessionOnEdit;
    private InterfaceWorkoutSelection interfaceWorkoutSelection;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<WorkoutLibrary> workoutListSelected;
 //   private ArrayList<Workout> workoutListsAlreadySelected;



    public WorkoutSelectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout_selection, container, false);

        //to trigger the onCreateOptionsMenu
        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.recycler_workout_selected);


        loadRecycle();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interfaceWorkoutSelection = (WorkoutSelectionFragment.InterfaceWorkoutSelection) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    context.toString() + " implement interfaceWorkoutSelection in: " + context.toString());
        }

    }

    public static WorkoutSelectionFragment newInstance() {
        WorkoutSelectionFragment workoutSelectionFragment = new WorkoutSelectionFragment();
        return workoutSelectionFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workoutsAllCategories = Controller.getInstance().getWorkoutLibraryList();
        workoutListSelected = new ArrayList<>();

        if (getArguments() != null) {
          //  workoutListSelected = new ArrayList<>();
          //  sessionOnEdit = (Session) getArguments().getSerializable(ARG_IDSECTION);
          //  workoutListsAlreadySelected = (ArrayList<Workout>) getArguments().getSerializable(ARG_WORKOUTALREADYSELECTED);
          //  workoutsAllCategories = controller.getWorkoutLibraryList();
        }
    }

    private void loadRecycle() {
        adapter = new AdapterRecyclerViewWorkoutSelection(workoutsAllCategories, this);
        adapter.addWorkoutList(workoutsAllCategories);

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
    public void notificatorWorkoutSelected(WorkoutLibrary workoutLibrary) {
        workoutListSelected.add(workoutLibrary);

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
                interfaceWorkoutSelection.addWorkoutsToSession(new ArrayList<WorkoutLibrary>());
                return true;
            case R.id.menu_save_cancel_saveoperation:
                interfaceWorkoutSelection.addWorkoutsToSession(workoutListSelected);
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    public interface InterfaceWorkoutSelection{
        public void addWorkoutsToSession(ArrayList<WorkoutLibrary> workoutSelected);
    }




}