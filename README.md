# SampleAndroid
This is Android Project 笔记
2019.10.12
一,安卓开发单例模式:
  1.1懒汉式:
      public class Singleton {
 
       /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
       private static Singleton instance = null;
 
       /* 私有构造方法，防止被实例化 */
       private Singleton() {
       }
 
       /* 1:懒汉式，静态工程方法，创建实例 */
       public static Singleton getInstance() {
           if (instance == null) {
               instance = new Singleton();
           }
       return instance;
       }
      }
   优点:延迟加载
   缺点:线程不安全
  1.1.2懒汉加锁
    /*2.懒汉式变种，解决线程安全问题**/
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
   优点:线程安全
   缺点:每次都要判断,消耗性能
  
   1.1.3懒汉双重加锁
     /*3.双重锁定:只在第一次初始化的时候加上同步锁*/
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();//可能存在安全隐患
                }
            }
        }
        return instance;
    }
   优点:在并发量不高的情况下 完美运行
   缺点:在高并发的情况下,可能存在在new的过程中,jvm分配了内存空间,instance !=null,但实际上instance还没有初始化完成

  1.1.4懒汉式内部类  推荐使用
  public class SingletonInner {
 
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static SingletonInner instance = new SingletonInner();
    }
    /**
     * 私有的构造函数
     */
    private SingletonInner() {
    }
 
    public static SingletonInner getInstance() {
        return SingletonHolder.instance;
    }
  }
 1.2饿汉式:空间换时间:private static Singleton instance=new Singleton(); 类似这样.静态创建


   
