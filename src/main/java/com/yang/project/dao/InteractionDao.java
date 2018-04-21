package com.yang.project.dao;

import com.yang.project.model.CollectionZan;
import com.yang.project.model.Comment;
import org.apache.ibatis.annotations.Param;

public interface InteractionDao {

    Long comment(Comment comment);

    Long deleteComment(@Param("commentId") String commentId);

    Long collection(CollectionZan collection);
}
