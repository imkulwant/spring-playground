# spring-jms-app

## JMS Specification and MOM

- Set of interfaces and terms implemented by provider
- Message-Oriendted middleware
- JMS providers furnish implementations in APIs
- A few types of MOMs(ActiveMQ, RabbitMQ, JBossMQ etc.)

## Two Messaging Domain Types
- **Point to Point (PTP)**
    - PTP operates around Queues
    - MSG is published to a specific queue by a producer
    - MSG is read and removed by a consumer
    
- **Publish/subscribe (pub/sub)**
    - pub/sub operates around Topics
    - MSG is published to a specific topic on MOM by a producer
    - MSG is read by one or more subscribers

## JMS 1.1 vs Spring JMS API
- Spring JMS API is not a MOM provider
- Spring JMS API is an abstraction layer to JMS 1.1
- Spring JMS API needs a MOM API to communicate to a MOM

## Using JMS 1.1 Specification
    1. Create the JNDI initial context
    2. Obtain a ConnectionFactory from the JNDI context
    3. Obtain a queue from the ConnectionFactory
    4. Create a Session object from the queue
    5. Create a Sender or Receiver object from the Session object
    6. Sender or Receiver object can send or receive a message
    7. Close all the JMS resources used for processing the message 
    
## Using Spring JMS API
    1. Abstracts the first 5 steps and the last step into one
    2. Manages the number of JMS resources needed to send and receive
    
### Spring Framework Template Class APIs
```
- JDBC -> JdbcTemplate
- JNDI -> JndiTemplate
- JMS -> JmsTemplate
    - JMS messaging operations distilled into send and receive message tasks
    - Makes development easier by abstracting all connection, session, and JNDI contexts

These are transactional and excellent at managing resources to open and close connections
```

## What is a Message Queue?
- A collection of JMS messages on a MOM or MQ server(can contain queues and topics)
- Retain messages until a consumer retrieves them
- Retrieved messages are pulled in the order they are received FIFO

## Message Queues
- MQs can have multiple consumers, but one message in the queue can have one consumer.
- Guarantees the message in queue is processed once.
- Point to point model domain design

## Topics
- Can have multiple consumers
    - Can be processed multiple times
    - FIFO does not apply
- Consumers must be active when message is published
    - Durable subscription important
    - pub/sub domain model design
    
# JMS Spec provides 5 Basic Message Types
- Streams - StreamMessages
- Maps - MapMessages
- Text - TextMessages
- Object - ObjectMessages
- Bytes - BytesMessages

These messages type exist for all other message types
- JSON or XML messages -> JMS TextMessage type
- Images/sound/music files -> JMS ByteMessage type

# How does Spring Handle these
-  Spring provides default conversion with - SimpleMessageConverter
- SimpleMessageConverter can handle - TextMessage, BytesMessage, ObjectMessage, MapMessages
- SimpleMessageConverter does not support - StreamMessages
- Sending messages via JmsTemplate.convertAndSend() method's default conversion strategy is SimpleMessageConverter
- If you need a different conversion strategy, then specify a different converter
    
# Other message converters Spring provides
- MappingJackson2MessageConverter
    - Uses Jackson 2.x API to convert messages to and from JSON
    - Internally, maps objects to BytesMessage or TextMessage depends on what targetType property is set
- MarshallingMessageConverter
    - Uses Marshaller and Unmarshaller to marshall
    - These are marshalled to BytesMessage or TestMessage again depending what targetType property is set
- MessagingMessageConverter
    - Relies on underlying MessageConverter for payloads
    - Also uses JmsHeaderMapper to map JMS headers
# How to create our own message converter
- By using spring's MessageConverter interface, it has 2 methods:
    - toMessage(obj,ses)
        - Accepts an object to be converted, and a session instance
        - Returns a message
    - fromMessage(msg)
        - Accepts a message to be converted
        - Returns a Java object    