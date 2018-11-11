package com.debenedetti.juaneugenio.fitv1.Views;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutLibrary;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;

public class AdapterRecyclerViewWorkoutSelection extends RecyclerView.Adapter {


private ArrayList<WorkoutLibrary> workoutsLibrary ;
private NotificatorWorkoutSelectionCell  notificatorWorkoutSelectionCell;

//recibo en el constructor del adapter, un set de datos ya armado desde afuera
//y me lo guardo como atributo
public AdapterRecyclerViewWorkoutSelection(ArrayList<WorkoutLibrary> workoutsLibrary, NotificatorWorkoutSelectionCell notificatorWorkoutCell) {
        this.workoutsLibrary = workoutsLibrary;
        this.notificatorWorkoutSelectionCell = notificatorWorkoutCell;
        }

@NonNull
@Override
public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //me creo el inflador para inflar el xml de la celda hacia una View.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_cell_workout_select, parent, false);
        //me creo un viewholder y le paso la View, que es la celda xml que inflamos
        ViewHolderWorkout  viewHolderWorkout = new ViewHolderWorkout(view);

        return viewHolderWorkout;
        }


@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WorkoutLibrary workoutLibrary = workoutsLibrary.get(position);
        ViewHolderWorkout viewHolderWorkout = (ViewHolderWorkout) holder;
        viewHolderWorkout.loadWorkout(workoutLibrary);
        }


@Override
public int getItemCount() {
        if (workoutsLibrary != null) {
        return workoutsLibrary.size();
        } else {
        return 0;
        }
        }

public class ViewHolderWorkout extends RecyclerView.ViewHolder {


    private EditText nameET, descriptionET, effortPoints, timeDuration ;


    //este itemview es la celda construida
    public ViewHolderWorkout(View itemView) {
        super(itemView);

        nameET = itemView.findViewById(R.id.title_seleccion__header);
        descriptionET = itemView.findViewById(R.id.description_selection_header);
        effortPoints= itemView.findViewById(R.id.tot_effort_points_selection_header);
        timeDuration = itemView.findViewById(R.id.tot_duration_selection_header);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) itemView.findViewById(R.id.fab_recycler_cel_workout_selection);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicionWorkoutClicked = getAdapterPosition();
                notificatorWorkoutSelectionCell.notificatorWorkoutSelected(workoutsLibrary.get(posicionWorkoutClicked));

            }
        });


    }

    public void loadWorkout(WorkoutLibrary workoutLibrary) {
        nameET.setText(workoutLibrary.getName());
        descriptionET.setText(workoutLibrary.getDescription());

    }


}

    public void addWorkoutList(ArrayList<WorkoutLibrary> workoutsLibrary) {
        this.workoutsLibrary = workoutsLibrary;
        notifyDataSetChanged();
    }

//INTERFAZ QUE COMUNICA ADAPTER CON FRAGMENT. EL FRAGMENT ES QUIEN IMPLEMENTA ESTA INTERFAZ
public interface NotificatorWorkoutSelectionCell {
    public void notificatorWorkoutSelected(WorkoutLibrary workoutLibrary);
}
}