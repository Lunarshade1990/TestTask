package com.lunarshade.util;

import com.lunarshade.model.UserData;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataBaseUtil {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "123456";
    static final String DB_DRIVER = "org.postgresql.Driver";
    static  int i = 1;



    public static Connection getDBConnection() {

        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }




    public static void createDbUserTable(String tableName, String sql) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE "+  tableName + " (" + sql + ")";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполнить SQL запрос
            statement.execute(createTableSQL);
            System.out.println("Таблица " + tableName + " создана!");
            UserData.setTableName(tableName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }




    public static void CVSImport(String table_name, String path) throws FileNotFoundException, IOException, SQLException {

        Connection dbConnection = null;
        Statement statement = null;
        String string = "";
        i = 1;
        final String UP = ", \'";
        final String END = "\'";

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

        ArrayList<String[]> startArray = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        System.out.println("Подключаемся к базе данных.....");

        dbConnection = getDBConnection();
        statement = dbConnection.createStatement();
        System.out.println("........Подключение установлено");

        ResultSet rs = statement.executeQuery("SELECT * FROM user_data");
        ResultSetMetaData rsmd = rs.getMetaData();

        int numberOfColumns = rsmd.getColumnCount();


        System.out.println("Начинаем считывать файл....");
        while ((string = br.readLine()) != null) {
            startArray.add(string.split(";"));
        }

        for (String[] strings : startArray) {

            StringBuilder stringResult = new StringBuilder();

            for (int columnIndex =0; columnIndex < numberOfColumns; columnIndex++) {

                String stringElement = strings[columnIndex];

                if (stringElement.length() == 0) stringResult.append(", null");

                else if (NumberUtils.isNumber(stringElement) && (rsmd.getColumnType(columnIndex+1) == Types.TIME || rsmd.getColumnType(columnIndex+1) == Types.DATE)) {

                    String date = new SimpleDateFormat("HH:mm:ss").format(new Date(TimeUnit.MILLISECONDS.convert(NumberUtils.createLong(stringElement), TimeUnit.SECONDS)));
                    stringResult.append(UP + date + END);


                } else if (rsmd.getColumnType(columnIndex+1) == Types.TIMESTAMP){

                    try {
                        stringResult.append(UP + getRightDate(stringElement) + END);
                    } catch (NumberFormatException e) {
                        e.getMessage();
                    }


                }else stringResult.append(UP + stringElement + END);

            }

            stringResult.delete(0, 2);
            stringResult.insert(0,"(");
            stringResult.insert(stringResult.length(), ")");
            result.add(stringResult.toString());
        }

        br.close();

        System.out.println("Считывание файла закончено. Загружаем в базу....");



            try {

                executeQuery(i, result, "user_data");

            } catch (SQLException e) {
                e.getMessage();
            }

        System.out.println("Данные внесены!");

    }




    private static String createSqlString(ArrayList<String> array, int i, String table_name) {
        return "INSERT INTO " + table_name + " VALUES " + " " + array.get(i);
}




    private static String getRightDate(String ymdh) throws  NumberFormatException {
        String[] resultString = ymdh.split("-");
        int[] resInt = new int[resultString.length];

        for (int i = 0; i < resultString.length; i++) {
            resInt[i] = Integer.parseInt(resultString[i]);
        }

        Calendar date = Calendar.getInstance();
        date.set(resInt[0], resInt[1]-1, resInt[2], resInt[3], 0,0);

        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date.getTime());
    }




    private static void executeQuery(int s, ArrayList<String> array, String table_name) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            for ( ; s < array.size(); s++, i++) {
                statement.executeUpdate(createSqlString(array, i, table_name));
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(i + " " + array.get(i));
            i+=1;
            executeQuery(i, array, table_name);


        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }


}
