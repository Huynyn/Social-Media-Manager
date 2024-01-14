

/**
 * Class extends JFrame in order to create a GUI system for Social Media System.  Important functionalites include reloading the page, advancing and going back a post, searching for a user based on username
 * and showing the selected post and its comments
 *
 * @author (Huy Nguyen)
 * @version (2023-10-16)
 */

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javafx.scene.control.Button;
import javax.swing.JPanel;
import javax.swing.JOptionPane; 

public class GraphicsInterface extends JFrame
{
    //INSTANCE VARIABLES
    private SocialMediaManager socialMediaManager = new SocialMediaManager(); 
    private GroupLayout layout; //Group layout has been chosen to achieve systematic group function of this GUI
    //components of Jframe
    private JTextField SearchField = new JTextField("Search by username"); //User can enter text here in order to search a user with inputted string of text
    //buttons
    private JButton btnSearch; //when button is clicked, search for user using string of text in SearchField 
    private JButton btnHome; //when button is clicked, go to home page of social media platform (feed)
    private JButton btnBack; //when clicked, go back a post
    private JButton btnForward; //when clicked advance a post
    //display of information
    private JTextArea txtPost; //text box that shows the content of a post
    private JTextArea txtComments; //text box that shows the content of comments of currently displayed post
    
    //scroll pane allows the text areas for post and comments to have scroll bars to allow user to see text that exceeds the given space
    //the parameters of JScrollPane is the client of the scroll pane, meaning the component that will gain scroll bars when needed
    private JScrollPane scpPost; 
    private JScrollPane scpComments; 


    
    public GraphicsInterface(SocialMediaManager socialMediaManager)
    {
       //CREATE LAYOUT FOR JFRAME 
       this.layout = new GroupLayout(this.getContentPane()); //parameter of group layout is the contentpane that layout will apply to
       
       //SET REFERENCE FOR SOCIAL MEDIA MANAGER FROM PARAMETER
       this.socialMediaManager = socialMediaManager; //set socialMediaManager to passed in parameter of constructor
       
       //CREATE OBJECTS FOR INSTANCE COMPONENTS
       this.SearchField = new JTextField("Search by username"); //User can enter text here in order to search a user with inputted string of text
       //buttons
       this.btnSearch = new JButton("Search"); //sets text of button to search
       this.btnHome = new JButton("Home"); //sets text of button to home
       this.btnBack = new JButton("Back"); //sets text of button to back
       this.btnForward = new JButton("Forward"); //sets text of button to forward
       //display of information
       this.txtPost = new JTextArea(); 
       this.txtComments = new JTextArea(); 
        
        //scroll pane allows the text areas for post and comments to have scroll bars to allow user to see text that exceeds the given space
        //the parameters of JScrollPane is the client of the scroll pane, meaning the component that will gain scroll bars when needed
       this.scpPost = new JScrollPane(txtPost); 
       this.scpComments = new JScrollPane(txtComments); 
       
       //SET IMPORTANT ATTRIBUTES OF COMPONENTS
       txtPost.setLineWrap(true);  //makes it so that if text in post/comments boxes exceed horizontal length given, text wraps downwards
       txtComments.setLineWrap(true);
       
       txtPost.setEditable(false); //makes it so you cannot edit the text in post/comments boxes
       txtComments.setEditable(false);
       
       //ADD ACTIVE LISTENERS TO BUTTONS to their relevant methods 
       btnBack.addActionListener(e -> BtnBackPressed());
       btnForward.addActionListener(e -> btnForwardPressed()); 
       btnHome.addActionListener(e -> Reload()); 
       btnSearch.addActionListener(e -> BtnSearchPressed(SearchField.getText()));  //BtnSearchPressed has an parameter for serachQuery, getting the text from SearchField textfield
       
       
       //SET ATTRIBUTES OF GROUPLAYOUT
       layout.setAutoCreateGaps(true);  //when set to true, automatically add gaps between components
       layout.setAutoCreateContainerGaps(true); //when set to true, automatically adds gaps between components and the container (in this case: jframe)
      
       
       //ADD COMPONENTS TO GROUPLAYOUT 
       //set components of horizontalGroup of layout
       layout.setHorizontalGroup(layout.createSequentialGroup()
           .addGroup(layout.createParallelGroup() //create and add parallel group
               .addComponent(btnHome) //add components to parallel group
               .addComponent(btnBack))
           .addGroup(layout.createParallelGroup() //create and add parallel group
               .addComponent(SearchField)//add components to parallel group
               .addComponent(scpPost)
               .addComponent(scpComments))
          .addGroup(layout.createParallelGroup() //create and add parallel group
               .addComponent(btnSearch)  //add components to parallel group
               .addComponent(btnForward)));
       
       //set components of verticalGroup of layout
       layout.setVerticalGroup(layout.createSequentialGroup()
           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //create and add parallel group, Alignment.BASELINE -> aligns components in parallel group to be aligned in a way so that their text is aligned on the same line
               .addComponent(btnHome) //add components to parallel group
               .addComponent(SearchField)
               .addComponent(btnSearch))
           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //create and add parallel group
               .addComponent(btnBack) //add components to parallel group
               .addComponent(scpPost)
               .addComponent(btnForward))
           .addComponent(scpComments)); //add scroll pane for comments to vertical group
               
                          
       
       
       //SET ATTRIBUTES OF JFRAME
       this.setTitle("Social Media Platform"); //set title
       this.setLayout(layout); //set layout to grouplayout created above
       this.setSize(600,600); //set size
       this.setResizable(false);  //make is to that the user cannot resize the jframe
       
