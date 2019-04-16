# The OpenAPI evaluation

## Design

### payara-evaluation-base

```
app.github.charleech.base.bean.ref

+ --------------------+       +----------------------+
+ MyAnnotatedDataBean + ---|> + MyAnnotatedSuperBean +
+ --------------------+       +----------------------+

+ ------------+       +-----------------+
+ MyOuterBean + ---|> + MyOuterBeanBase +
+ ------------+       +-----------------+
```

### payara-evaluation-openapi

```
app.github.charleech.openapi.bean

+ ----------------+       +---------------------+
+ MyLocalDataBean + ---|> + MyLocalDataBeanBase +
+ ----------------+       +---------------------+

+ --------------------+       +---------------------+
+ MyExtendedAnnotated + ---|> + MyAnnotatedDataBean +
+ --------------------+       +---------------------+
```

## Expected output

```json
{
    "openapi":"3.0.0",
    "components":{
        "schemas":{
            "MyAnnotatedDataBean":{
                "title":"This is a data bean which is annotated with schema. It is inside jar file.",
                "properties":{
                    "attributes":{
                        "title":"The simple attributes",
                        "type":"object"
                    },
                    "message":{
                        "title":"The simple message",
                        "type":"string"
                    }
                },                    
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyAnnotatedSuperBean"
                    }
                ]
            },
            "MyAnnotatedSuperBean":{
                "title":"This is a base data bean which is annotated with schema. It is inside jar file.",
                "properties":{
                    "correlationId":{
                        "title":"The correlation identifier",
                        "type":"string",
                        "format":"uuid"
                    }
                }
            },
            "MyExtendedAnnotated":{
                "title":"This is a simple data bean which is extedned from jar file.",
                "properties":{
                    "extendedMessage":{
                        "title":"The extended message",
                        "type":"string"
                    }
                },
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyAnnotatedDataBean"
                    }
                ]
            },
            "MyExtendedOuter":{
                "title":"This is a simple data bean which is extedned from jar file.",
                "properties":{
                    "extendedMessage":{
                        "title":"The extended message",
                        "type":"string"
                    }
                },
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyOuterBean"
                    }
                ]
            },
            "MyLocalDataBean":{
                "title":"This is a simple data bean from jar file.",
                "properties":{
                    "attributes":{
                        "title":"The simple attributes",
                        "type":"object"
                    },
                    "message":{
                        "title":"The simple message",
                        "type":"string"
                    }
                },
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyLocalDataBeanBase"
                    }
                ]
            },
            "MyLocalDataBeanBase":{
                "title":"This is a data bean base.",
                "properties":{
                    "correlationId":{
                        "title":"The correlation identifier",
                        "type":"string",
                        "format":"uuid"
                    }
                }
            },
            "MyOuterBean":{
                "type":"object",
                "properties":{
                    "message":{
                        "type":"string"
                    },
                    "attributes":{
                        "type":"object"
                    }
                },
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyOuterBeanBase"
                    }
                ]
            },
            "MyOuterBeanBase":{
                "type":"object",
                "properties":{
                    "correlationId":{
                        "type":"string"
                    }
                }
            }
        }
        }
    }
}
```

## But the output is invalid

```json
{
    "openapi":"3.0.0",
    "components":{
        "schemas":{
            "MyAnnotatedDataBean":{
                "title":"This is a data bean which is annotated with schema. It is inside jar file.",
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyAnnotatedSuperBean"
                    }
                ]
            },
            "MyAnnotatedSuperBean":{
                "title":"This is a base data bean which is annotated with schema. It is inside jar file."
            },
            "MyExtendedAnnotated":{
                "title":"This is a simple data bean which is extedned from jar file.",
                "properties":{
                    "extendedMessage":{
                        "title":"The extended message",
                        "type":"string"
                    }
                },
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyAnnotatedDataBean"
                    }
                ]
            },
            "MyExtendedOuter":{
                "title":"This is a simple data bean which is extedned from jar file.",
                "properties":{
                    "extendedMessage":{
                        "title":"The extended message",
                        "type":"string"
                    }
                },
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyOuterBean"
                    }
                ]
            },
            "MyLocalDataBean":{
                "title":"This is a simple data bean from jar file.",
                "properties":{
                    "attributes":{
                        "title":"The simple attributes",
                        "type":"object"
                    },
                    "message":{
                        "title":"The simple message",
                        "type":"string"
                    }
                },
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyLocalDataBeanBase"
                    }
                ]
            },
            "MyLocalDataBeanBase":{
                "title":"This is a data bean base."
            },
            "MyOuterBean":{
                "type":"object",
                "properties":{
                    "serialVersionUID":{
                        "type":"number"
                    },
                    "message":{
                        "type":"string"
                    },
                    "attributes":{
                        "type":"object"
                    }
                },
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyOuterBeanBase"
                    }
                ]
            },
            "MyOuterBeanBase":{
                "type":"object",
                "properties":{
                    "serialVersionUID":{
                        "type":"number"
                    },
                    "correlationId":{
                        "type":"string"
                    }
                }
            }
        }
    }
}
```

## If we remove the classes that extends the payara-evaluation-base.jar

* `app.github.charleech.openapi.bean.MyExtendedAnnotated`

* `app.github.charleech.openapi.bean.MyExtendedOuter`

The **OpenAPI** will not render the classes from the `payara-evaluation-base.jar`


```json
{
    "openapi":"3.0.0",
    "components":{
        "schemas":{
            "MyExtendedAnnotated":{
                "title":"This is a simple data bean which is extedned from jar file.",
                "properties":{
                    "extendedMessage":{
                        "title":"The extended message",
                        "type":"string"
                    }
                }
            },
            "MyExtendedOuter":{
                "title":"This is a simple data bean which is extedned from jar file.",
                "properties":{
                    "extendedMessage":{
                        "title":"The extended message",
                        "type":"string"
                    }
                }
            },
            "MyLocalDataBean":{
                "title":"This is a simple data bean from jar file.",
                "properties":{
                    "attributes":{
                        "title":"The simple attributes",
                        "type":"object"
                    },
                    "message":{
                        "title":"The simple message",
                        "type":"string"
                    }
                },
                "allOf":[
                    {
                        "$ref":"#/components/schemas/MyLocalDataBeanBase"
                    }
                ]
            },
            "MyLocalDataBeanBase":{
                "title":"This is a data bean base."
            }
        }
    }
}
```

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
cd payara-evaluation/payara-evaluation-openapi
mvn clean install -Dmaven.test.skip=true -Dpayara-micro=true

# If we wolud like to execute the integration test
mvn clean install -Dpayara-micro=true

#
# The microprofile rest client is configured to print the request
# and response as the following example: -
#
1 > GET http://localhost:8480/openapi
1 > Accept: application/json

1 < 200
1 < Content-Length: 3046
1 < Content-Type: application/json
1 < Server: Payara Micro #badassfish
1 < X-Frame-Options: SAMEORIGIN
{"openapi":"3.0.0",...}

```

* Execute via the **Payara-Micro**

```bash
cd payara-evaluation/payara-evaluation-openapi/target

#
# Start the application
# 

java -jar payara-micro-5.191.jar \
--deploy payara-evaluation-openapi-1.0.0-SNAPSHOT.war \
--contextroot / \
--addLibs ./libs

#
# Access via the cURL
#

curl -v \
-H "Accept: application/json" \
-X GET \
http://localhost:8080/openapi

#
# JSON prettify
#

curl ... | python -m json.tool

# or

curl ... | jq .

```

