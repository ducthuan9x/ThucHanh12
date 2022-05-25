package Model;

public class Order {
    private int id;
    private String time;
    private int total;
    private int cId;

    public Order() {
    }

    public Order(int id, String time, int total, int cId) {
        this.id = id;
        this.time = time;
        this.total = total;
        this.cId = cId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }
}
