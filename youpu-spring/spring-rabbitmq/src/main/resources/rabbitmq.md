# mac 安装rabbitmq

## 1.使用brew来安装 RabbitMQ
```
brew install rabbitmq

```
注意： rabbitmq的安装目录： /usr/local/Cellar/rabbitmq/3.6.6


## 2.RabbitMQ 的启动

进入到 /usr/local/Cellar/rabbitmq/3.6.9_1，执行

```
bogon:3.6.9_1 baoan$  sbin/rabbitmq-server
```
## 3.RabbitMQ 启动插件
待RabbitMQ 的启动完毕之后，另起终端进入cd /Users/lidong/javaEE/rabbitmq_server-3.6.6/sbin 。启动插件：

```
 sudo ./rabbitmq-plugins enable rabbitmq_management（执行一次以后不用再次执行）

```
## 4.登陆管理界面
```
http://localhost:15672/
```

账号密码初始默认都为guest


# Spring 集成

## 1.引入 RabbitMQ 需要两个包 
```
<!-- RabbitMQ -->
<dependency>
    <groupId>com.rabbitmq</groupId>
    <artifactId>amqp-client</artifactId>
    <version>3.5.1</version>
</dependency>
<dependency>
    <groupId>org.springframework.amqp</groupId>
    <artifactId>spring-rabbit</artifactId>
    <version>1.4.5.RELEASE</version>
</dependency>
```
## 2. 使用外部参数文件 application.properties：
   
```
mq.host=127.0.0.1
mq.username=queue
mq.password=1234
mq.port=8001
# 统一XML配置中易变部分的命名
mq.queue=test_mq
```

修改 applicationContext.xml 文件，引入我们创建的 properties 文件

```
context:property-placeholder location="classpath:application.properties"/>
<util:properties id="appConfig" location="classpath:application.properties"></util:properties>
```

## 3. 连接 RabbitMQ 服务器
   
```
<!-- 连接配置 -->
<rabbit:connection-factory id="connectionFactory" host="${mq.host}" username="${mq.username}"
        password="${mq.password}" port="${mq.port}"  />
        
<rabbit:admin connection-factory="connectionFactory"/>
```

## 4. 声明一个 RabbitMQ Template
   
```
<rabbit:template id="amqpTemplate" exchange="${mq.queue}_exchange" connection-factory="connectionFactory"  />

```

## 5. 在 applicationContext.xml 中声明一个交换机。
   
```
<rabbit:topic-exchange name="${mq.queue}_exchange" durable="true" auto-delete="false">
    <rabbit:bindings>
        <rabbit:binding queue="test_queue" pattern="${mq.queue}_patt"/>
    </rabbit:bindings>
</rabbit:topic-exchange>
```

## 6. 在 applicationContext.xml 中声明一个队列。
   
```
<rabbit:queue id="test_queue" name="${mq.queue}_testQueue" durable="true" auto-delete="false" exclusive="false" />

```

## 7. 创建生产者端
   
## 8. 在 applicationContext.xml 中配置监听及消费者端
   
## 9. 如何使用 RabbitMQ 发送一个消息
   
