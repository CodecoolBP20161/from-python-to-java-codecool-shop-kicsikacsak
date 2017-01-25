package com.codecool.shop.util;

/**
 * Created by svindler on 24.01.2017.
 */
import java.io.*;
public class SQLRunner {

    public static void initDB() {
        try {
            String line;
            Process p = Runtime.getRuntime().exec

            ("psql -U svindler -d codecoolshop -h /var/run/postgresql -f src/main/sql/init_db.sql");
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }
}
