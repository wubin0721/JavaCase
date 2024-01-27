package com.example.recycleview.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.Bean.ItemBean;
import com.example.recycleview.R;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_LODER_MORE = 1;

    protected final List<ItemBean> mData;
    private OnItemClickListener onItemClickListener;

    private OnReFleshListener onReFleshListener;


    public ListViewAdapter(List<ItemBean> mData) {
        this.mData = mData;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //绑定holder,设置数据
        if(getItemViewType(position)==TYPE_NORMAL && holder instanceof  InnerHoder){
            ((InnerHoder)holder).setData(mData.get(position),position);
        }else{
            ((LoaderMoreHolder)holder).update(LoaderMoreHolder.LOADER_STATE_LOADING);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount() - 1){
            //最后一个需要加载
            return TYPE_LODER_MORE;
        }

        return TYPE_NORMAL;
    }

    protected View getSubview(ViewGroup parent, int viewType) {
        View view;
        if( viewType == TYPE_NORMAL){
            view = View.inflate(parent.getContext(), R.layout.item_list_view,null);
        }else{
            view = View.inflate(parent.getContext(), R.layout.item_list_loader,null);
        }
        return view;
    }

    //    创建条目
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getSubview(parent,viewType);
        if(viewType == TYPE_NORMAL){
            //传进去的 View是条目的界面
            return new InnerHoder(view);
        }else {
            return new LoaderMoreHolder(view);
        }

    }



    public class InnerHoder extends RecyclerView.ViewHolder{

        private ImageView mIcon;
        private TextView mtitle;
        private int position;

        public InnerHoder(View itemView) {
            super(itemView);
            //找到条目的控件
            mIcon = (ImageView) itemView.findViewById(R.id.item_image);
            mtitle = (TextView) itemView.findViewById(R.id.item_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }

        public void setData(ItemBean itemBean,int position){

            this.position = position;
            //设置数据
            mIcon.setImageResource(itemBean.icon);
            mtitle.setText(itemBean.title);
        }
    }

   public interface OnReFleshListener{
        void UpPullReFlesh(ListViewAdapter.LoaderMoreHolder loaderMoreHolder);
   }

    public void setOnRefleshListener(OnReFleshListener listener){
            this.onReFleshListener = listener;
    }


//    返回条目个数
    @Override
    public int getItemCount() {
        if(mData!=null){
            return mData.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        //设置监听相当于设置一个回调的接口
        this.onItemClickListener  = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    //上拉加载
    public class LoaderMoreHolder extends RecyclerView.ViewHolder{

        public static final int LOADER_STATE_LOADING = 0;
        public static final int LOADER_STATE_RELOAD = 1;
        public static final int LOADER_STATE_NORMAL = 2;

        private LinearLayout loading;
        private TextView     reload;

        public LoaderMoreHolder(View itemView) {
            super(itemView);

            loading = (LinearLayout) itemView.findViewById(R.id.loading);
            reload = (TextView) itemView.findViewById(R.id.reloading);

            reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //触发加载数据
                    update(LOADER_STATE_LOADING);
                }
            });
        }

        public void update(int state){
            loading.setVisibility(View.GONE);
            reload.setVisibility(View.GONE);

            switch (state){
                case LOADER_STATE_LOADING:
                    loading.setVisibility(View.VISIBLE);
                    //触发加载数据
                    startLoadMore();
                    break;
                case LOADER_STATE_RELOAD:
                    reload.setVisibility(View.VISIBLE);
                    break;
                case LOADER_STATE_NORMAL:
                    loading.setVisibility(View.GONE);
                    reload.setVisibility(View.GONE);
                    break;
            }
        }

        private void startLoadMore(){
            if(onReFleshListener != null){
                onReFleshListener.UpPullReFlesh(this);
            }
        }
    }
}
