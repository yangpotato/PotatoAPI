package com.yang.project.service;

import com.yang.project.model.Album;
import com.yang.project.model.Video;

public interface AlbumService {
    Long uploadAlbum(Album album);

    Long uploadVideo(Video video);

    Album selectAlbum(Integer id);
}
