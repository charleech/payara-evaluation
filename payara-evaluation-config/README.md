# The Config evaluation


## How to build?

* Build the parent

```bash
cd payara-evaluation
mvn clean install -N
```

* Build the base

```bash
cd payara-evaluation/payara-evaluation-base
mvn clean install -Dmaven.test.skip=true

# If we wolud like to execute the integration test
mvn clean install -Dpayara-micro=true

```

* Build this project


```bash
cd payara-evaluation/payara-evaluation-config
mvn clean install -Dmaven.test.skip=true -Dpayara-micro=true

#
# If we wolud like to execute the integration test
#
# The integration test will be fail as
# - https://github.com/payara/Payara/issues/4817
#
mvn clean install -Dpayara-micro=true

#
# The microprofile rest client is configured to print the request
# and response as the following example: -
#
1 > GET http://CharleeCh:8218/api/config
1 > Accept: application/json


1 < 200
1 < Content-Length: 71
1 < Content-Type: application/json
1 < Server: Payara Micro #badassfish
1 < Vary: Accept-Encoding
1 < X-Frame-Options: SAMEORIGIN
value1=from-config-file,value2=from-config-file,value3=from-config-file

The expected result is
value1=from-config-file,value2=system-value1,value3=system-value2
```

* Execute via the **Payara-Micro**

```
#
# src/main/resource/META-INF/microprofile-config.properties
#
my.name=from-config-file
```


```bash

cd payara-evaluation/payara-evaluation-config/target

#
# Start the application
# 

java -jar payara-micro-5.2020.3.jar \
--deploy payara-evaluation-config-1.0.0-SNAPSHOT.war \
--contextroot / \
--addLibs ./libs

#
# Access via the cURL
#

curl -v \
-H "Accept: application/json" \
-X GET \
http://localhost:8080/api/config

value1=from-config-file,value2=from-config-file,value3=from-config-file

The expected result is
value1=from-config-file,value2=system-value1,value3=system-value2
```

* Access the `/openapi`

```bash
# - https://github.com/payara/Payara/issues/4818

curl -v \
-H "Accept: application/json" \
-X GET \
http://localhost:8080/openapi
```

The console will print the exception and the respose is empty.

