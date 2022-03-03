import java.util.NoSuchElementException;
class HashNode<KeyType, ValueType> {
    final KeyType key;
    ValueType value;
    HashNode<KeyType, ValueType> next;
    public HashNode(KeyType key, ValueType value, HashNode<KeyType, ValueType> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
    public KeyType getKey() {
        return key;
    }public ValueType getValue() {
        return value;
    }public HashNode<KeyType, ValueType> getNext() {
        return next;
    } public void setNext(HashNode<KeyType, ValueType> next) {
        this.next = next;
    }
}
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    private int capacity;
    private HashNode<KeyType, ValueType>[] table;
    //constructors
    public HashtableMap() {
        capacity = 20;
        table = new HashNode[capacity];
    }public HashtableMap(int input) {
        capacity = input;
        table = new HashNode[capacity];
    }
    private int size = 0;
    //adds new key value pair to the hash table
    public boolean put(KeyType key, ValueType value) {
        HashNode<KeyType, ValueType> node = new HashNode<>(key, value,null);
        int index = getIndex(key);

        HashNode<KeyType, ValueType> curNode = table[index];
        if (node.getKey() == null) {
            return false;
        }
        if (curNode == null) {
            table[index] = node;
            size++;
            reHash();
            return true;
        } else {
            while (curNode.getNext() != null) {
                if (curNode.getKey().equals(key)) {
                    return false;
                }
                curNode = curNode.getNext();
            }
            if (curNode.getKey().equals(key)) {
                return false;
            } else {
                curNode.setNext(node);
                size++;
                reHash();
                return true;
            }
        }
    }
    //returns the value of any inputted key, throws exception if key does not exist in the hash table
    public ValueType get(KeyType key) throws NoSuchElementException {
        HashNode<KeyType, ValueType> node = table[getIndex(key)];
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.value;
            }
            node = node.getNext();
        }
        throw new NoSuchElementException("No such key in hash table.");
    }
    //returns the number of key/value pairs in the hash table.
    public int size() {
        return size;
    }
    //returns true if input key is in the hash table, else returns false
    public boolean containsKey(KeyType key) {
        HashNode<KeyType, ValueType> node;
        for (int i = 0; i < capacity; i++) {
            node = table[i];
            while (node != null) {
                if (node.getKey().equals(key)) {
                    return true;
                }
                node = node.getNext();
            }
        }
        return false;
    }
    //removes key/value pair from hashtable and returns the value. If key does not exist in the hash table, returns
    
    public ValueType remove (KeyType key) {
        if (!containsKey(key)) {
            return null;
        } else {
            int index = getIndex(key);
            HashNode<KeyType, ValueType> node = table[index];
            if (node.getKey().equals(key)) {
                table[index] = node.getNext();
                size--;
                return node.getValue();
            } else {
                node = node.getNext();
                while (node.getNext() != null) {
                    if (node.getKey().equals(key)) {
                        break;
                    } else {
                        node = node.getNext();
                    }
                }
                HashNode<KeyType, ValueType> prevNode = table[index];
                while (!(prevNode.getNext().equals(node))) {
                    prevNode = prevNode.getNext();
                }
                ValueType value = node.getValue();
                prevNode.setNext(node.getNext());
                size--;
                return value;
            }
        }
    }
    //removes all key/value pairs from the hash table
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
        size = 0;
    }
    //helper methods
    private int getIndex(KeyType key) {
        if (key == null) {
            return 0;
        }
        return (Math.abs(key.hashCode()) % capacity);
    }
    public int getCapacity(){
        return capacity;
    }
    private void reHash() {
        double loadFactor = ((double)size / capacity);
        if (loadFactor >= 0.8) {
            capacity *= 2;
            @SuppressWarnings("unchecked")
            HashNode<KeyType, ValueType>[] temp = new HashNode[capacity];
            for (int i = 0; i < capacity/2; ++i) {
                temp[i] = table[i];
            }
            table = temp;
        }
    }
}