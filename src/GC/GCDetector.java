package GC;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GCDetector {
    public static void main(String[] args) {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("Garbage Collector: " + gcBean.getName());
        }
    }
}

/*
Java 程序可以在代码里面查看当前使用的是什么垃圾回收器吗？

是的，Java 程序可以通过编程的方式在代码里面查看当前使用的是什么垃圾回收器。
这可以通过 Java Management Extensions (JMX) API 实现，特别是通过连接到运行时的 Java 虚拟机的 GarbageCollectorMXBean。

以下是一个简单的示例代码，展示了如何列出 Java 虚拟机中当前活跃的垃圾回收器的名称：

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GCDetector {
    public static void main(String[] args) {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("Garbage Collector: " + gcBean.getName());
        }
    }
}

这段代码首先获取了 JVM 的 GarbageCollectorMXBean 实例列表，每个实例代表一个垃圾回收器。然后，它遍历这些实例并打印出每个垃圾回收器的名称。

运行这个程序会列出当前 JVM 实例中所有活跃的垃圾回收器的名称。例如，你可能会看到如下输出，这取决于你的 JVM 配置和版本：


Garbage Collector: G1 Young Generation
Garbage Collector: G1 Old Generation

这表明当前 JVM 使用的是 G1 垃圾回收器，它有两个部分：年轻代（Young Generation）和老年代（Old Generation）。

请注意，运行此代码的输出将根据你的 JVM 的配置和使用的垃圾回收器而有所不同。

* */