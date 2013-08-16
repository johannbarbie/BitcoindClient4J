= Bitcoind Json Rpc Client for Java

This is a Java library to call the Json Rpc API of the reference implementation Bitcoind, the goal is to support all methods listed by ./bitcoind help. The implementation started from version 8.3 of Bitcoind and will not support previous versions. For further details about the implemented API visit {https://en.bitcoin.it/wiki/Original_Bitcoin_client/API_Calls_list}[https://en.bitcoin.it/wiki/Original_Bitcoin_client/API_Calls_list].

== Build

Having OpenJDK 7 and [maven 3](http://maven.apache.org/download.cgi) installed, execute:

  mvn clean install

== Usage

Maven will need to know where to search for this artifact. Add this to your pom.xml:

  <repository>
    <id>github</id>
    <name>GitHub ${project.artifactId} Repository</name>
    <url>https://raw.github.com/johannbarbie/${project.artifactId}/mvn-repo</url>
  </repository>

Then add the dependency itself:

  <dependency>
    <groupId>me.payjet</groupId>
    <artifactId>BitcoindClient4j</artifactId>
    <version>0.1.0</version>
  </dependency>

After doing this, you can code away. First initialize the Factory:

  BitcionClientFactory clientFactory = 
      new BitcionClientFactory(
          new URL("http://localhost:8332/"), 
          "user", 
          "password");

Then get a client instance:

  BitcoinQtInterface client = clientFactory.getClient();

Now you can make calls to your node:

  Info info = client.getinfo();

The library also captures notifications from Bitcoind using the startup configuration. Launch your deamon with those parameters:

  ./bitcoind  -blocknotify="echo '%s' | nc 127.0.0.1 4001" 
              -walletnotify="echo '%s' | nc 127.0.0.1 4002" 
              -alertnotify="echo '%s' | nc 127.0.0.1 4003" 
              -daemon

You can register observers to capture events about blocks and addresses in you wallet:

  new BlockListener(client).addObserver(new Observer() {
    @Override
    public void update(Observable o, Object arg) {
      Block block = (Block)arg;
    }
  });

Bitcoind also emits alerts. You need to start a listener for them like this:

  Observable alertListener = new BitcoinDListener(4003);
  alertListener.addObserver(new Observer() {
      @Override
      public void update(Observable o, Object arg) {
        log.info("alert: {}",arg);
      }
  });
  new Thread((Runnable)alertListener,"alertListener").start();


== Host, Port and SSL

Host and port can be configured by passing an URL to the constructor as described before. SSL is not supported.


== Donations

Bitcoin donations can be sent to:

  152jsQJyQwxRywuHVVGLFEHkZqJ4QzuFS3

Thanks!

== License

GPL3, see LICENSE.txt