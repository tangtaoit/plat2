package com.tt.test.boot;


import com.tt.plat.theme.def.page.BasePage;

/**
 * Created by tao on 2015/3/29.
 */
public class Homepage extends BasePage {


    public Homepage() {

        super();



//        TestPanel testPanel = new TestPanel("test",getBreadCrumbBar());
//        add(testPanel);
//
//        getBreadCrumbBar().setActive(testPanel);
    }

//    public static void main(String[] args) throws JMSException, InterruptedException {
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("karaf","karaf","tcp://123.57.62.153:61616");
//
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//
//        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createQueue("my-queue");
//        MessageProducer producer = session.createProducer(destination);
//        for(int i=0; i<3; i++) {
//            MapMessage message = session.createMapMessage();
//            message.setLong("count", new Date().getTime());
//            Thread.sleep(1000);
//            //通过消息生产者发出消息
//            producer.send(message);
//        }
//        session.commit();
//        session.close();
//        connection.close();
//    }
}
