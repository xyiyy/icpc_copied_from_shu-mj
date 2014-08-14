import java.util.ArrayList;
import java.util.PriorityQueue;

class Dij {
	void dijkstra(V s) {
		PriorityQueue<E> que = new PriorityQueue<E>();
		s.min = 0;
		que.offer(new E(s, 0));
		while (!que.isEmpty()) {
			E crt = que.poll();
			if (crt.cost > crt.to.min)
				continue;
			for (E e : crt.to.es) {
				if (crt.cost + e.cost < e.to.min) {
					e.to.min = crt.cost + e.cost;
					que.offer(new E(e.to, e.to.min));
				}
			}
		}
	}

	int INF = 1 << 29;

	class V {
		ArrayList<E> es = new ArrayList<E>();
		int min = INF;

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