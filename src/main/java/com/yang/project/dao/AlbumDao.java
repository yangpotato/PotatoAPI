package com.yang.project.dao;

import com.yang.project.model.Album;
import com.yang.project.model.Video;

public interface AlbumDao {
    Long uploadAlbum(Album album);

    Long uploadVideo(Video video);
}
