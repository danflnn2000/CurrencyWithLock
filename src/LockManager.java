import java.util.concurrent.ConcurrentHashMap;

public class LockManager {

	private static LockManager lockManager = new LockManager();
	private ConcurrentHashMap<Integer, Lock> myMap = new ConcurrentHashMap<>(); 
	
	private LockManager() {
		
	}
	
	public Lock getLock(Lock lock) {
		while (true){
			if(myMap.putIfAbsent((Integer)lock.getInvoiceId(), lock)==null) {
				System.out.println("invoice " + lock.getUniqueId() + " is locked! and map count is " + lockManager.getCount());
				return lock;
			}else {
				synchronized(this) {
					try {
						System.out.println("invoice " + lock.getUniqueId() + " is awaiting!");
						wait(2000);
						//this.removeExpired();
						Lock lockInMap = myMap.get(lock.getInvoiceId());
						if(lockInMap!=null && lockInMap.hasExpired()) {
							//this.releaseLock(lockInMap);
							System.out.println("not expired test for " + lockInMap.getUniqueId());
						}
					}catch(Exception ex) {
						System.out.println(ex.toString());
					}
				}
			}
				
		}
	}
	
	public void removeExpired() {
		myMap.entrySet().removeIf(entry->entry!=null && entry.getValue().hasExpired());
	}
	
	public void releaseLock(Lock lock) {
		//myMap.remove(invoiceId, lock);
		int invoiceId = lock.getInvoiceId();
		System.out.println("invoice " + lock.getUniqueId() + " releasing lock " + myMap.remove(invoiceId, lock));
	}
	
	public int getCount() {
		return myMap.size();
	}
	
	public static LockManager getInstance() {
		return lockManager;
	}
	
	
}
