[![](https://img.shields.io/badge/license-Apache--2.0-4D7A97)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![](https://img.shields.io/badge/maven%20central-v1.0.3-brightgreen)](https://central.sonatype.com/artifact/io.github.benfromchina/fisco-bcos-spring-boot-starter)
[![](https://img.shields.io/badge/release-v1.0.3-blue)](https://github.com/benfromchina/fisco-bcos-spring-boot-starter/releases/tag/v1.0.3)

### 使用方法

1. jdk17+

2. 在 `pom.xml` 中添加依赖：

```xml
<dependency>
    <groupId>io.github.benfromchina</groupId>
    <artifactId>fisco-bcos-spring-boot-starter</artifactId>
    <version>1.0.3</version>
</dependency>
```

3. 在 `application.yml` 中添加配置：

```yaml
fisco-bcos:
  crypto-material:
    cert-path: conf
    use-s-m-crypto: false
    disable-ssl: false
  network:
    message-timeout: 10000
    default-group: group0
    peers:
      - 192.168.41.143:20200
      - 192.168.41.144:20200
      - 192.168.41.145:20200
  account:
    key-store-dir: account
    account-file-format: pem
  thread-pool:
    thread-pool-size: 16
```

4. 自动注入 `BcosSDK` 对象：

```java
@Autowired
private BcosSDK bcosSDK;
```