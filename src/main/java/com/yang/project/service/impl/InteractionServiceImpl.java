package com.yang.project.service.impl;

import com.yang.project.dao.InteractionDao;
import com.yang.project.model.CollectionZan;
import com.yang.project.model.Comment;
import com.yang.project.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionServiceImpl implements InteractionService {

    @Autowired
    InteractionDao interactionDao;

    @Override
    public Long comment(Comment comment) {
        return interactionDao.comment(comment);
    }

    @Override
    public Long deleteComment(String commentId) {
        return interactionDao.deleteComment(commentId);
    }

    @Override
    public Long collection(CollectionZan collection) {
        return interactionDao.collection(collection);
    }

    @Override
    public List<CollectionZan> getCollection(String userId) {
        return interactionDao.getCollection(userId);
    }
}
