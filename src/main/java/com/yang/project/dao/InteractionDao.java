package com.yang.project.dao;

import com.yang.project.model.CollectionZan;
import com.yang.project.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InteractionDao {

    Long comment(Comment comment);

    Long deleteComment(@Param("commentId") String commentId);

    Long collection(CollectionZan collection);

    List<CollectionZan> getCollection(@Param("userId") String userId);
}
