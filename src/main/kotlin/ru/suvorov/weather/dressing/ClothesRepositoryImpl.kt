package ru.suvorov.weather.dressing

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import ru.suvorov.weather.domain.dto.*

@Repository
class ClothesRepositoryImpl(
        @Autowired private val jdbc: JdbcTemplate
) : ClothesRepository {

    @Value("\${temperatureDiff}")
    private var temperatureDiff: Int = 0

    override fun getRecommendationsByParams(snow: Boolean, rain: Boolean, temperature: Double) = Recommendations(
            getHatByParams(snow, rain, temperature),
            getBodyByParams(snow, rain, temperature)
    )

    fun getHatByParams(snow: Boolean, rain: Boolean, temperature: Double): Hat? = jdbc.query(
            """SELECT NAME FROM CLOTHES WHERE
                    TYPE = '${Type.HAT.toString().toLowerCase()}' AND
                    TEMPERATURE BETWEEN ${temperature - temperatureDiff} AND ${temperature + temperatureDiff} AND
                    SNOW = $snow AND
                    RAIN = $rain""", rmHat())
            .getOrNull(0)

    fun getBodyByParams(snow: Boolean, rain: Boolean, temperature: Double): List<Body> = jdbc.query(
            """SELECT NAME FROM CLOTHES WHERE
                    TYPE = '${Type.BODY.toString().toLowerCase()}' AND
                    TEMPERATURE BETWEEN ${temperature - temperatureDiff} AND ${temperature + temperatureDiff} AND
                    SNOW = $snow AND
                    RAIN = $rain""", rmBody()
    )

    //TODO: Use generics to delete one from the methods
    fun rmHat(): RowMapper<Hat> = RowMapper { rs, _ -> Hat(rs.getString("name")) }

    fun rmBody(): RowMapper<Body> = RowMapper { rs, _ -> Body(rs.getString("name")) }
}