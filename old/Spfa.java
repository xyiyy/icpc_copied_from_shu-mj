import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Spfa {
	void spfa(V s) {
		Queue<V> que = new LinkedList<V>();
		s.min = 0;
		que.offer(s);
		while (!que.isEmpty()) {
			V crt = que.poll();
			crt.inQue = false;
			for (E e : crt.es) {
				if (crt.min + e.cost < e.to.min) {
					e.to.min = crt.min + e.cost;
					if (!e.to.inQue) {
						e.to.inQue = true;
						que.offer(e.to);
					}
				}
			}
		}
	}

	int INF = 1 << 29;

	class V {
		ArrayList<E> es = new ArrayList<E>();
		int min = INF;
		boolean inQue = false;

		void add(V to, int cost) {
			es.add(new E(to, cost));
		}
	}

	class E implements Comparable<E> {
		V to;
		int cost;

		E(V to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		public int compareTo(E o) {
			return cost - o.cost;
		}
	}
}