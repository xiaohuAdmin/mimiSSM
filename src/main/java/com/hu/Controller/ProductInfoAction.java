package com.hu.Controller;

import com.github.pagehelper.PageInfo;
import com.hu.Service.ProductInfoService;
import com.hu.pojo.ProductInfo;
import com.hu.utils.DataUtil;
import com.hu.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //异步上传的图片的名称
    String saveFileName="";
    //显示每页记录数
    public static final int PAGE_SIZE=5;
    //业务层
    @Autowired
    private ProductInfoService pService;

    //显示第一页的所有商品(5条 )
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        PageInfo info=pService.splitPage(1,PAGE_SIZE);
        request.setAttribute("info",info);
        return "product";
    }

    //ajax分页 翻页处理
    @ResponseBody //处理ajax
    @RequestMapping("/ajaxsplit")
    public void ajaxsplit(int page, HttpSession session){
        //取得当前page参数的页面的数据
        PageInfo info=pService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info",info);
    }

    @ResponseBody //处理ajax
    @RequestMapping("/ajaxImg")
    /**
     * MultipartFile:对于当前上传文件名的接收
     */
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        //提取生成文件名UUID + 上传图片的后缀(.jpg .png)
        saveFileName= FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目当中图片存储的路径
        String path=request.getServletContext().getRealPath("/image_big");
        System.out.println("文件存储路径"+path);
        System.out.println("文件名"+saveFileName);
        //转存
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回客户端json对象，封装图片的路径，为了在页面实现立即回显
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("imgurl",saveFileName);

        return jsonObject.toString();
    }

    //增加商品
    @RequestMapping("/save")

    public String save(ProductInfo info,HttpServletRequest request){
        info.setpImage(saveFileName);
        System.out.println("商品名称："+info.getpImage());
        info.setpDate(new Date());
        boolean flag=pService.save(info);
        if (flag){
            request.setAttribute("msg","添加商品成功!");
        }else {
            request.setAttribute("msg","添加失败！");
        }
        //清空saveFileName,方便下次增加或修改
        saveFileName="";
        //跳到分页

        return "forward:/prod/split.action";
    }


    @RequestMapping("/one")
    public ModelAndView selectProductInfo(Integer pid){
        ProductInfo Info=pService.getById(pid);
        ModelAndView mv = new ModelAndView();
        mv.addObject("prod",Info);
        mv.setViewName("update");
        return mv;
    }

    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){

        //因为ajax的异步图片上传,如果有上传过，则saveFileName里有上传上来的图片的名称，如果没有使用异步ajax上传过图片,则saveFileNme=" ".
        //实体类info使用隐藏表单域提供上来的pImage原始图片的名称;
        if (!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }
        boolean flag= pService.update(info);
       if (flag){
           request.setAttribute("msg","更新商品成功");
       }else {
           request.setAttribute("msg","更新商品失败");
       }
        saveFileName="";
        return"forward:/prod/split.action";
    }

    //单个删除商品

    @RequestMapping("/delete")
    public String delete(int pid,HttpServletRequest request){
       boolean flag= pService.delete(pid);
       if (flag){
           request.setAttribute("msg","删除成功");
       }else {
           request.setAttribute("msg","删除失败");
       }
       return "forward:/prod/deleteAjaxSplit.action";
    }
    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit",produces = "text/html;charset=utf-8")
    public Object deleteAjaxSplit(HttpServletRequest request){
        //取得第一页的数据
        PageInfo info=pService.splitPage(1,PAGE_SIZE);
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");
    }

    @ResponseBody
    @RequestMapping("/deleteAll")
    public Object deleteAll(HttpServletRequest request){
        String[] ids = request.getParameterValues("id");

        boolean flag = pService.deleteAll(ids);
        return flag;
    }
}
