package DAO;

public class DateSet {
    private String column;
    private String mold;
    private String value;

    public DateSet(String column, String mold, String value) {
        this.column = column;
        this.mold = mold;
        this.value = value;
    }

    public String getColumn() {
        return this.column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getMold() {
        return this.mold;
    }

    public void setMold(String mold) {
        this.mold = mold;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
