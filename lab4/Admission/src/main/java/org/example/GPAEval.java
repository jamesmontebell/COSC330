package org.example;

public class GPAEval extends EvaluationCriteria{
    @Override
    public boolean evaluate(Application theApp) {
        System.out.println("GPAEval.evaluate Called!");
        return true;
    }
}
