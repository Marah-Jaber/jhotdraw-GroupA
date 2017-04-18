
package edu.bzu.swen6304.editor.figure;

import static org.jhotdraw.draw.AttributeKeys.FONT_BOLD;

import org.jhotdraw.draw.TextFigure;

public class Text_Figure extends TextFigure implements styleFigure{
    /** Creates a new instance. */
    public Text_Figure() {

    }
    
    public void addStyle(){
       
        this.setFontSize(30);
        this.setAttributeEnabled(FONT_BOLD, true);
        
    }
    

}
