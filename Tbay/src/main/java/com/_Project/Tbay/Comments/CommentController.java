package com._Project.Tbay.Comments;

import com._Project.Tbay.Admin.AdminService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
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

//
//    @GetMapping("/{listingId}")
//    public String getCommentByListingId(@PathVariable long listingId, Model model) {
//        model.addAttribute("listing", commentservice.getCommentByListingId(listingId));
//        model.addAttribute("title", listingId);
//        List<Comment> commentForListing = new ArrayList<>();
//
//        List<Comment> commentList = commentservice.getAllComments();
//
//        for (Comment comment : commentList) {
//            if (comment.getListingId() == listingId) {
//                commentForListing.add(comment);
//            }
//        }
//        model.addAttribute("commentList", commentForListing);
//
//        return "indivListing";
//    }


    @GetMapping("/{posterId}")
    public String getCommentByPosterId(@PathVariable long posterId, Model model) {
        model.addAttribute("poster", commentservice.getCommentByPosterId(posterId));
        model.addAttribute("title", posterId);
        return "";
    }
}
