package ru.suvorov.weather.infrastructure.db.persistance

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository
import ru.suvorov.weather.core.port.secondary.UserRepository

@Repository
class UserRepositoryAdapter(
        @Autowired private val jdbc: JdbcTemplate
) : UserRepository {
    override fun getUserIdByUsername(username: String): Long = jdbc.queryForObject("""
            SELECT ID FROM USERS WHERE USERNAME = '$username'
        """)
}