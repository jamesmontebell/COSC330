package org.example;

public class TestCode {
    public static void main(String[] args) {
        Application anApp = new Application();
        Registrar reg = new Registrar();

        System.out.println("Running first eval!");
        GPAEval aGPAEval = new GPAEval();

        reg.evaluate(anApp, aGPAEval);

        System.out.println("Running second eval!");
        GREEval aGREEval = new GREEval(new GPAEval());
        reg.evaluate(anApp, aGREEval);

        System.out.println("Running third eval!");
        TOEFLEval aTOEFLEval = new TOEFLEval(new GPAEval());
        reg.evaluate(anApp, aTOEFLEval);

        System.out.println("Running fourth eval!");
        EvaluationCriteria criteria = new TOEFLEval(aGREEval);
        reg.evaluate(anApp, criteria);


    }
}
