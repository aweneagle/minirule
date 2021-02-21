package org.minirule.grammar;

import java.util.ArrayList;

public class TranRunner {
    private ArrayList<IVar[]> params;
    private Tran[] list;

    public TranRunner(Tran[] trans) {
        this.list = trans;
        this.params = new ArrayList<IVar[]>();
    }

    public IVar prepare(int i, IVar[] args) throws TryAgainException, FailException, RuleException {
        try {
            params.add(args);
            return list[i].prepare(list[i].uuid, list[i].context, params.get(i));
        } catch (TryAgainException e) {
            // 需要重试, do nothing
            throw new TryAgainException();
        } catch (FailException e) {
            // 取消所有事务
            for (int j = 0; j < i; j ++) {
                list[j].cancle(list[j].uuid);
            }
            throw e;
        }
    }

    public void commit() throws TryAgainException, RuleException {
        for (int i = 0; i < list.length; i ++) {
            list[i].confirm(list[i].uuid);
        }
    }
}
