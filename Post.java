
/**
 * Post class is used for posts.  Atributes includes having a postID, having content, having the username of the poster, storing post's comments, and post's timestamp
 * Functionality includes adding a comment to the post, returning a string for information of post, returning a string for information of all comments
 * 
 * @author (Huy Nguyen)
 * @version (2023-10-16)
 */

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post
{
    //VARIABLES
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //date_formatter is used to format ldtTimeStamp(Local Date Time object) with pattern "yyyy-MM-dd HH:mm"
    //it's static as each instance of class does need their own formatter, it's the same for all of them
    //it's final as the format of LocalDateTime variables should not change at runtime

    private static short shtNumPosts = -1; //static variable belongs to class to track the number of posts that have been created.  Set to -1 so that the first post has an ID of 0
    
    //instance variables
    private short shtPostID; //holds id of post
    private Content content; //holds content of post
    private String strPoster; //holds string of the username of the poster
    private LocalDateTime ldtTimeStamp; //holds timestamp of post -> set to be the same as the timestamp of content (when content was created)
    private ArrayList<Comment> lstComments = new ArrayList<Comment>(); //arraylist holds the comments for each instance of post
    
    public Post(Content content, String strPoster)
    {
        shtNumPosts++; //increments shtNumPosts by 1 
        shtPostID = shtNumPosts;  //sets postID based on shtNumPosts
        
        this.strPoster = strPoster; 
        this.content = content; 
        this.ldtTimeStamp = content.getTimeStamp(); //get timestamp of content to set to timestamp of post
        
    }
    
    //method adds a comment to the post, taking in the commenter of the post (username of UserProfile of poster) and the information to be posted
    public void addCommentOnPost(String strCommenter, String strComment)
    {
        lstComments.add(new Comment((byte)(lstComments.size()), strCommenter, new TextContent(strComment)));
        //add a comment to lstComments
        //pass current number of comments to set ID of comment, the username of the commenter, and a textContent with paramater comment string information 
    }
    
    public String toString() //output important information of post 
    {
        return String.format("Posted on %s by %s\n%s", ldtTimeStamp.format(DATE_FORMATTER), strPoster, content.getContent());   
        //output with content of post, and formatted time of when post was posted
    }
    
    public String commentsToString() //retrurns all the comments of instance of Post as string
    {
        String strComments = ""; 
        for (Comment c : lstComments) //loop through all comments in lstComments
        {
            strComments += c.getCommentInformation() + "\n"; 
            //call getCommentInformation and print out returned string to output comment
        }
        return (strComments.equals("")) ? "No comments" :  strComments; //returns string of comments.  if strComments == "" that means there are no comments on this post, so return "No comments"
                                                                        //otherwise return strComments
    }
    
    


    public short getPostID()
    {
        return this.shtNumPosts;
    }

 
}
