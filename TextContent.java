
/**
 *  TextContent class inherits the Content class, thereby becoming a specialized type of Content.  Child of Content has a short that tracks the length of Content

 *
 * @author (Huy Nguyen)
 * @version (2023-10-16)
 */
public class TextContent extends Content
{
    private short strTextLength; //stores length of textContent.  Short has been chosen as it means it can track up to 32767 characters or 5000+ words.  Applications
                                //of content will not reach that many characters.  But byte is too little, 127 characters only equating to around 20 words. 
     

    public TextContent(String strContent)
    {
        super(strContent);  //calls parent constructor with String Information (strContent)
        this.strTextLength = (short)strContent.length();  //get strTextLength using length method of string. 
    }
}
