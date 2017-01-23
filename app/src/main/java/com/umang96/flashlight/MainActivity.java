package com.umang96.flashlight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.io.DataOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        check();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void flash_on(){
        String[] c1 = {"echo 100 > ~/sys/class/leds/led:torch_0/brightness","echo 100 > ~/sys/class/leds/led:torch_1/brightness"};
        RunAsRoot(c1);
        finish();
    }

    private void flash_off(){
        String[] c1 = {"echo 0 > ~/sys/class/leds/led:torch_0/brightness","echo 0 > ~/sys/class/leds/led:torch_1/brightness"};
        RunAsRoot(c1);
        finish();
    }

    private void check(){
        ShellExecutor exe = new ShellExecutor();
            String outp = exe.Executor("cat ~/sys/class/leds/led:torch_0/brightness");
            int x = Integer.parseInt(outp);
            if(x==0)
                flash_on();
            if(x==100)
                flash_off();
    }

    public void RunAsRoot(String[] cmds){
       /* Toast.makeText(getApplicationContext(), "RunAsRoot begins !",
                Toast.LENGTH_SHORT).show(); */
        try{
        Process p = Runtime.getRuntime().exec("su");
        DataOutputStream os = new DataOutputStream(p.getOutputStream());
        for (String tmpCmd : cmds) {
            os.writeBytes(tmpCmd+"\n");
        }
        os.writeBytes("exit\n");
        os.flush();
    }
    catch(Exception e)
    {
        Toast.makeText(getApplicationContext(), "RunAsRoot failed !",
                Toast.LENGTH_SHORT).show();
    }
    }

}
