
/**
 * Test Class is used to test social media platform system by creating an instance of the manager class and the gui class
 * The test class hardcodes the objects and their attributes of the social media system, building the system.  Vizualiation of the system is effectuated through the gui system.  
 *
 * @author (Huy Nguyen)
 * @version (2023-1016)
 */
public class HuyNguyenTestClass
{
    public static void main(String[] args)
    {
        SocialMediaManager socialMediaManager = new SocialMediaManager();
        GraphicsInterface graphicsInterface = new GraphicsInterface(socialMediaManager);

        
        //Test class has been altered as contructor for UserProfile class no longer has a parameter for UserID.  This is rather achieved through a static
        //variable that holds num instances of UserProfile.  UserID is taken from this value, thus different for each UserID.
        //Benefits include lack of need to hardcode UserID value and each UserID is guaranteed to be different 
        UserProfile user1 = new UserProfile("Jack Skellington", "JackS@example.com");
        UserProfile user2 = new UserProfile("Sally", "Sally@example.com");
        UserProfile user3 = new UserProfile("Oogie Boogie", "OogieB@example.com"); 
        
        //Test Class has been altered so that content ID is no longer a paramter.  This is to ensure no content ID has the same value, achieved
        //through a static variable in Content class that keeps track of number of Content instances -> each new Content is the number of contents at time of creation
        Content textContent = new TextContent("The Nightmare before Christmas is the best movie!.");
        Content textContent2 = new TextContent("Best Halloween movie to watch?.");
        Content imageContent = new ImageContent("The Nightmare before Christmas poster", "jpg");
        Content videoContent = new VideoContent("Click to watch The Nightmare before Christmas Movie", "video_url.mp4");
        Content videoContent2 = new VideoContent("Click to watch Saunders Fright Fest videos", "frightfest.mp4");
        
        //test class has been outputted to print out the returned string of the createUserProfile
        //returned string outputs if user was created
        System.out.println(socialMediaManager.createUserProfile(user1));
        System.out.println(socialMediaManager.createUserProfile(user2));
        System.out.println(socialMediaManager.createUserProfile(user3));
    
    
        socialMediaManager.createPost(user1, textContent);
        socialMediaManager.createPost(user3, textContent2);
        socialMediaManager.createPost(user2, imageContent);
        socialMediaManager.createPost(user1, videoContent);
        socialMediaManager.createPost(user3, videoContent2);
        
        
        //original test class provided by teacher had parameter -> "socialMediaManager.listPostsByUser(user2).get(0)".  This returns a post. getPostOfUser method has been created, essentially doing the 
        //same thing: accessing the post of the users and returning the post associated with an index
        socialMediaManager.addComment(user2, socialMediaManager.getPostOfUser(user1, (byte)0), "Love that Poster!");
        socialMediaManager.addComment(user3, socialMediaManager.getPostOfUser(user1, (byte)0), "Best Movie to watch. Thanks!");
        socialMediaManager.addComment(user1, socialMediaManager.getPostOfUser(user2, (byte)0), "Great image! Love that movie");
        socialMediaManager.addComment(user2, socialMediaManager.getPostOfUser(user3, (byte)0), "Friday the 13th is the best one to watch!");
        socialMediaManager.addComment(user2, socialMediaManager.getPostOfUser(user3, (byte)0), "The best halloween movie is definitely \"Halloween\"");
        
        //adding extra comments to show scroll pane function of gui
        for (byte b = 0; b < 50; b++)
        {
            socialMediaManager.addComment(user2, socialMediaManager.getPostOfUser(user1, (byte)0), "Comment " + b);
        }
        
        graphicsInterface.launch(); //call launch method in graphicsInterface to show gui 
        
    }
}
