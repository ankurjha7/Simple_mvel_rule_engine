package rule.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rule.dao.spi.IRuleDao;
import rule.exception.RuleDAOException;
import rule.model.Rule;
import rule.util.MVELUtil;

import static rule.constants.RuleConstants.DEFAULT_PRIORITY;

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
@Component
public class RulesEditor {

    private static final Logger log = LoggerFactory.getLogger(RulesEditor.class);

    @Autowired
    private IRuleDao ruleDao;

    @Autowired
    private RuleLoader ruleLoader;


    public void addRule(String ruleExpression,int priority,String name)
    {
         MVELUtil.getcompiledRule(ruleExpression);
         Rule rule = new Rule(ruleExpression,name,priority);
         ruleDao.addRule(rule);
         log.info("Succesfully added rule with expression : {}",ruleExpression);
         ruleLoader.loadExistingRules();
    }

    public void addRule(String ruleExpression,String name)
    {
        addRule(ruleExpression,DEFAULT_PRIORITY,name);
    }


    public void deleteAllRules()
    {
        try {
            ruleLoader.getCompiledRules().keySet().forEach(key -> ruleDao.deleteRuleById(key.getId()));
            log.info("Sucessfully deleted all rules");
        }catch (RuleDAOException ex)
        {
            log.error("Failed to delete all rules");
        }
        finally {
            ruleLoader.loadExistingRules();
        }
    }



}
