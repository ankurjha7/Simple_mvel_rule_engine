package rule.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import rule.condition.FileBasedRuleDaoCondition;
import rule.dao.spi.IRuleDao;
import rule.exception.FileIOException;
import rule.exception.InvalidConfigException;
import rule.exception.RuleDAOException;
import rule.model.Rule;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static rule.constants.DaoConstants.FILE_RULE_PATH_ENV_PARAM;

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
@Component
@Conditional(FileBasedRuleDaoCondition.class)
public class FileBasedRuleDaoImpl implements IRuleDao {

    private static final Logger log = LoggerFactory.getLogger(FileBasedRuleDaoImpl.class);

    File ruleFile;

    ObjectMapper yamlFileMapper;

    @PostConstruct
    public void init()
    {
        try {
             yamlFileMapper = new ObjectMapper(new YAMLFactory());
             String ruleFileName = System.getProperty(FILE_RULE_PATH_ENV_PARAM);
             ruleFile = new File(ruleFileName);
             checkAndCreateFile(ruleFile);

        }catch (NullPointerException npe)
        {
            String errorMessage = "Rule File name can't be null. Property required :" + FILE_RULE_PATH_ENV_PARAM;
            log.error(errorMessage);
            throw new InvalidConfigException(errorMessage);
        }

    }

    private void checkAndCreateFile(File file)
    {
        if(file.exists()) {
            if (!file.canRead() || !file.canWrite())
            {
                String errorMessage = "Read/ Write Access not present for rule file :"+file.getName();
                throw new FileIOException(errorMessage);
            }
        }

        else
        {
            log.debug("Rule file not present trying to create it");
            try
            {
                if(!file.createNewFile())
                {
                    String errorMessage = "Failed to create rule file:" + file.getName();
                    log.error(errorMessage);
                    throw new FileIOException(errorMessage);
                }}
            catch (IOException io)
            {
                String errorMessage = "IO Exception while creating rule file:" + file.getName();
                log.error(errorMessage);
                throw new FileIOException(errorMessage);
            }
        }
    }


    /**
     * Retrieves list of all the rules stored
     *
     * @return
     */
    @Override
    public List<Rule> getAllRules() throws RuleDAOException {

        try {
            List<Rule> rules = new ArrayList<>();

            if(ruleFile.length() != 0) {
                Rule[] rulesArray = yamlFileMapper.readValue(ruleFile, Rule[].class);
                rules.addAll(Arrays.asList(rulesArray));
            }
            return rules;
        }catch (IOException ex)
        {
            String errorMessage = "Failed to read rule values from yaml file : "+ ruleFile.getName() + " error: "+ ex.getMessage();
            log.error(errorMessage);
            throw new RuleDAOException(errorMessage);
        }
    }

    /**
     * Add a rule to the data store. The id needs to be generated as per the implementation
     *
     * @param rule
     */
    @Override
    public void addRule(Rule rule) throws RuleDAOException {

        try {
            List<Rule> existingRules = getAllRules();
            rule.setId((long) existingRules.size() + 1);
            existingRules.add(rule);
            yamlFileMapper.writeValue(ruleFile,existingRules);
        }catch (IOException ex)
        {
            String errorMessage = "Failed to write rule values to yaml file : "+ ruleFile.getName() + " error: "+ ex.getMessage();
            log.error(errorMessage);
            throw new RuleDAOException(errorMessage);
        }

    }

    /**
     * Delete rule with id from rule object
     *
     * @param id
     */
    @Override
    public void deleteRuleById(Long id) throws RuleDAOException {

        try {
            List<Rule> existingRules = getAllRules();
            List<Rule> updatedRules = new ArrayList<>();

            for(Rule rule : existingRules)
            {
                if(rule.getId() != id)
                    updatedRules.add(rule);
            }
            yamlFileMapper.writeValue(ruleFile,updatedRules);

        }catch (IOException ex)
        {
            String errorMessage = "Failed to delete rule with id  : "+ id + " error: "+ ex.getMessage();
            log.error(errorMessage);
            throw new RuleDAOException(errorMessage);
        }

    }

    /**
     * Update rule with the id from rule object with the updated values from rule. The id won't get Updated even if a modified value is put
     *
     * @param rule
     */
    @Override
    public void updateRule(Rule rule) throws RuleDAOException {

    }
}
