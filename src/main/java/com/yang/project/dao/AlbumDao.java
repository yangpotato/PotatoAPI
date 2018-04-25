package com.yang.project.dao;

import com.yang.project.model.Album;
import com.yang.project.model.Video;
import org.apache.ibatis.annotations.Param;

public interface AlbumDao {
    Long uploadAlbum(Album album);

    Long uploadVideo(Video video);

    Album selectAlbum(@Param("id") Integer id);
}
