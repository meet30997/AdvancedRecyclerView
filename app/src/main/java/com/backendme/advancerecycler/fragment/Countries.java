package com.backendme.advancerecycler.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.backendme.advancerecycler.DashboardItem;
import com.backendme.advancerecycler.DashboardAdapter;
import com.backendme.advancerecycler.MyApplication;
import com.backendme.advancerecycler.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.backendme.advancerecycler.R.string.navigation_drawer_close;
import static com.backendme.advancerecycler.R.string.navigation_drawer_open;
import static com.thefinestartist.utils.content.ContextUtil.getSystemService;


public class Countries extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public static final int ITEMS_PER_AD = 8;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final String URL = "Your URL";
    private static final String AD_UNIT_ID = "Your AD Unit ID";
    private RecyclerView recyclerView;
    private List<Object> recyclerViewItemswithoutad = new ArrayList<>();
    private SearchView searchView;
    private List<Object> recyclerViewItems = new ArrayList<>();




    public Countries() {

    }

    public static Countries newInstance(String param1, String param2) {
        Countries fragment = new Countries();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_mainnosearch, menu);
       super.onCreateOptionsMenu(menu, inflater);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                filter(query);
                return false;
            }

        });

    }


    void filter(String text){
        List<Object> temp = new ArrayList();
        for(int pos = 0; pos<recyclerViewItems.size(); pos++){

            if(!(pos% ITEMS_PER_AD == 0)){
                DashboardItem pitem = (DashboardItem) recyclerViewItems.get(pos);
                if(pitem.getName().toLowerCase().contains(text.toLowerCase())){
                    temp.add(pitem);

                    }
            }

        }
        if(recyclerViewItemswithoutad.size()==temp.size()){
            RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = new DashboardAdapter(getContext(),
                    recyclerViewItems, "first");
            recyclerView.setAdapter(adapter);
        }else {
            RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = new DashboardAdapter(getContext(),
                    temp, "filter");
            recyclerView.setAdapter(adapter);
            Log.i("Filte","Adapter Set");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = RootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, navigation_drawer_open, navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mSwipeRefreshLayout = (SwipeRefreshLayout) RootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.black,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        // toolbar fancy stuff

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Countries");

        fetchContacts();
        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = new DashboardAdapter(getActivity(),
                recyclerViewItems, "first");
        recyclerView.setAdapter(adapter);

        return RootView;

    }
    private void addBannerAds() {
        // Loop through the items array and place a new banner ad in every ith position in
        // the items List.
        for (int i = 0; i <= recyclerViewItems.size(); i += ITEMS_PER_AD) {
            final AdView adView = new AdView(getActivity());
            adView.setAdSize(AdSize.SMART_BANNER);
            adView.setAdUnitId(AD_UNIT_ID);
            recyclerViewItems.add(i, adView);
        }
    }
    private void loadBannerAds() {
        // Load the first banner ad in the items list (subsequent ads will be loaded automatically
        // in sequence).
        loadBannerAd(0);
    }

    private void loadBannerAd(final int index) {

        if (index >= recyclerViewItems.size()) {
            return;
        }

        Object item = recyclerViewItems.get(index);
        if (!(item instanceof AdView)) {
            throw new ClassCastException("Expected item at index" + index +" to be a banner ad"
                    + " ad.");
        }

        final AdView adView = (AdView) item;

        // Set an AdListener on the AdView to wait for the previous banner ad
        // to finish loading before loading the next ad in the items list.
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // The previous banner ad loaded successfully, call this method again to
                // load the next ad in the items list.
                loadBannerAd(index + ITEMS_PER_AD);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // The previous banner ad failed to load. Call this method again to load
                // the next ad in the items list.
                loadBannerAd(index + ITEMS_PER_AD);
            }
        });

        // Load the banner ad.
        adView.loadAd(new AdRequest.Builder().build());
    }


    @Override
    public void onRefresh() {
        fetchContacts();
    }

    private void fetchContacts() {
        mSwipeRefreshLayout.setRefreshing(true);
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getContext(), "Couldn't fetch the contacts! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        addMenuItemsFromJson(response.toString());


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Toast.makeText(getContext(), "Some Error occured.. ", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(getActivity()).getCache().clear();
        Volley.newRequestQueue(getActivity()).getCache().remove(URL);
        MyApplication.getInstance().addToRequestQueue(request);


    }

    private void addMenuItemsFromJson(String jsonDataString) {
        try {
            recyclerViewItems.clear();
            //String jsonDataString = readJsonDataFromFile();
            JSONArray menuItemsJsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < menuItemsJsonArray.length(); ++i) {
                JSONObject menuItemObject = menuItemsJsonArray.getJSONObject(i);

                String menuItemName = menuItemObject.getString("name");
                String menuItemPrice = menuItemObject.getString("type");
                String menuItemImageName = menuItemObject.getString("image");

                DashboardItem dashboardItem = new DashboardItem(menuItemName, menuItemPrice, menuItemImageName);
                recyclerViewItems.add(dashboardItem);
                recyclerViewItemswithoutad.add(dashboardItem);


            }
        } catch (JSONException exception) {
            Log.e(String.valueOf(getContext()), "Unable to parse JSON file.", exception);
        }

        final AdView adView = new AdView(getActivity());
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(AD_UNIT_ID);
        addBannerAds();
        loadBannerAds();
        mSwipeRefreshLayout.setRefreshing(false);
        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = new DashboardAdapter(getActivity(),
                recyclerViewItems,"first");
        recyclerView.setAdapter(adapter);


        }


}
