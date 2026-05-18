package Observable;

import Observer.NotificationObserver;
import java.util.ArrayList;
import java.util.List;

public class MacbookObservableImpl implements StockObservable {

    List<NotificationObserver> observerList = new ArrayList<>();
    int stockCount = 0;

    @Override
    public void addObservers(NotificationObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObservers(NotificationObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        String message = "Macbook stock is up";
        for (NotificationObserver observer : observerList) {
            observer.update(message);
        }
    }

    @Override
    public void setStockCount(int newStocks) {
        if(stockCount == 0)
            notifyObservers();
        this.stockCount += newStocks;
    }

    @Override
    public int getStockCount() {
        return stockCount;
    }
}