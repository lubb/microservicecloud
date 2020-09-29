微服务

阿里云视频地址：https://developer.aliyun.com/course/1914?spm=5176.266315.1396729.3.54762b06dwg9Ms
业界大牛马丁.福勒 https://martinfowler.com/articles/microservices.html
 While there is no precise definition of this architectural style 对于微服务业界并没有一个统一的、标准的定义
In short, the microservice architectural style [1] is an approach to developing a single application as a suite of small services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API. These services are built around business capabilities and independently deployable by fully automated deployment machinery. There is a bare minimum of centralized management of these services, which may be written in different programming languages and use different data storage technologies.
1、它提倡将单一应用程序划分成一组小的服务，每个服务运行在其独立的进程中
2、服务之间采用轻量级的通信机制相互沟通（dubbo：rpc；springcloud：基于HTTP的restful Api）

微服务化的核心就是将传统的一站式应用，根据业务拆分成一个一个的服务，彻底地去耦合，每一个微服务提供给耽搁业务功能的服务，一个服务做一件事，从技术角度看就是一种小而独立的处理过程，类似进程概念，能够自行独立启动或销毁，拥有自己独立的数据库。

微服务优缺点
优点：1、每个服务足够内聚，足够小，代码容易理解这样能聚焦一个置顶的业务功能或者业务需求
            2、开发简单，开发效率高，一个服务可能就是转移的只干一件事
            3、微服务能够被小团队独立开发，这个团队是2到5人的开发人员组成
            4、微服务是松耦合的，是有概念意义的服务，无论是在开发阶段还是在部署阶段都是独立  
            5、易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具
            6、微服务易于被一个开发人员理解、维护和修改，小团队更能够关注自己的工作成果
            7、微服务允许你利用最新的技术
             8、 微服务知识业务逻辑的代码，不回和HTML、css或者其他界面组件混合
             9、每个微服务都有自己的存储能力，可以有自己的数据库，也可以统一数据库
缺点：
          1、开发人员需要处理分布式系统的复杂性
          2、多服务韵味难度、随着服务的增加，运维的压力也在增大
          3、系统部署依赖
          4、服务之间通信成本
          5、数据一致性
          6、系统集成测试、性能监控

微服务技术栈 ：多种技术的集合体
服务治理
服务注册与发现 eureka、zookeeper
服务调用 rpc、rest
服务断路器 Hystrix、envoy
服务负载均衡 ribbon、nginx
服务路由 zuul
服务接口调用：Feign
服务监控

为什么选择springCloud作为微服务框架 netflix
微服务架构：
阿里Dubbo/HSF
京东JSF
新浪微博Motan
当当网DubboX
facebook Thrift
谷歌   gRPC


SpringCloud入门
1、什么是SpringCloud
springcloud，基于Springboot提供了一套微服务解决方案，包括服务注册与发现，配置中心，全链路健康，服务网关，负载均衡，熔断器等组件，除了基于NetFlix的开源组件做高度抽象封装
2、springCloud与springBoot的区别
springboot专注于快速方便的开发单个个体微服务
springcloud是关注全局的微服务协调整理治理框架，它将Springboot开发的一个个单体微服务整合并管理起来，为各个微服务之间提供配置管理，服务发现，熔断器，路由，微代理，等服务
Springboot可以离开springcloud独立开发项目，但是springcloud离不开springboot,属于依赖关系
3、springcloud与dubbo的区别

4、springcloud功能域和官网资料
社区：https://www.springcloud.cc/
https://www.springcloud.cc/spring-cloud-dalston.html
https://www.springcloud.cc/spring-cloud-netflix.html

Eureka
Eureka是Netflix的一个子模块，Eureka是一个基于REST的服务，用于定位服务，以实现云端中间层服务发现和故障转移。服务注册和发现对于微服务来说非常重要。类似于dubbo中的zookeeper
Eureka Server服务注册中心
Eureka自我保护
EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE
某时刻某一个微服务不可用了，eureka不会立刻清理，依旧会对该微服务的信息进行保存
eureka.server.enable-self-preservation = false 禁用自我保护

Eureka集群配置
1、修改hosts文件
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
127.0.0.1 eureka7003.com
2、修改yml配置文件，修改对于的名称
server:
  port: 7003
eureka:
  instance:
    hostname: eureka7003.com #eureka服务端的实例名称
  client:
    register-with-eureka: false #false表示不向注册中心注册自己
    fetch-registry: false #false表示自己端就是注册中心，我的指责是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/
3、访问验证


Eureka比zookeeper的区别
http://note.youdao.com/s/6cUss0gw


