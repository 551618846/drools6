/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.mvc.drools
* @文件名：Hello2.java
* @创建时间： 2014年10月29日 上午11:16:50
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
 * @类名：Hello2
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年10月29日 上午11:16:50
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class Hello2 {

    /**
     * @方法名：main
     * @描述：TODO 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2014年10月29日 上午11:16:50
     * @param args 
     * @返回值：void 
     * @异常说明：
     */
    public static void main(String[] args) {
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
//        ksession.addEventListener( new DebugAgendaEventListener() );
//        ksession.addEventListener( new DebugRuleRuntimeEventListener() );

        // To setup a file based audit logger, uncomment the next line 
        // KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./helloworld" );
        
        // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );

        // The application can insert facts into the session
        A a = new A();
        a.setName("a");
        
        B b = new B();
        b.setName("b");
        
        A a1 = new A();
        a1.setName("a1");
        
        B b1 = new B();
        b1.setName("b1");
        
        ksession.insert( a );
        ksession.insert( b );
        
        
        //测试"规则"如何匹配工作内存中的对象:对规则A,规则中使用到的对象类型,从工作内存取出一个组合C,与规则进行匹配
        ksession.insert( a1 );
        ksession.insert( b1 );

        // and fire the rules
        ksession.fireAllRules();
        /**
         * 运行结果:C21+C21+C21*C21+C21*C21=12
         rule a ,name is :a1
            rule a ,name is :a
            rule b ,name is :b1
            rule b ,name is :b
            rule ab ,name is :a+b
            rule ab ,name is :a+b1
            rule ab ,name is :a1+b
            rule ab ,name is :a1+b1
            rule aa ,name is :a+a
            rule aa ,name is :a+a1
            rule aa ,name is :a1+a
            rule aa ,name is :a1+a1
            rule aora ,name is :a1,com.eelly.mvc.drools.Hello2$A@6920aca
            rule aora ,name is :a,com.eelly.mvc.drools.Hello2$A@3fa8a0fa
            rule aora ,name is :a1,com.eelly.mvc.drools.Hello2$A@6920aca
            rule aora ,name is :a,com.eelly.mvc.drools.Hello2$A@3fa8a0fa
            rule null when 
         */
        // Remove comment if using logging
        // logger.close();

        // and then dispose the session
        ksession.dispose();
    }
    
    
    public static class A{
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    public static class B{
        private String name;
        
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}

