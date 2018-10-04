package rule.model;

/**
 * The <code>ruleengine</code> represents {description}
 * <p>
 * <li>{Enclosing Methods}</li> {short description}
 * <p>
 * Created at 10/4/18
 *
 * @author a0j00cq (last updated by $Author$)
 * @version $Revision$ $Date$
 * @since GIF 1.0
 */
public class RuleOutput {

    private String output;
    private int priority;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public RuleOutput(String output) {
        this.output = output;
    }

    public RuleOutput(String output, int priority) {
        this.output = output;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "RuleOutput{" +
                "output='" + output + '\'' +
                ", priority=" + priority +
                '}';
    }
}
