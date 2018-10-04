package rule.dao.spi;

import rule.exception.RuleDAOException;
import rule.model.Rule;

import java.util.List;

/**
 * The <code>ruleengine</code> represents {description}
 * <p>
 * <li>{Enclosing Methods}</li> {short description}
 * <p>
 * Created at 9/27/18
 *
 * @author a0j00cq (last updated by $Author$)
 * @version $Revision$ $Date
 */
public interface IRuleDao {

    /**
     * Retrieves list of all the rules stored
     * @return
     */
    List<Rule> getAllRules() throws RuleDAOException;

    /**
     * Add a rule to the data store. The id needs to be generated as per the implementation
     * @param rule
     */
    void addRule(Rule rule) throws RuleDAOException;

    /**
     * Delete rule with id from rule object
     * @param id
     */
    void deleteRuleById(Long id) throws RuleDAOException;

    /**
     * Update rule with the id from rule object with the updated values from rule. The id won't get Updated even if a modified value is put
     * @param rule
     */
    void updateRule(Rule rule) throws RuleDAOException;



}
