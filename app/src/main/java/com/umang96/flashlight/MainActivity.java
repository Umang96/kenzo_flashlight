package com.umang96.flashlight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButtonClickListener1();
        TorchUtils ob = new TorchUtils();
        ob.Executor("");
    }

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

}
