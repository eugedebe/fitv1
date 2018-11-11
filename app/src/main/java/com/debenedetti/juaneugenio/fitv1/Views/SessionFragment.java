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

import com.debenedetti.juaneugenio.fitv1.Controller.Controller;

import com.debenedetti.juaneugenio.fitv1.Model.POJO.Session;
import com.debenedetti.juaneugenio.fitv1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SessionFragment extends Fragment implements AdapterRecyclerViewSession.NotificatorSessionCell {


    private static final String ARG_SESSIONS = "sessionsToBeOpened";

    private RecyclerView recyclerView;
    private AdapterRecyclerViewSession adapter;
    private ArrayList<Session> sessions;

   private CreateEditSession createEditSession;
    private LinearLayoutManager linearLayoutManager;



    public SessionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_session, container, false);
        // Inflate the layout for this fragment

        getActivity().setTitle(R.string.sessions);
        if (sessions == null)
        {
            Controller controller = Controller.getInstance();
            sessions = controller.getSessionList();

        }

        recyclerView = view.findViewById(R.id.recycler_session);
        loadRecycle();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_create_session);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEditSession.createSession();

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            createEditSession = (CreateEditSession) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    context.toString() + " implementar Create edit Session in: " + context.toString());
        }

    }


    public static SessionFragment newInstance(ArrayList<Session> sessions) {
        SessionFragment sessionFragment = new SessionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SESSIONS, sessions);
        sessionFragment.setArguments(args);
        return sessionFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sessions = (ArrayList<Session>) getArguments().getSerializable(ARG_SESSIONS);
        }
    }



    private void loadRecycle() {
        adapter = new AdapterRecyclerViewSession(sessions,this);
        adapter.addSessionList(sessions);

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
    public void notificatorSessionClicked(Session session) {

        createEditSession.editSession(session);

    }






    public interface CreateEditSession{
        public void editSession(Session session);
        public void createSession();
    }
}
