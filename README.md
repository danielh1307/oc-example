Builds / ImageStreams

Das Image wird innerhalb von Openshift erstellt

Zunächst neuen ImageStream erstellen: oc create -f kubernetes/imagestream/openjdk18-openshift.json

Neue App erstellen:
oc new-app oc-example-image-stream~https://github.com/danielh1307/oc-example

Triggert einen neuen Build:
curl -X POST --insecure https://192.168.64.3:8443/oapi/v1/namespaces/oc-build-iamge/buildconfigs/oc-example/webhooks/jQ992fkoHl2cMBkHabla/generic