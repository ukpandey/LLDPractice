import Observable.IphoneObservableImpl;
import Observable.MacbookObservableImpl;
import Observable.StockObservable;
import Observer.EmailObserverImpl;
import Observer.MobileObserverImpl;
import Observer.NotificationObserver;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StockObservable iphoneObservable = new IphoneObservableImpl();
        StockObservable macbookObservable = new MacbookObservableImpl();

        NotificationObserver observer1 = new EmailObserverImpl("email1", iphoneObservable);
        NotificationObserver observer2 = new EmailObserverImpl("email2", iphoneObservable);
        NotificationObserver observer3 = new MobileObserverImpl("user1", iphoneObservable);

        NotificationObserver observer4 = new MobileObserverImpl("user2", macbookObservable);
        NotificationObserver observer5 = new MobileObserverImpl("user3", macbookObservable);
        NotificationObserver observer6 = new EmailObserverImpl("email3", macbookObservable);

        iphoneObservable.addObservers(observer1);
        iphoneObservable.addObservers(observer2);
        iphoneObservable.addObservers(observer3);

        macbookObservable.addObservers(observer4);
        macbookObservable.addObservers(observer5);
        macbookObservable.addObservers(observer6);

        iphoneObservable.setStockCount(100);
        macbookObservable.setStockCount(90);

    }
}