```
  OpenAPI document creation failed.
fish.payara.microprofile.openapi.api.OpenAPIBuildException: java.lang.StackOverflowError
        at fish.payara.microprofile.openapi.impl.OpenApiService$OpenApiMapping.buildDocument(OpenApiService.java:284)
        at fish.payara.microprofile.openapi.impl.OpenApiService$OpenApiMapping.access$200(OpenApiService.java:248)
        at fish.payara.microprofile.openapi.impl.OpenApiService.getDocument(OpenApiService.java:203)
        at fish.payara.microprofile.openapi.impl.rest.app.service.OpenApiResource.getResponse(OpenApiResource.java:80)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.glassfish.jersey.server.model.internal.ResourceMethodInvocationHandlerFactory.lambda$static$0(ResourceMethodInvocationHandlerFactory.java:52)
        at org.glassfish.jersey.server.model.internal.AbstractJavaResourceMethodDispatcher$1.run(AbstractJavaResourceMethodDispatcher.java:124)
        at org.glassfish.jersey.server.model.internal.AbstractJavaResourceMethodDispatcher.invoke(AbstractJavaResourceMethodDispatcher.java:167)
        at org.glassfish.jersey.server.model.internal.JavaResourceMethodDispatcherProvider$ResponseOutInvoker.doDispatch(JavaResourceMethodDispatcherProvider.java:176)
        at org.glassfish.jersey.server.model.internal.AbstractJavaResourceMethodDispatcher.dispatch(AbstractJavaResourceMethodDispatcher.java:79)
        at org.glassfish.jersey.server.model.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:469)
        at org.glassfish.jersey.server.model.ResourceMethodInvoker.apply(ResourceMethodInvoker.java:391)
        at org.glassfish.jersey.server.model.ResourceMethodInvoker.apply(ResourceMethodInvoker.java:80)
        at org.glassfish.jersey.server.ServerRuntime$1.run(ServerRuntime.java:253)
        at org.glassfish.jersey.internal.Errors$1.call(Errors.java:248)
        at org.glassfish.jersey.internal.Errors$1.call(Errors.java:244)
        at org.glassfish.jersey.internal.Errors.process(Errors.java:292)
        at org.glassfish.jersey.internal.Errors.process(Errors.java:274)
        at org.glassfish.jersey.internal.Errors.process(Errors.java:244)
        at org.glassfish.jersey.process.internal.RequestScope.runInScope(RequestScope.java:265)
        at org.glassfish.jersey.server.ServerRuntime.process(ServerRuntime.java:232)
        at org.glassfish.jersey.server.ApplicationHandler.handle(ApplicationHandler.java:680)
        at org.glassfish.jersey.servlet.WebComponent.serviceImpl(WebComponent.java:394)
        at org.glassfish.jersey.servlet.WebComponent.service(WebComponent.java:346)
        at org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:366)
        at org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:319)
        at org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:205)
        at org.apache.catalina.core.StandardWrapper.service(StandardWrapper.java:1636)
        at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:259)
        at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:161)
        at org.apache.catalina.core.StandardPipeline.doInvoke(StandardPipeline.java:757)
        at org.apache.catalina.core.StandardPipeline.invoke(StandardPipeline.java:577)
        at com.sun.enterprise.web.WebPipeline.invoke(WebPipeline.java:99)
        at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:158)
        at org.apache.catalina.connector.CoyoteAdapter.doService(CoyoteAdapter.java:371)
        at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:238)
        at com.sun.enterprise.v3.services.impl.ContainerMapper$HttpHandlerCallable.call(ContainerMapper.java:520)
        at com.sun.enterprise.v3.services.impl.ContainerMapper.service(ContainerMapper.java:217)
        at org.glassfish.grizzly.http.server.HttpHandler.runService(HttpHandler.java:182)
        at org.glassfish.grizzly.http.server.HttpHandler.doHandle(HttpHandler.java:156)
        at org.glassfish.grizzly.http.server.HttpServerFilter.handleRead(HttpServerFilter.java:218)
        at org.glassfish.grizzly.filterchain.ExecutorResolver$9.execute(ExecutorResolver.java:95)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.executeFilter(DefaultFilterChain.java:260)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.executeChainPart(DefaultFilterChain.java:177)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.execute(DefaultFilterChain.java:109)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.process(DefaultFilterChain.java:88)
        at org.glassfish.grizzly.ProcessorExecutor.execute(ProcessorExecutor.java:53)
        at org.glassfish.grizzly.nio.transport.TCPNIOTransport.fireIOEvent(TCPNIOTransport.java:524)
        at org.glassfish.grizzly.strategies.AbstractIOStrategy.fireIOEvent(AbstractIOStrategy.java:89)
        at org.glassfish.grizzly.strategies.WorkerThreadIOStrategy.run0(WorkerThreadIOStrategy.java:94)
        at org.glassfish.grizzly.strategies.WorkerThreadIOStrategy.access$100(WorkerThreadIOStrategy.java:33)
        at org.glassfish.grizzly.strategies.WorkerThreadIOStrategy$WorkerThreadRunnable.run(WorkerThreadIOStrategy.java:114)
        at org.glassfish.grizzly.threadpool.AbstractThreadPool$Worker.doWork(AbstractThreadPool.java:569)
        at org.glassfish.grizzly.threadpool.AbstractThreadPool$Worker.run(AbstractThreadPool.java:549)
        at java.lang.Thread.run(Thread.java:748)
Caused by: java.lang.StackOverflowError
        at org.glassfish.hk2.classmodel.reflect.impl.TypeProxy$1.iterator(TypeProxy.java:108)
        at fish.payara.microprofile.openapi.impl.model.util.AnnotationInfo.init(AnnotationInfo.java:269)
        at fish.payara.microprofile.openapi.impl.model.util.AnnotationInfo.init(AnnotationInfo.java:271)
        at fish.payara.microprofile.openapi.impl.model.util.AnnotationInfo.init(AnnotationInfo.java:271)
        at fish.payara.microprofile.openapi.impl.model.util.AnnotationInfo.init(AnnotationInfo.java:271)
        at fish.payara.microprofile.openapi.impl.model.util.AnnotationInfo.init(AnnotationInfo.java:271)
        at fish.payara.microprofile.openapi.impl.model.util.AnnotationInfo.init(AnnotationInfo.java:271)
```