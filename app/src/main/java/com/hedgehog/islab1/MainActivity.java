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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button graphButton;
    EditText numberOfVertices;
    EditText startVertex;
    EditText destinationVertex;
    ListView listView;
    CheckListDialogFragment checkListDialogFragment;
    CreateGraph createGraph;
    Graph graph;
    TextView textView;
    Lists lists;
    Searches searches;
    String s;
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
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final String[] strings = {"Поиск в ширину", "Поиск в глубину", "Поиск в глубину (рекурсия)"};

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.INVISIBLE);
                try {
                    count = Integer.parseInt(numberOfVertices.getText().toString());
                } catch (Exception e) {
                    Toast toast = Toast.makeText(MainActivity.this, "NaN", Toast.LENGTH_SHORT);
                    toast.show();
                }

                createGraph = new CreateGraph(count);
                ArrayAdapter<Vertex> adapter = new ArrayAdapter<Vertex>
                        (MainActivity.this, android.R.layout.simple_list_item_1, createGraph.getVertices());

                try {
                    listView.setAdapter(adapter);
                    Utils utils = new Utils();
                    utils.setListViewHeightBasedOnChildren(listView);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(MainActivity.this, "WTF, ADAPTER?", Toast.LENGTH_SHORT);
                    toast.show();
                }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        FragmentManager manager = getSupportFragmentManager();
                        checkListDialogFragment = new CheckListDialogFragment(createGraph, position, count);
                        checkListDialogFragment.show(manager, "Check adjacent vertices");
                    }
                });


            }
        });

        Button createGraphButton = (Button) findViewById(R.id.test_button);

        createGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    graph = new Graph(createGraph.getVertices());
                } catch (Exception e) {
                    String stringGraph = "Ошибка при создании графа " + e;
                    Toast toast = Toast.makeText(MainActivity.this, stringGraph, Toast.LENGTH_SHORT);
                    toast.show();
                }
                String stringGraph = "Создан граф:\n";
                for (int i = 0; i < count; i++) {
                    try {
                        stringGraph = stringGraph + " " + createGraph.getVertices().get(i).getStringAdjacentVertices() + "\n";
                    } catch (Exception e) {
                    }
                }
                Toast toast = Toast.makeText(MainActivity.this, stringGraph, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, strings);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    lists = new Lists(graph);
                } catch (Exception e) {
                    /*String stringGraph = "Ошибка при создании списков " + e;
                    Toast toast = Toast.makeText(MainActivity.this, stringGraph, Toast.LENGTH_SHORT);
                    //toast.show();*/
                }
                try {
                    searches = new Searches(new Lists(graph), createGraph.getVertices().get(Integer.parseInt(startVertex.getText().toString()) - 1),
                            createGraph.getVertices().get(Integer.parseInt(destinationVertex.getText().toString()) - 1));
                } catch (Exception e) {
                    /*String stringGraph = "Ошибка при создании объекта поиска " + e;
                    Toast toast = Toast.makeText(MainActivity.this, stringGraph, Toast.LENGTH_SHORT);
                    //toast.show();*/
                }
                textView = (TextView) findViewById(R.id.path);
                switch (position) {

                    case 0:
                        try {
                            searches.breadthSearch(graph.getVertices(), lists.getOpen(), lists.getClosed());
                        } catch (Exception e) {
                            /*String ex = "Ошибка в поиске " + e;
                            Toast toast = Toast.makeText(MainActivity.this, ex, Toast.LENGTH_LONG);
                            //toast.show();*/
                        }

                        try {
                            s = "Путь от вершины " + startVertex.getText().toString() + " до вершины " + destinationVertex.getText().toString()
                                    + " был пройден за " + searches.getBreadthCount() + " шага(шагов) при поиске в ширину.";
                        } catch (Exception e) {

                        }
                        textView.setText(s);
                        textView.setVisibility(View.VISIBLE);
                        break;

                    case 1:
                        try {
                            searches.depthSearch(graph.getVertices(), lists.getOpen(), lists.getClosed());
                        } catch (Exception e) {
                            /*String ex = "Ошибка в поиске " + e;
                            Toast toast = Toast.makeText(MainActivity.this, ex, Toast.LENGTH_LONG);
                            //toast.show();*/
                        }
                        try {
                            s = "Путь от вершины " + startVertex.getText().toString() + " до вершины " + destinationVertex.getText().toString()
                                    + " был пройден за " + searches.getDepthCount() + " шага(шагов) при поиске в глубину.";
                        } catch (Exception e) {

                        }
                        textView.setText(s);
                        textView.setVisibility(View.VISIBLE);
                        break;

                    case 2:
                        try {
                            searches.depthRecSearch(searches.getStartVertex(), graph.getVertices(), lists.getClosed());
                        } catch (Exception e) {
                            /*String ex = "Ошибка в поиске " + e;
                            Toast toast = Toast.makeText(MainActivity.this, ex, Toast.LENGTH_LONG);
                            //toast.show();*/
                        }
                        try {
                            s = "Путь от вершины " + startVertex.getText().toString() + " до вершины " + destinationVertex.getText().toString()
                                    + " был пройден за " + searches.getRecCount() + " шага(шагов) при рекурсивном поиске в глубину.";
                        } catch (Exception e) {

                        }
                        textView.setText(s);
                        textView.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}
