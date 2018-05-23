Builds / ImageStreams
=====================

Das Image wird innerhalb von Openshift erstellt

Zunächst neuen ImageStream erstellen:   
```oc create -f kubernetes/imagestream/openjdk18-openshift.json```

Neue App erstellen:  
```oc new-app oc-example-image-stream~https://github.com/danielh1307/oc-example```

Triggert einen neuen Build:  
```curl -X POST --insecure https://192.168.64.3:8443/oapi/v1/namespaces/oc-build-iamge/buildconfigs/oc-example/webhooks/jQ992fkoHl2cMBkHabla/generic```


AB-Deployment
=============
Applikation deployen:  
```oc new-app oc-example --name=ab-example-a --labels=ab-example=true SUBTITLE="shard A"```  
```oc new-app oc-example --name=ab-example-b --labels=ab-example=true SUBTITLE="shard B"```

Service kreieren (gilt für beide DeploymentConfigs):  
```oc expose dc ab-example-a --name=ab-example --selector=ab-example=true --port=8080```

Service exposen:  
```oc expose svc ab-example-a```

Pods scalen:  
```oc scale dc ab-example-b --replicas=1```