package com._Project.Tbay.Comments;
import com._Project.Tbay.Admin.AdminRepository;
import com._Project.Tbay.Report.Report;
import com._Project.Tbay.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentByPosterId(long posterId) {
        return commentRepository.findById(posterId).orElse(null);
    }

    public Comment getCommentByListingId(long listingId) {
        return commentRepository.findById(listingId).orElse(null);
    }

    public void addNewComment(Comment comment){
        commentRepository.save(comment);
    }
    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }


//    public Listing getListingById(long listingId) {
//        return listingRepository.findById(listingId).orElse(null);
//    }
}
