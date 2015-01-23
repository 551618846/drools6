/** 
* @组件名：eelly_springmvc_component
* @包名：com.eelly.mvc.drools
* @文件名：Person.java
* @创建时间： 2014年10月30日 上午9:32:53
* @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
*/

package com.huangzl.drools6;

import java.util.ArrayList;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


/**
 * @类名：Person
 * @描述: 
 * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
 * @修改人：
 * @修改时间：2014年10月30日 上午9:32:53
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class Person {
    
    private String name;
    private int age;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @方法名：main
     * @描述：TODO 
     * @创建人：<a href=mailto: huangzhenliang@eelly.net>huangzhenliang</a>
     * @修改人：
     * @修改时间：2014年10月30日 上午9:32:53
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
//        ksession.setGlobal( "list",
//                            new ArrayList<Object>() );

        // The application can also setup listeners
//        ksession.addEventListener( new DebugAgendaEventListener() );
//        ksession.addEventListener( new DebugRuleRuntimeEventListener() );

        // To setup a file based audit logger, uncomment the next line 
        // KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./helloworld" );
        
        // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );

        // The application can insert facts into the session
        Person a = new Person();
        a.setName("zhangsan");
        a.setAge(10);
        
        Person b = new Person();
        b.setName("zhangsan");
        b.setAge(11);
        
        Person a1 = new Person();
        a1.setName("zhangsan");
        a1.setAge(12);
        
        Person b1 = new Person();
        b1.setName("zhangsan");
        b1.setAge(13);
        
        ksession.insert( a );
        ksession.insert( b );
        
        
        //测试"规则"如何匹配工作内存中的对象:对规则A,规则中使用到的对象类型,从工作内存取出一个组合C,与规则进行匹配
//        ksession.insert( a1 );
//        ksession.insert( b1 );

        // and fire the rules
        ksession.fireAllRules();
        
        ksession.dispose();

    }

}

