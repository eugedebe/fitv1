package com.debenedetti.juaneugenio.fitv1.Views;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.debenedetti.juaneugenio.fitv1.Model.POJO.Exercise;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.ExerciseLibrary;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;

public class AdapterRecyclerViewExerciseSelection extends RecyclerView.Adapter {


private ArrayList<ExerciseLibrary> exercisesLibrary ;
private NotificatorExerciseSelectionCell  notificatorExerciseSelectionCell;

//recibo en el constructor del adapter, un set de datos ya armado desde afuera
//y me lo guardo como atributo
public AdapterRecyclerViewExerciseSelection(ArrayList<ExerciseLibrary> exercisesLibrary, NotificatorExerciseSelectionCell notificatorExerciseCell) {
        this.exercisesLibrary = exercisesLibrary;
        this.notificatorExerciseSelectionCell = notificatorExerciseCell;
        }

@NonNull
@Override
public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //me creo el inflador para inflar el xml de la celda hacia una View.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_cell_exercise_select, parent, false);
        //me creo un viewholder y le paso la View, que es la celda xml que inflamos
        ViewHolderExerciseSelect  viewHolderExerciseSelect = new ViewHolderExerciseSelect(view);
        return viewHolderExerciseSelect;
        }


//en este metodo recibimos una posicion, que es la posicion de la pelicula que debemos
//mostrar en el recycler. Por lo que iremos con un GET a nuestro atributo (el set de datos)
//y le pedirimos peliculas.get(position) lo que nos devuelvela pelicula en la posicion
//recibida por parametro. Al viewholder que recibimos por parametro le pasamos esta
//pelicula que obtuvimos, y el viewholder se encarga de volcar la info a la celda.
@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExerciseLibrary exerciseLibrary = this.exercisesLibrary.get(position);

        ViewHolderExerciseSelect viewHolderExerciseSelect = (ViewHolderExerciseSelect) holder;
        viewHolderExerciseSelect.loadExercise(exerciseLibrary);
        }


@Override
public int getItemCount() {
        if (exercisesLibrary != null) {
        return exercisesLibrary.size();
        } else {
        return 0;
        }
        }

public class ViewHolderExerciseSelect extends RecyclerView.ViewHolder {


    private EditText nameET, descriptionET, effortPoints, timeDuration ;


    //este itemview es la celda construida
    public ViewHolderExerciseSelect(View itemView) {
        super(itemView);

        nameET = itemView.findViewById(R.id.title_seleccion__header);
        descriptionET = itemView.findViewById(R.id.description_selection_header);
        effortPoints= itemView.findViewById(R.id.tot_effort_points_selection_header);
        timeDuration = itemView.findViewById(R.id.tot_duration_selection_header);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) itemView.findViewById(R.id.fab_recycler_cel_exercise_selection);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicionExerciseClicked = getAdapterPosition();
                notificatorExerciseSelectionCell.notificatorExerciseSelected(exercisesLibrary.get(posicionExerciseClicked));

            }
        });


    }

    public void loadExercise(ExerciseLibrary exerciseLibrary) {
        nameET.setText(exerciseLibrary.getName());
        descriptionET.setText(exerciseLibrary.getDescription());
    }
}

    public void addExerciseList(ArrayList<ExerciseLibrary> exercisesLibrary) {

        this.exercisesLibrary = exercisesLibrary;
        notifyDataSetChanged();
    }

//INTERFAZ QUE COMUNICA ADAPTER CON FRAGMENT. EL FRAGMENT ES QUIEN IMPLEMENTA ESTA INTERFAZ
public interface NotificatorExerciseSelectionCell {
    public void notificatorExerciseSelected(ExerciseLibrary exerciseLibrary);
}
}
