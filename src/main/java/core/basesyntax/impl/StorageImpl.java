package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int MAX_SIZE = 10;
    private static final int KEY_NOT_FOUND = -1;

    private final Object[] keys;
    private final Object[] values;

    private int size;

    public StorageImpl() {
        keys = new Object[MAX_SIZE];
        values = new Object[MAX_SIZE];
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        int keyIndex = findKeyIndex(key);

        if (keyIndex != KEY_NOT_FOUND) {
            values[keyIndex] = value;
            return;
        }
        if (size == MAX_SIZE) {
            throw new IllegalStateException("Storage is full");
        }

        keys[size] = key;
        values[size] = value;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(K key) {
        int keyIndex = findKeyIndex(key);

        if (keyIndex != KEY_NOT_FOUND) {
            return (V) values[keyIndex];
        }
        return null;
    }

    private int findKeyIndex(K key) {
        for (int index = 0; index < size; index++) {
            if (keys[index] == null ? key == null : keys[index].equals(key)) {
                return index;
            }
        }
        return KEY_NOT_FOUND;
    }

    @Override
    public int size() {
        return size;
    }
}
