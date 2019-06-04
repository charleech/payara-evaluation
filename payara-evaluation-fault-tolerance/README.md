# The Fault Tolerance evaluation

## Updated on June 4, 2019

Both two issues below has been fixed with **Payara-micro** version `5.192`.

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
cd payara-evaluation/payara-evaluation-fault-tolerance
mvn clean install -Dmaven.test.skip=true -Dpayara-micro=true

#
# If we wolud like to execute the integration test
#
# The integration test will be fail as
# - https://github.com/payara/Payara/issues/3821
#
mvn clean install -Dpayara-micro=true

#
# The microprofile rest client is configured to print the request
# and response as the following example: -
#
1 > GET http://CharleeCh:8997/api/ft/timeout
1 > Accept: application/json

java.lang.ClassCastException: java.lang.String cannot be cast to java.time.temporal.ChronoUnit
    at fish.payara.microprofile.faulttolerance.interceptors.TimeoutInterceptor.timeout(TimeoutInterceptor.java:199)
    at fish.payara.microprofile.faulttolerance.interceptors.TimeoutInterceptor.intercept(TimeoutInterceptor.java:126)

1 < 500
1 < Connection: close
1 < Content-Length: 94
1 < Content-Type: application/json
1 < Server: Payara Micro #badassfish
1 < Vary: Accept-Encoding
1 < X-Frame-Options: SAMEORIGIN
java.lang.ClassCastException: java.lang.String cannot be cast to java.time.temporal.ChronoUnit

```

* Execute via the **Payara-Micro**

```
#
# src/main/resource/META-INF/microprofile-config.properties
#
app.github.charleech.ft.MyTimeoutResource/alwaysFail/Timeout/unit=MILLIS

app.github.charleech.ft.MyCircuitBreakerResource/alwaysFail/CircuitBreaker/failOn=java.lang.Throwable.class
app.github.charleech.ft.MyCircuitBreakerResource/alwaysFail/CircuitBreaker/requestVolumeThreshold=4
app.github.charleech.ft.MyCircuitBreakerResource/alwaysFail/CircuitBreaker/failureRatio=0.75d
app.github.charleech.ft.MyCircuitBreakerResource/alwaysFail/CircuitBreaker/successThreshold=2
app.github.charleech.ft.MyCircuitBreakerResource/alwaysFail/CircuitBreaker/delay=1000
app.github.charleech.ft.MyCircuitBreakerResource/alwaysFail/CircuitBreaker/unit=MILLIS
```


```bash

cd payara-evaluation/payara-evaluation-fault-tolerance/target

#
# Start the application
# 

java -jar payara-micro-5.191.jar \
--deploy payara-evaluation-fault-tolerance-1.0.0-SNAPSHOT.war \
--contextroot / \
--addLibs ./libs

#
# Access via the cURL
#

curl -v \
-H "Accept: application/json" \
-X GET \
http://localhost:8080/api/ft/timeout

java.lang.ClassCastException: java.lang.String cannot be cast to java.time.temporal.ChronoUnit
    at fish.payara.microprofile.faulttolerance.interceptors.TimeoutInterceptor.timeout(TimeoutInterceptor.java:199)
    at fish.payara.microprofile.faulttolerance.interceptors.TimeoutInterceptor.intercept(TimeoutInterceptor.java:126)
    

curl -v \
-H "Accept: application/json" \
-X GET \
http://localhost:8080/api/ft/circuit

#
# Expect the circit breaker exception will be thrown at 5th round,
# but, it is throwm at 21st round
#

```
