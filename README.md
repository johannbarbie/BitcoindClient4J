# Bitcoind Json Rpc Client for Java
[![Build Status](http://staging.37coins.com:4080/buildStatus/icon?job=build_BitcoindClient4j)](http://staging.37coins.com:4080/job/build_BitcoindClient4j/)

This is a Java library to call the Json Rpc API of the reference implementation Bitcoind. The goal is to support all methods listed in the `./bitcoind help`-command. The implementation started from version 0.8.3 of Bitcoind and will not support previous versions. For further details about the implemented API visit [the bitcoin.org wiki](https://en.bitcoin.it/wiki/Original_Bitcoin_client/API_Calls_list). Currently tested version is 0.9.0.

## Build

Having [OpenJDK 7](http://openjdk.java.net/install/) and [Maven 3](http://maven.apache.org/download.cgi) installed, execute:
```bash
  mvn clean install
```

## Usage

Maven will need to know where to search for this artifact. Add this to your `pom.xml`:
```xml
    <repository>
      <id>BitcoindClient4j</id>
      <name>GitHub BitcoindClient4j Repository</name>
      <url>https://s3.amazonaws.com/37mvnrepo/release</url>
    </repository>
```
Then add the dependency itself:
```xml
    <dependency>
      <groupId>com.37coins</groupId>
      <artifactId>BitcoindClient4J</artifactId>
      <version>0.2.2</version>
    </dependency>
```
Having dependencies resolved, you can code away. First initialize the Factory with network parameters:
```java
  BitcoindClientFactory clientFactory = 
      new BitcoindClientFactory(
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

If you had the API start your bitcoind, you might be interested in stopping it again:
```java
  client.stop();
```

### Blockchain Events

The library also captures notifications from Bitcoind using the startup configuration. Launch your deamon with those parameters:
```bash
  ./bitcoind  -blocknotify="echo '%s' | nc 127.0.0.1 4001" 
              -walletnotify="echo '%s' | nc 127.0.0.1 4002" 
              -alertnotify="echo '%s' | nc 127.0.0.1 4003" 
              -daemon
```
Or, if you have your bitcoind locally, have the API start up the daemon. Call the factory with a path instead of network parameters:
```java
  BitcoindClientFactory clientFactory = 
      new BitcoindClientFactory(
          "/home/user/.bitcoin/",
          Arrays.asList("./bitcoind"));
```

You can register observers to capture events about blocks, addresses in you wallet, and alerts:
```java
  new BlockListener(client).addObserver(new Observer() {
    @Override
    public void update(Observable o, Object arg) {
      Block block = (Block)arg;
    }
  });
```

Make sure to close sockets later:
```java
  blockListener.stop();
```

## Donations

Bitcoin donations can be sent to:
```
  152jsQJyQwxRywuHVVGLFEHkZqJ4QzuFS3
```
Thanks!

## License

GPL3, see LICENSE.txt