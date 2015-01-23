/** 
* @组件名：drools6
* @包名：com.huangzl.drools6
* @文件名：HelloWorld.java
* @创建时间： 2014年10月27日 下午2:27:41
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.drools6;

import java.util.ArrayList;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @类名：HelloWorld
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年10月27日 下午2:27:41
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class HelloWorld {
    
    

    /**
     * @方法名：main
     * @描述：TODO 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2014年10月27日 下午2:27:41
     * @param args 
     * @返回值：void 
     * @异常说明：
     */
    public static void main(String[] args) {
     // KieServices is the factory for all KIE services 
        KieServices ks = KieServices.Factory.get();
        
        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();
        
        // From the container, a session is created based on  
        // its definition and configuration in the META-INF/kmodule.xml file 
        KieSession ksession = kc.newKieSession("HelloWorldKS");
        
        // Once the session is created, the application can interact with it
        // In this case it is setting a global as defined in the 
        // org/drools/examples/helloworld/HelloWorld.drl file
        ksession.setGlobal( "list",
                            new ArrayList<Object>() );

        // The application can also setup listeners
        ksession.addEventListener( new DebugAgendaEventListener() );
        ksession.addEventListener( new DebugRuleRuntimeEventListener() );

        // To setup a file based audit logger, uncomment the next line 
        // KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./helloworld" );
        
        // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );

        // The application can insert facts into the session
        final Message message = new Message();
        message.setName("huangzl1");
        ksession.insert( message );

        // and fire the rules
        ksession.fireAllRules();
        
        System.out.println(message.getName() + ":" + message.getResult());
        
        // Remove comment if using logging
        // logger.close();

        // and then dispose the session
        ksession.dispose();

    }
    
    public static class Message{
        private String name;
        private String result;
        
        /**
         * @return the name
         */
        public String getName() {
            return name;
        }
        
        /**    
         * @param name 要设置的 name    
         */
        public void setName(String name) {
            this.name = name;
        }
        
        /**
         * @return the result
         */
        public String getResult() {
            return result;
        }
        
        /**    
         * @param result 要设置的 result    
         */
        public void setResult(String result) {
            this.result = result;
        }
        
        
    }

}



