
/**
 * ImageContent class inherits the Content class, thereby becoming a specialized type of Content.  Child of Content has a String that tracks the ImageType of Content
 *
 * @author (Huy Nguyen)
 * @version (2023-10-16)
 */
public class ImageContent extends Content
{
    //Instance variables
    private String strImageType;  //stores content Image Type
    
    
    public ImageContent(String strContent, String strImageType) 
    {
        super(strContent); //calls parent constructor with String Information (strContent)
        this.strImageType = strImageType;  //set image Type
    }
}
