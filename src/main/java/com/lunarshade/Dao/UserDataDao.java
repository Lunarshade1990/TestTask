package com.lunarshade.Dao;

import com.lunarshade.model.UserData;

import java.sql.Statement;
import java.util.List;

public interface UserDataDao {

    public Statement getConnection();
    public void closeConnection();
    public List<UserData> getTopFiveForms();
    public List<UserData> getUsedFormForLastHour();
    public List<UserData> getUnfinishedUsers();

}
