package basic.examaple.java._proxy.jdk;

public class Tb implements IShop{
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
