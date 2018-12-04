# test_case_hadoop

准备：
---
1.下载hadoop
```
http://mirrors.hust.edu.cn/apache/hadoop/common/hadoop-2.9.1/hadoop-2.9.1.tar.gz</br>
```
2.解压
```
tar -xvf hadoop-2.9.1.tar.gz
```
3.参照官网
```
http://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleCluster.html
```

测试 map reduce：
---

1 设置java环境变量
```
export JAVA_HOME=/usr/java/latest</br>
```

2 初始化hadoop环境变量
```
etc/hadoop/hadoop-env.sh </br>
```

3 验证
```
bin/hadoop</br>
```

4 测试 map reduce
```
$ mkdir input
$ cp etc/hadoop/*.xml input
$ bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.9.1.jar grep input output 'dfs[a-z.]+'
$ cat output/*
```

测试 hdfs：
---


1 vim etc/hadoop/core-site.xml:
```
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
```
2 vim etc/hadoop/hdfs-site.xml:
```
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
</configuration>
```

3 测试ssh本机
```
ssh localhost

$ ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa</br>
$ cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys</br>
$ chmod 0600 ~/.ssh/authorized_keys</br>
```

4.格式化
```
$ bin/hdfs namenode -format
```

5.启动hdfs
```
sbin/start-dfs.sh

注：启动时会报找不到JAVA_HOME，我是手动修改了etc/hadoop/hadoop-env.sh 显示生命了JAVA_HOME
```

6 通过命令行创建目录、查询目录
```
bin/hdfs dfs -mkdir /user</br>
bin/hdfs dfs -ls /
```

通过程序操作hdfs：
---
todo
```
客户端调用需要配置host
可能出现权限问题，可以在服务端设置文件权限
./hadoop fs -chmod 755 /
  
```

