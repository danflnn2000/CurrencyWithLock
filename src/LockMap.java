
public class LockMap implements Runnable{
	private int invoiceId;
	private int uniqueId;
	public LockMap(int invoiceId, int uniqueId) {
		this.invoiceId = invoiceId;
		this.uniqueId = uniqueId;
	}

	public void run() {
		LockManager lockManager = LockManager.getInstance();
		Lock lockx = lockManager.getLock(new Lock(invoiceId, uniqueId));
		System.out.println("Total count (middle) on map is " + lockManager.getCount());	
		lockManager.releaseLock(lockx);
		System.out.println("Total count (end) on map is " + lockManager.getCount());
	}
}
