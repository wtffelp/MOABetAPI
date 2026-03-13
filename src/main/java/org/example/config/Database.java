package org.example.config;
import org.jdbi.v3.core.Jdbi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Database {
    private static Jdbi jdbi;

    public static Jdbi getJdbi() {
        if (jdbi == null){
            try {
                Properties props = new Properties();
                FileInputStream input = new FileInputStream("db.properties");
                props.load(input);
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");
                jdbi = Jdbi.create(url, props);
            } catch (IOException e) {
                System.out.println("Erro ao conectar com o banco: " + e.getMessage());
            }
            return jdbi;
        }
        return null;
    }
}
