package com.koolearn.qa.dao.mantis;

import com.koolearn.qa.model.Mantis;
import com.koolearn.qa.util.DBUtils;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/5
 */
public class MantisDAO {
    public static Mantis statisticsEveryday(Map<String, Object> map) throws UnsupportedEncodingException {
        Connection conn = DBUtils.connection();
        Statement stmt = null;
        ResultSet rs = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer sb = new StringBuffer("SELECT project_id,category,version,sum(CASE  WHEN status = 50 THEN 1 ELSE 0 END ) AS assignedBugs,");
        sb.append("sum(CASE  WHEN date_submitted >= '").append(formatter.format(map.get("date"))).append("' AND date_submitted <= '").append(formatter.format(map.get("date"))).append(" 23:59:59' THEN 1 ELSE 0 END ) AS newBugs,");
        sb.append("sum(CASE WHEN status = 40 THEN 1 ELSE 0 END) AS confirmedBugs,sum(CASE WHEN status = 80 THEN 1 ELSE 0 END ) AS resolvedBugs,sum(CASE WHEN status = 20 THEN 1 ELSE 0 END  ) AS feedbackBugs,sum(CASE WHEN status = 90 THEN 1 ELSE 0 END ) AS closedBugs FROM mantis_bug_table WHERE project_id =");
        sb.append(map.get("project_id"));
        if (map.get("category") != null) {
            sb.append(" AND category='").append(new String(map.get("category").toString().getBytes(), "latin1")).append("'");
        }
        if (map.get("version") != null) {
            sb.append(" AND version= '")
                  //  .append(new String(map.get("version").toString().getBytes(), "latin1")).append("'");
                    .append(map.get("version")).append("'");

        }
        sb.append(" GROUP BY project_id");
        System.out.println("********"+sb.toString());
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery( new String(sb.toString().getBytes(), "latin1"));
            Mantis mantis = new Mantis();
            while (rs.next()) {
                mantis.setProject_id(rs.getInt("project_id"));
                mantis.setCategory(rs.getString("category"));
                mantis.setVersion(rs.getString("version"));
                mantis.setDate((java.util.Date) map.get("date"));
                mantis.setAssignedBugs(rs.getInt("assignedBugs"));
                mantis.setNewBugs(rs.getInt("newBugs"));
                mantis.setConfirmedBugs(rs.getInt("confirmedBugs"));
                mantis.setResolvedBugs(rs.getInt("resolvedBugs"));
                mantis.setFeedbackBugs(rs.getInt("feedbackBugs"));
                mantis.setClosedBugs(rs.getInt("closedBugs"));
            }
            rs.close();
            stmt.close();
            conn.close();
            return mantis;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn,stmt,rs);
            return null;
        }
    }


}
