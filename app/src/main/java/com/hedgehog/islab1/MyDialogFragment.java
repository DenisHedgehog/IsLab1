package com.hedgehog.islab1;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends android.support.v4.app.DialogFragment {

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public CreateGraph getCreateGraph() {
        return createGraph;
    }

    public void setCreateGraph(CreateGraph createGraph) {
        this.createGraph = createGraph;
    }

    public int[] getAdjacentVertices() {
        return adjacentVertices;
    }

    public void setAdjacentVertices(int[] adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
    }

    private int position;
    private CreateGraph createGraph;
    private int[] adjacentVertices;

    public MyDialogFragment(CreateGraph createGraph, int position) {
        this.position = position;
        this.createGraph = createGraph;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "Через запятую введите смежные вершины:";
        String positiveButtonText = "Ок";
        String negativeButtonText = "Отмена";

        LinearLayout linearLayout = new LinearLayout(getContext());
        final EditText editText = new EditText(getContext());
        LinearLayout.LayoutParams paramForEditText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(editText, paramForEditText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setView(linearLayout);

        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (!editText.getText().toString().equals("")) {
                    String[] split = (editText.getText().toString()).split(",");
                    int[] arr = new int[split.length];
                    adjacentVertices = arr;
                    for (int i = 0; i < split.length; i++) {
                        try {
                            getAdjacentVertices()[i] = Integer.parseInt(split[i]);
                        } catch (Exception e) {
                            String exception = "Ошибка в преобразовании: " + e;
                            Toast toast = Toast.makeText(getContext(), exception, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }
                createGraph.getVertices().set(getPosition(), new Vertex(getPosition(), getAdjacentVertices()));
            }
        });
        builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setCancelable(true);

        return builder.create();
    }

}
