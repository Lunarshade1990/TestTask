package com.lunarshade.util;

import com.lunarshade.model.UserData;

public class Sql {
    public static final String USER_DATA_TABLE = ""
            + "ssoid VARCHAR(36), "
            + "ts TIME, "
            + "grp VARCHAR(50),"
            + "type VARCHAR(50), "
            + "subtype VARCHAR(50), "
            + "url  VARCHAR(200), "
            + "orgid  VARCHAR(50), "
            + "formid  VARCHAR(50), "
            + "code  VARCHAR(50), "
            + "ltpa  VARCHAR(50), "
            + "sudirresponse VARCHAR(50), "
            + "ymdh TIMESTAMP"
            + "";

    public static final String TOP_FIVE_FORMS = "SELECT " + Sql.GRP_REGEX + " as grp FROM " + UserData.getTableName() + " WHERE grp IS NOT NULL GROUP BY " + Sql.GRP_REGEX + " ORDER BY COUNT(*) DESC LIMIT 5";

    public static final String USED_FORM_FOR_LAST_HOUR = "SELECT DISTINCT ssoid, "+ Sql.GRP_REGEX + " as grp FROM " + UserData.getTableName() + " WHERE ts >= ((SELECT MAX(ts) FROM " + UserData.getTableName() + ") - interval '1 hour')";

    public static final String UNFINISHED_USERS = "select ssoid, subtype from " + UserData.getTableName() + " where concat(ssoid, ts) in (select concat(ssoid, max(ts)) from " + UserData.getTableName() + " group by ssoid) and subtype not in('success', 'result', 'done', 'pay', 'confirmBooking')";

    public static final String GRP_REGEX = "regexp_replace(grp, '[_].+|[-]?[0-9]+', '')";

    public static final String GRP_AND_SUBTYPES = "select distinct regexp_replace(grp, '[_].+|[-]?[0-9]+', '') as grp, subtype from " + UserData.getTableName() + " order by grp";
}
