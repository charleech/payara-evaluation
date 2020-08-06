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
# - <TOBE DEFINE>
# - <TOBE DEFINE>
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
