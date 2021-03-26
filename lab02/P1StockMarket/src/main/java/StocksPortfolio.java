import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    String name;
    IStockMarket marketService;
    List<Stock> stocks = new ArrayList<>();

    IStockMarket getMarketService() {
        return marketService;
    }

    void setMarketService(IStockMarket marketService) {
        this.marketService = marketService;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Double getTotalValue() {
        double value = 0;
        for (Stock stock : stocks) {
            value += stock.getQuantity() * marketService.getPrice(stock.getName());
        }
        return value;
    }

    void addStock(Stock stock) {
        stocks.add(stock);
    }
}
