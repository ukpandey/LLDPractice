package Observer;

import Observable.StockObservable;

public class EmailObserverImpl implements NotificationObserver{
    String email;
    StockObservable observable;
    public EmailObserverImpl(String email, StockObservable observable){
        this.email = email;
        this.observable = observable;
    }

    @Override
    public void update(String message) {
        sendEmail(email, message);
    }
    private  void sendEmail(String email, String message){
        System.out.println("Mail sent to " + email + "Message: " + message);
    }
}
