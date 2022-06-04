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

```



* Execute via the **Payara-Micro**

```
#
# src/test/resources/scripts/preboot
#
set-config-cache --duration=0

set-config-ordinal --ordinal=999 --source=domain
set-config-ordinal --ordinal=998 --source=config
set-config-ordinal --ordinal=997 --source=server
set-config-ordinal --ordinal=996 --source=application
set-config-ordinal --ordinal=995 --source=module
set-config-ordinal --ordinal=994 --source=cluster
set-config-ordinal --ordinal=993 --source=jndi
set-config-ordinal --ordinal=992 --source=secrets
set-config-ordinal --ordinal=991 --source=password
set-config-ordinal --ordinal=990 --source=jdbc
set-config-ordinal --ordinal=989 --source=cloud
set-config-ordinal --ordinal=987 --source=ldap
```


```bash

cd payara-evaluation/payara-evaluation-config/target

#
# Start the application
# 

java -jar payara-micro-5.2022.2.jar \
--deploy payara-evaluation-config-1.0.0-SNAPSHOT.war \
--contextroot / \
--addLibs ./libs \
--prebootcommandfile ./preboot \
--postbootcommandfile ./postboot

# Example Output

Boot Command set-config-cache returned with result SUCCESS : PlainTextActionReporterSUCCESSNo monitoring data to report.
Boot Command get-config-ordinal returned with result SUCCESS : PlainTextActionReporterSUCCESScloud : 180
Boot Command set-config-ordinal returned with result SUCCESS : PlainTextActionReporterSUCCESSNo monitoring data to report.
Boot Command get-config-ordinal returned with result SUCCESS : PlainTextActionReporterSUCCESScloud : 500

#
# Access via the cURL
#

curl -v \
-H "Accept: application/json" \
-X GET \
http://localhost:8080/api/config

# Example Output
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /api/config HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.78.0
> Accept: application/json
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 OK
< Server: Payara Micro #badassfish
< Vary: Accept-Encoding
< Content-Type: application/json;charset=UTF-8
< Content-Length: 792
< X-Frame-Options: SAMEORIGIN
<
configName=Payara, configOrdinal=1000
configName=Domain, configOrdinal=999
configName=ServerConfig, configOrdinal=998
configName=Server, configOrdinal=997
configName=Application, configOrdinal=996
configName=Module, configOrdinal=995
configName=Cluster, configOrdinal=994
configName=Directory, configOrdinal=992
configName=Password Alias, configOrdinal=991
configName=JDBC, configOrdinal=990
configName=SystemProperty, configOrdinal=400
configName=Environment, configOrdinal=300
configName=JNDI, configOrdinal=115 # <----- expected 993
configName=Properties, configOrdinal=100
configName=aws, configOrdinal=100 # <----- expected 989
configName=azure, configOrdinal=100 # <----- expected 989
configName=dynamodb, configOrdinal=100 # <----- expected 989
configName=gcp, configOrdinal=100 # <----- expected 989
configName=hashicorp, configOrdinal=100 # <----- expected 989
configName=ldap, configOrdinal=100 # <----- expected 987


```

