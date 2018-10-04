package rule.util;

import org.mvel2.MVEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rule.exception.InvalidInputException;
import rule.exception.RuleCompilationException;
import rule.model.RuleOutput;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

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
public class MVELUtil {

    private static final Logger log = LoggerFactory.getLogger(MVELUtil.class);

    public static Serializable getcompiledRule(String ruleExpression)
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

    public static String getResultForInput(Map<String,Object> input, Serializable rule)
    {
        if(isInputValidForRule(input,rule))
        {
            return (String)MVEL.executeExpression(rule,input);
        }
        else
        {
            String errorMessage = "input : " + input + " not valid for rule : " + rule;
            log.error(errorMessage);
            throw new InvalidInputException(errorMessage);
        }
    }

    /**
     *
     * @param input
     * @param rule
     * @return true or false on the basis of input returning valid result or an exception. The exception can come if the input can't be casted properly
     * or the input is missing a required variable for rule
     */
    public static boolean isInputValidForRule(Map<String,Object> input,Serializable rule)
    {
        try
        {
           MVEL.executeExpression(rule,input);
           return true;
        }catch (Exception ex)
        {
            String errorMessage = "input : " + input + " not valid for rule : " + rule;
            log.debug(errorMessage);
            return false;
        }
    }

    /**
     *
     * @param input
     * @param rule
     * @return it will return false if rule is not using all the variables of input. It will also return false if input does not contain all params
     * for rule
     *
     */
    public static boolean isRuleCompleteForInput(Map<String,Object> input, Serializable rule)
    {

        if(!isInputValidForRule(input,rule))
            return false;

       for (String key : input.keySet())
       {
           /*
           this checks if by removing one key is the input still valid for rule. If yes then the rule doesn't uses all the variables of
           input and hence return false
            */
          if(isInputValidForRule(filterMapWithKey(key,input),rule))
              return false;
       }

       return true;
    }

    private static Map<String,Object> filterMapWithKey(String key,Map<String,Object> source)
    {
        Map<String,Object> filteredMap =
                source.entrySet()
                        .stream()
                        .filter(p -> !(p.getKey().equals(key)))
                        .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        return filteredMap;
    }

}
