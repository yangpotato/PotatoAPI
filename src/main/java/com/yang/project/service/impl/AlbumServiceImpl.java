package com.yang.project.service.impl;

import com.yang.project.dao.AlbumDao;
import com.yang.project.model.Album;
import com.yang.project.model.Video;
import com.yang.project.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumDao albumDao;

    @Override
    public Long uploadAlbum(Album album) {
        return albumDao.uploadAlbum(album);
    }

    @Override
    public Long uploadVideo(Video video) {
        return albumDao.uploadVideo(video);
    }
}
