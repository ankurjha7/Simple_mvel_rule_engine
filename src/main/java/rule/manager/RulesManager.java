package rule.manager;

import org.mvel2.MVEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import rule.dao.impl.FileBasedRuleDaoImpl;
import rule.dao.spi.IRuleDao;
import rule.exception.RuleCompilationException;
import rule.model.Rule;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class RulesManager {

    private static final Logger log = LoggerFactory.getLogger(FileBasedRuleDaoImpl.class);

    @Autowired
    private IRuleDao ruleDao;

    Map<Rule,Serializable> compiledRules;

    @PostConstruct
    public void init()
    {
        loadExistingRules();
    }

    private void loadExistingRules()
    {
        log.debug("Loading all existing rules");
        compiledRules = new HashMap<>();
        List<Rule> rules = ruleDao.getAllRules();

        if(!CollectionUtils.isEmpty(rules))
        {
            for(Rule rule: rules)
            {
                compiledRules.put(rule,getcompiledRule(rule.getRuleExpression()));
            }
        }

        log.info("All rules loaded successfully ");
    }


    public void addRule(String ruleExpression,int priority,String name)
    {
         getcompiledRule(ruleExpression);
         Rule rule = new Rule(ruleExpression,name,priority);
         ruleDao.addRule(rule);

        log.info("Succesfully added rule with expression : {}",ruleExpression);

         loadExistingRules();
    }

    public void addRule(String ruleExpression,String name)
    {
        addRule(ruleExpression,DEFAULT_PRIORITY,name);
    }


    private Serializable getcompiledRule(String ruleExpression)
    {
        try {
            Serializable compiledExpression = MVEL.compileExpression(ruleExpression);
            return compiledExpression;
        }
        catch (Exception ex)
        {
            String errorMessage = "Failure to compile mvel expression : "+ ruleExpression + " with error :-"+ ex.getMessage();
            log.error(errorMessage);
            throw new RuleCompilationException(errorMessage);
        }
    }



}
