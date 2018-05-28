package com.backendme.advancerecycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdView;

import java.util.List;



public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int MENU_ITEM_VIEW_TYPE = 0;
    private static final int BANNER_AD_VIEW_TYPE = 1;
    private Context context;
    private List<Object> recyclerViewItems;
    private String type;
    public DashboardAdapter(Context context, List<Object> recyclerViewItems,String type) {
        this.context = context;
        this.recyclerViewItems = recyclerViewItems;
        this.type = type;
    }




    public class MenuItemViewHolder extends RecyclerView.ViewHolder {
        private TextView menuItemName;
        private TextView menuItemPrice;
        private ImageView menuItemImage;

        MenuItemViewHolder(final View view) {

            super(view);
            context = view.getContext();
            menuItemImage = view.findViewById(R.id.thumbnail);
            menuItemName = view.findViewById(R.id.name);
            menuItemPrice = view.findViewById(R.id.phone);




            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DashboardItem dashboardItem = (DashboardItem) recyclerViewItems.get(getPosition());
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("message", dashboardItem.getName());
                    intent.putExtra("url", dashboardItem.getImageName());
                    context.startActivity(intent);
                }
            });
        }
    }


    public class AdViewHolder extends RecyclerView.ViewHolder {

        AdViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }


    @Override
    public int getItemViewType(int position) {
        if(type.equals("first")){
            return (position % DashboardActivity.ITEMS_PER_AD == 0) ? BANNER_AD_VIEW_TYPE
                    : MENU_ITEM_VIEW_TYPE;
        }else{
            return  MENU_ITEM_VIEW_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                View menuItemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.user_row_item, viewGroup, false);
                return new MenuItemViewHolder(menuItemLayoutView);
            case BANNER_AD_VIEW_TYPE:
                // fall through
            default:
                View bannerLayoutView = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.banner_ad_container,
                        viewGroup, false);
                return new AdViewHolder(bannerLayoutView);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;
                DashboardItem dashboardItem = (DashboardItem) recyclerViewItems.get(position);

                Glide.with(context)
                        .load(dashboardItem.getImageName())
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(600, 400)
                        .into(menuItemHolder.menuItemImage);
                menuItemHolder.menuItemName.setText(dashboardItem.getName());
                menuItemHolder.menuItemPrice.setText(dashboardItem.getPrice());


                break;
            case BANNER_AD_VIEW_TYPE:
                // fall through
            default:
                AdViewHolder bannerHolder = (AdViewHolder) holder;
                AdView adView = (AdView) recyclerViewItems.get(position);
                ViewGroup adCardView = (ViewGroup) bannerHolder.itemView;
                if (adCardView.getChildCount() > 0) {
                    adCardView.removeAllViews();
                }
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }adCardView.addView(adView);

        }
    }

}
