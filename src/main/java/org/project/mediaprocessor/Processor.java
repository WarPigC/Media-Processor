package org.project.mediaprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.InterruptedException;
import java.lang.ProcessBuilder;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.lang.Process;

public class Processor{         // keep sending Output to CompletionScene   (check for 'progress')

    public void process(ArrayList<String> cmds) throws IOException,InterruptedException{

        ArrayList<String> com = new ArrayList<String>();
        com.add("ffmpeg.exe");
        com.add("-i");
        for(int i = 0; i < cmds.size(); ++i){
            com.add(cmds.get(i));
        }

        ProcessBuilder pro = new ProcessBuilder(com);
        pro.inheritIO();
        Process prcs = pro.start();
    }

}