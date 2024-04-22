package com.kulsin.string_type_message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumeMessage {

    @KafkaListener(topics = "test", groupId = "groupId")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }



    /**
     * We can implement multiple listeners for a topic, each with a different group Id.
     * Furthermore, one consumer can listen for messages from various topics:

    @KafkaListener(topics = "topic1, topic2", groupId = "foo")
    public void multipleTopicListener(String message) {
        System.out.println("Received Message in group foo: " + message);
    }


    @KafkaListener(topics = "topicName")
    public void listenWithHeaders(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println(
                "Received Message: " + message + "from partition: " + partition);
    }
     */
    /**
     * Since the initialOffset has been set to 0 in this listener,
     * all the previously consumed messages from partitions 0 and 3 will be re-consumed every time this listener is initialized.

    @KafkaListener(
            topicPartitions = @TopicPartition(topic = "topicName",
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "0"),
                            @PartitionOffset(partition = "3", initialOffset = "0")}),
            containerFactory = "partitionsKafkaListenerContainerFactory")
    public void listenToPartition(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println(
                "Received Message: " + message + "from partition: " + partition);
    }
     */

    /**
     * If we don't need to set the offset,
     * we can use the partitions property of @TopicPartition annotation to set only the partitions without the offset:

    @KafkaListener(topicPartitions
            = @TopicPartition(topic = "topicName", partitions = { "0", "1" }))
    public void listenToPartitionWithoutOffset(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println(
                "Received Message: " + message + "from partition: " + partition);
    }

    */

    /**
     * In this listener, all the messages matching the filter will be discarded.

    @KafkaListener(
            topics = "topicName",
            containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        System.out.println("Received Message in filtered listener: " + message);
    }
     */

}
