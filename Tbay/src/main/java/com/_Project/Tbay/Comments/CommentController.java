package com._Project.Tbay.Comments;

import com._Project.Tbay.Admin.AdminService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentservice;
    @Autowired
    private ListingService listingService;

    //GET all users
    @GetMapping("/all")
    public String getAllComments(Model model) {
        model.addAttribute("commentList", commentservice.getAllComments());
        model.addAttribute("title", "All Comments");
        return "comment-list"; //Like the table from hw
    }
}
