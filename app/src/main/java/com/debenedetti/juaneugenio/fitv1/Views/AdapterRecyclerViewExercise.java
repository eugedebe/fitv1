package com.debenedetti.juaneugenio.fitv1.Views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.debenedetti.juaneugenio.fitv1.Model.POJO.Exercise;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;

public class AdapterRecyclerViewExercise extends RecyclerView.Adapter {


private ArrayList<Exercise> exercises ;
private NotificatorExercise notificatorExercise;

//recibo en el constructor del adapter, un set de datos ya armado desde afuera
//y me lo guardo como atributo
public AdapterRecyclerViewExercise(ArrayList<Exercise> exercises, NotificatorExercise notificatorExercise) {
        this.exercises = exercises;
        this.notificatorExercise = notificatorExercise;
        }

@NonNull
@Override
public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //me creo el inflador para inflar el xml de la celda hacia una View.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_cell_exercise, parent, false);
        //me creo un viewholder y le paso la View, que es la celda xml que inflamos
        ViewHolderExercise viewHolderExercise = new ViewHolderExercise(view);
        return viewHolderExercise;
        }


//en este metodo recibimos una posicion, que es la posicion de la pelicula que debemos
//mostrar en el recycler. Por lo que iremos con un GET a nuestro atributo (el set de datos)
//y le pedirimos peliculas.get(position) lo que nos devuelvela pelicula en la posicion
//recibida por parametro. Al viewholder que recibimos por parametro le pasamos esta
//pelicula que obtuvimos, y el viewholder se encarga de volcar la info a la celda.
@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        ViewHolderExercise viewHolderExercise = (ViewHolderExercise) holder;
        viewHolderExercise.loadExercise(exercise);
        }


@Override
public int getItemCount() {
        if (exercises != null) {
        return exercises.size();
        } else {
        return 0;
        }
        }

public class ViewHolderExercise extends RecyclerView.ViewHolder {


    private TextView nameTV, descriptionTV;
    private LinearLayout exerciseNamesRecyclerContainer;

    //este itemview es la celda construida
    public ViewHolderExercise(View itemView) {
        super(itemView);

        exerciseNamesRecyclerContainer = itemView.findViewById(R.id.recycler_exercices_cell_name_container);
        nameTV = itemView.findViewById(R.id.name_exercise);
        descriptionTV = itemView.findViewById(R.id.description_exercise);

        exerciseNamesRecyclerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionExercise = getAdapterPosition();
                notificatorExercise.notificatorExercise(positionExercise);
            }

        });
    }

    public void loadExercise(Exercise exercise) {
        nameTV.setText(exercise.getName());
        descriptionTV.setText(exercise.getDescription());
    }


}

    public void addExercise(ArrayList<Exercise> exercises) {

        this.exercises = exercises;
        notifyDataSetChanged();
    }

//INTERFAZ QUE COMUNICA ADAPTER CON FRAGMENT. EL FRAGMENT ES QUIEN IMPLEMENTA ESTA INTERFAZ
public interface NotificatorExercise {
    public void notificatorExercise(int position);
}
}


