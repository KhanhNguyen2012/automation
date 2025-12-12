package starter.stepdefinitions.Common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ActorLockManager {
  private static final Map<String, ReentrantLock> locks = new ConcurrentHashMap<>();

  public static void lock(String actorName) {
    locks.computeIfAbsent(actorName, n -> new ReentrantLock()).lock();
  }

  public static void unlock(String actorName) {
    ReentrantLock lock = locks.get(actorName);
    if (lock != null && lock.isHeldByCurrentThread()) {
      lock.unlock();
    }
  }
}
