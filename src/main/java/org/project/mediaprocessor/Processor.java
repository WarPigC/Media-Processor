package org.project.mediaprocessor;

import java.lang.InterruptedException;
import java.lang.ProcessBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Process;

public class Processor{

    public void process(String[] cmds) throws IOException,InterruptedException{
        
        ArrayList<String> list = new ArrayList<String>();
        list.add("..\\MediaProcessor\\src\\main\\java\\org\\project\\ffmpegEssentials\\bin\\ffmpeg.exe");
        list.add("-version");                                             // change command from -h
        //list.add("C:/Users/callm/downloads/testing.mp4");
        //list.add("C:/Users/callm/downloads/123.mp4");                 add any file

        ProcessBuilder pro = new ProcessBuilder(list);
        pro.inheritIO();
        Process prcs = pro.start();

        prcs.waitFor();

    }

}
