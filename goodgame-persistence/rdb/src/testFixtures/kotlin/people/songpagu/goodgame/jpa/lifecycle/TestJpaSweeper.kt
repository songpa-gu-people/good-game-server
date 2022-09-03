package people.songpagu.goodgame.jpa.lifecycle

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import people.songpagu.infrastructure.log.Slf4j
import people.songpagu.infrastructure.log.Slf4j.Companion.log
import java.sql.Connection
import javax.sql.DataSource

@Slf4j
@Repository
class TestJpaSweeper(
    private var dataSource: DataSource
) {
    @Transactional
    fun sweep() {
        try {
            log.info("jpa repository sweep start.")

            val c: Connection = dataSource.connection
            val s = c.createStatement()

            // Disable FK
            s.execute("SET REFERENTIAL_INTEGRITY FALSE")

            // Find all tables and truncate them
            val tables: MutableSet<String> = HashSet()
            var rs = s.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA='public'")
            while (rs.next()) {
                tables.add(rs.getString(1))
            }
            rs.close()
            for (table in tables) {
                s.executeUpdate("TRUNCATE TABLE $table")
            }

            // Enable FK
            s.execute("SET REFERENTIAL_INTEGRITY TRUE")
            s.close()
            c.close()

            log.info("jpa repository sweep end.")
        } catch (e: Exception) {
            throw IllegalArgumentException(e.message)
        }
    }
}
