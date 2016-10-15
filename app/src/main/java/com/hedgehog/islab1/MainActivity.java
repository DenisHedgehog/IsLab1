package com.hedgehog.islab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    Button graphButton;
    EditText numberOfVertices;
    EditText startVertex;
    EditText destinationVertex;
    ListView listView;
    int count;
    private ArrayList<Vertex> vertices;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphButton = (Button) findViewById(R.id.create_graph_button);
        numberOfVertices = (EditText) findViewById(R.id.number_of_vertices);
        startVertex = (EditText) findViewById(R.id.edit_start);
        destinationVertex = (EditText) findViewById(R.id.edit_destination);
        listView = (ListView) findViewById(R.id.list_view_vertices);



        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    count = Integer.parseInt(numberOfVertices.getText().toString());
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(MainActivity.this, "NaN", Toast.LENGTH_SHORT);
                    toast.show();
                }

                for (int i = 0; i < count; i++){



                }









                /*try{
                    count = Integer.parseInt(numberOfVertices.getText().toString());
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(MainActivity.this, "NaN", Toast.LENGTH_SHORT);
                    toast.show();
                }

                CreateGraph createGraph = new CreateGraph(count);
                vertices = createGraph.getVertices();
                ArrayAdapter<Vertex> adapter = new ArrayAdapter<Vertex>
                        (MainActivity.this, android.R.layout.simple_list_item_1, vertices);

                try {
                    listView.setAdapter(adapter);
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(MainActivity.this, "WTF, ADAPTER?", Toast.LENGTH_SHORT);
                    toast.show();
                }*/
            }
        });

    }

}
