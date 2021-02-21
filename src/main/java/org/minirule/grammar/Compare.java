package org.minirule.grammar;

/**
 * Hello world!
 *
 */
public class Compare 
{
    // greater than, ">"
    public IVar gt(IVar a, IVar b) throws RuleException {
        return new VarBoolean(a.toDouble() > b.toDouble());
    }
    // smaller than, "<"
    public IVar st(IVar a, IVar b) throws RuleException {
        return new VarBoolean(a.toDouble() < b.toDouble());
    }
    // equal, "=="
    public IVar eq(IVar a, IVar b) throws RuleException {
        return new VarBoolean(a.toDouble() == b.toDouble());
    }
    // not equal, "!="
    public IVar neq(IVar a, IVar b) throws RuleException {
        return new VarBoolean(a.toDouble() != b.toDouble());
    }
    // greater than or equal, ">="
    public IVar gte(IVar a, IVar b) throws RuleException {
        return new VarBoolean(a.toDouble() >= b.toDouble());
    }
    // smaller than or equal, "<="
    public IVar ste(IVar a, IVar b) throws RuleException {
        return new VarBoolean(a.toDouble() <= b.toDouble());
    }
}