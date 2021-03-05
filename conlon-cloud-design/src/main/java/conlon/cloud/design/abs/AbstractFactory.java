package conlon.cloud.design.abs;

/**
 * @program: conlon-cloud abstractFactory.java
 * @description: ${description}
 * @author: Mr conlon
 * @create: 2021-03-04 21:29
 */
public abstract class AbstractFactory {
   public abstract BuildPay createBuild();
   public abstract SendPay createSend();
}
