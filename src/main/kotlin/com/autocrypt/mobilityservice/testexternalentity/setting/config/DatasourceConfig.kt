package com.autocrypt.mobilityservice.testexternalentity.setting.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(
    basePackages = ["com.autocrypt.mobilityservice.testentity.entity", "com.autocrypt.mobilityservice.testexternalentity"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
class DatasourceConfig {
    @Primary
    @Bean(name = ["dataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @Primary
    @Bean(name = ["entityManagerFactory"])
    fun entityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(dataSource())
            .packages(
                "com.autocrypt.mobilityservice.testentity.entity",
                "com.autocrypt.mobilityservice.testexternalentity",
            )
            .persistenceUnit("test-entity")
            .properties(jpaProperties())
            .build()
    }

    protected fun jpaProperties(): Map<String, Any>? {
        val props: MutableMap<String, Any> = HashMap()
        props["hibernate.physical_naming_strategy"] = SpringPhysicalNamingStrategy::class.java.name
        props["hibernate.implicit_naming_strategy"] = SpringImplicitNamingStrategy::class.java.name
        return props
    }

    @Primary
    @Bean("transactionManager")
    fun transactionManager(builder: EntityManagerFactoryBuilder): PlatformTransactionManager? {
        return JpaTransactionManager(entityManagerFactory(builder).getObject()!!)
    }
}