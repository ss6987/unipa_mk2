
package DAO;

import java.util.List;

public class SQLCreater {
    public SQLCreater() {
    }

    public String select(String tableName, List<DateSet> list) {
        String sql = "SELECT * FROM " + tableName;
        boolean flag = true;

        for (int i = 0; i < list.size(); ++i) {
            DateSet tmp = (DateSet) list.get(i);
            if (!tmp.getValue().isEmpty() && flag && !tmp.getValue().equals("-1")) {
                if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                    sql = sql + " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql = sql + " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            }
        }

        System.out.println(sql);
        return sql;
    }

    public String selectAnd(String tableName, List<DateSet> list) {
        String sql = "SELECT * FROM " + tableName;
        boolean flag = true;

        for (int i = 0; i < list.size(); ++i) {
            DateSet tmp = (DateSet) list.get(i);
            if (!tmp.getValue().isEmpty() && flag && !(tmp.getValue().equals("-1"))) {
                if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                    sql = sql + " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql = sql + " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            } else if (!tmp.getValue().isEmpty() && !(tmp.getValue() != "-1")) {
                if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                    sql = sql + " AND " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql = sql + " AND " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            }
        }

        System.out.println(sql);
        return sql;
    }

    public String selectOr(String tableName, List<DateSet> list) {
        String sql = "SELECT * FROM " + tableName;
        boolean flag = true;

        for (int i = 0; i < list.size(); ++i) {
            DateSet tmp = (DateSet) list.get(i);
            if (!tmp.getValue().isEmpty() && flag && !(tmp.getValue() != "-1")) {
                if (tmp.getMold() != "string") {
                    sql = sql + " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql = sql + " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            } else if (!tmp.getValue().isEmpty() && !(tmp.getValue() != "-1")) {
                if (tmp.getMold() != "string") {
                    sql = sql + " OR " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql = sql + " OR " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            }
        }

        System.out.println(sql);
        return sql;
    }

    public String insert(String tableName, List<DateSet> list) {
        String sql = "INSERT INTO " + tableName + "(" + ((DateSet) list.get(0)).getColumn();

        for (int i = 1; i < list.size(); ++i) {
            sql = sql + "," + ((DateSet) list.get(i)).getColumn();
        }

        sql = sql + ") VALUES (";
        boolean flag = true;

        for (int j = 0; j < list.size(); ++j) {
            DateSet tmp = (DateSet) list.get(j);
            if (!tmp.getValue().isEmpty() && flag) {
                if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                    sql = sql + tmp.getValue();
                } else {
                    sql = sql + "'" + tmp.getValue() + "'";
                }

                flag = false;
            } else if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                sql = sql + "," + tmp.getValue();
            } else {
                sql = sql + ",'" + tmp.getValue() + "'";
            }
        }

        sql = sql + ")";
        System.out.println(sql);
        return sql;
    }

    public String update(String tableName, List<DateSet> list) {
        String sql = "UPDATE " + tableName;
        boolean flag = true;

        for (int i = 0; i < list.size(); ++i) {
            DateSet tmp = (DateSet) list.get(i);
            if (!tmp.getValue().isEmpty() && flag) {
                if (tmp.getMold() != "string") {
                    sql = sql + " SET " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql = sql + " SET " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            } else if (!tmp.getValue().isEmpty()) {
                if (tmp.getMold() != "string") {
                    sql = sql + "," + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql = sql + "," + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }
            }
        }

        if (list.get(0).getMold() != "string" && list.get(0).getMold() != "date") {
            sql = sql + " WHERE " + ((DateSet) list.get(0)).getColumn() + " = " + list.get(0).getValue();
        } else {
            sql = sql + " WHERE " + ((DateSet) list.get(0)).getColumn() + " = '" + list.get(0).getValue() + "'";
        }
        return sql;
    }

    public String delete(String tableName, List<DateSet> list) {
        String sql = "DELETE FROM " + tableName;
        boolean flag = true;

        for (int i = 0; i < list.size(); ++i) {
            DateSet tmp = (DateSet) list.get(i);
            if (!tmp.getValue().isEmpty() && flag) {
                if (tmp.getMold() != "string") {
                    sql = sql + " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql = sql + " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            }
        }

        System.out.println(sql);
        return sql;
    }

    public String deleteAnd(String tableName,List<DateSet> list){
        String sql = "DELETE FROM " + tableName;
        boolean flag = true;

        for (int i = 0; i < list.size(); ++i) {
            DateSet tmp = (DateSet) list.get(i);
            if (!tmp.getValue().isEmpty() && flag && !(tmp.getValue().equals("-1"))) {
                if (tmp.getMold() != "string") {
                    sql = sql + " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql = sql + " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            }else if(!tmp.getValue().isEmpty() && !(tmp.getValue().equals("-1"))){
                if (tmp.getMold() != "string") {
                    sql = sql + " AND " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql = sql + " AND " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }
            }
        }

        System.out.println(sql);
        return sql;
    }
}
