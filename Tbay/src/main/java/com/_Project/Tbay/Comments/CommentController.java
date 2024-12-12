package com._Project.Tbay.Comments;

import com._Project.Tbay.Admin.AdminService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.Report.Report;
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
    @Autowired
    private AdminService adminService;

    //GET all users
    @GetMapping("/all/{adminId}")
    public String getAllComments(@PathVariable long adminId,Model model) {
        model.addAttribute("admin", adminService.getAdminById(adminId));
        model.addAttribute("commentList", commentservice.getAllComments());
        model.addAttribute("title", "All Comments");
        return "all-comments"; //Like the table from hw
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

    @GetMapping("/createComment/{posterId}")
    public String showCreateForm(@PathVariable long posterId, Model model){
        model.addAttribute("user", commentservice.getCommentByPosterId(posterId));
        model.addAttribute("title", posterId);
        return "comment-create";
    }

    @PostMapping("/newComment")
    public String addNewComment(Comment comment, @RequestParam("listingId") Long listingId) {
        commentservice.saveComment(comment);

        return "redirect:/Listing/" + listingId;
    }

}
