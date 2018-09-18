package com.one.collection;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author One
 * @description 写复制实现 CopyOnWriteArrayList
 *
 * 读操作不加锁,写操作用🔐同步
 * 因为 array 用 volatile 修饰,,所以修改后可以立即读取到最新数组
 * @date 2018/09/18
 */
public class CopyOnWriteLab<E> {
    private volatile transient Object[] array;

    // 读取
    public E get(int index) {
        return get(getArray(), index);
    }

    private E get(Object[] array, int index) {
        return (E) array[index];
    }

    final Object[] getArray() {
        return array;
    }

    // 写入
    final ReentrantLock lock = new ReentrantLock();

    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int length = elements.length;
            Object[] objects = Arrays.copyOf(elements, length + 1);
            objects[length] = e;
            setArray(objects);
            return true;
        } finally {
            lock.unlock();
        }
    }

    private void setArray(Object[] objects) {
        array = objects;
    }
}
