package pl.malirz.vshop

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jsonMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.JdbcTemplate
import pl.malirz.vshop.shared.utils.OracleJsonQuery
import javax.sql.DataSource

@Configuration
@Profile("JSON")
class VShopJsonConfig {

    @Bean
    fun jdbcTemplate(dataSource: DataSource) = JdbcTemplate(dataSource)

    @Bean
    fun oracleJsonQuery(
        jdbcTemplate: JdbcTemplate,
        objectMapper: ObjectMapper
    ) = OracleJsonQuery(jdbcTemplate, objectMapper)

    @Bean
    fun objectMapper(): ObjectMapper =
        jsonMapper {
            addModule(
                KotlinModule.Builder()
                    .enable(KotlinFeature.StrictNullChecks)
                    .build()
            )
        }


}