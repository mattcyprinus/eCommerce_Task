package com.example.loginandcheckout;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Search extends AppCompatActivity {

    private EditText searchInput;
    private RecyclerView resultsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = findViewById(R.id.search_input);
        resultsRecyclerView = findViewById(R.id.search_results_rv);
        TextView backButton = findViewById(R.id.back_button);

        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Handle Search Action
        searchInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(searchInput.getText().toString());
                return true;
            }
            return false;
        });

        // Back Button
        backButton.setOnClickListener(v -> finish());

        // Navigation (Example)
        findViewById(R.id.nav_home).setOnClickListener(v -> { /* Intent to Home */ });
    }

    private void performSearch(String query) {
        if (query.isEmpty()) return;
        Toast.makeText(this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
        // TODO: Filter your data and update RecyclerView Adapter
    }
}