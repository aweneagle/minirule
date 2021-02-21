package org.minirule.grammar;

public class FuncForTest {
    public int number = 10;
    public float fn = 10.2f;
    // 测试Method
    public void setMethod(Tran m) {

    }
    // 重载乘法
    public int mul(int a, int b, int c) {
        return a * b + c;
    }
    // 乘法
    public int mul(int a, int b) {
        return a * b;
    }
    // 私有函数
    protected int prov(int a) {
        return a;
    }

    // 无返回
    public void noReturn() {
        return;
    }

    // 返回null
    public Integer returnNull() {
        return null;
    }
    
    // 抛异常
    public void throwsExp() throws Exception {
        throw new Exception("test func exception");
    }

    // 不定参数
    public int sum(int base, int ...a) {
        int sum = 0;
        for (int i = 0; i < a.length; i ++) {
            sum += a[i];
        }
        return sum + base;
    }
}
