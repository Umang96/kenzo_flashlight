package com.umang96.flashlight;



        import java.io.BufferedReader;
        import java.io.InputStreamReader;

public class ShellExecutor {

    public ShellExecutor() {

    }

    public String Executor(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(new String[] { "su", "-c", command });
            p.waitFor();
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
}