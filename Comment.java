
/**
 * Comment class is used for comments on posts.  Functionality includes tracking a content ID, the commenter of the comment, the textContent of comment, and 
 * TimeStamp of when comment was created.  As well, getCommentInformation() method returns an outputted string with comment information
 * 
 * @author (Huy Nguyen)
 * @version (2023-10-16)
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Comment
{
    //VARIABLES
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //date_formatter is used to format ldtTimeStamp(Local Date Time object) with pattern "yyyy-MM-dd HH:mm"
    //it's static as each instance of class does need their own formatter, it's the same for all of them
    //it's final as the format of LocalDateTime variables should not change at runtime
    
    private short shtCommentID; //comment ID of comment.  Comment ID stores what number of comment comment is of the post
                               //it is populated through constructor as method in Post class sends in its num of comments + 1
                               //short has been designated for this value as there is a predicted maximum of 32767 comments on each post
                               //however more than 127 comments is expected so cannot be byte
    private String strCommenter; //the username of the commenter
    private TextContent textContent; //the text content of the comment
    private LocalDateTime ldtTimeStamp; //stores timeStamp of comment, the same as when its textContent was created
    
    //Constructor passes in post's number of comments + 1, the username of commenter, and textContemt
    public Comment(short shtNumComments, String strCommenter, TextContent textContent)
    {
        //set instance variables
        this.shtCommentID = (short)(shtCommentID + 1); //set comment ID to number of current comments +1. Add + 1 because this value is set before post adds instance of comment to 
                                                      //its array list of comments.
        this.strCommenter = strCommenter; 
        this.textContent = textContent; 
        //set timeStamp of comment to the same timeStamp of when content was created
        this.ldtTimeStamp = textContent.getTimeStamp(); 
    }
    
    public String getCommentInformation() //return important information of comment for output
    {
        return String.format("Commented on %s by %s\n%s", ldtTimeStamp.format(DATE_FORMATTER),strCommenter, textContent.getContent()); 
        //return name of commenter, the content of the comment, and the formatted time of when the comment was commented
    }


}