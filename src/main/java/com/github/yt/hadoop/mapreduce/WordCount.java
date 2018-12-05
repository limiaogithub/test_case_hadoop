package com.github.yt.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);
        //指定本次mr job jar包运行主类
        job.setJarByClass(WordCount.class);

        //指定本次mr 所用的mapper reducer类分别是什么
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //指定本次mr mapper阶段的输出  k  v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //指定本次mr 最终输出的 k v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // job.setNumReduceTasks(3); //ReduceTask个数

        //如果业务有需求，就可以设置combiner组件
        //job.setCombinerClass(WordCountReducer.class);
        FileInputFormat.setInputPaths(job, new Path("/user/root/count/in"));
        FileOutputFormat.setOutputPath(job, new Path("/user/root/count/out"));
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
