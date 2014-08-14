class Main {
	class T {
		int key, size;
		double p;
		T left, right;

		public T(int key, int size, double p, T left, T right) {
			this.key = key;
			this.size = size;
			this.p = p;
			this.left = left;
			this.right = right;
		}

		T(int key) {
			this(key, 1, random(), NULL, NULL);
		}
	}

	T change(T t, T left, T right) {
		t.size = left.size + right.size + 1;
		t.left = left;
		t.right = right;
		return t;
	}

	T[] splitSize(T t, int size) {
		T[] res;
		if (size <= 0) {
			res = new T[] { NULL, t };
		} else if (size <= t.left.size) {
			res = splitSize(t.left, size);
			res[1] = change(t, res[1], t.right);
		} else {
			res = splitSize(t.right, size - t.left.size - 1);
			res[0] = change(t, t.left, res[0]);
		}
		return res;
	}

	T[] splitKey(T t, int key) {
		T[] res;
		if (t == NULL) {
			res = new T[] { NULL, NULL };
		} else if (key < t.key) {
			res = splitKey(t.left, key);
			res[1] = change(t, res[1], t.right);
		} else {
			res = splitKey(t.right, key);
			res[0] = change(t, t.left, res[0]);
		}
		return res;
	}

	void print(T t, String indent) {
		if (t != NULL) {
			print(t.right, indent + "      ");
			out.printf("%3d%3d%n", t.key, t.size);
			print(t.left, indent + "      ");
		}
		if (indent.length() == 0)
			out.println("----------------------------------");
	}

	T merge(T t1, T t2) {
		if (t1 == NULL)
			return t2;
		if (t2 == NULL)
			return t1;
		if (t1.p < t2.p)
			return change(t1, t1.left, merge(t1.right, t2));
		return change(t2, merge(t1, t2.left), t2.right);
	}

	T NULL = new T(0, 0, 0, null, null);
}
