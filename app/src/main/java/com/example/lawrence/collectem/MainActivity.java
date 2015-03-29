package com.example.lawrence.collectem;

        import android.app.FragmentManager;
        import android.app.ListFragment;
        import android.app.SearchManager;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.SearchView;

        import java.io.File;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;


public class MainActivity extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        handleIntent(getIntent());
        FragmentManager fm = getFragmentManager();

        if (fm.findFragmentById(android.R.id.content) == null) {
            MyFragment fragment = new MyFragment();
            fm.beginTransaction().add(android.R.id.content, fragment).commit();
        }
        //MyFragment fragment = new MyFragment();
        //getSupportFragmentManager().beginTransaction().replace(android.R.id.pic_frag, fragment).commit();
    }

    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        //SearchManager searchManager =
        //        (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //SearchView searchView =
        //        (SearchView) menu.findItem(R.id.search).getActionView();
        //searchView.setSearchableInfo(
        //      searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        // When Add Album action menu item is clicked
        //if (id == R.id.addalbum) {
            // Create Intent for Adding Activity
        //    Intent addAlbIntent = new Intent(this, AddPhoto.class);
            // Start Add Album Activitys
        //    startActivity(addAlbIntent);
        //    return true;
        //}
        return super.onOptionsItemSelected(item);
    }

    public void addAlbum(final View view) {
        Intent intent = new Intent(MainActivity.this, AddPhoto.class);
        startActivity(intent);
    }

    public void albumDetails(final View view) {
        Intent intent = new Intent(MainActivity.this, PhotoDetails.class);
        startActivity(intent);
    }

    public static class MyFragment extends ListFragment {
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            PhotoDataSource photosource = new PhotoDataSource(inflater.getContext());
            try {
                photosource.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //use the SimpleCursorAdapter to show the elements in a ListView
            //List<String> values = photosource.getPaths();
            //File imgFile = new File(values.get(1));
            //List<Bitmap> bitmap = new ArrayList<Bitmap>();
            //Bitmap bitmap1;


            //for (int i = 0; i < values.size(); i++) {
            //    bitmap1 = BitmapFactory.decodeFile(values.get(i));
            //    bitmap.add(bitmap1);
            //}

            //ArrayAdapter<Bitmap> adapter = new ArrayAdapter<Bitmap>(inflater.getContext(),
            //        android.R.layout.simple_list_item_1, bitmap);
            //setListAdapter(adapter);

            return super.onCreateView(inflater, container, savedInstanceState);
        }

        public void onListItemClick(ListView listView, View view, int position, long id) {
        }
    }
}



