package android.eservices.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GameActionInterface{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton changeLayoutButton;
    private boolean switchBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        changeLayoutButton = findViewById(R.id.fabSwitch);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        setupRecyclerView();

    }

    private void setupRecyclerView() {
        //TODO Bind recyclerview and set its adapter.

        recyclerView = findViewById(R.id.my_recyclerview);
        recyclerView.setHasFixedSize(true);

        final RecyclerView.LayoutManager layoutManager_lign = new LinearLayoutManager(this);
        final RecyclerView.LayoutManager layoutManager_grid = new GridLayoutManager(this,2);

        layoutManager = layoutManager_lign;
        recyclerView.setLayoutManager(layoutManager);

        changeLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchBoolean) {
                    switchBoolean = false;
                    layoutManager = layoutManager_lign;
                }
                else {
                    switchBoolean = true;
                    layoutManager = layoutManager_grid;
                }
                recyclerView.setLayoutManager(layoutManager);
            }
        });

        // Assign layout
        //recyclerView.setLayoutManager(layoutManager);

        //Use data generator to get data to display.
        List<GameViewModel> myDataset = DataGenerator.generateData();

        GameAdapter gameAdapter = new GameAdapter(this);
        recyclerView.setAdapter(gameAdapter);

        gameAdapter.bindGameViewModelList(myDataset);
    }

    public void displaySnackBar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    //TODO create callback methods for item click
    //Use ressource strings to get the text to display

    @Override
    public void onGameInfoClicked(String gameTitle) {
        //Log.e("GAMEINFOCLICKED", "onGameInfoClicked: "+gameTitle);
        displaySnackBar("Game Infos clicked : " + gameTitle);
    }

    @Override
    public void onGameClicked(String gameTitle) {
        displaySnackBar("Game clicked : " + gameTitle);
    }

}
