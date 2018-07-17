import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;

    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {

        int result = 0;
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            result += entry.getValue().size();
        }
        return result;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код

        if (map.containsKey(key)) {

            if (map.get(key).size() < repeatCount) map.get(key).add(value);
             else if (map.get(key).size() == repeatCount) {
                map.get(key).remove(0);
                map.get(key).add(value);
            }
            List<V> list = map.get(key);
            return list.get(list.size()-2);
        }
        List<V> list = new ArrayList<V>();
        list.add(value);
        map.put(key, list);
        return null;

    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        if (map.containsKey(key))
         {
            if (map.get(key).size() > 1) {
                V value = map.get(key).get(0);
                map.get(key).remove(0);
                return value;
            } else if (map.get(key).size() <= 1) {
                V result = map.get(key).get(0);
                map.remove(key);
                return result;
            }

        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        Set<K> setKey = new HashSet<K>();
        map.forEach((k,v)->{setKey.add(k);});
        return setKey;
    }

    @Override
    public Collection<V> values() {
        List<V> result = new ArrayList<V>();
        map.forEach((k,v)->{result.addAll(v);});
        return result;
    }

    @Override
    public boolean containsKey(Object key) {

        return map.containsKey(key);
        //напишите тут ваш код
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            if (entry.getValue().contains(value)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}