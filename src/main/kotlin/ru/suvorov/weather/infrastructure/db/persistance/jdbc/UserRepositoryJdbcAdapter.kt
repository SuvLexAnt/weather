package ru.suvorov.weather.infrastructure.db.persistance.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Repository
import ru.suvorov.weather.core.component.user.MyUserDetails
import ru.suvorov.weather.core.port.secondary.UserRepository

private const val USER_DETAILS_QUERY = "SELECT id, username, password, enabled FROM users"

@Repository
class UserRepositoryJdbcAdapter(
        @Autowired private val jdbc: JdbcTemplate
) : UserRepository {
    override fun getUserDetails(username: String): MyUserDetails? {
        val userDetails = jdbc.queryForObject("""$USER_DETAILS_QUERY WHERE username = '$username'""",
                rmUserDetails())
        userDetails?.authorities = getUserAuthority(username)
        return userDetails
    }

    private fun getUserAuthority(username: String) = jdbc.query("""
        SELECT role 
        FROM user_roles
        WHERE username = '$username'
    """, rmAuthorities())

    fun rmAuthorities(): RowMapper<GrantedAuthority> = RowMapper { rs, _ ->
        SimpleGrantedAuthority(rs.getString("ROLE"))
    }

    fun rmUserDetails() = RowMapper { rs, _ ->
        MyUserDetails(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getBoolean("enabled"))
    }
}