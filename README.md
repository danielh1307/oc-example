Builds / ImageStreams
=====================

From Source:
------------

Das Image wird innerhalb von Openshift erstellt

Zunächst neuen ImageStream erstellen:   
```oc create -f kubernetes/imagestream/openjdk18-openshift.json```

Neue App erstellen:  
```oc new-app redhat-openjdk18-openshift~https://github.com/danielh1307/oc-example```

Triggert einen neuen Build:  
```curl -X POST --insecure https://192.168.64.3:8443/oapi/v1/namespaces/oc-build-iamge/buildconfigs/oc-example/webhooks/jQ992fkoHl2cMBkHabla/generic```

Binary:
------

Neuen Build erstellen:  
`oc new-build --binary=true --name=<BUILD> --image-stream=redhat-openjdk18-openshift`  
Dies erstellt einen neuen ImageStream und eine BuildConfig. 

Aus dieser BC lässt sich jetzt ein Docker-Image erstellen mit einem fertig gebauten .jar-File:  
`oc start-build <BUILD> --from-dir=./ocp --follow`  
Dabei gehen wir davon aus, dass das .jar-File im Verzeichnis ./ocp/deployments liegt.  

Falls wir den Tag des Images beeinflussen wollen, können wir direkt die BC editieren (default ist latest):  
`{"spec":{"output":{"to":{"name":...}}}}`  
... und dann einen neuen Build wie oben starten. 

Das Docker-Image ist jetzt getaggt im ImageStream, infolgedessen kann man daraus eine neue Applikation (DC) erstellen:  
`oc new-app <IS>`  
Hinweis: Auch wenn der IS jezt in diesem Fall heisst wie der BUILD, könnten die Namen auch unterschiedlich sein.

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