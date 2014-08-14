class T {
    int key, val, size, sum;
    double p;
    T left, right, pre;
 
    public T(int key, int val, int size, int sum, double p, T left,
            T right, T pre) {
        this.key = key;
        this.val = val;
        this.size = size;
        this.sum = sum;
        this.p = p;
        this.left = left;
        this.right = right;
        this.pre = pre;
    }
 
    T(int key, int val) {
        this(key, val, 1, val, random(), NULL, NULL, NULL);
    }
 
    boolean isLeft() {
        return pre.left == this;
    }
 
    boolean isRight() {
        return pre.right == this;
    }
 
    boolean isRoot() {
        return !isLeft() && !isRight();
    }
 
    T last() {
        T res = this;
        while (res.right != NULL)
            res = res.right;
        return res;
    }
 
    T fa() {
        if (left != NULL) return left.last();
        if (isRoot()) return pre;
        T res = this;
        while (res.isLeft()) res = res.pre;
        return res.pre;
    }
     
    T root() {
        T res = this;
        while (!res.isRoot()) res = res.pre;
        return res;
    }
 
    T change(T left, T right) {
        size = left.size + right.size + 1;
        sum = left.sum + right.sum + val;
        this.left = left;
        this.right = right;
        return left.pre = right.pre = this;
    }
    T changeL(T left) {
        return change(left, right);
    }
    T changeR(T right) {
        return change(left, right);
    }
 
    void print(int l) {
        if (this != NULL) {
            right.print(l + 1);
            for (int i = 0; i < l; i++) out.print("        ");
            out.printf("%4d%4d%n", key, fa().key);
            left.print(l + 1);
        }
        if (l == 0) out.println("----------------------------------");
    }
}
 
T merge(T t1, T t2) {
    if (t1 == NULL) return t2;
    if (t2 == NULL) return t1;
    if (t1.p < t2.p) return t1.changeR(merge(t1.right, t2));
    return t2.changeL(merge(t1, t2.left));
}
 
T NULL = new T(-1, 0, 0, 0, 0, null, null, null);
 
T split(T t) {
    T R = t.right;
    T L = t.changeR(NULL);
    T c = t;
    for (;; c = c.pre) {
        if (c.isLeft()) R = c.pre.changeL(R);
        else if (c.isRight()) L = c.pre.changeR(L);
        else break;
    }
    L.pre = c.pre;
    R.pre = t;
    return L;
}
 
T expose(T t) {
    T fa = t.fa();
    if (fa != NULL) split(fa);
    return t.root();
}
 
void link(T son, T fa) {
    expose(son).pre = fa;
}
 
void cut(T t) {
    expose(t).pre = NULL;
}
 
void access(T t) {
    t = split(t);
    while (t.pre != NULL) {
        T sp = split(t.pre);
        T pre = sp.pre;
        t = merge(sp, t);
        t.pre = pre;
    }
}

// updating