;
    }
    
    //method launches gui
    public void launch()
    {
        Reload(); //on launch of program reload, set the feed to that of the home page using BtnHomePressed()
        this.setVisible(true);  //set JFrame visibility to true
    }
    
    public void BtnSearchPressed(String strSearchQuery)
    {
        if (socialMediaManager.setUser(socialMediaManager.search(strSearchQuery))) //if setUser was successful (return value of true). socialMediaManager.search(strSearchQuery) searches
        {                                                                           // for a user given the username fromm method paramter.  returns user or null depending on search success
            JOptionPane.showMessageDialog(null, "User found"); //output that user was found
            setPostAndComment(); //update currently displayed post and comments
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "User not found"); ////output that user was not found
        }
    }
    
    public void BtnBackPressed() //when button back is pressed
    {
        if (socialMediaManager.incrementPostIndex((byte)-1)) //attempt to increment the post index of displayed post by -1.  if successful, incrementPostIndex() will return true
        {
            setPostAndComment(); //update currently displayed post and comments as selected post has been changed
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Earliest Post Already Reached"); //output that user can't go back anymore
        }
    }

    public void btnForwardPressed() //when button forward is pressed
    {
        if (socialMediaManager.incrementPostIndex((byte)1)) //attempt to increment the post index of displayed post by 1.  if successful, incrementPostIndex() will return true
        {
            setPostAndComment();//update currently displayed post and comments as selected post has been changed
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Latest Post Already Reached");//output that user can't go forward anymore
        }
    }
    
    public void Reload() //when home button is pressed or to simply reload the page by calling reloadFeed() method in socialMediaManager
    {
        socialMediaManager.reloadFeed(); 
        setPostAndComment(); //update currently displayed post and comments as selected post
    }
    
    public void setPostAndComment() //set currently displayed post and comments as selected post
    {
        this.txtPost.setText(socialMediaManager.getCurrentPost().toString()); //set txtPost's text to current post using socialMediaManager to access post and then use post's toString method
        this.txtComments.setText(socialMediaManager.getCurrentPost().commentsToString()); //set txtComments's text to current post's comment using socialMediaManager to access post and then use post's commentsToString method
    }
    


}
