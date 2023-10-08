package org.example;

public class TOEFLEval extends CriteriaLink{

    public TOEFLEval(EvaluationCriteria theNext)
    {
        super(theNext);
    }

    @Override
    public boolean evaluate(Application theApp) {
        if(super.evaluate(theApp))
        {
            System.out.println("TOEFLEval.evaluate Called!");
            return true;
        }
        else
        {
            return false;
        }
    }
}
