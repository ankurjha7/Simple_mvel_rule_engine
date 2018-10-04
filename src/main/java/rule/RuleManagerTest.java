package rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rule.manager.InputProcessor;
import rule.manager.RulesEditor;
import rule.model.RuleOutput;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
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

    private static final Logger log = LoggerFactory.getLogger(RuleManagerTest.class);

    @Autowired
    RulesEditor rulesEditor;

    @Autowired
    InputProcessor inputProcessor;

    @PostConstruct
    public void init(){

        Map<String,Object> testMap = new HashMap<>();
        testMap.put("priority",0.5);
//        testMap.put("abc",3);

        String ruleExpression1 = "if (priority < 1) return \"mophandler\";";
        String ruleExpression2 = "if (priority < 2) return \"handler2\";";
        String ruleExpression3 = "if priority < 3 return \"handler3\";";


//        rulesManager.addRule(ruleExpression1,"RULE1");
//        rulesManager.addRule(ruleExpression2,"RULE2");
//        rulesEditor.addRule(ruleExpression3,"RULE3");

        List<RuleOutput> ruleOutputs =  inputProcessor.processInput(testMap);

        log.info("Output is : {}",ruleOutputs);



    }



}
