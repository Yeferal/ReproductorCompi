
package frontend.gui.editor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
 
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;
/**
 *
 * @author luis
 */
public class LineasText extends JPanel{
    
  public JTextPane pane;
  public JScrollPane scrollPane;
 
  public LineasText (){
    super ();
    setMinimumSize (new Dimension (30, 30));
    setPreferredSize (new Dimension (30, 30));
    setMinimumSize (new Dimension (30, 30));
    pane = new JTextPane (){ // se necesita pintar las lineas en el panel 
      public void paint (Graphics g)
      {
	super.paint (g);
	LineasText.this.repaint ();
      }
    };
    pane.setFont(new java.awt.Font("Monospaced", 0, 13));
    
    //setTabs();
    
    
    scrollPane = new JScrollPane (pane);
  }
 
  @Override
  public void paint (Graphics g){
    super.paint (g);
    int start =
      pane.viewToModel (scrollPane.getViewport ().getViewPosition ());
    int end =
      pane.viewToModel (new
		   Point (scrollPane.getViewport ().getViewPosition ().x +
			  pane.getWidth (),
			  scrollPane.getViewport ().getViewPosition ().y +
			  pane.getHeight ()));
    Document doc = pane.getDocument ();
    int startline = doc.getDefaultRootElement ().getElementIndex (start);
    int endline = doc.getDefaultRootElement ().getElementIndex (end)+1; //pinta la linea numero 1
    int fontHeight = g.getFontMetrics (pane.getFont ()).getHeight ();	// fuente
    
    for (int line = startline, y = 0; line <= endline;line++, y += fontHeight){
	g.drawString (Integer.toString (line), 0, y);
      }
  }
  
  public void setTabs(){
      Font font = new Font("Monospaced", Font.PLAIN, 13);
    Toolkit t = Toolkit.getDefaultToolkit();
    FontMetrics fm = t.getFontMetrics(font);
    int cw = fm.stringWidth("    ");
    float f = (float)cw;
    TabStop[] tabs = new TabStop[50]; // this sucks

    for(int i = 0; i < tabs.length; i++){
        tabs[i] = new TabStop(f * (i + 1), TabStop.ALIGN_LEFT, TabStop.LEAD_NONE);
    }
    TabSet tabset = new TabSet(tabs);

    StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
    StyleConstants.TabSet, tabset);
    pane.setParagraphAttributes(aset, false);
  }
 
}
