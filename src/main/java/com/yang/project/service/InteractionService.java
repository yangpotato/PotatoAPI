package com.yang.project.service;

import com.yang.project.model.CollectionZan;
import com.yang.project.model.Comment;

public interface InteractionService {
    Long comment(Comment comment);

    Long deleteComment(String commentId);

    Long collection(CollectionZan collection);
}
