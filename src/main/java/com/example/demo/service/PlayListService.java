package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Playlist;

public interface PlayListService {

	 public void addPlaylist(Playlist playlist);

	public List<Playlist> fetchAllPlaylists();
		
}
