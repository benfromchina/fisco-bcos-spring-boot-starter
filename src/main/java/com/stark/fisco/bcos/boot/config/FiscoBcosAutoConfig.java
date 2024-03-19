package com.stark.fisco.bcos.boot.config;

import com.moandjiezana.toml.Toml;
import com.stark.fisco.bcos.boot.properties.FiscoBcosProperties;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.config.ConfigOption;
import org.fisco.bcos.sdk.v3.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.v3.config.model.ConfigProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FISCO BCOS 自动配置
 *
 * @author <a href="mailto:mengbin@hotmail.com">Ben</a>
 * @version 1.0.0
 * @since 2024/3/18
 */
@Configuration
@EnableConfigurationProperties(FiscoBcosProperties.class)
public class FiscoBcosAutoConfig {

    @Bean
    public ConfigProperty bcosConfigProperty(FiscoBcosProperties fiscoBcosProperties) {
        return new Toml().read(fiscoBcosProperties.toString()).to(ConfigProperty.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public BcosSDK bcosSDK(ConfigProperty bcosConfigProperty) throws ConfigException {
        ConfigOption configOption = new ConfigOption(bcosConfigProperty);
        return new BcosSDK(configOption);
    }

}
