package rule.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import static rule.constants.DaoConstants.FILE_TYPE_RULE_DAO;
import static rule.constants.DaoConstants.RULE_DAO_TYPE_ENV_PARAM;

/**
 * The <code>ruleengine</code> represents {description}
 * <p>
 * <li>{Enclosing Methods}</li> {short description}
 * <p>
 * Created at 10/3/18
 *
 * @author a0j00cq (last updated by $Author$)
 * @version $Revision$ $Date$
 * @since GIF 1.0
 */
public class FileBasedRuleDaoCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return FILE_TYPE_RULE_DAO.equals(System.getProperty(RULE_DAO_TYPE_ENV_PARAM));
    }
}
