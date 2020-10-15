package ru.suvorov.weather.db.dressing

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import ru.suvorov.weather.application.clothes.Body
import ru.suvorov.weather.application.clothes.Hat
import ru.suvorov.weather.application.clothes.SetOfClothes
import ru.suvorov.weather.application.clothes.Type

@Repository
class ClothesRepositoryImpl(
        @Autowired private val jdbc: JdbcTemplate
) : ClothesRepository {

    @Value("\${temperatureDiff}")
    private var temperatureDiff: Int = 0

    override fun getRecommendationsByParams(snow: Boolean, rain: Boolean, temperature: Double, temperatureDiff: Int) = SetOfClothes(
            getHatByParams(snow, rain, temperature, temperatureDiff),
            getBodyByParams(snow, rain, temperature, temperatureDiff)
    )

    fun getHatByParams(snow: Boolean, rain: Boolean, temperature: Double, temperatureDiff: Int = this.temperatureDiff): Hat? = jdbc.query(
            """SELECT NAME FROM CLOTHES WHERE
                    LOWER(TYPE) = '${Type.HAT.toString().toLowerCase()}' AND
                    TEMPERATURE BETWEEN ${temperature - temperatureDiff} AND ${temperature + temperatureDiff} AND
                    SNOW = $snow AND
                    RAIN = $rain""", rmHat())
            .getOrNull(0)

    fun getBodyByParams(snow: Boolean, rain: Boolean, temperature: Double, temperatureDiff: Int = this.temperatureDiff): List<Body> = jdbc.query(
            """SELECT NAME FROM CLOTHES WHERE
                    LOWER(TYPE) = '${Type.BODY.toString().toLowerCase()}' AND
                    TEMPERATURE BETWEEN ${temperature - temperatureDiff} AND ${temperature + temperatureDiff} AND
                    SNOW = $snow AND
                    RAIN = $rain""", rmBody()
    )

    //TODO: Use generics to delete one from the methods
    fun rmHat(): RowMapper<Hat> = RowMapper { rs, _ -> Hat(rs.getString("name")) }

    fun rmBody(): RowMapper<Body> = RowMapper { rs, _ -> Body(rs.getString("name")) }
}