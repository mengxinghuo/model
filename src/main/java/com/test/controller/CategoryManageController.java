package com.test.controller;

import com.test.common.Const;
import com.test.common.ResponseCode;
import com.test.common.ServerResponse;
import com.test.pojo.User;
import com.test.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/category/")
public class CategoryManageController {


    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("addCategorys.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {

        User user = (User) session.getAttribute(Const.CUURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMassage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请先登录");

        }
        //todo 校检是否是管理员，是则进行操作，不是返回无权限错误
        return iCategoryService.addCategory(categoryName, parentId);
    }


    @RequestMapping("setCategoryName.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName) {

        User user = (User) session.getAttribute(Const.CUURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMassage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请先登录");

        }
        //todo 校检是否是管理员，是则进行操作，不是返回无权限错误
        return iCategoryService.updateCategoryName(categoryId, categoryName);

    }

    @RequestMapping("getChildrenParallelCategory.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        //todo 权限判断
        //查询子节点的category信息，并且不递归，保持平级
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping("getCategoryAndDeepChildrenCategory.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        //todo 权限判断
        //查询当前节点的id及递归子节点的id
        return iCategoryService.selectCategoryAndChilerenById(categoryId);
    }


}
