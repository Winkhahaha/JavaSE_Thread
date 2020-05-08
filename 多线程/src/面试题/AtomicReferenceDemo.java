package 面试题;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String args[]) {
        AtomicReference<User> reference = new AtomicReference<>();
        User a = new User("A", 1);
        User b = new User("B", 2);
        // 将a放入主存中(真实值)
        reference.set(a);
        // 如果主存中还是a(期待没被修改,仍是a),则将b刷入主存进行更新
        System.out.println(reference.compareAndSet(a, b) + " 当前:" + reference.get());
        // false,仍是b
        System.out.println(reference.compareAndSet(a, b) + " 当前:" + reference.get());

    }
}
