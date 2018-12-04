package com.github.yt.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class Test {

    public void upload() throws IOException {
        Configuration conf = new Configuration();
        //必须用域名
        conf.set("fs.default.name", "hdfs://limiao1:9000/");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");

        FileSystem hdfs = FileSystem.get(conf);
        String dir = "/test2/";
        Path path2 = new Path(dir);
        if (hdfs.exists(path2)) {
            System.out.println("dir \t" + conf.get("fs.default.name") + dir
                    + "\t already exists");
            return;
        }
        hdfs.mkdirs(path2);
        System.out.println("new dir \t" + conf.get("fs.default.name") + dir);
    }

    public static void main(String[] args) {
        Test test = new Test();
        try {
            test.upload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
