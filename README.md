# Bitcoind Json Rpc Client for Java
[![Build Status](http://staging.37coins.com:4080/buildStatus/icon?job=build_BitcoindClient4j)](http://staging.37coins.com:4080/job/build_BitcoindClient4j/)

This is a Java library to call the Json Rpc API of the reference implementation Bitcoind. The goal is to support all methods listed in the `./bitcoind help`-command. The implementation started from version 8.3 of Bitcoind and will not support previous versions. For further details about the implemented API visit [the bitcoin.org wiki](https://en.bitcoin.it/wiki/Original_Bitcoin_client/API_Calls_list).

## Build

Having [OpenJDK 7](http://openjdk.java.net/install/) and [Maven 3](http://maven.apache.org/download.cgi) installed, execute:
```bash
  mvn clean install
```

## Usage

Maven will need to know where to search for this artifact. Add this to your `pom.xml`:
```xml
  <repository>
    <id>github</id>
    <name>GitHub BitcoindClient4j Repository</name>
    <url>https://raw.github.com/johannbarbie/BitcoindClient4j/mvn-repo</url>
  </repository>
```
Then add the dependency itself:
```xml
  <dependency>
    <groupId>com.37coins</groupId>
    <artifactId>BitcoindClient4j</artifactId>
    <version>0.1.0</version>
  </dependency>
```
Having dependencies resolved, you can code away. First initialize the Factory:
```java
  BitcionClientFactory clientFactory = 
      new BitcionClientFactory(
          new URL("http://localhost:8332/"), 
          "user", 
          "password");
```
Then get a client instance:
```java
  BitcoindInterface client = clientFactory.getClient();
```
Now you can make calls to your node:
```java
  Info info = client.getinfo();
```
### Blockchain Events

The library also captures notifications from Bitcoind using the startup configuration. Launch your deamon with those parameters:
```bash
  ./bitcoind  -blocknotify="echo '%s' | nc 127.0.0.1 4001" 
              -walletnotify="echo '%s' | nc 127.0.0.1 4002" 
              -alertnotify="echo '%s' | nc 127.0.0.1 4003" 
              -daemon
```
You can register observers to capture events about blocks and addresses in you wallet:
```java
  new BlockListener(client).addObserver(new Observer() {
    @Override
    public void update(Observable o, Object arg) {
      Block block = (Block)arg;
    }
  });
```
Bitcoind also emits alerts. You need to start a listener for them like this:
```java
  Observable alertListener = new BitcoinDListener(4003);
  alertListener.addObserver(new Observer() {
      @Override
      public void update(Observable o, Object arg) {
        log.info("alert: {}",arg);
      }
  });
  new Thread((Runnable)alertListener,"alertListener").start();
```

### Host, Port and SSL

Host and port can be configured by passing an URL to the constructor as described before. SSL is not supported.


## Donations

Bitcoin donations can be sent to:
```
  152jsQJyQwxRywuHVVGLFEHkZqJ4QzuFS3
```
Thanks!

## License

GPL3, see LICENSE.txt