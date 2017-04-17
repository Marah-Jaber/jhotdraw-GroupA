
package groupA.figure;

import java.awt.*;
import org.jhotdraw.samples.*;
import java.beans.*;
import java.util.HashMap;

import static org.jhotdraw.draw.AttributeKeys.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.xml.*;

public class Text_Figure extends TextFigure implements styleFigure{
    /** Creates a new instance. */
    public Text_Figure() {

    }
    
    public void addStyle(){
       
        this.setFontSize(30);
        this.setAttributeEnabled(FONT_BOLD, true);
        
    }
    

}
