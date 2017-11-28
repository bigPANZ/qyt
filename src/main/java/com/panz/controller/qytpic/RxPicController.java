package com.panz.controller.qytpic;

import com.panz.dao.mapper.SPicMapper;
import com.panz.entity.SPic;
import com.panz.service.rxpic.RxpicService;
import com.panz.util.FastDFSClient;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(value = "/rxPic")
public class RxPicController {
    private static final Logger logger = LoggerFactory.getLogger(RxPicController.class);
    @Autowired
    private RxpicService rxpicService;
    @RequestMapping(value = "/uploadRxPic",method = RequestMethod.POST)
    @ResponseBody
    public String returnSuccess(HttpServletRequest request) {
        String ret="";
        try{
            FastDFSClient client = new FastDFSClient("classpath:tracker_server.conf");
            CommonsMultipartResolver multipartResolver= new CommonsMultipartResolver(request.getSession().getServletContext());
            if(multipartResolver.isMultipart(request)){
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
                Iterator<String> iter=multipartRequest.getFileNames();
                while (iter.hasNext()){
                    MultipartFile multipartFile=multipartRequest.getFile(iter.next());
                    CommonsMultipartFile cf= (CommonsMultipartFile)multipartFile;
                    DiskFileItem fi = (DiskFileItem)cf.getFileItem();
                    File file =fi.getStoreLocation();
                    String ext = FilenameUtils.getExtension(fi.getName());
                    String uploadFile = client.uploadFile(file.getAbsolutePath(), ext);
                    SPic sPic = new SPic();
                    sPic.setPath(uploadFile);
                    sPic.setTitle(fi.getName());
                    rxpicService.addRxPic(sPic);
                    ret=toJson(uploadFile);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
    @RequestMapping(value = "returnString", produces = {"application/json;charset=UTF-8"})
    //produces用于解决返回中文乱码问题，application/json;为json解决中文乱码
    @ResponseBody
    public String returnString() {
        return "hello return string 这是中文，并没有乱码";
    }
    public String toJson(String str){
        JSONObject jo =new JSONObject();
        jo.put("path",str);
        return jo.toString();
    }
}