Ribbon负载均衡
spring cloud ribbon是基于netflix ribbon实现的一套客户端负载均衡工具
RoundRobinRule(轮询算法)
RandomRule(随机算法)
AvailabilityFilteringRule()：会先过滤由于多次访问故障而处于断路器跳闸状态的服务，还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问
WeightedResponseTimeRule()：根据平均响应的时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高，刚启动时如果统计信息不足，则使用RoundRobinRule策略，等统计信息足够会切换到WeightedResponseTimeRule
RetryRule()：先按照RoundRobinRule的策略获取服务，如果获取失败则在制定时间内进行重试，获取可用的服务。
BestAviableRule()：会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
ZoneAvoidanceRule()：默认规则，符合判断server所在区域的性能和server的可用性选择服务器
@Configuration
public class ConfigBean {
    @Bean
    @LoadBalanced //spring cloud ribbon客户端负载均衡工具 默认算法类型：轮训RoundRobinRule
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @Bean
    public IRule myRule(){
        return new RandomRule();//随机算法
    }
}
Ribbon自定义
@SpringBootApplication
@EnableEurekaClient
//启动微服务的时候加载自定义的ribbon配置类
@RibbonClient(name="MICROSERVICECLOUD-PROVIDER", configuration = MySelfRule.class)
public class ConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }
}
package com.lbb.myrule;//切记包名不要和@compentScan目录相同
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule_MY();//自定义算法
    }
}

package com.lbb.myrule;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
/**
 * 每台服务调用5次，再换下一台微服务
 * 参考事例：https://github.com/Netflix/ribbon/blob/master/ribbon-loadbalancer/src/main/java/com/netflix/loadbalancer/RandomRule.java
 */
public class RandomRule_MY extends AbstractLoadBalancerRule {
    private int total=0 ;       //担当total==5以后，我们指针才能往下走
    private int currentIndex=0; //当前对外服务的服务器地址
    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;
        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();
            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }
//            int index = chooseRandomInt(serverCount);
//            server = upList.get(index);
            if(total<5){
                server = upList.get(currentIndex);
                total++;
            }else {
                total=0;
                currentIndex++;
                if(currentIndex>=upList.size()){
                    currentIndex = 0;
                }
            }
            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }
            if (server.isAlive()) {
                return (server);
            }
            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }
        return server;
    }
    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }
    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }
}

Feign负载均衡 声明式模版话的http客户端
Feign是一个声明式的Web服务客户端。只需要创建一个接口
大家习惯面向接口编程，
1、微服务名字获得调用地址
//private static final String REST_URL_PREFIX = "http://localhost:8001";
private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-PROVIDER";
2、就是通过接口+注解，获得我们的调用服务 feign
公共服务：
@FeignClient(value = "MICROSERVICECLOUD-PROVIDER")
public interface DeptmentClientService {
    @RequestMapping(value = "/deptment/get/{id}", method = RequestMethod.GET)
    public Deptment getBy(@PathVariable("id") Long id);
    @RequestMapping(value = "/deptment/list", method = RequestMethod.GET)
    public List<Deptment> list();
    @RequestMapping(value = "/deptment/add", method = RequestMethod.POST)
    public boolean add(Deptment deptment);
}
消费端
@EnableFeignClients(basePackages = {"com.lbb.springcloud"})

Hystrix断路器
问题：复杂分布式体系结构中的应用程序由十来个依赖关系，避免不了调用失败
服务雪崩
多个服务之间调用时，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所谓的"扇出",如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统雪崩，就是所谓的“雪崩效应”。
Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，便面级联故障，以提高分布式系统的弹性。
Hystrix向调用方返回一个符合预期的、可处理的备选响应，而不是长时间的等待或者抛出调用方无法处理的异常。
服务熔断：服务端
@HystrixCommand(fallbackMethod = "processHystrix_Get") 作用于服务端对应的接口上
@EnableCircuitBreaker
public Deptment processHystrix_Get(@PathVariable("id") Long id){
        return new Deptment().setDeptno(id).setDname("该ID"+id+"返回为空,HystrixCommand").setDb_source("数据库没有对应的数据");
    }

服务降级：整体资源不够了，忍痛将某些服务先关掉，待渡过难关，再开启回来；客户端
@FeignClient(value = "MICROSERVICECLOUD-PROVIDER", fallbackFactory = DeptmentClientServiceFallBackFactory.class)

@Component
public class DeptmentClientServiceFallBackFactory implements FallbackFactory<DeptmentClientService> {
    @Override
    public DeptmentClientService create(Throwable cause) {
        return new DeptmentClientService() {
            @Override
            public Deptment get(Long id) {
                return new Deptment().setDeptno(id).setDname("该ID"+id+"没有对应的信息,服务降级").setDb_source("数据库没有对应的数据");
            }
            @Override
            public List<Deptment> list() {
                return null;
            }
            @Override
            public boolean add(Deptment deptment) {
                return false;
            }
        };
    }
}

Hystrix dashboard
http://localhost:8001/hystrix.stream
@EnableHystrixDashboard
<!-- hystrix dashboard相关配置 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
        </dependency>

Zuul
代理+路由+过滤功能 网关
@EnableZuulProxy
<!--zuul-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zuul</artifactId>
        </dependency>

http://myzuul.com:9527/microservicecloud-provider/deptment/get/1
equal
http://localhost:8001/deptment/get/1

虚拟映射，不暴露，真实微服务映射
zuul:
  routes:
    mydept.serviceId: microservicecloud-provider
    mydept.path: /mydept/**

equal
http://myzuul.com:9527/mydept/deptment/get/1

SpringCloud Config
是微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环节提供了一个去中心化的外部配置






