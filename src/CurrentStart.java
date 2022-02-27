import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CurrentStart {

	public static void main(String args[]) throws InterruptedException {
		//lockerRunInMainThread();
		//lockerRunInRunnable();
		lockerRunInExecutor();
	}
	public static void lockerRunInMainThread() {
		LockManager lockManager = LockManager.getInstance();
		Lock locka = lockManager.getLock(new Lock(100, 1001));
		Lock lockb = lockManager.getLock(new Lock(100, 1002));
		Lock lockc = lockManager.getLock(new Lock(200, 2001));
		Lock lockd = lockManager.getLock(new Lock(300, 3001));
		System.out.println("Total count on map is " + lockManager.getCount());		
		lockManager.releaseLock(locka);
		lockManager.releaseLock(lockb);
		lockManager.releaseLock(lockc);
		lockManager.releaseLock(lockd);
		System.out.println("Total count on map is " + lockManager.getCount());
	}
	public static void lockerRunInRunnable() {
		new Thread(new LockMap(100,1001)).start();
		new Thread(new LockMap(100,1002)).start();
		new Thread(new LockMap(200,2001)).start();
		new Thread(new LockMap(300,3001)).start();
		
	}
	public static void lockerRunInExecutor() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		try {
			service.execute(()->new LockMap(100,1001).run());
			service.execute(()->new LockMap(100,1002).run());
			service.execute(()->new LockMap(200,2001).run());
			service.execute(()->new LockMap(300,3001).run());
		}finally {
			if(service!=null) service.shutdown();
		}

	}
}
