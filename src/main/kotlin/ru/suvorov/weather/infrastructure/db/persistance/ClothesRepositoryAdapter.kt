package ru.suvorov.weather.infrastructure.db.persistance

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import ru.suvorov.weather.core.component.clothes.Clothes
import ru.suvorov.weather.core.component.clothes.Type
import ru.suvorov.weather.core.port.secondary.ClothesRepository

@Repository
class ClothesRepositoryAdapter(
        @Autowired private val jdbc: JdbcTemplate
) : ClothesRepository {

    @Value("\${temperatureDiff}")
    private var temperatureDiff: Int = 0

    override fun getClothesByParams(snow: Boolean,
                           rain: Boolean,
                           temperature: Double,
                           temperatureDiffOrNull: Int?): List<Clothes> {
        val temperatureDiff = temperatureDiffOrNull ?: this.temperatureDiff
        return jdbc.query("""
            SELECT NAME, TYPE FROM CLOTHES WHERE
            TEMPERATURE BETWEEN ${temperature - temperatureDiff} AND ${temperature + temperatureDiff} 
            AND SNOW = $snow 
            AND RAIN = $rain
            """, rmClothes())
    }


    fun rmClothes(): RowMapper<Clothes> = RowMapper { rs, _ ->
        Clothes(rs.getString("name"), Type.valueOf(rs.getString("type").toUpperCase()))
    }
}