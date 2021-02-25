package ru.suvorov.weather.infrastructure.db.persistance.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.queryForObject
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Repository
import ru.suvorov.weather.core.component.user.MyUserDetails
import ru.suvorov.weather.core.port.secondary.UserRepository
import java.sql.ResultSet

@Repository
class UserRepositoryAdapter(
        @Autowired private val jdbc: JdbcTemplate
) : UserRepository {
    //TODO: Delete limit and order so multiple authority is ok
    override fun getUserDetails(username: String) = jdbc.queryForObject("""
        SELECT users.username, password, enabled, role AS authority
        FROM users
            JOIN user_roles ON users.username = user_roles.username
        WHERE users.username = '$username'
        ORDER BY id
        LIMIT 1""",
            rmUserDetails()
    )!!

    override fun getUserIdByUsername(username: String): Long = jdbc.queryForObject("""
            SELECT ID FROM USERS WHERE USERNAME = '$username'
        """)

    fun rmUserDetails() = RowMapper { rs, _ ->
        MyUserDetails(
                rs.getString("username"),
                rs.getString("password"),
                rs.getBoolean("enabled"),
                SimpleGrantedAuthority (rs.getString("authority")))
    }
}