package com.debenedetti.juaneugenio.fitv1.Views;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.debenedetti.juaneugenio.fitv1.Model.POJO.Session;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;


public class AdapterRecyclerViewSession extends RecyclerView.Adapter {


    private ArrayList<Session> sessions ;
    private NotificatorSessionCell notificadorSessionCell;

    //recibo en el constructor del adapter, un set de datos ya armado desde afuera
    //y me lo guardo como atributo
    public AdapterRecyclerViewSession(ArrayList<Session> session, NotificatorSessionCell notificatorSessionCell) {
        this.sessions = sessions;
        this.notificadorSessionCell = notificatorSessionCell;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //me creo el inflador para inflar el xml de la celda hacia una View.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycle_cell_session, parent, false);
        //me creo un viewholder y le paso la View, que es la celda xml que inflamos
        ViewHolderSession viewHolderSession = new ViewHolderSession(view);
        return viewHolderSession;
    }


    //en este metodo recibimos una posicion, que es la posicion de la pelicula que debemos
    //mostrar en el recycler. Por lo que iremos con un GET a nuestro atributo (el set de datos)
    //y le pedirimos peliculas.get(position) lo que nos devuelvela pelicula en la posicion
    //recibida por parametro. Al viewholder que recibimos por parametro le pasamos esta
    //pelicula que obtuvimos, y el viewholder se encarga de volcar la info a la celda.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Session session = sessions.get(position);
        ViewHolderSession viewHolderSession = (ViewHolderSession) holder;
        viewHolderSession.loadSession(session);
    }


    @Override
    public int getItemCount() {
        if (sessions != null) {
            return sessions.size();
        } else {
            return 0;
        }
    }

    public class ViewHolderSession extends RecyclerView.ViewHolder {


        private TextView nameTV, descriptionTV;
        private LinearLayout linearLayoutContainerCell;

        //este itemview es la celda construida
        public ViewHolderSession(View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.name_session);
            descriptionTV = itemView.findViewById(R.id.description_session);
            linearLayoutContainerCell = itemView.findViewById(R.id.linearlayout_container_recycler_session);

            linearLayoutContainerCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicionSessionClicked = getAdapterPosition();
                    notificadorSessionCell.notificatorSessionClicked(sessions.get(posicionSessionClicked));
                }

            });
        }

        public void loadSession(Session session) {
            nameTV.setText(session.getName());
            descriptionTV.setText(session.getDescription());
        }


    }

    public void addSessionList(ArrayList<Session> sessions) {

        this.sessions = sessions;
        notifyDataSetChanged();
    }

    //INTERFAZ QUE COMUNICA ADAPTER CON FRAGMENT. EL FRAGMENT ES QUIEN IMPLEMENTA ESTA INTERFAZ
    public interface NotificatorSessionCell {
        public void notificatorSessionClicked(Session session);
    }
}
