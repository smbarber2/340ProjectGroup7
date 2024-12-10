package com._Project.Tbay.Comments;
import com._Project.Tbay.Admin.AdminRepository;
import com._Project.Tbay.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<User> getAllComments() {
        return commentRepository.findAll();
    }

    public User getCommentByPosterId(long posterId) {
        return commentRepository.findById(posterId).orElse(null);
    }

//    public void deleteCommentById(long userId) {
//        commentRepository.deleteById(userId);
//    }

//    public void banUser(long userId, Model model) {
//        Model existing = getUserById(userId);
//        existing.setBan(model.getBan());
//        userRepository.save(existing);
//    }

}
