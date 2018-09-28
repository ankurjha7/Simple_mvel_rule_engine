package rule.enums;

/**
 * The <code>ruleengine</code> represents {description}
 * <p>
 * <li>{Enclosing Methods}</li> {short description}
 * <p>
 * Created at 9/27/18
 *
 * @author a0j00cq (last updated by $Author$)
 * @version $Revision$ $Date$
 * @since GIF 1.0
 */
public enum RuleLogicalOperator {
    AND("&&"),
    OR("||"),
    NA(";");

    private String value;

    RuleLogicalOperator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }
}
