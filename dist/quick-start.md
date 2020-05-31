# Quick Start

All'interno della cartella dist sono presenti 2 file:

- [opcserver.jar](opcserver.jar)
- [application.properties](application.properties)

Il jar è compilato con Open JDK 12 per cui sarà necessaria una JVM Java 12+.

Nel file `application.properties` è possibile variare i parametri del server quali la porta di ascolto tramite la seguente proprietà:

`camel.component.milo-server.bind-port=51120`


Per eseguire il programma scaricare la cartella dist ed eseguire il seguente comando da terminale:

```bash
$ java -jar opcserver.jar
````


Dovreste vedere a terminale la seguente videata:
````

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.3.0.RELEASE)

2020-05-31 18:50:11.350  INFO 22697 --- [           main] c.g.c.o.SpringBootOpcServerApplication   : 
......
......

2020-05-31 19:14:53.859  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210/discovery bound to 0.0.0.0:51210 [None/None]
2020-05-31 19:14:53.859  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [Aes256_Sha256_RsaPss/SignAndEncrypt]
2020-05-31 19:14:53.859  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [Aes128_Sha256_RsaOaep/SignAndEncrypt]
2020-05-31 19:14:53.859  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [Basic256Sha256/SignAndEncrypt]
2020-05-31 19:14:53.859  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [Basic256/SignAndEncrypt]
2020-05-31 19:14:53.859  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [Basic128Rsa15/SignAndEncrypt]
2020-05-31 19:14:53.859  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [None/None]
2020-05-31 19:14:53.859  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [None/None]
2020-05-31 19:14:53.859  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [Basic128Rsa15/SignAndEncrypt]
2020-05-31 19:14:53.860  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [Basic256/SignAndEncrypt]
2020-05-31 19:14:53.860  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [Basic256Sha256/SignAndEncrypt]
2020-05-31 19:14:53.860  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [Aes128_Sha256_RsaOaep/SignAndEncrypt]
2020-05-31 19:14:53.860  INFO 23995 --- [ty-event-loop-0] o.e.m.o.s.server.tcp.UaTcpStackServer    : opc.tcp://<your-machine>:51210 bound to 0.0.0.0:51210 [Aes256_Sha256_RsaPss/SignAndEncrypt]
````

