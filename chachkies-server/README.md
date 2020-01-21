```bash
./mvnw com.google.cloud.tools:jib-maven-plugin:dockerBuild -Dimage=ashumilov/chachkies-rmi-server
```

```bash
./mvnw com.google.cloud.tools:jib-maven-plugin:build -Dimage=ashumilov/chachkies-rmi-server
```

```bash
k port-forward svc/rmi-server 1099:1099
```

```bash
k run rmi-server --image=ashumilov/chachkies-rmi-server --restart=Never
```

docker build -t chachkies-rmi-server .

docker run -e JAVA_OPTS='-Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.local.only=false -Djava.rmi.server.hostname=0.0.0.0' -p 1099:1099 ashumilov/chachkies-rmi-server



docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' f2d98145cd14

jconsole 127.0.0.1:30117