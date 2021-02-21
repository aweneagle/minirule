package org.minirule.grammar;

import java.util.HashMap;

public abstract class Tran implements Cloneable {
    protected HashMap<String, Object> context;
    protected String uuid;
    public void setContext(HashMap<String, Object> ctx) {
        this.context = ctx;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Tran copy() {
        try {
            Tran cp = (Tran) this.clone();
            cp.context = null;
            cp.uuid = "";
            return cp;
        } catch (Exception e) {
            return null;
        }
    }

    // prepare阶段
    // 成功返回结果, 失败返抛出异常, 无法确定结果（例如DB访问超时)抛出TryAgainException
    public abstract IVar prepare(String uuid, HashMap<String, Object>context, IVar ... params) throws TryAgainException, FailException, RuleException;

    // confirm阶段
    // 成功返回true, 失败返回false, 无法确定结果（例如DB访问超时)抛出TryAgainException
    public abstract void confirm(String uuid) throws TryAgainException;

    // confirm阶段
    public abstract void cancle(String uuid) throws TryAgainException;
}
