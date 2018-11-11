package com.debenedetti.juaneugenio.fitv1.Views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.debenedetti.juaneugenio.fitv1.R;

public class DialogAddWorkouts  extends DialogFragment {

    public OnOptionDialogAddWorkoutSelected onOptionDialogAddWorkoutSelected;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            String bodyDialogString = onOptionDialogAddWorkoutSelected.messageoptionAddNew();
            String questionDialogString= onOptionDialogAddWorkoutSelected.question();

            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(questionDialogString)
                    .setPositiveButton(R.string.dialog_answer_add_existing_wkt, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //
                            onOptionDialogAddWorkoutSelected.sendAdd();

                            getDialog().dismiss();
                        }
                    })
                    .setNegativeButton(R.string.dialog_answer_cancel_wkt, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            onOptionDialogAddWorkoutSelected.sendCancel();


                            getDialog().dismiss();
                        }
                    })
            .setNeutralButton(bodyDialogString, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onOptionDialogAddWorkoutSelected.sendNew();


                    getDialog().dismiss();
                }
            });
            // Create the AlertDialog object and return it
            return builder.create();
        }

        @Override
        public void onAttach(Context context){
            super.onAttach(context);
            try{
                onOptionDialogAddWorkoutSelected = (OnOptionDialogAddWorkoutSelected) getTargetFragment();

            }catch (ClassCastException e)
            {
                throw new ClassCastException(
                        context.toString() + " implementar DialogAddWorkout: " + context.toString());
            }
        }

        public interface OnOptionDialogAddWorkoutSelected{
            void sendAdd();
            void sendNew();
            void sendCancel();
            String messageoptionAddNew();
            String question();
        }

}


