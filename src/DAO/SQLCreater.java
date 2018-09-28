
package DAO;

import java.util.List;

public class SQLCreater {
    public SQLCreater() {
    }

    public String select(String tableName, List<DateSet> list, Integer page) {
        String sql = "SELECT ";
        boolean flag = true;

        for (DateSet tmp : list) {
            sql += tmp.getColumn() + ",";
        }
        sql = sql.replaceFirst(",$", "");
        sql += " FROM " + tableName;

        for (DateSet tmp : list) {
            if (!tmp.getValue().isEmpty() && flag && !tmp.getValue().equals("-1")) {
                if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                    sql += " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }
                flag = false;
            }
        }

        sql += " OFFSET " + (page * 100) + " LIMIT 100";
        return sql;
    }

    public String getCount(String tableName, List<DateSet> list) {
        String sql = "SELECT COUNT(*) FROM " + tableName;

        boolean flag = true;

        for (DateSet tmp : list) {
            if (!tmp.getValue().isEmpty() && flag && !(tmp.getValue().equals("-1"))) {
                if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                    sql += " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            } else if (!tmp.getValue().isEmpty() && !(tmp.getValue() != "-1")) {
                if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                    sql += " AND " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " AND " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            }
        }

        return sql;
    }

    public String selectAnd(String tableName, List<DateSet> list, Integer page) {
        String sql = "SELECT ";
        boolean flag = true;

        for (DateSet tmp : list) {
            sql += tmp.getColumn() + ",";
        }
        sql = sql.replaceFirst(",$", "");
        sql += " FROM " + tableName;

        for (DateSet tmp : list) {
            if (!tmp.getValue().isEmpty() && flag && !(tmp.getValue().equals("-1"))) {
                if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                    sql += " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            } else if (!tmp.getValue().isEmpty() && !(tmp.getValue().equals("-1"))) {
                if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                    sql += " AND " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " AND " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            }
        }

        sql += " OFFSET " + (page * 100) + " LIMIT 100";

        return sql;
    }

    public String selectOr(String tableName, List<DateSet> list, Integer page) {
        String sql = "SELECT ";
        boolean flag = true;

        for (DateSet tmp : list) {
            sql += tmp.getColumn() + ",";
        }
        sql = sql.replaceFirst(",$", "");
        sql += " FROM " + tableName;

        for (DateSet tmp : list) {
            if (!tmp.getValue().isEmpty() && flag && !(tmp.getValue() != "-1")) {
                if (tmp.getMold() != "string") {
                    sql += " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            } else if (!tmp.getValue().isEmpty() && !(tmp.getValue() != "-1")) {
                if (tmp.getMold() != "string") {
                    sql += " OR " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " OR " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            }
        }

        sql += " OFFSET " + (page * 100) + " LIMIT 100";

        return sql;
    }

    public String insert(String tableName, List<DateSet> list) {
        String sql = "INSERT INTO " + tableName + "(" + ((DateSet) list.get(0)).getColumn();

        for (int i = 1;i < list.size();i++) {
            sql += "," + list.get(i).getColumn();
        }

        sql += ") VALUES (";
        boolean flag = true;

        for (DateSet tmp : list) {
            if (!tmp.getValue().isEmpty() && flag) {
                if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                    sql += tmp.getValue();
                } else {
                    sql += "'" + tmp.getValue() + "'";
                }

                flag = false;
            } else if (tmp.getMold() != "string" && tmp.getMold() != "date") {
                sql += "," + tmp.getValue();
            } else {
                sql += ",'" + tmp.getValue() + "'";
            }
        }

        sql += ")";
        System.out.println(sql);
        return sql;
    }

    public String update(String tableName, List<DateSet> list) {
        String sql = "UPDATE " + tableName;
        boolean flag = true;

        for (DateSet tmp : list) {
            if (!tmp.getValue().isEmpty() && flag && !(tmp.getValue().equals("-1"))) {
                if (tmp.getMold() != "string") {
                    sql += " SET " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " SET " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            } else if (!tmp.getValue().isEmpty() && !(tmp.getValue().equals("-1"))) {
                if (tmp.getMold() != "string") {
                    sql += "," + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += "," + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }
            }
        }

        if (list.get(0).getMold() != "string" && list.get(0).getMold() != "date") {
            sql += " WHERE " + ((DateSet) list.get(0)).getColumn() + " = " + list.get(0).getValue();
        } else {
            sql += " WHERE " + ((DateSet) list.get(0)).getColumn() + " = '" + list.get(0).getValue() + "'";
        }

        return sql;
    }

    public String delete(String tableName, List<DateSet> list) {
        String sql = "DELETE FROM " + tableName;
        boolean flag = true;

        for (DateSet tmp : list) {
            if (!tmp.getValue().isEmpty() && flag) {
                if (tmp.getMold() != "string") {
                    sql += " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            }
        }

        System.out.println(sql);
        return sql;
    }

    public String deleteAnd(String tableName, List<DateSet> list) {
        String sql = "DELETE FROM " + tableName;
        boolean flag = true;

        for (DateSet tmp : list) {
            if (!tmp.getValue().isEmpty() && flag && !(tmp.getValue().equals("-1"))) {
                if (tmp.getMold() != "string") {
                    sql += " WHERE " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " WHERE " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }

                flag = false;
            } else if (!tmp.getValue().isEmpty() && !(tmp.getValue().equals("-1"))) {
                if (tmp.getMold() != "string") {
                    sql += " AND " + tmp.getColumn() + " = " + tmp.getValue();
                } else {
                    sql += " AND " + tmp.getColumn() + " = '" + tmp.getValue() + "'";
                }
            }
        }

        System.out.println(sql);
        return sql;
    }
}
