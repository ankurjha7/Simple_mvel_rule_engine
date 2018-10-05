package manager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rule.dao.impl.FileBasedRuleDaoImpl;
import rule.exception.RuleCompilationException;
import rule.manager.InputProcessor;
import rule.manager.RuleLoader;
import rule.manager.RulesEditor;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RulesEditor.class, RuleLoader.class, FileBasedRuleDaoImpl.class,InputProcessor.class})
public class RuleManagerTest {

    private static final Logger log = LoggerFactory.getLogger(RuleManagerTest.class);

    @Autowired
    RulesEditor rulesEditor;

    @Autowired
    InputProcessor inputProcessor;

    @Autowired
    RuleLoader ruleLoader;

    private static boolean isDeleted = false;



    @Before
    public void deleteExistingRules()
    {
        if(!isDeleted) {
            rulesEditor.deleteAllRules();
            isDeleted = true;
        }
    }

    
    @Test
    public void ruleAdditionWithoutPriorityTest()
    {

        String ruleExpression1 = "if (priority == 1) return \"handler1\";";
        String ruleExpression2 = "if (priority == 2) return \"handler2\";";

        rulesEditor.addRule(ruleExpression1,"RULE1");
        rulesEditor.addRule(ruleExpression2,"RULE2");
        
    }


    @Test
    public void ruleAdditionWithPriorityTest()
    {

        String ruleExpression = "if (priority == 3) return \"handler3\";";
        rulesEditor.addRule(ruleExpression,70,"RULE3");
    }

    @Test(expected = RuleCompilationException.class)
    public void ruleAdditionInvalidSyntax()
    {
        String ruleExpression = "if priority == 3 return \"handler3\";";
        rulesEditor.addRule(ruleExpression,"RULE4");
    }

    @Test
    public void complexRuleAddition()
    {
        String ruleExpression = "if (priority > 0) && (commodity == 999) return \"handler5\";";
        rulesEditor.addRule(ruleExpression,"RULE5");
    }



}
