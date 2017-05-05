package mmry.rabbitmq.helloworld;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 创建连接
		ConnectionFactory factory = new ConnectionFactory();
		// 设置MabbitMQ, 主机ip或者主机名
		factory.setHost("localhost");
		// 创建一个连接
		Connection connection = factory.newConnection();
		// 创建一个通道
		Channel channel = connection.createChannel();
		// 指定一个队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 发送消息
		String message = "Hello World";
		for(int i=0;i<20;i++){
			String msg = message + "----" + i;
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		}
		System.out.println(" [x] Sent '" + message + "'");
		// 关闭频道和连接
		channel.close();
		connection.close();
	}
}
