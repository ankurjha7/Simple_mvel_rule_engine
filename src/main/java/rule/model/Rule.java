package rule.model;

import java.io.Serializable;

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
public class Rule {

    private int inputParams;
    private Serializable compiledRule;

    public int getInputParams() {
        return inputParams;
    }

    public void setInputParams(int inputParams) {
        this.inputParams = inputParams;
    }

    public Serializable getCompiledRule() {
        return compiledRule;
    }

    public void setCompiledRule(Serializable compiledRule) {
        this.compiledRule = compiledRule;
    }
}
