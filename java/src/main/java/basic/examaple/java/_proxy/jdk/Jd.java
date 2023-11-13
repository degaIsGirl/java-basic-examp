package basic.examaple.java._proxy.jdk;

public class Jd implements IShop{
    @Override
    public String pay(String orderNo) {
        System.out.println("exec jd pay method");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String debug() {
        System.out.println("exec jd debug method");
        return null;
    }
}
