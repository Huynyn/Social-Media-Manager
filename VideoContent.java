
/**
 * VideoContent class inherits the Content class, thereby becoming a specialized type of Content.  Child of Content has a string that stores url of video
 *
 * @author (Huy Nguyen)
 * @version (2023-10-16)
 */
public class VideoContent extends Content
{
    private String strURL; //stores url of video
    
    public VideoContent(String strContent, String strURL)
    {
        super(strContent); //calls parent constructor with String Information (strContent)
        this.strURL = this.strURL; //sets strURL from parameter 
    }
}
