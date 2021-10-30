import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ListMap<K, V> extends AbstractMap<K, V> {
//Inner class node
	class node {
		K key;
		V val;
		node next;

		public node(K key, V val) {
			this.key = key;
			this.val = val;
		}
	}

	private node head;
	private int size;
	private node temp;

	public ListMap() {
		size = 0;
		temp = head;
	}

	public V put(K key, V value) {
		if (head == null) {
			head = new node(key, value);
			return null;
		}
		node ptr = head;
		while (ptr.next != null) {
			if (ptr.key == key) {
				V temp = ptr.val;
				ptr.val = value;
				return temp;
			}
			ptr = ptr.next;
		}
		if (ptr.key == key) {
			V temp = ptr.val;
			ptr.val = value;
			return temp;
		}
		ptr.next = new node(key, value);

		size++;
		return null;
	}

	public static void main(String[] args) {
		ListMap<String, String> map = new ListMap<String, String>();
		map.put("haha", "haha");
		map.put("haha2", "haha2");
		map.put("haha3", "haha3");
		map.put("haha4", "haha4");
		map.put("haha5", "haha5");

		System.out.println(map.get("haha"));
		System.out.println(map.size());

	}

	/*
	 * public V get(K key) { node ptr = head; while (ptr != null) { if (((String)
	 * ptr.key).compareTo((String) key) == 0) return ptr.val; } return null; }
	 */
	public int size() {
		return size;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return new ListMapEntrySet();
	}

	private class ListMapEntrySet extends AbstractSet<Entry<K, V>> {
		Set res = new TreeSet<Entry<K, V>>();

		@Override
		public Iterator<Entry<K, V>> iterator() {
			// TODO Auto-generated method stub
			return new ListMapEntrySetIterator();
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return size;
		}

		public boolean contains(Object o) {

			return res.contains(o);
		}

	}

	private class ListMapEntrySetIterator<T> implements Iterator<T> {
		private node temp2=head;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return this.temp2 == null;
		}

		@Override
		public T next() {
			
			// TODO Auto-generated method stub
			return (T) Map.entry(temp2.key, temp2.val);
		}

	}

}
