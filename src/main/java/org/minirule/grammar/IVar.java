package org.minirule.grammar;

public interface IVar {
    // get type
    public Class<?> getType();

    // get value
    public Object getValue();

    // convert data type
    public Object cast(Class<?> type) throws RuleException ;

    // convert into double
    public double toDouble() throws RuleException;

    // convert into string
    public String toString();

    // check if var is a sring or not
    public boolean isString() ;

    // check if var is null or not
    public boolean isNull() ;

    // check if var is number or not
    public boolean isNumber() ;

    // check if var is boolean or not
    public boolean isBoolean() ;

    // get boolean value
    public boolean getBoolean();

    // get property
    public IVar getProperty(String field) throws RuleException ;

    // get element
    public IVar getElement(int index) throws RuleException ;

    // set property
    public void setProperty(String field, IVar value) throws RuleException ;

    // get property
    public IVar property(IVar field) throws RuleException ;
}
