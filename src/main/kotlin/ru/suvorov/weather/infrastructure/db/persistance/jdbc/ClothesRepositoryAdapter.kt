package ru.suvorov.weather.infrastructure.db.persistance.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import ru.suvorov.weather.core.component.clothes.Clothes
import ru.suvorov.weather.core.component.clothes.ClothesShort
import ru.suvorov.weather.core.component.clothes.Type
import ru.suvorov.weather.core.port.secondary.ClothesRepository

private const val CLOTHES_QUERY = """SELECT ID, NAME, TYPE, TEMPERATURE, SNOW, RAIN, IMAGE FROM clothes"""

@Repository
class ClothesRepositoryAdapter(
        @Autowired private val jdbc: JdbcTemplate
) : ClothesRepository {

    override fun getAllClothesNotAuthorizedUser(): List<Clothes> = jdbc.query("""
        $CLOTHES_QUERY
        WHERE USER_ID IS NULL
    """, rmClothes())

    override fun getAllClothesByUserId(userId: Long): List<Clothes> = jdbc.query("""
        $CLOTHES_QUERY
        WHERE USER_ID = $userId OR USER_ID IS NULL
    """, rmClothes())

    override fun addClothesForUserId(clothes: Clothes, userId: Long) {
        jdbc.update("""
            INSERT INTO clothes (NAME, TYPE, TEMPERATURE, SNOW, RAIN, USER_ID) VALUES (?, ?, ?, ?, ?, ?)
            """,
                clothes.name,
                clothes.type.name.toLowerCase(),
                clothes.temperature,
                clothes.snow,
                clothes.rain,
                userId)
    }

    override fun getAllClothes(): List<Clothes> = jdbc.query(CLOTHES_QUERY, rmClothes())

    override fun getClothesByParams(snow: Boolean,
                                    rain: Boolean,
                                    temperature: Double,
                                    temperatureDiff: Int): List<ClothesShort> = jdbc.query("""
            SELECT NAME, TYPE FROM CLOTHES WHERE
            TEMPERATURE BETWEEN ${temperature - temperatureDiff} AND ${temperature + temperatureDiff} 
            AND SNOW = $snow 
            AND RAIN = $rain
            """, rmClothesShort())
}

fun rmClothesShort(): RowMapper<ClothesShort> = RowMapper { rs, _ ->
    ClothesShort(
            rs.getString("NAME"),
            Type.valueOf(rs.getString("TYPE").toUpperCase()))
}

fun rmClothes(): RowMapper<Clothes> = RowMapper { rs, _ ->
    Clothes(
            rs.getLong("ID"),
            rs.getString("NAME"),
            Type.valueOf(rs.getString("TYPE").toUpperCase()),
            rs.getLong("TEMPERATURE").toInt(),
            rs.getBoolean("SNOW"),
            rs.getBoolean("RAIN"))
}