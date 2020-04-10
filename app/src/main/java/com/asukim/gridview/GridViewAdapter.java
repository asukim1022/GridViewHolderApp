package com.asukim.gridview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *@CustomAdapter
 *@brief MainActivity에있는 GridView을 관리, listView에 ListData에서 저장한 데이터 출력하기
 *@date 2016.02.18
 */
public class GridViewAdapter extends ArrayAdapter<ImageItem> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ImageItem> data = new ArrayList<ImageItem>();


    /*
    context : 두번째 인자인 viewId의 사용에 필요한 context 객체이다.
    viewId : 뷰 객체를 생성하기 위해 ArrayAdapter가 사용할 레이아웃을 나타낸다.
    listDataArrayList : 객체들이 저장된 데이터
    */
    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<ImageItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    /** @brief getView
     *  @param  position : listView가 찾을 리스트 항목의 위치
     *  @dtails getView의 구현 코드 내부에서 어댑터는 배열의 올바른 항목에 대한 뷰객체를 생성하고 그 뷰 객체를 listView에 반환한다.
     *
     *  ViewHolder
     *  getView() 에서 넘어오는 파라메터인 converView 사용하지 않고 매번 새로운 뷰를 inflate하면 메모리 낭비를 하게 된다.
     *  그래서 보통 convertView가 null 일때만 inflate 되도록한다.
     *  이때 항상 아이템의 값을 setText하기 위해서 findViewById 를 통해 XML 리소스에 접근하게 되고 이는 성능저하를 나타내는 원인이 된다.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        //convertView가 null 일 때 inflate 하고, ViewHolder를 생성해 각요소를 findViewById 를 통해 저장
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) view.findViewById(R.id.text);
            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        } else {

            //ViewHolder를 getTag로 불러와서 재사용하게 된다.
            //이렇게하면 xml 리소스에 findeViewById로 직접 접근하지 않아도 된다다.
           holder = (ViewHolder) view.getTag();
        }

        ImageItem item = data.get(position);
        holder.imageTitle.setText(item.getTitle());
        holder.image.setImageBitmap(item.getImage());
        return view;
    }



    /*
    ViewHolder
    ListView / RecyclerView는 inflate를 최소화 하기 위해서 뷰를 재활용 하는데,
    이 때 각 뷰의 내용을 업데이트 하기 위해 findViewById 를 매번 호출한다.
    이로 인해 성능저하가 일어남에 따라 ItemView의 각 요소를 바로 엑세스 할 수 있도록 저장해두고 사용하기 위한 객체이다.

    static을 붙히는 이유는 상위클래스의 멤버변수나 객체를 사용하지 않겠라는 것을 명시적으로 표시
    */
    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}