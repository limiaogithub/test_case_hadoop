# test_case_hadoop
test hadoop

1.下载hadoop</br>
http://mirrors.hust.edu.cn/apache/hadoop/common/hadoop-2.9.1/hadoop-2.9.1.tar.gz</br>
</br>
2.解压</br>
tar -xvf hadoop-2.9.1.tar.gz</br>
</br>
3.参照官网</br>
http://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleCluster.html</br>
</br>
主要步骤如下：</br>
3.1  etc/hadoop/hadoop-env.sh </br>
export JAVA_HOME=/usr/java/latest</br>
</br>
3.2 验证</br>
bin/hadoop</br>
</br>
3.3</br>
$ mkdir input</br>
$ cp etc/hadoop/*.xml input</br>
$ bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.9.1.jar grep input output 'dfs[a-z.]+'</br>
$ cat output/*</br>

3.4 vim etc/hadoop/core-site.xml:</br>
<pre>
&lt;configuration&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;property&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;name&gt;fs.defaultFS&lt;/name&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;value&gt;hdfs://localhost:9000&lt;/value&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/property&gt;
&lt;configuration&gt;
</pre>

3.5 vim etc/hadoop/hdfs-site.xml:</br>
<pre>
&lt;configuration&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;property&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;name&gt;dfs.replication&lt;/name&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;value&gt;1&lt;/value&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/property&gt;
&lt;configuration&gt;
</pre>

</br>
3.6 ssh localhost</br>
3.7</br>
  $ ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa</br>
  $ cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys</br>
  $ chmod 0600 ~/.ssh/authorized_keys</br>
3.8</br>
  $ bin/hdfs namenode -format</br>
3.9</br>
  sbin/start-dfs.sh</br>
  </br>
3.10</br>
  bin/hdfs dfs -mkdir /user</br>
  

4.客户端调用需要配置host
5.可能出现权限问题，可以在服务端设置文件权限
./hadoop fs -chmod 755 /
  
  


