package org.example;

public class GREEval extends CriteriaLink{

    public GREEval(EvaluationCriteria theNext)
    {
        super(theNext);
    }

    @Override
    public boolean evaluate(Application theApp) {
        if(super.evaluate(theApp))
        {
            System.out.println("GREEval.evaluate Called!");
            return true;
        }
        else
        {
            return false;
        }
    }
}
