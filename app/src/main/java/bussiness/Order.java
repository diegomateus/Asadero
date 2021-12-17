package bussiness;

import java.util.List;

public class Order {
    public long price;
    public Waiter waiter;
    public List <Dish> dishes;
    public int table;

    public Order(long price, Waiter waiter, List<Dish> dishes, int table) {
        this.price = price;
        this.waiter = waiter;
        this.dishes = dishes;
        this.table = table;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }
}
