package com.debenedetti.juaneugenio.fitv1.Model.DAO.DaoFirebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DaoFirebase {

    private ChildEventListener mChildEventListener;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;




    public void onSignedOutCleanup() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        detachDatabaseReadListener();
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            myRef.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }
}
