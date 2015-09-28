package com.aol.logprocessor.hadoop;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Application {

    public static void main(String[] args) throws Exception {

        Tool job = new ProcessorJob();
        int status = ToolRunner.run(job, args);
        System.exit(status);
    }

}
