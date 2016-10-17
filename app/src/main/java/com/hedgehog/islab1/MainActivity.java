package com.hedgehog.islab1;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Button graphButton;
    EditText numberOfVertices;
    EditText startVertex;
    EditText destinationVertex;
    ListView listView;
    MyDialogFragment myDialogFragment;
    int count;


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

                final CreateGraph createGraph = new CreateGraph(count);
                ArrayAdapter<Vertex> adapter = new ArrayAdapter<Vertex>
                        (MainActivity.this, android.R.layout.simple_list_item_1, createGraph.getVertices());

                try {
                    listView.setAdapter(adapter);
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(MainActivity.this, "WTF, ADAPTER?", Toast.LENGTH_SHORT);
                    toast.show();
                }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        FragmentManager manager = getSupportFragmentManager();
                        myDialogFragment = new MyDialogFragment(createGraph, position);
                        myDialogFragment.show(manager, "dialog");

                        Button testButton = (Button) findViewById(R.id.test_button);

                        testButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Graph graph = new Graph(createGraph.getVertices());
                                String stringGraph = "";

                                for (int i = 0; i < count; i++) {
                                    stringGraph = stringGraph + " " + createGraph.getVertices().get(i).getStringAdjacentVertices()+ "\n";
                                }

                                Toast toast = Toast.makeText(MainActivity.this, stringGraph, Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });

                /*String pos = "position: " + position + ", id: " + id;
                Toast toast3 = Toast.makeText(MainActivity.this, pos, Toast.LENGTH_SHORT);
                toast3.show();*/

                    }
                });
            }
        });





    }

}
