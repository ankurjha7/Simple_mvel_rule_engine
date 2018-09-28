package rule.model;

import rule.enums.RuleBinaryOperator;
import rule.enums.RuleLogicalOperator;

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
public class RuleGeneratorData {
    String Key;
    String value;
    RuleBinaryOperator operator;
    RuleLogicalOperator appender;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RuleBinaryOperator getOperator() {
        return operator;
    }

    public void setOperator(RuleBinaryOperator operator) {
        this.operator = operator;
    }

    public RuleLogicalOperator getAppender() {
        return appender;
    }

    public void setAppender(RuleLogicalOperator appender) {
        this.appender = appender;
    }
}
