package by.verus.debts.model;


public class Debtor {
    private String name;
    private float sum;
    private boolean debtor;

    public Debtor(String name, float sum, boolean debtor) {
        this.name = name;
        this.sum = sum;
        this.debtor = debtor;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSum() {
        return this.sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public boolean isDebtor() {
        return this.debtor;
    }

    public void setDebtor(boolean debtor) {
        this.debtor = debtor;
    }

    public String toString() {
        return "Debtor: [" + this.name + ", " + this.sum + "]";
    }
}
