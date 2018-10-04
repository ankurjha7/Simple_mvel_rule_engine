package rule.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rule.model.Rule;
import rule.model.RuleOutput;
import rule.util.MVELUtil;

import java.io.Serializable;
import java.util.ArrayList;
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
public class InputProcessor {

    private static final Logger log = LoggerFactory.getLogger(RulesEditor.class);

    @Autowired
    RuleLoader ruleLoader;

    public List<RuleOutput> processInput(Map<String,Object> input)
    {
          List<RuleOutput> ruleOutputs = new ArrayList<>();

          for (Map.Entry<Rule,Serializable> entry : ruleLoader.getCompiledRules().entrySet())
          {
             if(MVELUtil.isRuleCompleteForInput(input,entry.getValue()))
             {
                 RuleOutput ruleOutput = new RuleOutput(MVELUtil.getResultForInput(input,entry.getValue()),entry.getKey().getPriority());
                 if(ruleOutput.getOutput() != null) {
                     ruleOutputs.add(ruleOutput);
                 }
             }
          }

          return ruleOutputs;

    }



}
