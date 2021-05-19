# Simulator Connector Commons

The aim of this repo is to handle utility classes and default functions/behaviors necessary to interact with the CosmoTech Simulator.

## Compile
``` shell
mvn compile
```

## Direct dependency
```
<dependency>
    <groupId>com.cosmotech</groupId>
    <artifactId>simulator-connector-commons</artifactId>
    <version>VERSION</version>
</dependency>
```

## [jitpack.io](https://jitpack.io/) dependency
In your pom.xml:
```
...
  <properties>
    ...
    <simulator-connector-commons.version>VERSION</simulator-connector-commons.version>
...
  <repositories>
    <repository>
      <id>jitpack.io</id>
      <url>https://www.jitpack.io</url>
    </repository>
...
  <dependencies>
...
    <dependency>
      <groupId>com.github.Cosmo-Tech</groupId>
      <artifactId>simulator-connector-commons</artifactId>
      <version>${simulator-connector-commons.version}</version>
    </dependency>
```


## Release Note
### Version 0.2.0
* Change default path from "/mnt/simulation-data/" to "/mnt/scenariorun-data/"
