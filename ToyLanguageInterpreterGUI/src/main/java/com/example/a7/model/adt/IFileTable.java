package com.example.a7.model.adt;

import com.example.a7.exception.KeyNotFoundException;

import java.util.List;
import java.util.Set;

public interface IFileTable<K, V> {
    void insert(K key, V value);

    V getValue(K key) throws KeyNotFoundException;

    void remove(K key) throws KeyNotFoundException;

    boolean contains(K key);

    String toString();
    List<String> toStringGUI();

    public Set<K> getKeys();
}
