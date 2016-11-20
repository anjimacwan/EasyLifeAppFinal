/* Copyright 2015 Braden Farmer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anjalimacwan.fragment.dialog;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.anjalimacwan.R;

public class WearPluginDialogFragment extends DialogFragment {

    /* The activity that creates an instance of this fragment must
     * implement this interface in order to receive event call backs. */
    public interface Listener {
        void onWearDialogPositiveClick();
        void onWearDialogNegativeClick();
    }

    // Use this instance of the interface to deliver action events
    Listener listener;

    // Override the Fragment.onAttach() method to instantiate the Listener
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the Listener so we can send events to the host
            listener = (Listener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement Listener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.install_wear_plugin_message)
        .setTitle(R.string.install_wear_plugin_title)
        .setPositiveButton(R.string.action_install, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listener.onWearDialogPositiveClick();
            }
        })
        .setNegativeButton(R.string.action_no_thanks, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listener.onWearDialogNegativeClick();
            }
         });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}