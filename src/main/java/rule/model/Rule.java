package rule.model;

/**
 * The <code>ruleengine</code> represents {description}
 * <p>
 * <li>{Enclosing Methods}</li> {short description}
 * <p>
 * Created at 9/27/18
 *
 * @author a0j00cq (last updated by $Author$)
 * @version $Revision$ $Date$
 */
public class Rule {

    private String ruleExpression;
    private String name;
    private Long id;
    private int priority = 50;

    public String getRuleExpression() {
        return ruleExpression;
    }

    public void setRuleExpression(String ruleExpression) {
        this.ruleExpression = ruleExpression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Rule(String ruleExpression, String name, int priority) {
        this.ruleExpression = ruleExpression;
        this.name = name;
        this.priority = priority;
    }

    public Rule() {
    }

    @Override
    public String toString() {
        return "Rule{" +
                "ruleExpression='" + ruleExpression + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", priority=" + priority +
                '}';
    }
}
