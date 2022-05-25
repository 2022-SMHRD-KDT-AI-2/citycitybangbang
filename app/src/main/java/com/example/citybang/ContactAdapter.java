package com.example.citybang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    Context context; // 현재 페이지 정보를 담고 있음
    int layout; // 항목 뷰, 템플릿, 아이템을 감싸고 있는 디자인
    List<ContactVO> data; // 아이템 클래스 정의!
    LayoutInflater layoutInflater; // xml의 view 객체들을 -> java 코드에서 접근할 수 있게 해주는 역할
    ViewHolder holder;

    // 생성자
    public ContactAdapter(Context context, int layout, List<ContactVO> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        // Context의 inflate기능을 추출해 지금 생성자 안에 있는 context변수에 부여!!
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        holder = new ViewHolder();

    }


    @Override
    public int getCount() {
        return data.size(); // data의 크기만큼 view가 호출됨
    }

    @Override
    public Object getItem(int position) {
        return data.get(position); // 사용자가 원하는 item의 정보를 position을 통해서 알려줌
    }

    @Override
    public long getItemId(int position) {
        return position; // 사용자가 원하는 item_id를 돌려주자. 내가 정해서!
    }



    // ★★★★★
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContactViewHolder holder = new ContactViewHolder();



        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_cardview2, null);

            // ViewHolder Pattern 사용하는 이유
            // findViewById() 메소드는
            // Resource에 접근해 id를 가져온다
            // 메모리적으로 과부하가 많이 되기 때문에
            // 단, 한 번만 findViewById()를 사용해 메모리를 절약

            // if문 안에 들어왔다는 건 : 최초 1회 getView()가 실행되고 있다.
            holder.repTvarea2 = convertView.findViewById(R.id.repTvarea2);
            holder.reportList2 = convertView.findViewById(R.id.reportList2);
            holder.reportTime = convertView.findViewById(R.id.reportTime);



            //findViewById()가 끝난 holder를 convertView에 Tag로 달아 놓자
            convertView.setTag(holder);

        } else{

            holder = (ContactViewHolder) convertView.getTag();

        }

        holder.repTvarea2.setText("발생 지역 : "+data.get(position).getArea());
        holder.reportList2.setText("발생 일자 : "+data.get(position).getDate());
        holder.reportTime.setText("발생 시간 : "+data.get(position).getTime());







//        // 만약, convertView가 생성되었다면
//        // 우리는 convertView.findViewById()를 통해
//        // Resource에 있는 id에 접근할 수 있다
//
//        // 각 아이템에 대한 View들을 초기화 해주자!!
//        ImageView img_contact = convertView.findViewById(R.id.img_contact);
//        TextView tv_name = convertView.findViewById(R.id.tv_name);
//        TextView tv_tel = convertView.findViewById(R.id.tv_tel);
//        Button btn_call = convertView.findViewById(R.id.btn_call);
//
//
//        // 각 View들에 대한 정보를 대입하자
//        img_contact.setImageResource(data.get(position).getImg_contact());
//        tv_name.setText(data.get(position).getName());
//        tv_tel.setText(data.get(position).getTel());

        return convertView;
    }

    // inner class
    // class 안에 class
    // 사용 용법은 보통 4가지
    // 그 중 한가지 방법이 익명클래스

    class ContactViewHolder{

        TextView repTvarea2, reportList2, reportTime, textView41, textView42, textView43;

    }



}
