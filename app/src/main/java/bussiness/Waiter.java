package bussiness;

public class Waiter {

    private String name;
    private long sells;

    public Waiter(String name, long sells) {
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

    public long getSells() {
        return sells;
    }

    public void setSells(long sells) {
        this.sells = sells;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
