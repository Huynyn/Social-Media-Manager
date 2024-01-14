/**
 * Class is for UserProfile.  functionality includes having a userId, a username, a date joined, an emial, and a list that contains the post ID's -> representing the posts that belong to the user
 * 
 * @author (Huy Nguyen)
 * @version (2023-10-16)
 */

import java.util.ArrayList;
import java.time.LocalDate;


public class UserProfile
{
    //VARIABLES
    private static byte shtNumUsers = -1; 
    //Static variable belonging to the class tracks number of users created. This is used to set the userIDs, ensuring each one is unique as each one is
    //set from the number of users created which goes up when a user is created. 
    //set to -1 so the first user created has a userID of 0
    
    //Instance variables
    private byte shtUserID; //holds ID of each user, set to the nth number of users created
    private String strUserName; //holds username of UserProfile
    private String strEmail; //holds email of UserProfile
    private LocalDate ldtDateJoined; //holds when the UserProfile was created.  LocalDate class stores the year, date, and month only -- only things important
                                        //with regard to porfile time of creation.  Contarily content, post, and comment use LocalDateTime to also track thier time of creation
                                        
    private ArrayList<Short> lstPostID = new ArrayList<Short>(); //holds the ID's of the posts that belong to object
    
    public UserProfile(String strUserName, String strEmail)
    {
        shtNumUsers++; //increment number of UserProfiles by 1 as the new UserProfile has been created
        this.shtUserID = shtNumUsers; //set User ID to new number of users
        this.strUserName = strUserName;
        this.strEmail = strEmail;
        this.ldtDateJoined = LocalDate.now(); //set date joined to the date of now. 
    }
    
    public void addPost(Short shtPostID) //adds post to userprofile's posts by adding the post's ID to lstPostID
    {
        lstPostID.add(shtPostID); 
    }
    
    
    //GETTER AND SETTER
    public void setUserID(int intUserID)
    {
        this.shtUserID = shtUserID;
    }

    public void setUsername(String strUserName)
    {
        this.strUserName = strUserName;
    }

    public void setEmail(String strEmail)
    {
        this.strEmail = strEmail;
    }

    public void setDateJoined(LocalDate ldtDateJoined)
    {
        this.ldtDateJoined = ldtDateJoined;
    }

    public void setPosts(ArrayList<Short> lstPostsID)
    {
        this.lstPostID = lstPostID;
    }

    public int getUserID()
    {
        return this.shtUserID;
    }

    public String getUsername()
    {
        return this.strUserName;
    }

    public String getEmail()
    {
        return this.strEmail;
    }

    public LocalDate getDateJoined()
    {
        return this.ldtDateJoined;
    }

    public ArrayList<Short> getLstPostID()
    {
        return lstPostID; 
    }
    
    
}