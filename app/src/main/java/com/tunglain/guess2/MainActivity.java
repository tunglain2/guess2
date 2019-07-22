package com.tunglain.guess2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 100;
    private EditText edNumber;
    private Button bGuess;
    private TextView tvCounter;
    private GuessViewModel viewModel;
    private boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!login) {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivityForResult(intent,REQUEST_LOGIN);
        }


        viewModel = ViewModelProviders.of(this).get(GuessViewModel.class);

        viewModel.getCounter().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer counter) {
                tvCounter.setText(String.valueOf(counter));
            }
        });

        viewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Result")
                        .setMessage(message)
                        .setNeutralButton("OK",null)
                        .setPositiveButton("Restart",(dialog,which) -> {
                            viewModel.resetGame();
                            edNumber.setText("");
                        })
                        .show();
            }
        });

        findViews();
        viewModel.resetGame();

        bGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(edNumber.getText().toString());
                viewModel.setNumber(number);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode != RESULT_OK) {
                finish();
            }
        }
    }

    private void findViews() {
        edNumber = findViewById(R.id.ed_number);
        tvCounter = findViewById(R.id.tv_counter);
        bGuess = findViewById(R.id.b_guess);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
