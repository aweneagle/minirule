package org.minirule.grammar;

/**
 * Hello world!
 *
 */
public class Calculater 
{
    // "+"
    public IVar add(IVar a, IVar b) throws RuleException {
        return new VarNumber(a.toDouble() + b.toDouble());
    }
    // "-"
    public IVar dec(IVar a, IVar b) throws RuleException {
        return new VarNumber(a.toDouble() - b.toDouble());
    }
    // "*"
    public IVar mul(IVar a, IVar b) throws RuleException {
        return new VarNumber(a.toDouble() * b.toDouble());
    }
    // "/"
    public IVar div(IVar a, IVar b) throws RuleException {
        double dv = b.toDouble();
        if (dv == 0) {
            throw new RuleException(RuleError.CalDivByZero);
        }
        return new VarNumber(a.toDouble() / b.toDouble());
    }
    // "^n", to the power of n
    public IVar pow(IVar a, IVar n) throws RuleException {
        return new VarNumber(Math.pow(a.toDouble(), n.toDouble()));
    }
}