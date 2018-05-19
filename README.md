https://medium.com/google-cloud/kubernetes-nodeport-vs-loadbalancer-vs-ingress-when-should-i-use-what-922f010849e0
https://kubernetes.io/docs/concepts/services-networking/service/

Ein Update läuft wie folgt (wenn es schon im Minishift läuft):

- Code ändern
- neu bauen: mvn clean package (es wird ein neues Image in die Registry geschrieben)
- Pod killen: kubectl delete pod <NAME> (kann man auch über das Web-GUI von Minishift killen)
- es sollte automatisch ein neuer Pod erzeugt werden, der das neue Image pullt