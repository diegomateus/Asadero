package bussiness;

public class Waiter {

    private String name;
    private Long sells;

    public Waiter(String name, Long sells) {
        this.name = name;
        this.sells = sells;
    }

    public Waiter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSells() {
        return sells;
    }

    public void setSells(Long sells) {
        this.sells = sells;
    }
}
