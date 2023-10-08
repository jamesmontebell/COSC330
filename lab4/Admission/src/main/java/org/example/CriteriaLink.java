package org.example;

public class CriteriaLink extends EvaluationCriteria{
    private EvaluationCriteria next;
    public CriteriaLink(EvaluationCriteria theNext)
    {
        next =theNext;
    }

    @Override
    public boolean evaluate(Application theApp) {
         if(next != null)
         {
             return next.evaluate(theApp);
         }
         else
         {
             return true;
         }
    }
}
