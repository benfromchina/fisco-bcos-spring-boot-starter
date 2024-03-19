package com.stark.fisco.bcos.boot.properties;

import com.stark.fisco.bcos.enums.AccountFileFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * FISCO BCOS 配置项
 *
 * @author <a href="mailto:mengbin@hotmail.com">Ben</a>
 * @version 1.0.0
 * @since 2024/3/18
 */
@ConfigurationProperties(prefix = "fisco-bcos")
@Data
public class FiscoBcosProperties {

    /** 证书配置 */
    private CryptoMaterial cryptoMaterial = new CryptoMaterial();

    /** 网络连接配置 */
    private Network network = new Network();

    /** 账户配置 */
    private Account account = new Account();

    /** 线程池配置 */
    private ThreadPool threadPool = new ThreadPool();

    /** 证书配置 */
    @Data
    public static class CryptoMaterial {

        /** 证书存放路径，默认是 <i>conf</i> 目录 */
        private String certPath = "conf";

        /** 是否使用国密 <i>SSL</i> 连接，<i>true</i> 为使用国密 <i>SSL</i>，默认 <i>false</i> */
        private boolean useSMCrypto = false;

        /** 与节点通信时禁用 <i>SSL</i> */
        private boolean disableSsl = false;

        /** <i>CA</i> 证书路径，非国密时默认 <i>${certPath}/ca.crt</i>，国密时默认 <i>${certPath}/sm_ca.crt</i> */
        private String caCert;

        /** <i>SDK</i> 证书路径，非国密时默认 <i>${certPath}/sdk.crt</i>，国密时默认 <i>${certPath}/sm_sdk.crt</i> */
        private String sslCert;

        /** <i>SDK</i> 私钥路径，非国密时默认 <i>${certPath}/sdk.key</i>，国密时默认 <i>${certPaht}/sm_sdk.key</i> */
        private String sslKey;

        /** 国密 <i>SSL</i> 加密证书路径，默认 <i>${certPath}/sm_ensdk.crt</i> */
        private String enSslCert;

        /** 国密 <i>SSL</i> 加密私钥路径，默认 <i>${certPath}/sm_ensdk.key</i> */
        private String enSslKey;

        @Override
        public String toString() {
            return "[cryptoMaterial]" +
                    "\ncertPath = \"" + certPath + "\"" +
                    "\nuseSMCrypto = \"" + useSMCrypto + "\"" +
                    "\ndisableSsl = \"" + disableSsl + "\"" +
                    "\ncaCert = \"" + caCert + "\"" +
                    "\nsslCert = \"" + sslCert + "\"" +
                    "\nsslKey = \"" + sslKey + "\"" +
                    "\nenSslCert = \"" + enSslCert + "\"" +
                    "\nenSslKey = \"" + enSslKey + "\"";
        }

    }

    /** 网络连接配置 */
    @Data
    public static class Network {

        /** <i>SDK</i> 与节点通信超时毫秒数，默认 <i>10000</i> */
        private long messageTimeout = 10000;

        /** <i>SDK</i> 默认发送请求的群组，默认 <i>group0</i> */
        private String defaultGroup = "group0";

        /** <i>SDK</i> 连接的节点的 <i>IP:Port</i> 信息，可配置多个连接 */
        private List<String> peers = new ArrayList<>();

        @Override
        public String toString() {
            return "[network]" +
                    "\nmessageTimeout = \"" + messageTimeout + "\"" +
                    "\ndefaultGroup = \"" + defaultGroup + "\"" +
                    "\npeers = [\"" + StringUtils.join(peers, "\",\"") + "\"]";
        }

    }

    /** 账户配置 */
    @Data
    public static class Account {

        /** 加载/保存账户文件的路径，默认为 <i>account</i> */
        private String keyStoreDir = "account";

        /**
         * 账户文件格式，默认为 {@link AccountFileFormat#pem pem}，目前仅支持 {@link AccountFileFormat#pem pem} 和 {@link AccountFileFormat#p12 p12}，
         * {@link AccountFileFormat#pem pem} 格式的账户文件不需要口令加载，
         * 加载 {@link AccountFileFormat#p12 p12} 格式的账户文件时需要口令
         */
        private AccountFileFormat accountFileFormat = AccountFileFormat.pem;

        /**
         * 加载的账户文件路径，非国密时默认 <i>${keyStoreDir}/ecdsa/${accountAddress}.${accountFileFormat}</i>，国密时默认 <i>${keyStoreDir}/gm/${accountAddress}.${accountFileFormat}</i>
         */
        private String accountFilePath;

        /** 加载的账户地址，默认为空 */
        private String accountAddress;

        /** 加载 <i>p12</i> 类型账户文件的口令 */
        private String password;

        @Override
        public String toString() {
            return "[account]" +
                    "\nkeyStoreDir = \"" + keyStoreDir + "\"" +
                    "\naccountFileFormat = \"" + accountFileFormat + "\"" +
                    "\naccountFilePath = \"" + accountFilePath + "\"" +
                    "\naccountAddress = \"" + accountAddress + "\"" +
                    "\npassword = \"" + password + "\"";
        }

    }

    /** 线程池配置 */
    @Data
    public static class ThreadPool {

        /** 接收交易的线程数目，默认值为机器的 <i>CPU</i> 数目 */
        private Integer threadPoolSize;

        @Override
        public String toString() {
            return "[threadPool]" +
                    "\nthreadPoolSize = \"" + (threadPoolSize != null ? threadPoolSize : Runtime.getRuntime().availableProcessors()) + "\"";
        }

    }

    @Override
    public String toString() {
        return cryptoMaterial
                + "\n" + network
                + "\n" + account
                + "\n" + threadPool
                + "\n";
    }

}
