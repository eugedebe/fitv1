package com.debenedetti.juaneugenio.fitv1.Views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.debenedetti.juaneugenio.fitv1.Model.POJO.Workout;
import com.debenedetti.juaneugenio.fitv1.Model.POJO.WorkoutOnEdit;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;

public class AdapterRecyclerViewWorkout extends RecyclerView.Adapter {


        private ArrayList<WorkoutOnEdit> workoutsOnEdit ;
        private NotificatorWorkoutCell  notificatorWorkoutCell;

        //recibo en el constructor del adapter, un set de datos ya armado desde afuera
        //y me lo guardo como atributo
        public AdapterRecyclerViewWorkout(ArrayList<WorkoutOnEdit> workoutsOnEdit, NotificatorWorkoutCell notificatorWorkoutCell) {
            this.workoutsOnEdit = workoutsOnEdit;
            this.notificatorWorkoutCell = notificatorWorkoutCell;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            //me creo el inflador para inflar el xml de la celda hacia una View.
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.recycle_cell_workouts, parent, false);
            //me creo un viewholder y le paso la View, que es la celda xml que inflamos
            ViewHolderWorkout  viewHolderWorkout = new ViewHolderWorkout(view);
            return viewHolderWorkout;
        }


        //en este metodo recibimos una posicion, que es la posicion de la pelicula que debemos
        //mostrar en el recycler. Por lo que iremos con un GET a nuestro atributo (el set de datos)
        //y le pedirimos peliculas.get(position) lo que nos devuelvela pelicula en la posicion
        //recibida por parametro. Al viewholder que recibimos por parametro le pasamos esta
        //pelicula que obtuvimos, y el viewholder se encarga de volcar la info a la celda.
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            WorkoutOnEdit workoutOnEdit = workoutsOnEdit.get(position);
            ViewHolderWorkout viewHolderWorkout = (ViewHolderWorkout) holder;
            viewHolderWorkout.loadWorkout(workoutOnEdit);
        }


        @Override
        public int getItemCount() {
            if (workoutsOnEdit != null) {
                return workoutsOnEdit.size();
            } else {
                return 0;
            }
        }

        public class ViewHolderWorkout extends RecyclerView.ViewHolder {


            private TextView nameTV, descriptionTV;
            private LinearLayout nameContainer;

            //este itemview es la celda construida
            public ViewHolderWorkout(View itemView) {
                super(itemView);
                

                nameContainer = itemView.findViewById(R.id.recycler_workout_cell_name_container);
                nameTV = itemView.findViewById(R.id.name_workout);
                descriptionTV = itemView.findViewById(R.id.description_workout);

                nameContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int positionWorkoutClicked = getAdapterPosition();
                        notificatorWorkoutCell.notificatorWorkoutClicked(positionWorkoutClicked);
                    }

                });
            }

            public void loadWorkout(WorkoutOnEdit workoutOnEdit) {
                nameTV.setText(workoutOnEdit.getWorkoutTemp().getName());
                descriptionTV.setText(workoutOnEdit.getWorkoutTemp().getDescription());
            }


        }

        public void addWorkoutList(ArrayList<WorkoutOnEdit> workoutsOnEdit) {

            this.workoutsOnEdit = workoutsOnEdit;
            notifyDataSetChanged();
        }

        //INTERFAZ QUE COMUNICA ADAPTER CON FRAGMENT. EL FRAGMENT ES QUIEN IMPLEMENTA ESTA INTERFAZ
        public interface NotificatorWorkoutCell {
            public void notificatorWorkoutClicked(int positionWorkoutClicked);
        }
}


