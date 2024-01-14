
/**
 * Social Media manages the classes of social media system.  Functionality includes creating a users, adding comments, adding posts, and changing the selected post,
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class SocialMediaManager
{
    private ArrayList<UserProfile> lstUsers = new ArrayList<UserProfile>();  //arraylist holds all users
    private ArrayList<Post> lstPosts = new ArrayList<Post>(); //arraylist holds all posts
    private short shtCurrentPostIndex; //short holds the index of the lstPosts of the current Post.  essentially stores current post.  Short has been chosen because User's are not expected to exceed 127 in number
                                        //meaning each user would need to post 250 posts in order to exceed size of this short -> improbable    
    private UserProfile uspCurrentUserProfile;  //stores the UserProfile of the currently selected user.  

    
    //default constructor to set values of shtCurrentPostIndex
    public SocialMediaManager()
    {
        this.shtCurrentPostIndex = 0; //sets the currentPostIndex to 0 by default
        this.uspCurrentUserProfile = null; //sets to null, meaning no user is currently selected on instantiation of object socialMediaManager
    }

    
    //METHODS ASSOCIATD WITH USERS
    public String createUserProfile(UserProfile user) //method creates a userprofile with passed in user. returns string to test class saying whether or not the user has been created
    {
        if (checkIfUserExists(user)) //before adding passed in user to lstUsers, check if email or username has already been taken
        {
            this.lstUsers.add(user); //add user to lstUsers
            return "Account sucessfully added to our network!"; //return that user has been added to network
        }
        else
        {
            return "Account has not been added to our network."; //return taht user has not been added to the network
        }
    }
    
    public boolean checkIfUserExists(UserProfile user) //method checks if user exists as in if another use already has the email or username associated with passed in user 
    {
        for (UserProfile u : this.lstUsers) //go through all existing users
        {
            if (user.getUsername().equals(u.getUsername()) || user.getEmail().equals(u.getEmail())) //if either the username or the email of passed in user is equal to the existing user
            {
                return false; //return false since the userrname or email is already taken
            }               
        }
        return true; //return true, meaning passed in user shares no email nor username with existing users
    }
    
    public UserProfile search(String strUsername) //searches by username: method accepts a string for the username of user that wants to be found
    {     
        for (UserProfile u : this.lstUsers) //loop through all current users and return user if user has the same id as one provided (parameter) for searching
        {                              //rather than using ID to access index of lstUsers, this allows for deletion of a user to not affect function
            if (strUsername.equals(u.getUsername())) //if UserProfile with same username is found, return found UserProfile
                return u; 
        }

        return null; //return null as no user was found with provided strUsername
    }
    
    
    public boolean setUser(UserProfile userProfile) //set the user of uspCurrentUserProfile, changing what user is selected
    {
        this.uspCurrentUserProfile = userProfile; //set uspCurrentUserProfile to passed in user
        
        if (this.uspCurrentUserProfile == null) //if uspCurrentUserProfile is null that means that passed in user does not exist
            return false; //return false to let caller know that userProfile was not set
        
        shtCurrentPostIndex = (short)(uspCurrentUserProfile.getLstPostID().size() - 1); //set the index of current post to amount of posts that the user has (uspCurrentUserProfile.getLstPostID().size()).  Remove 1 because index starts at 0
        return true; //return true meanign setting the user was sucessful 
    }
    
    
    //METHODS ASSOCIATED WITH POSTS
    public void createPost(UserProfile Poster, Content content) //metods creates a post
    {
        Post post = new Post(content, Poster.getUsername()); //create a post with passed in content and userrname of passed in poster
        lstPosts.add(post); //add post to list of posts
        Poster.addPost(post.getPostID()); //add the post ID of created post to the poster's list of post ID, essentialyl telling the Poster that this post, represented by ID, belongs to them 
    }
    
    public Post getPostOfUser(UserProfile uspPoster, byte bytIndex) //method returns the post of a passed in user at a passed in index
    {
        //UserProfiles have a list that contain the ID's of their posts
        //by accessing the ID at passed in index gives their nth post
        //the nth post relates to the nth post in SocialMediaManager's lstPosts by index 
        return lstPosts.get(uspPoster.getLstPostID().get(bytIndex)); 
    }
    
    public Post getCurrentPost() //gets the currently selected post
    {
        //the currently selected post is dependant on whether a user is selected or not, meaning whether the home page (all posts) are being viewed or only the selected user
        if (uspCurrentUserProfile == null) //if null that means no user is selected
            return this.lstPosts.get(shtCurrentPostIndex); //return the post at shtCurrentPostIndex 
        
        //return the post in lstPost that is associated with the postID in selected user's list of post IDs.  The selected post ID is found at index by shtCurrentPostIndex. 
        return this.lstPosts.get(uspCurrentUserProfile.getLstPostID().get(shtCurrentPostIndex));
    }
    
    public boolean incrementPostIndex(byte bytIncrement) //used to increment shtCurrentPostIndex, meaning changing selecting post to the earlier post in time, or the one before
    {
        this.shtCurrentPostIndex += bytIncrement; //increment shtCurrentPostIndex by passed in increment  
        
        if (uspCurrentUserProfile == null) //if the user profile is null, meaning no user is selected
        {
           if (this.shtCurrentPostIndex == this.lstPosts.size() || this.shtCurrentPostIndex == -1) //if the new shtCurrentPostIndex reaches
           {                                                                                        //beyond bounds of lstPosts (either less than 0 or greator than the size of lstPosts - 1)
               this.shtCurrentPostIndex -= bytIncrement; //reverse increment
               return false; //return false to tell caller that increment was not successful
           } 
        }
        else //a user has been selectted
        {
           if (this.shtCurrentPostIndex == this.uspCurrentUserProfile.getLstPostID().size() || this.shtCurrentPostIndex == -1) //if the new shtCurrentPostIndex reaches beyond bounds of userprofile's lstPostID (meaning shtCurrentPostIndex + 1
           {                                                                                                                    // exceeds that of the amount of posts that belong to UserProfile.  Or if shtCurrentPostIndex is less than 0
               this.shtCurrentPostIndex -= bytIncrement; //reverse increment
               return false; //return false to tell caller that increment was not successful
           } 
        }
        
        return true; //return true to tell caller that increment was successful
    }
    
    
    //METHODS ASSOCIATED WITH COMMMENTS
    public void addComment(UserProfile Commenter, Post post, String strComment) //method adds comment to post by passing in the UserProfile of the commenter, the post to be commented on, the the text information of desired comment
    {
        post.addCommentOnPost(Commenter.getUsername(), strComment);//call addCommentOnPost of passed in post with requried parameters   
    }
    
    
    //MORE METHODS
    public void reloadFeed() //reloads the feeds, meaning it deselects if a user is selected or not.  Sets the shtCurrentPostIndex to the latest post in lstPosts -> latest post in the timeline
    {
        this.shtCurrentPostIndex = (short)(lstPosts.size() - 1); 
        uspCurrentUserProfile = null; 
    }
        

    
    
}
