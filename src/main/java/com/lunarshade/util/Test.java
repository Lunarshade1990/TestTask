package com.lunarshade.util;

import java.io.IOException;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {

        String pathCVS = "E:\\JavaLearning\\Introduction to Spring MVC\\springmvc-intro\\DataBaseAccesseTeat\\src\\main\\resourses\\test_case.csv";

        try {
            DataBaseUtil.createDbUserTable("user_data", Sql.USER_DATA_TABLE);
            DataBaseUtil.CVSImport("user_data", pathCVS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
