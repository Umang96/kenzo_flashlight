package com.umang96.flashlight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButtonClickListener1();
        addButtonClickListener2();;
        et1 = (EditText) findViewById(R.id.editText1);
        TorchUtils ob = new TorchUtils();
        ob.Executor("");
        pref_load();
    }

    public String tile_label;
    EditText et1;

    private void addButtonClickListener1() {
        Button b1 = (Button) findViewById(R.id.button1);
        assert b1 != null;
        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                        TorchUtils ob = new TorchUtils();
                                        ob.check(getApplicationContext());
                                  }
                              }
        );
    }

    private void addButtonClickListener2() {
        Button b1 = (Button) findViewById(R.id.button2);
        assert b1 != null;
        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      tile_label=et1.getText().toString();
                                      pref_edit(tile_label);
                                  }
                              }
        );
    }

    public void pref_edit(String st) {
        SharedPreferences.Editor editor = getSharedPreferences("tile_preferences", MODE_PRIVATE).edit();
        editor.putString("tile_name", st);
        editor.commit();
        Toast.makeText(getApplicationContext(), "Done, Enjoy !",
                Toast.LENGTH_LONG).show();
    }

    public void pref_load() {
        SharedPreferences prefs = getSharedPreferences("tile_preferences", MODE_PRIVATE);
        String restoredText = prefs.getString("tile_name", null);
        if (restoredText != null) {
            String temp = prefs.getString("tile_name", "Flashlight");
            et1.setText(temp);
        }
    }

}
