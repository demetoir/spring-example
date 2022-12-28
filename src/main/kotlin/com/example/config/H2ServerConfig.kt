package com.example.config

import com.zaxxer.hikari.HikariDataSource
import org.h2.tools.Server
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

// TODO H2를 사용하는 Profile에서만 동작하도록 설정
// @Profile(*["local", "development"])
// @Configuration
class H2ServerConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun h2TcpServer(): DataSource {
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start()
        return HikariDataSource()
    }
}
