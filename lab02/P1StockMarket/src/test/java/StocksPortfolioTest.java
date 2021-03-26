import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Needed to use annotations
class StocksPortfolioTest {

    @Mock
    IStockMarket market;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void getTotalValue() {

//      1.Prepare a mock to substitute the remote service (@Mock annotation)
//        IStockMarket market = mock(IStockMarket.class);

//      2.Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.
//        StocksPortfolio portfolio = new StocksPortfolio();
//        portfolio.setMarketService(market);

//      3.Load the mock with the proper expectations (when...thenReturn)
        when(market.getPrice("EBAY")).thenReturn(4.0);
        when(market.getPrice("MSFT")).thenReturn(1.5);

//      4.Execute the test (use the service in the SuT)
        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("MSFT", 4));
        double result = portfolio.getTotalValue();

//      5.Verify the result (assert) and the use of the mock (verify)
        assertThat(result, is(14.0));
        verify(market, times(2)).getPrice(anyString());

    }

}