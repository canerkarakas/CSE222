import java.util.*;

public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
   ArrayList<String> fnames = new ArrayList<String>();
   ArrayList<List<Integer>> occurances = new ArrayList<>();

    @Override
    public int size() {
        return fnames.size();
    }

    @Override
    public boolean isEmpty() {
        return fnames.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) { return fnames.contains(key); }

    @Override
    public boolean containsValue(Object value) { return occurances.contains(value); }

    @Override
    public Object get(Object key) {
        if (containsKey(key))
            return occurances.get(fnames.indexOf(key));
        return null;
    }

    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {
        if (containsKey(key)){
            int index = fnames.indexOf(key);
            ArrayList<Integer> result = new ArrayList<>(occurances.get(index));
            occurances.set(index, (List<Integer>) value);
            return result;
        }
        else{
            fnames.add((String) key);
            occurances.add((List<Integer>)value);
            return null;
        }
    }

    @Override
    public Object remove(Object key) {
        if (containsKey(key)){
            int index = fnames.indexOf(key);
            fnames.remove(key);
            return occurances.remove(index);
        }
        else
            return null;
    }

    @Override
    public void putAll(Map m) {
        Collection values = new ArrayList(m.values());
        Set<String> keys = new TreeSet();
        keys = m.keySet();
        Object keysArr[] = new String[keys.size()];
        keysArr = keys.toArray();
        for (int i=0;i<keys.size();i++)
            this.put(keysArr[i], ((ArrayList) values).get(i));
    }

    @Override
    public void clear() {
        fnames.clear();
        occurances.clear();
    }

    @Override
    public Set keySet() {
        Set<String> result = new TreeSet<>();
        for (int i=0;i<fnames.size();i++)
            result.add(fnames.get(i));
        return result;
    }

    @Override
    public Collection values() {
        Collection<List<Integer>> result = new ArrayList<>();
        for (int i=0;i<occurances.size();i++)
            result.add(occurances.get(i));
        return result;
    }

    @Override
    public Set<Entry> entrySet() {
        HashMap<String, List<Integer>> result = new HashMap<>();
        for (int i=0;i<fnames.size();i++)
            result.put(fnames.get(i), occurances.get(i));
        return (Set)result.entrySet();
    }

    @Override
    public String toString() {
        return fnames.toString() + "\n" + occurances.toString();
    }
}
