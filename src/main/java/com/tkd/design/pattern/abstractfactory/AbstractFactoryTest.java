package com.tkd.design.pattern.abstractfactory;

enum SalesChannel {
	AMAZON, FLIPKART, EBAY
}

enum SalesType {
	ORDER, RETURN
}

class SalesData {

	SalesChannel salesChannel;
	SalesType salesType;
	String salesData;

}

class AmazonSalesPayloadProcessorFactory extends AbstractSalesPayloadProcessorFactory {

	class AmazonSalesOrderPayloadProcessor implements SalesPayloadProcessor {

		@Override
		public void processSalesData(SalesData salesData) {
			System.out.println("Amazon Sales Order is processed successfully");
		}

	}

	class AmazonSalesReturnPayloadProcessor implements SalesPayloadProcessor {

		@Override
		public void processSalesData(SalesData salesData) {
			System.out.println("Amazon Sales Return Order is processed successfully");
		}

	}

	@Override
	public void processSalesData(SalesData salesData) {
		if (salesData.salesType.equals(SalesType.ORDER)) {
			new AmazonSalesOrderPayloadProcessor().processSalesData(salesData);
		} else if (salesData.salesType.equals(SalesType.RETURN)) {
			new AmazonSalesReturnPayloadProcessor().processSalesData(salesData);
		} else
			throw new RuntimeException("Invalid Sales Type");
	}
}

class FlipkartSalesPayloadProcessorFactory extends AbstractSalesPayloadProcessorFactory {
	class FlipkartSalesOrderPayloadProcessor implements SalesPayloadProcessor {

		@Override
		public void processSalesData(SalesData salesData) {
			System.out.println("Flipkart Sales Order is processed successfully");
		}

	}

	class FlipkartSalesReturnPayloadProcessor implements SalesPayloadProcessor {

		@Override
		public void processSalesData(SalesData salesData) {
			System.out.println("Flipkart Sales Return Order is processed successfully");
		}

	}

	@Override
	public void processSalesData(SalesData salesData) {
		if (salesData.salesType.equals(SalesType.ORDER)) {
			new FlipkartSalesOrderPayloadProcessor().processSalesData(salesData);
		} else if (salesData.salesType.equals(SalesType.RETURN)) {
			new FlipkartSalesReturnPayloadProcessor().processSalesData(salesData);
		} else
			throw new RuntimeException("Invalid Sales Type");
	}
}

class EBaySalesPayloadProcessorFactory extends AbstractSalesPayloadProcessorFactory {
	class EBaySalesOrderPayloadProcessor implements SalesPayloadProcessor {

		@Override
		public void processSalesData(SalesData salesData) {
			System.out.println("Ebay Sales Order is processed successfully");
		}

	}

	class EBaySalesReturnPayloadProcessor implements SalesPayloadProcessor {

		@Override
		public void processSalesData(SalesData salesData) {
			System.out.println("EBay Sales Return Order is processed successfully");
		}

	}

	@Override
	public void processSalesData(SalesData salesData) {
		if (salesData.salesType.equals(SalesType.ORDER)) {
			new EBaySalesOrderPayloadProcessor().processSalesData(salesData);
		} else if (salesData.salesType.equals(SalesType.RETURN)) {
			new EBaySalesReturnPayloadProcessor().processSalesData(salesData);
		} else
			throw new RuntimeException("Invalid Sales Type");
	}
}

interface SalesPayloadProcessor {
	public abstract void processSalesData(SalesData salesData);
}

abstract class AbstractSalesPayloadProcessorFactory {
	public abstract void processSalesData(SalesData salesData);
}

class FactoryProducer {
	public static AbstractSalesPayloadProcessorFactory getFactory(SalesChannel salesChannel) {
		if (salesChannel.equals(SalesChannel.AMAZON)) {
			return new AmazonSalesPayloadProcessorFactory();
		}
		if (salesChannel.equals(SalesChannel.FLIPKART)) {
			return new FlipkartSalesPayloadProcessorFactory();
		}
		if (salesChannel.equals(SalesChannel.EBAY)) {
			return new EBaySalesPayloadProcessorFactory();
		}

		throw new RuntimeException("Invalid Sales Channel");
	}
}

public class AbstractFactoryTest {

	public static void main(String[] args) {

		// Suppose You are Big Brand like Nike,Puma And You are Selling your products in
		// Multiple Ecommerce Website Like Amazon,Flipkart and Ebay
		// And you are getting the data as a generic payload from a middle system and
		// after that you are processing
		// then you can follow abstract factory approach

		SalesData salesData = new SalesData();
		salesData.salesData = "Data";
		// For Order Creation
		salesData.salesType = SalesType.ORDER;
		salesData.salesChannel = SalesChannel.AMAZON;
		FactoryProducer.getFactory(salesData.salesChannel).processSalesData(salesData);

		salesData.salesChannel = SalesChannel.FLIPKART;
		FactoryProducer.getFactory(salesData.salesChannel).processSalesData(salesData);

		salesData.salesChannel = SalesChannel.EBAY;
		FactoryProducer.getFactory(salesData.salesChannel).processSalesData(salesData);

		// For Return Creation
		salesData.salesType = SalesType.RETURN;
		salesData.salesChannel = SalesChannel.AMAZON;
		FactoryProducer.getFactory(salesData.salesChannel).processSalesData(salesData);

		salesData.salesChannel = SalesChannel.FLIPKART;
		FactoryProducer.getFactory(salesData.salesChannel).processSalesData(salesData);

		salesData.salesChannel = SalesChannel.EBAY;
		FactoryProducer.getFactory(salesData.salesChannel).processSalesData(salesData);
	}

}
