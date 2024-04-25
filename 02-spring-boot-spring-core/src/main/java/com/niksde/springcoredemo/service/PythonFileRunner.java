package com.niksde.springcoredemo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class PythonFileRunner {
    public boolean runFile() {
        boolean successfully = false;
        String pythonScriptPath = "src/main/resources/scripts/file_check.py";

        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);
        try {
            Process process = processBuilder.start();

            BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = stdOutput.readLine()) != null) {
                System.out.println(s);
                if (s.contains("Rain python script"))
                    successfully = true;
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return successfully;
    }

}
