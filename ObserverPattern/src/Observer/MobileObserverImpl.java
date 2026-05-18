package Observer;

import Observable.StockObservable;

public class MobileObserverImpl implements NotificationObserver{
    String username;
    StockObservable observable;
    public MobileObserverImpl(String username, StockObservable observable){
        this.username = username;
        this.observable = observable;
    }

    @Override
    public void update(String message) {
        sendMessage(username, message);
    }
    private void sendMessage(String username, String message){
        System.out.println("Message sent to username: "+ username+ " Message: " + message);
    }
}
