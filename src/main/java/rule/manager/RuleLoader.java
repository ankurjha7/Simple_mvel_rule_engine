package rule.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import rule.dao.spi.IRuleDao;
import rule.model.Rule;
import rule.util.MVELUtil;

import javax.annotation.PostConstruct;
import java.io.Serializable;
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
public class RuleLoader {

    private static final Logger log = LoggerFactory.getLogger(RuleLoader.class);

    @Autowired
    private IRuleDao ruleDao;

    private Map<Rule,Serializable> compiledRules;

    @PostConstruct
    public void init()
    {
        loadExistingRules();
    }

    public void loadExistingRules()
    {
        log.debug("Loading all existing rules");
        compiledRules = new HashMap<>();
        List<Rule> rules = ruleDao.getAllRules();

        if(!CollectionUtils.isEmpty(rules))
        {
            for(Rule rule: rules)
            {
                compiledRules.put(rule, MVELUtil.getcompiledRule(rule.getRuleExpression()));
            }
        }

        log.info("All rules loaded successfully ");
    }

    public Map<Rule, Serializable> getCompiledRules() {
        return compiledRules;
    }

    public void setCompiledRules(Map<Rule, Serializable> compiledRules) {
        this.compiledRules = compiledRules;
    }

}
