package com.example.moviesearch;

import java.io.File;
import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewMovies extends ListActivity implements OnItemClickListener {
	ArrayList<Movie> movies;
	Bookmarks bookmark;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		Bundle extras = getIntent().getExtras();
		ArrayList<Movie> movielist = extras.getParcelableArrayList("movielist");
		movies = movielist;

		MovieAdapter adapter = new MovieAdapter(this, R.layout.viewmovie,
				movielist, maxMemory);
		setListAdapter(adapter);
		ListView lv = getListView();
		lv.setOnItemClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		bookmark = new Bookmarks(getFilesDir());
		bookmark.addBookmark(movies.get(arg2));
		Toast.makeText(arg0.getContext(), "Added bookmark", 2).show();

	}
	

}