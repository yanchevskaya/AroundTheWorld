package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Listener for initializing database
 * @author Ali Yan
 * @version 1.0
 */
@WebListener
public class DBInit implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(DBInit.class);

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Pattern pattern = Pattern.compile("^\\d+\\.sql$");
        Path sqlDirectoryPath = Paths.get(sce.getServletContext().getRealPath(
                "WEB-INF/classes/sql"));

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             DirectoryStream<Path> paths = Files.newDirectoryStream(sqlDirectoryPath)) {
            for (Path filePath : paths)
                if (pattern.matcher(filePath.toFile().getName()).find()){
                    statement.addBatch(
                            Files.lines(filePath)
                                .collect(Collectors.joining())
                    );
            }
            statement.executeBatch();
        } catch (IOException | SQLException e){
            log.error("Unexpected message", e);
        }
    }
}