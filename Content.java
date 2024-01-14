
/**
 *Parent class that holds content.  important functionality includes content ID, string for content's content, and timestamp when it was created
 *
 * @author (Huy Nguyen)
 * @version (2023-10-16)
 */

import java.time.LocalDateTime;


public class Content
{
    //VARIABLES
    private static long numContents = 0; //
    //Static variable belonging to the class tracks number of users created. This is used to set the contentID, ensuring each one is unique as each one is
    //set from the number of content objects created which goes up when a content is created.
    
    
    private long lngContentID; //holds contentID of content -> int has been chosen because predicted maxium of 32767 UserProfiles each with predicted maximun 127 posts each with predcited maxium 
                                //of 32767 means there could be a maxium of 136,356,888,703 comments alone, each with a content object. Thus, long has been chosen for data type to be able to store this number and greater 
    private String strContent; //holds string information of content
    private LocalDateTime ldtTimeStamp; //holds when the content was created, giving it a timestamp for time of creation.  Local date time has been used as it tracks year, month, date, and time
    
    
    public Content(String strContent)
    {
        numContents++; //increase num of contents
        this.lngContentID = numContents; //set content ID to new number of contents 
        this.strContent = strContent;  
        this.ldtTimeStamp = LocalDateTime.now();  //set timestamp to now 
    }
    
    
    
    
    //GETTERS AND SETTERS
    public void setContentID(long lngContentID)
    {
        this.lngContentID = lngContentID;
    }

    public void setContent(String strContent)
    {
        this.strContent = strContent;
    }

    public void setTimeStamp(LocalDateTime ldtTimeStamp)
    {
        this.ldtTimeStamp = ldtTimeStamp;
    }

    public long getContentID()
    {   
        return this.lngContentID;
    }

    public String getContent()
    {
        return this.strContent;
    }

    public LocalDateTime getTimeStamp()
    {
        return this.ldtTimeStamp;
    }
}