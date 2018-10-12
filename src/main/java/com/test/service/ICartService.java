package com.test.service;

import com.test.common.ServerResponse;
import com.test.pojo.Cart;
import com.test.vo.CartVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);
    ServerResponse<CartVo>del(Integer userId,String productIds);
    ServerResponse<CartVo>list(Integer userId);
    ServerResponse<CartVo>selectOrUnselect(Integer userId,Integer productId,Integer checked);
    ServerResponse<Integer>getCartProductCount(Integer userId);
}
