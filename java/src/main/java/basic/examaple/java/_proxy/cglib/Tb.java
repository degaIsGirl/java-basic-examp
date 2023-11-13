package basic.examaple.java._proxy.cglib;

public class Tb extends AbstractShop {
    @Override
    public String pay(String orderNo) {
        System.out.println("exec tb pay method");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
