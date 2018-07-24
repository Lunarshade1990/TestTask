package com.lunarshade.Dao;

import com.lunarshade.model.UserData;
import com.lunarshade.util.DataBaseUtil;
import com.lunarshade.util.Sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDataDataImpl implements UserDataDao {

    private static Connection connection = null;
    private static Statement statement = null;

    @Override
    public Statement getConnection() {
        try {
        connection = DataBaseUtil.getDBConnection();
        statement = connection.createStatement();
        } catch (SQLException e) {
            e.getMessage();
        }

        return statement;

    }

    public void closeConnection() {
        try {
        if (connection != null) connection.close();
        if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    @Override
    public List<UserData> getTopFiveForms() {

        List<UserData> userDataList = new ArrayList<>();

        try {
            Statement stmt = getConnection();
            ResultSet res = stmt.executeQuery(Sql.TOP_FIVE_FORMS);

            while (res.next()) {
                UserData userData = new UserData();
                userData.setGrp(res.getString("grp"));
                userDataList.add(userData);
            }

        }catch (SQLException e) {
            e.getMessage();
        }

        closeConnection();



        return userDataList;

    }

    @Override
    public List<UserData> getUsedFormForLastHour() {
        List<UserData> userDataList = new ArrayList<>();

        try {
            Statement stmt = getConnection();
            ResultSet res = stmt.executeQuery(Sql.USED_FORM_FOR_LAST_HOUR);

            while (res.next()) {
                UserData userData = new UserData();
                userData.setSsoid(res.getString("ssoid"));
                userData.setGrp(res.getString("grp"));
                userDataList.add(userData);
            }

        }catch (SQLException e) {
            e.getMessage();
        }

        closeConnection();

        return userDataList;
    }

    @Override
    public List<UserData> getUnfinishedUsers() {
        List<UserData> userDataList = new ArrayList<>();

        try {
            Statement stmt = getConnection();
            ResultSet res = stmt.executeQuery(Sql.UNFINISHED_USERS);

            while (res.next()) {
                UserData userData = new UserData();
                userData.setSsoid(res.getString("ssoid"));
                userData.setSubtype(res.getString("subtype"));
                userDataList.add(userData);
            }

        }catch (SQLException e) {
            e.getMessage();
        }

        closeConnection();

        return userDataList;
    }
}
