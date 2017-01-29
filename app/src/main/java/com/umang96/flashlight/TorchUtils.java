package com.umang96.flashlight;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;


public class TorchUtils {

    private static void flash_on(Context context){
        String[] c1 = {"echo 100 > ~/sys/class/leds/led:torch_0/brightness","echo 100 > ~/sys/class/leds/led:torch_1/brightness"};
        RunAsRoot(c1, context);
    }

    private static void flash_off(Context context){
        String[] c1 = {"echo 0 > ~/sys/class/leds/led:torch_0/brightness","echo 0 > ~/sys/class/leds/led:torch_1/brightness"};
        RunAsRoot(c1, context);
    }

    public static boolean check(Context context, int x){
        String outp = Executor("cat ~/sys/class/leds/led:torch_0/brightness");
        try {
            if (outp.length()==1) {
                if(x==2) {
                    flash_on(context);
                }
                return true;
            }
            if (!(outp.length()==1)) {
                if(x==2) {
                flash_off(context);
            }
            }
        }
        catch(Exception e)
        {
            Toast.makeText(context, "Error, have you granted root ?",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public static String Executor(String command) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(new String[] { "su", "-c", command });
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String response = output.toString();
        return response;

    }

    public static void RunAsRoot(String[] cmds, Context context){
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
