package basic.examaple.java._proxy.cglib;


public class Jd extends AbstractShop {
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

    public final boolean stock() {
        System.out.println("exec jd stock method");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
