package rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rule.manager.RulesManager;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

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
@Component
public class RuleManagerTest {

    @Autowired
    RulesManager rulesManager;

    @PostConstruct
    public void init(){

        Map<String,Object> testMap = new HashMap<>();
        testMap.put("priority",3);
        testMap.put("abc",3);

        String ruleExpression1 = "if (priority < 1) return \"mophandler\";";
        String ruleExpression2 = "if (priority < 2) return \"handler2\";";
        String ruleExpression3 = "if priority < 3 return \"handler3\";";


//        rulesManager.addRule(ruleExpression1,"RULE1");
//        rulesManager.addRule(ruleExpression2,"RULE2");
        rulesManager.addRule(ruleExpression3,"RULE3");


    }



}
