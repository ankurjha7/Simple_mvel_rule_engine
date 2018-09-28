import org.mvel2.MVEL;
import org.mvel2.util.MVELClassLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <code>ruleengine</code> represents {description}
 * <p>
 * <li>{Enclosing Methods}</li> {short description}
 * <p>
 * Created at 12/25/17
 *
 * @author a0j00cq (last updated by $Author$)
 * @version $Revision$ $Date$
 * @since GIF 1.0
 */
public class RuleTest {

    public static void main(String[] args) {

        //Map<String, Object> params = new HashMap<String, Object>();
        //params.put("x", 10);
        //params.put("y", 20);
        //Object result = MVEL.eval("x+y", params);
        List<Integer> lst=new ArrayList<>();
        for(int i=0;i<10*100*100;i++){
            lst.add(i);
        }
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("AA",lst);
        //Object result = MVEL.eval(" val v=0;for(x:lst) { v=v+x;} return v;", lst);
        //Object result = MVEL.eval(" foreach (x : AA) { System.out.println(x+\"\");} return 0;", input);
//        Object result = MVEL.eval("  foreach (x : AA) { System.out.println(x+1000);} return 0;", input);
        //打印到51，然后 Error: can't load this type of class file

//        System.out.println("AAAA:"+result);


        Map<String,Object> testMap = new HashMap<>();
        testMap.put("priority",1);
        testMap.put("abc",3);

        Serializable compiledRule= MVEL.compileExpression("if (priority >= 1 && priority < 3) return \"hello\";");

        System.out.println( MVEL.executeExpression(compiledRule,testMap));

    }

}
