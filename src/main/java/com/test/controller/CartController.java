package com.test.controller;


import com.test.common.Const;

import com.test.common.ServerResponse;

import com.test.service.ICartService;
import com.test.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart/")
public class CartController {


    @Autowired
    private ICartService iCartService;

    /**
     * 加入购物车功能
     * 1.需要的数据：商品信息，数量，(用户信息，店铺信息)
     *
     * @param session

     * @return
     */

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<CartVo> list(HttpSession session,Integer userId) {
        //获取当前用户信息，判断是否登录
//        User user = (User) session.getAttribute(Const.CUURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMassage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
        return iCartService.list(userId);
    }
    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse<CartVo> add(HttpSession session,Integer userId, Integer productId, Integer count) {
        //获取当前用户信息，判断是否登录
//        User user = (User) session.getAttribute(Const.CUURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMassage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
        return iCartService.add(userId, productId, count);
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse<CartVo> update(HttpSession session,Integer userId, Integer productId, Integer count) {
        //获取当前用户信息，判断是否登录
//        User user = (User) session.getAttribute(Const.CUURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMassage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
        return iCartService.update(userId, productId, count);
    }
    @RequestMapping("del.do")
    @ResponseBody
    public ServerResponse<CartVo> del(HttpSession session,Integer userId, String productIds) {
        //获取当前用户信息，判断是否登录
//        User user = (User) session.getAttribute(Const.CUURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMassage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
        return iCartService.del(userId,productIds);
    }


    /**
     *  全选，全反选， 单选，单反选，查询购物车的产品数量
     */


    @RequestMapping("selectAll.do")
    @ResponseBody
    public ServerResponse<CartVo> selectAll(HttpSession session,Integer userId) {
        //获取当前用户信息，判断是否登录
//        User user = (User) session.getAttribute(Const.CUURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMassage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
        return iCartService.selectOrUnselect(userId,null,Const.Cart.CHECKED);
    }


    @RequestMapping("unSelectAll.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelectAll(HttpSession session,Integer userId) {
        //获取当前用户信息，判断是否登录
//        User user = (User) session.getAttribute(Const.CUURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMassage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
        return iCartService.selectOrUnselect(userId,null,Const.Cart.UN_CHECKED);
    }

    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse<CartVo> selectAll(HttpSession session,Integer userId, Integer productId) {
        //获取当前用户信息，判断是否登录
//        User user = (User) session.getAttribute(Const.CUURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMassage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
        return iCartService.selectOrUnselect(userId,productId,Const.Cart.CHECKED);
    }

    @RequestMapping("unSelect.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelect(HttpSession session,Integer userId,Integer productId) {
        //获取当前用户信息，判断是否登录
//        User user = (User) session.getAttribute(Const.CUURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMassage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
        return iCartService.selectOrUnselect(userId,productId,Const.Cart.UN_CHECKED);
    }


    @RequestMapping("get_cart_product_count.do")
    @ResponseBody
    public ServerResponse<Integer> getCartProductCount(HttpSession session,Integer userId) {
//        //获取当前用户信息，判断是否登录
//        User user = (User) session.getAttribute(Const.CUURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createBySuccess(0);
//        }
        return iCartService.getCartProductCount(userId);
    }



}
