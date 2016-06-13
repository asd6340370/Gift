package com.example.dllo.gift.bmob;

import android.content.Context;
import android.content.Intent;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.dllo.gift.LoginActivity;
import com.example.dllo.gift.bmob.UserBmobBean;
import com.example.dllo.gift.details.DetailsPurchaseActivity;
import com.example.dllo.gift.details.DetailsRaiderActivtiy;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/6/13.
 */
public class BmobData {

//  public class CollectCheckBoxTool {
    private BmobQuery<UserBmobBean> query = new BmobQuery<>();
    private Context context;
    private UserBmobBean userBmobBean;


    public BmobData(Context context) {
        this.context = context;
    }

    public void queryIsLike(String urlId, final CheckBox checkBox,String userName) {
        query.addWhereEqualTo("userName",userName);
        query.addWhereEqualTo("id", urlId);
        query.findObjects(context, new FindListener<UserBmobBean>() {
            @Override
            public void onSuccess(List<UserBmobBean> list) {
                if (list.size() == 0) {
                    checkBox.setChecked(false);
                } else {
                    checkBox.setChecked(true);
                }
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(context, "查询失败" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public void addLike(){
//        userBmobBean = new UserBmobBean();
//        userBmobBean.setId(getUserBmobBean.getId());
//        userBmobBean.setImgUrl(getUserBmobBean.getImgUrl());
//        userBmobBean.setTitleName(getUserBmobBean.getTitleName());
//        userBmobBean.setKey(getUserBmobBean.getKey());
//        userBmobBean.setUserName(BmobUser.getCurrentUser(this).getUsername());
//        userBmobBean.setPurchaseUrl(getUserBmobBean.getPurchaseUrl());
//        userBmobBean.setLikeCount(getUserBmobBean.getLikeCount());
//        userBmobBean.save(this, new SaveListener() {
//            @Override
//            public void onSuccess() {
//                Toast.makeText(DetailsPurchaseActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onFailure(int i, String s) {
//                Toast.makeText(DetailsPurchaseActivity.this, "收藏失败", Toast.LENGTH_SHORT).show();
//                checkBox.setChecked(false);
//            }
//        });
//    }


    public void cancleLike(String urlId,String userName) {

        BmobQuery<UserBmobBean> query = new BmobQuery<>();
        //条件查询
        query.addWhereEqualTo("userName",userName);
        query.addWhereEqualTo("id", urlId);
        //查询到对应id的数据库内容
        query.findObjects(context, new FindListener<UserBmobBean>() {
            @Override
            public void onSuccess(List<UserBmobBean> list) {

                //遍历这个List
                for (UserBmobBean bmobCollectBean : list) {
                    //进行删除
                    bmobCollectBean.delete(context, new DeleteListener() {
                        @Override
                        public void onSuccess() {
                            //删除成功
                            Toast.makeText(context, "取消喜欢成功", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
    public void CheckBoxSelectedSetData(final CheckBox checkBox , String urlId,UserBmobBean getUserBmobBean
    ,String userName) {
        BmobUser bmobUser = BmobUser.getCurrentUser(context);

        if (bmobUser == null) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            checkBox.setChecked(false);
        }else if (checkBox.isChecked()){
            userBmobBean = new UserBmobBean();
            userBmobBean.setId(getUserBmobBean.getId());
            userBmobBean.setImgUrl(getUserBmobBean.getImgUrl());
            userBmobBean.setTitleName(getUserBmobBean.getTitleName());
            userBmobBean.setKey(getUserBmobBean.getKey());
            userBmobBean.setPurchaseUrl(getUserBmobBean.getPurchaseUrl());
            userBmobBean.setLikeCount(getUserBmobBean.getLikeCount());
            userBmobBean.setUserName(userName);
            userBmobBean.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
                    checkBox.setChecked(false);
                }
            });
        }else {
            Toast.makeText(context, "取消喜欢成功", Toast.LENGTH_SHORT).show();
            BmobQuery<UserBmobBean> query = new BmobQuery<>();
            //条件查询
            query.addWhereEqualTo("id", urlId);
            //查询到对应id的数据库内容
            query.findObjects(context, new FindListener<UserBmobBean>() {
                @Override
                public void onSuccess(List<UserBmobBean> list) {

                    //遍历这个List
                    for (UserBmobBean bmobCollectBean : list) {
                        //进行删除
                        bmobCollectBean.delete(context, new DeleteListener() {
                            @Override
                            public void onSuccess() {
                                //删除成功
                            }
                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onError(int i, String s) {

                }
            });

        }
    }

    List<String> ids = new ArrayList<>();

    public void queryAllLike(final BaseAdapter baseAdapter){
        if(BmobUser.getCurrentUser(context)!= null) {
            query.findObjects(context, new FindListener<UserBmobBean>() {
                @Override
                public void onSuccess(List<UserBmobBean> list) {
                    for (UserBmobBean bmobRaidersBean : list) {
                        ids.add(bmobRaidersBean.getId());
                        baseAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void queryAllLike() {
        if (BmobUser.getCurrentUser(context) != null) {
            query.findObjects(context, new FindListener<UserBmobBean>() {
                @Override
                public void onSuccess(List<UserBmobBean> list) {
                    for (UserBmobBean bmobRaidersBean : list) {
                        ids.add(bmobRaidersBean.getId());

                    }
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void queryIsLikeRaiders(String id, final CheckBox checkBox) {

        checkBox.setChecked(ids.contains(id));

    }
}

