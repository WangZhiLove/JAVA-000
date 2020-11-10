## 多线程学习笔记

#### 线程池的创建

`com.wangzhi.thread.pool.create.CreateTheadPool` : 演示了线程池的创建，包括了使用工具类创建和手动创建两种方式，
以及固定大小线程池中线程数的大小如何设置比较合适。

#### lock的使用

`com.wangzhi.thread.lock.LockCount`: ReentrantLock的使用。

`com.wangzhi.thread.lock.ReadWriteLockCount`: ReentrantReadWriteLock的使用，这里发现默认读锁可同时被 2倍的CPU核数 来占用，这个就很
有意思，也就说默认的并行数是有限制的，不是无穷大。

`com.wangzhi.thread.lock.ConditionDemo`: 测试Condition的案例。

#### 并发工具类的使用（`com.wangzhi.thread.tool`）

`SemaphoreDemo` 演示的是ppt中的SemaphoreCount案例，演示的时候自己出了点小问题，那就是使用IntStream去演示效果，并没有单独开线程来执行，未达到
预期的效果。

`SemaphoreDemo2` 演示ExecutorService不关闭，程序不会停止的问题，还有Semaphore一次可以锁住多个并发数

`SemaphoreDemo3` 演示使用Semaphore实现生产者和消费者，重点理解核心锁设置为1的目的

`CountDownLatchDemo1` 演示使用CountDownLatch的案例，主要说的是主线程等待子线程的问题

### homework下为第一次的作业

用了好几种方法，感觉本质都是future接口，Callable的call方法的实现，因为只有call有返回值，run方法是没有返回值的。
 