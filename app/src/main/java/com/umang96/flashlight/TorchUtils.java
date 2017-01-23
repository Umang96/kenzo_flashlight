package com.umang96.flashlight;

import android.content.Context;
import android.widget.Toast;

import java.io.DataOutputStream;

/**
 * Created by Hicham on 23/01/2017.
 */

public class TorchUtils {

    private static void flash_on(Context context){
        String[] c1 = {"echo 100 > ~/sys/class/leds/led:torch_0/brightness","echo 100 > ~/sys/class/leds/led:torch_1/brightness"};
        RunAsRoot(c1, context);
    }

    private static void flash_off(Context context){
        String[] c1 = {"echo 0 > ~/sys/class/leds/led:torch_0/brightness","echo 0 > ~/sys/class/leds/led:torch_1/brightness"};
        RunAsRoot(c1, context);
    }

    public static boolean check(Context context){
        ShellExecutor exe = new ShellExecutor();
        String outp = exe.Executor("cat ~/sys/class/leds/led:torch_0/brightness");
        int x = Integer.parseInt(outp);
        if(x==0)
        {
            flash_on(context);
            return true;
        }
        if(x==100)
        {
            flash_off(context);
        }
        return false;
    }

    public static void RunAsRoot(String[] cmds, Context context){
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
            Toast.makeText(context, "RunAsRoot failed !",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
