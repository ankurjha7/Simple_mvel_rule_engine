package rule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rule.dao.impl.FileBasedRuleDaoImpl;
import rule.exception.RuleCompilationException;
import rule.manager.InputProcessor;
import rule.manager.RuleLoader;
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
 * Created at 10/5/18
 *
 * @author a0j00cq (last updated by $Author$)
 * @version $Revision$ $Date$
 * @since GIF 1.0
 */
@Component
public class RuleManagerUsageSample {

    private static final Logger log = LoggerFactory.getLogger(RuleManagerUsageSample.class);

    @Autowired
    RulesEditor rulesEditor;

    @Autowired
    InputProcessor inputProcessor;

    @Autowired
    RuleLoader ruleLoader;



    @PostConstruct
    public void init()
    {
        rulesEditor.deleteAllRules();
        ruleAdditionWithoutPriority();
        ruleAdditionWithPriority();
        ruleAdditionInvalidSyntax();
        complexRuleAddition();
        singleRuleSatisfyingInput();
        addingConflictingRule();
        multipleRuleWithSameParamsSatisfyingInput();
        multipleRuleWithDifferentParamsSatisfyingInput();

    }


    public void ruleAdditionWithoutPriority()
    {

        String ruleExpression1 = "if (priority == 1) return \"handler1\";";
        String ruleExpression2 = "if (priority == 2) return \"handler2\";";

        rulesEditor.addRule(ruleExpression1,"RULE1");
        rulesEditor.addRule(ruleExpression2,"RULE2");
        
    }


    public void ruleAdditionWithPriority()
    {

        String ruleExpression = "if (priority == 3) return \"handler3\";";
        rulesEditor.addRule(ruleExpression,70,"RULE3");
    }

    public void ruleAdditionInvalidSyntax()
    {
        String ruleExpression = "if priority == 3 return \"handler3\";";
        try {
            rulesEditor.addRule(ruleExpression, "RULE4");
        }catch (RuleCompilationException ex)
        {
            log.error("Invalid rule added");
        }
    }

    public void complexRuleAddition()
    {
        String ruleExpression = "if ( priority > 0 && commodity == 999) return \"handler5\";";
        rulesEditor.addRule(ruleExpression,"RULE5");
    }

    public void singleRuleSatisfyingInput()
    {
        Map<String,Object> input = new HashMap<>();
        input.put("priority",3);
        List<RuleOutput> ruleOutputs =  inputProcessor.processInput(input);
        log.info("\n\nOutput received : " + ruleOutputs);
    }

    public void addingConflictingRule()
    {
        String ruleExpression = "if (priority < 4) return \"handler6\";";
        rulesEditor.addRule(ruleExpression,70,"RULE6");
    }

    public void multipleRuleWithSameParamsSatisfyingInput()
    {
        Map<String,Object> input = new HashMap<>();
        input.put("priority",3);
        List<RuleOutput> ruleOutputs =  inputProcessor.processInput(input);
        log.info("\n\nOutput received : " + ruleOutputs);

    }

    public void multipleRuleWithDifferentParamsSatisfyingInput()
    {
        Map<String,Object> input = new HashMap<>();
        input.put("priority",3);
        input.put("commodity",999);
        List<RuleOutput> ruleOutputs =  inputProcessor.processInput(input);
        log.info("\n\nOutput received : " + ruleOutputs);
    }


}
