package model;

/**
 * Class model Account.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 14.04.2017
 */
public class Account {
    /**
     * property value - amount of money.
     */
    private double value;

    /**
     * property requisites account number.
     */
    private long requisites;

    /**
     * Constructor it creates a new object User with the specified values.
     *
     * @param value      - set property value
     * @param requisites - set property requisites
     */
    public Account(double value, long requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Getter property requisites.
     *
     * @return requisites - property requisites
     */
    public long getRequisites() {
        return this.requisites;
    }

    /**
     * Setter property value.
     *
     * @param requisites - property requisites
     */
    public void setRequisites(long requisites) {
        this.requisites = requisites;
    }

    /**
     * Getter property value.
     *
     * @return value - property value
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Setter property value.
     *
     * @param value - property value
     */
    public void setValue(double value) {
        this.value = value;
    }


}
