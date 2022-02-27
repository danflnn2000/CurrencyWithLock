import java.time.Instant;

public class Lock {
	private Instant startTime;
	private Instant endTime;
	private int invoiceId;
	private int uniqueId;
	
	public Lock(int invoiceId,int uniqueId) {
		this.invoiceId = invoiceId;
		this.uniqueId = uniqueId;
		this.startTime = Instant.now();
		this.endTime = startTime.plusSeconds(3L);
	}
	
	public boolean hasExpired() {
		return Instant.now().isAfter(endTime) ? true : false;
		
	}
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public int getUniqueId() {
		return uniqueId;
	}

}
