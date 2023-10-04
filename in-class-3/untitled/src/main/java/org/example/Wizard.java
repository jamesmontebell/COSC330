package org.example;

public class Wizard {
    private static Wizard male = null;
    private static Wizard female = null;
    private Wizard()
    {}

    public static Wizard[] getWizard(boolean isMale)
    {
        if(male == null && isMale)
        {
            male = new Wizard();
        }
        else if (female == null)
        {
            female = new Wizard();
        }
        Wizard n[] = {male, female};
        return n;
    }

}
