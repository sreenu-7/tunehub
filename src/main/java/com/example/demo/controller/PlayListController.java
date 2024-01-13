package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.service.PlayListService;
import com.example.demo.service.SongService;

@Controller
public class PlayListController {
	@Autowired
	SongService songService;
	
	@Autowired
	PlayListService playlistservice;
	
	@GetMapping("/createPlayList")
	public String createPlayList(Model model) {
		List<Song> songList=songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createPlayList";
	}
	
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		//updating playlist tale
		playlistservice.addPlaylist(playlist);
		
		//updating song table
		List<Song> songList=playlist.getSong();
		for(Song s:songList) {
			s.getPalylists().add(playlist);
			songService.updateSong(s);
		}
		return "admin";
	}
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model) {
		List<Playlist> allPlaylists=playlistservice.fetchAllPlaylists();
		model.addAttribute("allPlaylists", allPlaylists);
		return "displayPlaylists";
	}
	
}
