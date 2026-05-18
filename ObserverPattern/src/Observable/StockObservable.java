package Observable;

import Observer.NotificationObserver;

public interface StockObservable {
    void addObservers(NotificationObserver observer);
    void removeObservers(NotificationObserver observer);
    void notifyObservers();
    void setStockCount(int stocksAdded);
    int getStockCount();
}
