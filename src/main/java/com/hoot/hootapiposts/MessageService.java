package com.hoot.hootapiposts;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class MessageService
{
    public static MessageService Instance = new MessageService();
    private Connection connection;
    private Channel channel;

    MessageService()
    {
        try {
            // Connect to the deleted-objects message queue
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("hoot-message-queues");
            this.connection = factory.newConnection();
            this.channel = this.connection.createChannel();
            this.channel.queueDeclare("deleted-objects", true, false, false, null);
        }
        catch (Exception e) {
            System.out.println("Failed to initialise MessageService. " + e.getMessage());
        }
    }

    // Called when an object is deleted so that subscribers may process the deletion as required
    public void PostDeletionMessage(String type, Long objId)
    {
        try {
            byte[] body = (new MessageBody(type, objId).ToString().getBytes());
            this.channel.basicPublish("", "deleted-objects", null, body);
            System.out.println("Deleted " + type + " " + objId);
        }
        catch (Exception e) {
            System.out.println("Failed to PostDeletionMessage. " + e.getMessage());
        }
    }
}
