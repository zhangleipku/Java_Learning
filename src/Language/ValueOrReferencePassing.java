package Language;

// Java 语言中使用的是”值传递“ 还是 ”引用传递“ ？
public class ValueOrReferencePassing {
    public static void main(String[] args) {
        int value = 10;
        IntegerWrapper wrapper = new IntegerWrapper(10);

        changeValue(value);
        changeWrapper(wrapper);

        System.out.println("After changeValue, value is: " + value);
        System.out.println("After changeWrapper, wrapper's value is: " + wrapper.getValue());
    }

    public static void changeValue(int number) {
        number = 55;
    }

    public static void changeWrapper(IntegerWrapper wrapper) {
        wrapper.setValue(55);
    }
}

class IntegerWrapper {
    private int value;

    public IntegerWrapper(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

/*
Java 语言中使用的是”值传递“ 还是 ”引用传递“ ？
Java 语言中使用的是值传递。

这个问题经常会引起混淆，尤其是在讨论对象时。在 Java 中，不管是基本数据类型还是对象，传递给方法的都是值。对于基本数据类型（如 int、double 等），这个值就是数据值本身。而对于对象，传递的值是对象的引用（也就是内存地址）的拷贝。尽管对象引用的拷贝可能会导致人们误以为是通过引用传递，但实际上仍然是值传递，因为传递的是引用的值，而不是引用本身。

基本类型的值传递
对于基本类型，如果你将一个变量传递给一个方法，Java 会将变量的值拷贝一份传递给方法。在方法内部对这个值的任何修改都不会影响到原始数据。

对象的值传递
当你传递一个对象给方法时，实际上传递的是对象引用的拷贝，而不是对象本身。这意味着你可以通过这个引用访问和修改对象的成员变量。但是，如果你尝试在方法内部将引用指向一个新的对象，原始引用仍然指向传递给方法的那个对象。因为传递的是引用的值（即地址值）的拷贝，而不是引用本身。

示例
java
Copy code

public class Test {
    public static void main(String[] args) {
        int value = 10;
        IntegerWrapper wrapper = new IntegerWrapper(10);

        changeValue(value);
        changeWrapper(wrapper);

        System.out.println("After changeValue, value is: " + value);
        System.out.println("After changeWrapper, wrapper's value is: " + wrapper.getValue());
    }

    public static void changeValue(int number) {
        number = 55;
    }

    public static void changeWrapper(IntegerWrapper wrapper) {
        wrapper.setValue(55);
    }
}

class IntegerWrapper {
    private int value;

    public IntegerWrapper(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
在这个例子中，changeValue 方法试图修改基本类型的值，但这个修改不会反映到原始变量上。而 changeWrapper 方法修改了 wrapper 引用的对象的状态，这个修改会反映到原始对象上，因为传递的是对象引用的拷贝。但如果在 changeWrapper 里尝试将 wrapper 引用指向一个新的 IntegerWrapper 实例，则外部的 wrapper 引用不会改变。



Q
调用函数 changeWrapper(wrapper);  的时候，实际上传的参数是 wrapper 对象的地址吗？

A
在 Java 中，当你调用函数 changeWrapper(wrapper); 的时候，实际上传递的参数是 wrapper 对象引用的拷贝，而不是直接传递对象本身或其内存地址。这个引用拷贝指向了原始 wrapper 对象存储在堆（Heap）上的地址。

要理解这一点，关键是区分“引用”和“对象”本身：

引用：变量 wrapper 是一个引用，它保存了对象在内存中的地址信息。这个引用是如何指向堆中的对象的“指针”或“地址”。
对象：实际存储在堆内存中的数据实体，wrapper 引用指向这个对象。
当 changeWrapper(wrapper); 被调用时：

方法接收的是 wrapper 引用的一个拷贝。
这个拷贝同样指向堆内存中的同一个对象。
因此，方法内部可以通过这个引用拷贝来修改对象的状态。
但如果你尝试在方法内部改变引用拷贝本身（比如指向一个新的对象），这个改变不会影响到原始的引用，因为传递的是引用的拷贝，而不是引用本身。
所以，虽然我们通常说传递的是“引用”，但准确来说，传递的是引用的值（即对象的内存地址信息的拷贝），而不是直接传递内存地址本身。这种机制确保了方法内部不能改变外部引用本身的指向，但可以通过这个引用来修改对象的状态。


Q
所以wrapper 对象和它的拷贝都仅仅是一个内存地址，并不包含实际对象的内容本身，对吗？

A
是的，你的理解是正确的。wrapper 对象引用及其拷贝都仅仅保存了一个内存地址，这个地址指向堆内存中的实际对象。它们本身并不包含对象的内容，只是指向那些内容所在的位置。这意味着，通过任何一个引用（无论是原始的还是拷贝的），你都能访问和修改同一个对象的状态，因为它们指向同一个内存地址。

这种机制允许对象在方法间被高效地“传递”，因为实际上传递的只是地址信息，而不需要复制对象的实际数据。但这也意味着，如果你在一个方法中修改了通过引用访问的对象，这些修改会反映到任何其他也指向该对象的引用上。

程序中的 int value = 10; 和  IntegerWrapper wrapper 这两个变量都是在堆内存中吗？
在 Java 中，变量存储的位置取决于它们的类型和声明的上下文。具体到您提到的 int value = 10; 和 IntegerWrapper wrapper 这两个变量，它们的存储位置有所不同：

int value = 10;：

这是一个基本类型的变量，它直接存储数据值。
在这个例子中，value 是一个局部变量，它被存储在栈（Stack）内存中。栈内存用于存储方法调用和局部变量，这些变量的生命周期与方法调用密切相关。
IntegerWrapper wrapper：

wrapper 是一个引用类型的变量，它指向一个对象。
对象本身是在堆（Heap）内存中分配的。堆内存用于存储 Java 程序创建的所有对象和数组，它们的生命周期不受单一方法调用的限制，而是由垃圾收集器根据可达性来管理。
wrapper 变量（即引用本身，而不是它指向的对象）的存储位置依赖于它被声明的上下文。如果 wrapper 是一个方法内部声明的局部变量，那么这个引用变量存储在栈内存中。如果它是一个对象的成员变量，那么这个引用变量是随着对象一起存储在堆内存中的。
总结来说，int value = 10; 这个基本类型的局部变量存储在栈内存中，而 IntegerWrapper wrapper 这个引用类型的变量指向的对象存储在堆内存中，wrapper 这个引用变量本身（即存储对象地址的部分）如果是作为局部变量声明，则存储在栈内存中；如果是作为对象的成员变量声明，则随对象一起存储在堆内存中。





 */