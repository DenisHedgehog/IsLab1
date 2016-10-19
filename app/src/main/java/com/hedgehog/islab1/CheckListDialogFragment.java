package com.hedgehog.islab1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;


import java.util.ArrayList;

/**
 * Created by hedgehog on 18.10.16.
 */

public class CheckListDialogFragment extends android.support.v4.app.DialogFragment {

    public int getPosition() {
        return position;
    }

    private int position;
    private CreateGraph createGraph;
    private int count;


    public CheckListDialogFragment(CreateGraph createGraph, int position, int count) {
        this.position = position;
        this.createGraph = createGraph;
        this.count = count;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String[] strings = new String[count];
        for (int i = 0; i < count; i++) {
            strings[i] = "" + (i + 1);
        }
        final ArrayList<Integer> integers = new ArrayList<Integer>();

        final boolean[] checkedItemsArray = new boolean[count];

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите смежные вершины")
                .setMultiChoiceItems(strings, checkedItemsArray,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                checkedItemsArray[which] = isChecked;
                            }
                        })
                .setPositiveButton("Готово",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                int c = 0;
                                for (int i = 0; i < checkedItemsArray.length; i++) {
                                    if (checkedItemsArray[i]) {
                                        integers.add(c, i);
                                        c++;
                                    }
                                }
                                int[] adjacentVertices = new int[integers.size()];
                                for (int i = 0; i < integers.size(); i++) {
                                    adjacentVertices[i] = integers.get(i);
                                }

                                createGraph.getVertices().set(getPosition(), new Vertex(getPosition(), adjacentVertices));
                            }
                        })

                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });

        return builder.create();
    }

}
