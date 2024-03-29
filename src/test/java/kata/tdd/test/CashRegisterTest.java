package kata.tdd.test;

import org.junit.Test;

import kata.tdd.CashRegister;
import kata.tdd.Item;
import kata.tdd.Printer;
import kata.tdd.Purchase;

import static org.mockito.Mockito.*;

public class CashRegisterTest {
	
	@Test
	public void processShouldPrintTheRealPurchase() throws Exception {
		Item items[] = new Item[] {
				new Item("Item1", 50),
				new Item("Item2", 60)
		};
		Purchase purchase = new Purchase(items);
		MockPrinter printer = new MockPrinter();
		
		CashRegister cashRegister = new CashRegister(printer);
		
		cashRegister.process(purchase);
		
		printer.verifyThatPrintWasCalledWith(purchase.asString());
	}
	
	@Test
    public void processShouldPrintTheStubPurchase() throws Exception {
        StubPurchase purchase = new StubPurchase("purchase as string");
        MockPrinter printer = new MockPrinter();

        CashRegister cashRegister = new CashRegister(printer);

        cashRegister.process(purchase);

        printer.verifyThatPrintWasCalledWith("purchase as string");
    }
	
	@Test
    public void processShouldPrintThePurchaseUsingMockito() throws Exception {
        Purchase purchase = mock(Purchase.class);
        when(purchase.asString()).thenReturn("purchase as string");
        Printer printer = mock(Printer.class);

        CashRegister cashRegister = new CashRegister(printer);

        cashRegister.process(purchase);

        verify(printer, times(1)).print("purchase as string");
    }
}
