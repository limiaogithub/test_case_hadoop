# test_case_hadoop

准备：
-------
* hadoop
```
hadoop-2.9.1.tar.gz
wget http://mirrors.hust.edu.cn/apache/hadoop/common/hadoop-2.9.1/hadoop-2.9.1.tar.gz
```
* jdk8
```
jdk-8u60-linux-x64.gz
```
* 本工程打成jar包
```
test_case_hadoop-jar-with-dependencies.jar
```
* map reduce输入
```
text.txt
```
* 其他
```
centos6.5+ 
```

配置：
-------

* 创建目录
```
mkdir /opt/app -p
```

* 上传tar包到/opt/app、解压
```
将hadoop-2.9.1.tar.gz、jdk-8u60-linux-x64.gz、test_case_hadoop-jar-with-dependencies.jar、text.txt上传到/opt/app目录下

tar -xvf hadoop-2.9.1.tar.gz
tar -xvf jdk-8u60-linux-x64.gz
```

* 设置java环境变量、HADOOP_HOME
```
配置环境变量
vim /etc/profile

在对下面加入
export JAVA_HOME=/opt/app/jdk1.8.0_60
export JRE_HOME=/opt/app/jdk1.8.0_60
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib
export HADOOP_HOME=/opt/app/hadoop-2.9.1

保存后，执行
source /etc/profile
java -version
```

* 初始化hadoop环境变量
```
chmod 755 -R $HADOOP_HOME
$HADOOP_HOME/etc/hadoop/hadoop-env.sh 
```

* 验证
```
$HADOOP_HOME/bin/hadoop
```

* vim $HADOOP_HOME/etc/hadoop/core-site.xml:
```
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
```
* vim $HADOOP_HOME/etc/hadoop/hdfs-site.xml:
```
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
</configuration>
```

* 测试ssh本机
```
关闭防火墙
service iptables stop

（cat /etc/ssh/sshd_config可以关注一下ssh配置文件）
ssh localhost

$ ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
$ cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
$ chmod 0600 ~/.ssh/authorized_keys
```

* 格式化
```
$HADOOP_HOME/bin/hdfs namenode -format
```

* 启动hadoop
```
手动修改了$HADOOP_HOME/etc/hadoop/hadoop-env.sh 修改JAVA_HOME
export JAVA_HOME=/opt/app/jdk1.8.0_60

$HADOOP_HOME/sbin/start-all.sh
```

测试：
-------

* 通过命令行创建目录、查询目录、添加文件、删除文件
```
$HADOOP_HOME/bin/hdfs dfs -mkdir -p /user/root/count/in
$HADOOP_HOME/bin/hdfs dfs -ls /user/root/count
```

* 测试自定义的map reduce
```
将本工程打包，传到$HADOOP_HOME
在hdfs创建目录/user/root/count/in，
$HADOOP_HOME/bin/hdfs dfs -mkdir /user/root/count/in
将text.txt上传到这个目录
$HADOOP_HOME/bin/hadoop fs -put /opt/app/test.txt /user/root/count/in
$HADOOP_HOME/bin/hadoop jar /opt/app/test_case_hadoop-jar-with-dependencies.jar
$HADOOP_HOME/bin/hadoop fs -ls /user/root/count/out
```

