package etc;

public class Paging {
    private Integer count = 0;
    private Integer startPage = 1;
    private Integer nowPage = 1;
    private Integer lastPage;

    public Paging(){

    }

    public Paging(Integer count) {
        this.count = count;
        this.lastPage = this.count / 100 + 1;
    }

    public void changePage(Integer nextPage) {
        this.nowPage = nextPage;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public Integer getNowPage() {
        return nowPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }
}
