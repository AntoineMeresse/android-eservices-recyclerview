package android.eservices.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        //TODO Bind recyclerview and set its adapter.

        recyclerView = findViewById(R.id.my_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Use data generator to get data to display.
        List<GameViewModel> myDataset = DataGenerator.generateData();

        GameAdapter gameAdapter = new GameAdapter();
        recyclerView.setAdapter(gameAdapter);

        gameAdapter.bindGameViewModelList(myDataset);
    }

    public void displaySnackBar(String message) {
        //TODO write a method that displays a snackbar in the coordinator layout with the "message" parameter as content.
    }

    //TODO create callback methods for item click
    //Use ressource strings to get the text to display

}
