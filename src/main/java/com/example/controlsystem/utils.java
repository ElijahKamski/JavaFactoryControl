package com.example.controlsystem;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class utils {
    private static final String url = "jdbc:mysql://localhost:3306/ant";
    private static final String user = "root";
    private static final String password = "0000";

    public static String ReadFrom(String query) {
        // JDBC variables for opening and managing connection

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            // opening database connection to MySQL server

            // getting Statement object to execute query

            // executing SELECT query
            if (rs.next()) {
                System.out.println(query);
                return rs.getString(1);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }

    public static void WriteTo(String query) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            System.out.println(query);
            stmt.executeUpdate(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    /**
     * Get a diff between two dates
     *
     * @param date1 the oldest date
     * @param date2 the newest date
     * @return the diff value, in the provided unit
     */
    public static String timePassed(Date date1, Date date2) {
        long millis = date2.getTime() - date1.getTime();
        long minutes = (millis / 1000) / 60;
        long hours = minutes / 60;
        minutes %= 60;
        int seconds = (int) ((millis / 1000) % 60);
        return hours + ":" + minutes + ":" + seconds;
    }

    public static Vector<String> ReadArray(String query) {
        // JDBC variables for opening and managing connection

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            // opening database connection to MySQL server

            // getting Statement object to execute query

            // executing SELECT query
            Vector<String> res = new Vector<String>();
            System.out.println(query);
            while (rs.next()) {
                res.add(rs.getString("res"));
            }
            return res;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }

    public static Vector<String> getDatesWindow(int window_size) {
        Date date = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        Date today = new Date();
        ;
        cal.add(Calendar.DAY_OF_MONTH, -6);
        Vector<String> result = new Vector<>();
        for (int i = 0; i < 7; i++) {
            result.add(fmt.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return result;
    }

    public static Vector<WorkerRecord> getTableSklad() {
        Vector<String> logins = ReadArray("select login as res from workers where status='rabochiy'");
        Vector<String> names = ReadArray("select name as res from workers where status='rabochiy'");
        Vector<String> dates = getDatesWindow(7);
        Vector<WorkerRecord> result = new Vector<>();
        assert names != null;
        assert logins != null;
        for (int i = 0; i < names.size(); ++i) {
            WorkerRecord tmp_row = new WorkerRecord();
            String cur_log = logins.get(i);
            tmp_row.setName(names.get(i));
            System.out.println(dates.get(0));
            for (int j = 0; j < dates.size(); ++j) {
                String cur_date = dates.get(j);
                System.out.println(cur_log+' '+cur_date);
                int vyp = Integer.parseInt(Objects.requireNonNull(ReadFrom("select coalesce((select" +
                        "(select proizv as res from result where login='" +
                        cur_log +
                        "' and date='" +
                        cur_date +
                        "')" +
                        "as res),0) as res")));
                int articul = Integer.parseInt(Objects.requireNonNull(ReadFrom("select coalesce((select" +
                        "(select articul as res from result where login='" +
                        cur_log +
                        "' and date='" +
                        cur_date +
                        "')" +
                        "as res),0) as res")));
                int plan = Integer.parseInt(Objects.requireNonNull(ReadFrom("select coalesce((select" +
                        "(select plan from details where articul=" +
                        articul +
                        ") as res),0) as res")));
                String color = "r";
                if(vyp==0 && articul==0){
                    color="_";
                }
                else if (vyp >= plan) {
                    color = "g";
                } else if ((plan - vyp) < 0.1 * plan) {
                    color = "o";
                }
                if (j == 0) {
                    tmp_row.setDay1(color + vyp);
                } else if (j == 1) {
                    tmp_row.setDay2(color + vyp);
                } else if (j == 2) {
                    tmp_row.setDay3(color + vyp);
                } else if (j == 3) {
                    tmp_row.setDay4(color + vyp);
                } else if (j == 4) {
                    tmp_row.setDay5(color + vyp);
                } else if (j == 5) {
                    tmp_row.setDay6(color + vyp);
                } else {
                    tmp_row.setDay7(color + vyp);
                }
            }
            result.add(tmp_row);
        }
        return result;
    }
    public static Vector<WorkerRecord> getTableRab() {
        Vector<String> logins = ReadArray("select login as res from workers where status='rabochiy'");
        Vector<String> names = ReadArray("select name as res from workers where status='rabochiy'");
        Vector<String> dates = getDatesWindow(7);
        Vector<WorkerRecord> result = new Vector<>();
        assert names != null;
        assert logins != null;
        for (int i = 0; i < names.size(); ++i) {
            WorkerRecord tmp_row = new WorkerRecord();
            String cur_log = logins.get(i);
            tmp_row.setName(names.get(i));
            System.out.println(dates.get(0));
            for (int j = 0; j < dates.size(); ++j) {
                String cur_date = dates.get(j);
                System.out.println(cur_log+' '+cur_date);
                int vyp = Integer.parseInt(Objects.requireNonNull(ReadFrom("select coalesce((select" +
                        "(select proizv as res from result where login='" +
                        cur_log +
                        "' and date='" +
                        cur_date +
                        "')" +
                        "as res),0) as res")));
                int articul = Integer.parseInt(Objects.requireNonNull(ReadFrom("select coalesce((select" +
                        "(select articul as res from result where login='" +
                        cur_log +
                        "' and date='" +
                        cur_date +
                        "')" +
                        "as res),0) as res")));
                int plan = Integer.parseInt(Objects.requireNonNull(ReadFrom("select coalesce((select" +
                        "(select plan from details where articul=" +
                        articul +
                        ") as res),0) as res")));
                String color = "r";
                String start=ReadFrom("select coalesce((select" +
                        "(select start as res from result where login='" +
                        cur_log +
                        "' and date='" +
                        cur_date +
                        "')" +
                        "as res),'00:00:00') as res");
                String end=ReadFrom("select coalesce((select" +
                        "(select end as res from result where login='" +
                        cur_log +
                        "' and date='" +
                        cur_date +
                        "')" +
                        "as res),'23:59:59') as res");
                String pos = start+" - "+end;
                if(vyp==0 && articul==0){
                    color="_";
                }
                else if (vyp >= plan) {
                    color = "g";
                } else if ((plan - vyp) < 0.1 * plan) {
                    color = "o";
                }
                if (j == 0) {
                    tmp_row.setDay1(color + pos);
                } else if (j == 1) {
                    tmp_row.setDay2(color + pos);
                } else if (j == 2) {
                    tmp_row.setDay3(color + pos);
                } else if (j == 3) {
                    tmp_row.setDay4(color + pos);
                } else if (j == 4) {
                    tmp_row.setDay5(color + pos);
                } else if (j == 5) {
                    tmp_row.setDay6(color + pos);
                } else {
                    tmp_row.setDay7(color + pos);
                }
            }
            result.add(tmp_row);
        }
        return result;
    }
}
